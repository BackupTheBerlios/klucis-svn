package lv.webkursi.klucis.component;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;

import lv.webkursi.klucis.KlucisConfigurationException;
import lv.webkursi.klucis.KlucisException;
import lv.webkursi.klucis.data.KlucisDAO;
import lv.webkursi.klucis.event.LifecycleEvent;
import lv.webkursi.klucis.event.LifecycleEventListener;
import lv.webkursi.klucis.event.LifecycleManager;
import lv.webkursi.klucis.event.LifecycleManagerImpl;

import com.hp.hpl.jena.rdf.model.Resource;


/**
 * A ComponentManager is used to create various items used in drawings. It ensures
 * that each component in a request has a unique name, mutually injects
 * various component references and tries to optimize component retrieval through caching.
 * 
 * @author kap
 */
public class ComponentManager implements LifecycleManager, InitializingBean {
	
	private final Log log = LogFactory.getLog(ComponentManager.class);

	private FactoryCatalog factoryCatalog;
	
	protected HttpServletRequest servletRequest;

	protected HttpServletResponse servletResponse;
	
	protected LifecycleManagerImpl lifecycleManager = new LifecycleManagerImpl();

	private Map<Resource, Component> cache = new HashMap<Resource, Component>();
	
	protected Map<String, Object> nameTable = new HashMap<String, Object>();
	
	protected KlucisDAO klucisDAO; 
	
	private Map<String, String> interactionStates = new HashMap<String, String>();

	
	/**
	 * At this point everything works only at the root context
	 */
	public ComponentManager() {		
		klucisDAO = new KlucisDAO("/");
		
	}
	
	public void afterPropertiesSet() {
		KlucisDAO.setFactoryCatalog(factoryCatalog);
	}
	

	public KlucisDAO getKlucisDAO() {
		return klucisDAO;
	}
	
	public void setFactoryCatalog(FactoryCatalog factoryCatalog) {
		this.factoryCatalog = factoryCatalog;
		KlucisDAO.setFactoryCatalog(factoryCatalog);
	}
	
	
	/**
	 * Get a component from an RDF component description.
	 * 
	 * <p>
	 * Multiple calls with the same argument will return the same (==)
	 * component.
	 * </p>
	 * 
	 * @param r
	 *            the RDF description of the component.
	 * @return the requested component.
	 * @throws PortalConfigurationException
	 *             if factory for the resource cannot be found
	 */
	public Component getStaticComponent(Resource r) {
		Component result = null;
		if (cache.containsKey(r)) {
			result = cache.get(r);
		} else {
			Component c = createNewComponent(r, null);
			cache.put(r, c);
			result = c;
		}
		return result;
	}

	

