package lv.webkursi.klucis.data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import lv.webkursi.klucis.KlucisConfigurationException;
import lv.webkursi.klucis.KlucisException;
import lv.webkursi.klucis.component.FactoryCatalog;
import lv.webkursi.klucis.vocabulary.KLUCIS;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.util.iterator.Filter;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;

/**
 * This is an all-purpose class for RDF data access. Many methods are static.
 * Those, which depend on context (e.g. application-wide params, user's
 * role-dependent params, user's configured params, session params, interaction
 * params, etc., use separate instances of KlucisDAO to allow result caching
 * sometime in the future.
 * 
 * @author kap
 * 
 */
public class KlucisDAO {

	public static ResourceComparator comparatorInstance;

	private static Log log = LogFactory.getLog(KlucisDAO.class);

	protected Map<String, KlucisDAO> contextMap = new HashMap<String, KlucisDAO>();

	protected String context = "/";

	protected static FactoryCatalog factoryCatalog;

	public KlucisDAO() {
	}

	public KlucisDAO(String context) {
		this.context = context;
	}

	/**
	 * Return a cached RDFUtil instance or create a new one
	 * 
	 * @param context
	 * @param factoryCatalog
	 * @return
	 */
	public KlucisDAO getKlucisDAO(String context, FactoryCatalog factoryCatalog) {
		if (!contextMap.containsKey(context)) {
			contextMap.put(context, new KlucisDAO(context));
		}
		return contextMap.get(context);
	}

	public static FactoryCatalog getFactoryCatalog() {
		return factoryCatalog;
	}

