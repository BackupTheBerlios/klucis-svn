package lv.webkursi.klucis;

import java.util.HashMap;
import java.util.Map;

import lv.webkursi.klucis.blocks.Component;
import lv.webkursi.klucis.blocks.ComponentFactory;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

/**
 * A ComponentManager is used to create various items used in drawings. It ensures
 * that each component in a request has a unique name, mutually injects
 * various component references and tries to optimize component retrieval through caching.
 * 
 * @author kap
 */
public class ComponentManager {

	private FactoryCatalog factoryCatalog;

	private Map<Resource, Component> cache = new HashMap<Resource, Component>();
	
	public ComponentManager() {		
	}

	public void setFactoryCatalog(FactoryCatalog factoryCatalog) {
		this.factoryCatalog = factoryCatalog;
	}

	/**
	 * Get a component from an RDF description.
	 * If no factory exists, throw runtime exception.
	 * Multiple calls with the same argument will return the same (==)
	 * component.
	 */
	public Component getComponent(Resource r) {
		if (cache.containsKey(r)) {
			return cache.get(r);
		} else {
			Resource rType = getComponentType(r);
			ComponentFactory f = factoryCatalog.getFactory(rType
					.getURI());
			f.setComponentManager(this);
			if (f == null) {
				throw new RuntimeException("Catalog misconfiguration: no factory for resource type " + rType);
			}
			Component c = f.getComponent(r);
			cache.put(r, c);
			return c;
		}
	}

	/**
	 * Returns the same (==) component object as was previously returned when
	 * the component created with a call to getComponent. Throws a
	 * <code>RuntimeException</code> if the component was not previously
	 * created.
	 * 
	 * @param r
	 *            the RDF description of the component to be returned.
	 * @return the required component
	 */
	public Component getExistingComponent(Resource r) {
		if (cache.containsKey(r)) {
			return cache.get(r);
		}
		throw new RuntimeException("Attempt to access a component that has not been created: " + r);
	}
    


	/**
	 * Find the  type from the resource to pick the right factory from
	 * the catalog
	 * 
	 * @param resource
	 * @return its type (or throw a PortalException, if the resource has no
	 *         explicit type besides PORTAL.PageSet and PORTAL.Immutable ).
	 */

	protected Resource getComponentType(Resource resource) {
        Model model = resource.getModel();
		NodeIterator i = model.listObjectsOfProperty(resource, RDF.type);
		Resource widgetType = null;
		// should have exactly one RDF.type, which is present in the component catalog
		int count = 0; 
		while (i.hasNext()) {
			RDFNode node = i.nextNode();
			Resource rTemp = (Resource) node;
			if (factoryCatalog.getFactory(rTemp.getURI()) != null) {
				widgetType = rTemp;
				count++;
			}
		}
		if (widgetType == null) {
			throw new RuntimeException("Component Type not specified for description: "
					+ resource);
		}
		if (count != 1) {
			throw new RuntimeException("Non-unique component type for description: "
					+ resource);
		}		
		return widgetType;
	}
}