	/**
	 * Return a cloned component given a resource description. Does not affect
	 * cache
	 * 
	 * <p>
	 * <b>Contract</b>
	 * <p>
	 * 
	 * <ol>
	 * <li>Returns a component as described by <code>resource</code></li>
	 * <li>Returns a new instance of the component each time it is called.</li>
	 * </ol>
	 * 
	 * @param resource
	 *            a description of the component to be cloned.
	 * @param id
	 *            an id for the component - must not be null
	 * @return the newly minted component.
	 */
	public Component getDynamicComponent(Resource resource, String id) {
		if (id == null) {
			throw new KlucisException(
					"Could not initialize dynamic component for resource "
							+ resource + ", id should not be null");
		}
		Component result = (Component) createNewComponent(resource, id);
		return result;
	}

	
	private Component createNewComponent(Resource resource, String id) {
		Resource rType = klucisDAO.getComponentType(resource);
		ComponentFactory f = factoryCatalog.getFactory(rType.getURI());
		if (f == null) {
			throw new KlucisConfigurationException(
					"Catalog misconfiguration: no factory for resource type "
							+ rType);
		}
		Component c = f.getComponent(resource, this, id);
		if (c instanceof Component) {
			registerComponent((Component) c);
		}
		return c;
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
    	
	public void registerComponent(Component c) {
		c.setComponentManager(this);
		String cId = c.getId();
		if (nameTable.containsKey(cId)) {
			throw new KlucisConfigurationException("Using duplicate id: " + cId
					+ " for component of type " + c.getClass().getName());
		} else {
			nameTable.put(cId, c);
		}
		if (servletRequest != null && servletRequest.getParameterMap().containsKey(cId)) {
			c.setInteractionState(servletRequest.getParameter(cId));
		}
		if (c instanceof LifecycleEventListener) {
			addLifecycleEventListener((LifecycleEventListener)c);
		}
	}
	
	/**
	 * Find a component, which needs action to be performed
	 */
	public void doAction() {
		String componentName = servletRequest.getParameter("_ac");
		String action = servletRequest.getParameter("_aa");
		Component component = (Component) nameTable.get(componentName);
		if (component != null) {
			component.doAction(action);
		} else {
			log.warn("Action '" + action + "' on component '" + componentName
					+ "' ignored; component does not exist");
		}
	}


	
	public void announce(Object source, LifecycleEvent.Kind kind) {
		lifecycleManager.announce(source, kind);
	}

	public void addLifecycleEventListener(LifecycleEventListener listener) {
		lifecycleManager.addLifecycleEventListener(listener);		
	}

	public void removeLifecycleEventListener(LifecycleEventListener listener) {
		lifecycleManager.removeLifecycleEventListener(listener);
	}
	
	
	/**
	 * For arguments "pageSetId", "linkObject" and list ["p1","v1","p2","v2"]
	 * produce the following:<br />
	 * &lt;a href="pageSetId?p1=v1&p2=v2"&gt;linkObject&lt;/a&gt; The parameter
	 * names "p1", "p2", etc. are always strings. The respective values ("v1",
	 * "v2", etc.) may be any type that was passed from Velocity template; they
	 * are converted to strings - first with some datatype-dependent SPARQL
	 * escaping and then URL-encoded.
	 * 
	 * @param pageSetId
	 *            indicates the pageset we are linking to
	 * @param linkObject
	 *            the string (HTML construct), which is surrounded by
	 *            anchor(&lt;a&gt;) tags
	 * @param parameters
	 *            a list of even length, containing parameters and their values
	 *            (not yet URLEncoded)
	 * @param actionComponent
	 *            a component ID on which an action is to be performed
	 * @param action
	 *            the action to be performed by the actionComponent
	 * @return HTML construct enclosed in anchor tags.
	 */
	public String getLinkToPageSet(String pageSetId, String linkObject,
			List parameters, String actionComponent, String action) {
		String url = getUrlToPageSet(pageSetId, parameters, actionComponent,
				action);

		return "<a href=\"" + url + "\">" + linkObject + "</a>";
	}

	/**
	 * See getLinkToPageSet() for explanation of parameters
	 */
	public String getUrlToPageSet(String pageSetId, List parameters,
			String actionComponent, String action) {
		String url = pageSetId;
		Iterator iter = parameters.iterator();
		String sep = "?";
		if (actionComponent != null) {
			url = url + sep + "_ac=" + encodeAsUtf(actionComponent);
			sep = "&amp;";
			if (action != null) {
				url = url + sep + "_aa=" + encodeAsUtf(action);
			}
		}
		while (iter.hasNext()) {
			// will fail, if parameter name is not a string - as it should
			String pName = (String) iter.next();
			String pValue = null;
			if (iter.hasNext()) {
				Object val = iter.next();
				pValue = "" + val;
			}
			url = url + sep + encodeAsUtf(pName) + "=" + encodeAsUtf(pValue);
			sep = "&amp;";
		}
		for (String key : interactionStates.keySet()) {
			url = url + sep + encodeAsUtf(key) + "="
					+ encodeAsUtf(interactionStates.get(key));
			sep = "&amp;";
		}

		return url;
	}

	private String encodeAsUtf(String arg) {
		if (arg == null) {
			log.warn("Argument to encode should not be null");
			return "null";
		}
		try {
			String result = URLEncoder.encode(arg, "UTF-8");
			return result;
		} catch (Exception e) {
			log.error("Unexpected: Bad encoding UTF-8", e);
			throw new KlucisException("Unexpected: Bad encoding UTF-8");
		}
	}

	
	/**
	 * @return the servletRequest
	 */
	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}

	/**
	 * @param servletRequest
	 *            the servletRequest to set
	 */
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}

	/**
	 * @return the servletResponse
	 */
	public HttpServletResponse getServletResponse() {
		return servletResponse;
	}

	/**
	 * @param servletResponse
	 *            the servletResponse to set
	 */
	public void setServletResponse(HttpServletResponse servletResponse) {
		this.servletResponse = servletResponse;
	}

}