	public static void setFactoryCatalog(FactoryCatalog catalog) {
		factoryCatalog = catalog;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	/**
	 * Alphabetical comparison of resource URLs or id-s; useful for debug
	 * messages
	 * 
	 * @return
	 */
	public static ResourceComparator getComparator() {
		if (comparatorInstance == null) {
			comparatorInstance = new ResourceComparator();
		}
		return comparatorInstance;
	}

	/**
	 * Get an alphabetically sorted list of types for a resource (e.g. for error
	 * messages)
	 */

	public static Set<Resource> getSortedTypes(Resource resource) {
		Set<Resource> set = new TreeSet<Resource>(getComparator());
		for (ExtendedIterator i = getAllTypes(resource); i.hasNext();) {
			set.add((Resource) i.next());
		}
		return set;
	}

	/**
	 * Return the iterator of all explicitly declared rdf:type-s for the given
	 * resource. The resulting iterator does NOT guarantee any certain ordering
	 * of the returned types.
	 * 
	 * @param r
	 *            the resource
	 * @return list of all types or empty list, if no types are found
	 */

	public static ExtendedIterator getAllTypes(Resource resource) {
		Model model = resource.getModel();
		NodeIterator i = model.listObjectsOfProperty(resource, RDF.type);
		return i.filterKeep(new Filter() {
			@Override
			public boolean accept(Object o) {
				return (o instanceof Resource);
			}
		});
	}

	/**
	 * Find the component type from the resource to pick the right factory from
	 * the catalog. If none such types are found for this resource, visit its
	 * klucis:parent.
	 * 
	 * @param resource
	 * @return its type (or throw a PortalException, if the resource has no
	 *         explicit type, which is mapped by the factoryCatalog).
	 */

	public Resource getComponentType(Resource resource) {
		ExtendedIterator i = getAllTypes(resource);
		Resource resType = null;
		int count = 0;
		while (i.hasNext()) {
			Resource rTemp = (Resource) i.next();
			if (factoryCatalog.getFactory(rTemp.getURI()) != null) {
				resType = rTemp;
				count++;
			}
		}
		if (resType == null) {
			Statement stmtParent = resource.getProperty(KLUCIS.parent);
			if (stmtParent != null) {
				Resource parent = stmtParent.getResource();
				return getComponentType(parent);
			} else {
				Set<Resource> typeList = getSortedTypes(resource);
				throw new KlucisConfigurationException(
						"Type not in catalog for " + resource
								+ ", its declared types are " + typeList);
			}
		}
		if (count != 1) {
			Set<Resource> typeList = getSortedTypes(resource);
			throw new KlucisConfigurationException(
					"Ambiguous type in catalog for " + resource
							+ ", its declared types are " + typeList);
		}
		return resType;
	}

	/**
	 * Return a list of abstract components for this RDFUtil's context and all
	 * its supercontexts. A component is abstract for type resType iff it has
	 * property "rdf:type klucis:AbstractComponent" AND "rdf:type resType" AND
	 * its context attribute has value, which matches the current KlucisDAO
	 * context or is some prefix thereof (e.g. context "/a/b/" is a supercontext
	 * of "/a/b/c/", and context "/" is a root context - supercontext of any
	 * other context).
	 * 
	 * 
	 * @param resType
	 * @return
	 */
	public List<Resource> getAbstractComponents(Resource resType) {
		List<Resource> result = new ArrayList<Resource>();
		Resource[] tempArray = new Resource[getContext().length()];
		Model model = resType.getModel();
		ResIterator i = model.listSubjectsWithProperty(RDF.type,
				KLUCIS.AbstractComponent);
		while (i.hasNext()) {
			Resource r = i.nextResource();
			String context = "";
			if (r.hasProperty(KLUCIS.context)) {
				context = r.getRequiredProperty(KLUCIS.context).getString();
			}
			boolean isValid = r.hasProperty(RDF.type, resType)
					&& context.endsWith("/")
					&& context.length() <= getContext().length()
					&& context.equals(getContext().substring(0,
							context.length()));
			if (isValid) {
				tempArray[context.length() - 1] = r;
			}
		}

		for (int j = 0; j < tempArray.length; j++) {
			if (tempArray[j] != null) {
				result.add(tempArray[j]);
			}
		}
		return result;
	}

	/**
	 * Return property of type float by consecutive checking of one or more
	 * enclosing scopes - the resource r itself, all its ancestors, abstract
	 * configuration beans for the current context or its parent contexts
	 * 
	 * <h2>Contract</h2>
	 * 
	 * <ul>
	 * <li>If resource r itself has property p, return it (innermost scope)</li>
	 * <li>Otherwise follow the klucis:parent, and return the property p of r's
	 * parent (next enclosing scope); if not found there, proceed recursively -
	 * visit grandparent, etc.</li>
	 * <li>If some encountered resource has multiple parents, log error and
	 * throw KlucisConfigurationException</li>
	 * <li>When some encountered resource has no parent, and property p is
	 * still not found, find the type of r (as by getComponentType(r)), find
	 * abstract component of the given type with the klucis:context equal to the
	 * RDFUtil's context.</li>
	 * <li>If no scope contains property p (including the abstract ones),
	 * return null
	 * <li>If any scope has several values of the property p, return any value
	 * and log a warning</li>
	 * <li>If any encountered value of property p has a value of incorrect type
	 * (cannot be converted to float), throw KlucisConfigurationException</li>
	 * </ul>
	 * 
	 * @param r
	 * @param p
	 * @return
	 */
	public RDFNode getCascadedProperty(Resource r, Property p) {
		RDFNode result = null;
		Model model = r.getModel();
		NodeIterator i = model.listObjectsOfProperty(r, p);
		int count = 0;
		while (i.hasNext()) {
			result = i.nextNode();
			count++;
		}
		if (result == null) {
			Resource rParent = findParent(r);
			if (rParent != null) {
				result = getCascadedProperty(rParent, p);
			} else {
				Resource resType = getComponentType(r);
				List<Resource> abstractComponent = getAbstractComponents(resType);
				for (Resource ri : abstractComponent) {
					result = getCascadedProperty(ri, p);
					if (result != null) {
						break;
					}
				}
			}
		} else {
			if (count > 1) {
				log.warn("Non-unique property '" + p + "' for resource '" + r
						+ "'");
			}
		}
		return result;
	}

	/**
	 * Find the parent of node r in its current model. If none is found return
	 * null. If parent is non-unique, throw KlucisConfigurationException.
	 * 
	 * @param r
	 * @return
	 */
	public Resource findParent(Resource r) {
		Resource parent = null;
		NodeIterator i = r.getModel().listObjectsOfProperty(r, KLUCIS.parent);
		int count = 0;
		while (i.hasNext()) {
			parent = (Resource) i.nextNode();
			count++;
		}
		if (count > 1) {
			KlucisConfigurationException e = new KlucisConfigurationException(
					"Non-unique parent for resource r = '" + r + "'");
			log.error(e);
			throw e;
		}
		return parent;
	}

	/*
	 * public static int getIntProperty(Resource r, Property p, int intDefault) {
	 * int result = intDefault; if (r.getProperty(p) != null) { try { result =
	 * r.getRequiredProperty(p).getInt(); } catch (RuntimeException e) {
	 * log.warn("Cannot coerce resource '" + r + "' property '" + p + "' to int;
	 * using default" + intDefault, e); } } return result; }
	 */
	public float getFloatProperty(Resource r, Property p) {
		RDFNode node = getCascadedProperty(r, p);
		if (node == null) {
			throw new KlucisConfigurationException("Float property " + p
					+ " for resource " + r + " cannot be resolved");
		}
		return ((Literal) node).getFloat();
	}
	
	public int getIntProperty(Resource r, Property p) {
		RDFNode node = getCascadedProperty(r, p);
		if (node == null) {
			throw new KlucisConfigurationException("Integer property " + p
					+ " for resource " + r + " cannot be resolved");
		}
		return ((Literal) node).getInt();		
	}

	public String getStringProperty(Resource r, Property p) {
		RDFNode node = getCascadedProperty(r, p);
		if (node == null) {
			throw new KlucisConfigurationException("String property " + p
					+ " for resource " + r + " cannot be resolved");
		}
		return ((Literal) node).getString();		
	}
	
	public boolean getBooleanProperty(Resource r, Property p) {
		RDFNode node = getCascadedProperty(r, p);
		if (node == null) {
			throw new KlucisConfigurationException("Boolean property " + p
					+ " for resource " + r + " cannot be resolved");
		}
		return ((Literal) node).getBoolean();		
	}
	
	

	/**
	 * @param r
	 *            the resource to inspect
	 * @param p
	 *            the property
	 * @param stringDefault
	 *            the default value, if no value is found
	 * @param isSingle
	 *            should this fail, if property value is non-unique
	 * @return the property value coerced to a String
	 */
	public static String getStringProperty(Resource r, Property p,
			String stringDefault, boolean isSingle) {
		String result = stringDefault;
		Model model = r.getModel();
		NodeIterator i = model.listObjectsOfProperty(r, p);
		int count = 0;
		while (i.hasNext()) {
			try {
				result = ((Literal) i.nextNode()).getString();
			} catch (Exception e) {
				log.warn("Could not coerce resources " + r + " property " + p
						+ " to a string. Using default.", e);
			}
			count++;
		}
		if (isSingle && count > 1) {
			log
					.error("Nonunique value of property " + p
							+ " for resource " + r);
			throw new KlucisException("Nonunique value of property " + p
					+ " for resource " + r);
		}
		return result;
	}

	/**
	 * This is used to pick the alphabetically first resource, whenever we need
	 * to pick between alternatives (e.g. in ResourceToComponentByType).
	 * 
	 * @author kap
	 */
	public static class ResourceComparator implements Comparator<Resource> {
		public int compare(Resource r1, Resource r2) {
			return r1.getURI().compareTo(r2.getURI());
		}
	}

	/**
	 * <p>
	 * <b>Contract</b>
	 * </p>
	 * 
	 * <ol>
	 * <li>If there are no rdfs:label properties as literals without any
	 * language (i.e. without the xml:lang attribute in RDF/XML notation), then
	 * return null</li>
	 * <li>If there are several rdfs:label properties without language, return
	 * any one and log a warning</li>
	 * <li>Any rdfs:label properties, which are not literals are ignored, and
	 * any non-string values are coerced to strings</li>
	 * </ol>
	 */

	public static String getLabel(Resource resource) {
		return getLabel(resource, null);
	}

	/**
	 * <p>
	 * <b>Contract</b>
	 * </p>
	 * 
	 * <ol>
	 * <li>If there are no rdfs:label properties as literals with the given
	 * locale's language or without language, then return null</li>
	 * <li>If there are any rdfs:label properties with the needed language,
	 * then return one of them (they have highest priority)</li>
	 * <li>If there are only rdfs:label properties without a language, then
	 * return one of them (they have a lower priority)</li>
	 * <li>If there is any ambiguity (several labels with same highest
	 * priority), return any one and log a warning</li>
	 * <li>Any rdfs:label values, which are not literals are ignored, and any
	 * non-string values are coerced to strings</li>
	 * </ol>
	 * 
	 * @param resource
	 * @return
	 */
	public static String getLabel(Resource resource, Locale locale) {
		String language = locale == null ? null : locale.getLanguage();
		String[] result = new String[2];
		StmtIterator iterator = resource.listProperties(RDFS.label);
		int ambiguityFlags = 0x00000000;
		while (iterator.hasNext()) {
			Statement stmt = iterator.nextStatement();
			RDFNode node = stmt.getObject();
			if (node.isLiteral()) {
				ambiguityFlags |= langSpecificStringLiteral((Literal) node,
						result, language);
			} else {
				// ignore those labels, which are not literals
				continue;
			}
		}
		// set priority = 0, if the highest priority match is available
		// otherwise set priority = 1
		int priority = (result[0] == null) ? 1 : 0;
		if ((ambiguityFlags & (1 << priority)) != 0) {
			log.warn("Ambiguous rdfs:label for resource " + resource
					+ ", locale " + locale);
		}
		return result[priority];
	}

	/**
	 * Update the array of strings 'result' (indexed by priority) with literals,
	 * whose language matches the 'language' argument and set the ambiguitiy
	 * flag, if appropriate
	 * 
	 * @param literal
	 *            the literal value to check
	 * @param language
	 *            code for a language or null, if literals without language are
	 *            desired
	 * @return ambiguity flag (0 - no ambiguity, 1 - ambiguity for the highest
	 *         priority values, 2 - ambiguity for the lowest priority, 3 -
	 *         ambiguity for both)
	 */
	private static int langSpecificStringLiteral(Literal literal,
			String[] result, String language) {
		int flag = 0x00000000;
		language = (language == null) ? "" : language;
		String lang = literal.getLanguage();
		int priority = -1;
		if (language.equals(lang)) {
			priority = 0;
		} else if (lang.equals("")) {
			priority = 1;
		}
		if (priority >= 0 && result[priority] == null) {
			result[priority] = literal.getString();
		} else if (priority >= 0) {
			flag = 1 << priority;
		}
		return flag;
	}

	public static void setLog(Log log) {
		KlucisDAO.log = log;
	}

	/**
	 * <p>
	 * <b>Contract</b>
	 * </p>
	 * 
	 * <ul>
	 * <li> if r has no portal:id property, but does have a URI with a fragment
	 * identifier then id is the fragment identifier. </li>
	 * <li> if r has no portal:id property and a URI without a fragment
	 * identifier then id is the URI. </li>
	 * <li> if r has no portal:id property and no URI then the bnode id of the r
	 * is the id prefixed with a constant to ensure uniqueness. </li>
	 * </ul>
	 * 
	 * @param r
	 *            resource, which needs implicit Id computed
	 * @return localName, URL, or BNode identifier prefixed with a constant
	 *         string "__" (2 underbars)
	 */
	public static String getImplicitId(Resource r) {
		// return localName
		String id = r.getLocalName();
		if (id != null) {
			return id;
		}
		// return full URL
		id = r.getURI();
		if (id != null) {
			return id;
		}
		// return blank node's id
		id = r.getId().toString();
		return "__" + id;
	}

	/**
	 * p> <b>Contract</b>
	 * </p>
	 * 
	 * <ul>
	 * <li>Return a resource in the ontology with the given label, provided the
	 * label in the ontology has either the specified language (xml:lang
	 * attribute) or no language at all</li>
	 * <li>Return null, if no such resource is found.</li>
	 * <li>If locale is null, then ignore all those labels in the ontology with
	 * explicit xml:lang</li>
	 * <li>If locale is not null, then first search among labels with the given
	 * language, and return it (highest priority). If none such exists, then
	 * search among labels without any language (next priority).</li>
	 * <li>Log a warning, if there are several eligible resources of the same
	 * priority.</li>
	 * </ul>
	 * 
	 * @param ontology
	 * @param label
	 * @param locale
	 * @return
	 */
	public static Resource getResourceByLabel(Model ontology, String label,
			Locale locale) {
		String language = locale == null ? null : locale.getLanguage();
		Literal literal = null;
		if (language != null) {
			literal = ontology.createLiteral(label, language);
		} else {
			literal = ontology.createLiteral(label);
		}
		ResIterator iterator = ontology.listSubjectsWithProperty(RDFS.label,
				literal);
		int count = 0;
		Resource r = null;
		while (iterator.hasNext()) {
			Resource candidate = iterator.nextResource();
			count++;
			if (count != 1) {
				log.warn("Label '" + label + "' has matching resource #"
						+ count + " :" + candidate);
			}
			r = candidate;
		}
		if (locale != null && r == null) {
			// try lower priority literals - those without language
			return getResourceByLabel(ontology, label, null);
		} else {
			return r;
		}
	}

	public static Resource getResourceByLabel(Model ontology, String label) {
		return getResourceByLabel(ontology, label, null);
	}

	/**
	 * Return a resource "b" such that "b rdfs:label label" AND ("a downProperty
	 * b" OR "b upProperty a"). If there are several such resources "b", then
	 * return any one of them and log a warning.
	 * 
	 * (Could be also implemented via rules or via SPARQL query)
	 * 
	 * @param a
	 *            the parent resource
	 * @param label
	 *            the label of the child
	 * @param downProperty
	 *            property going from parent to a child
	 * @param upProperty
	 *            inverse - going from child to the parent
	 * @return the child in the hierarchy.
	 */
	public static Resource jumpToLabel(Resource a, String label,
			Property downProperty, Property upProperty) {
		List<Resource> results = new ArrayList<Resource>();

		StmtIterator iter = a.getModel()
				.listStatements(null, RDFS.label, label);
		while (iter.hasNext()) {
			Resource r = iter.nextStatement().getSubject();
			if (r.hasProperty(upProperty, a) || a.hasProperty(downProperty, r)) {
				results.add(r);
			}
		}
		if (results.size() == 0) {
			throw new KlucisException("Cannot find a child of '" + a
					+ "' with label '" + label + "'");
		}
		if (results.size() > 1) {
			log.warn("Multiple resources under '" + a + "' with label '"
					+ label + "'");
		}
		return results.get(0);
	}

}