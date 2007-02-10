package lv.webkursi.klucis.component;

import java.util.HashMap;
import java.util.Map;

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

	private FactoryCatalog factoryCatalog;
	
	protected LifecycleManagerImpl lifecycleManager = new LifecycleManagerImpl();

	private Map<Resource, Component> cache = new HashMap<Resource, Component>();
	
	protected Map<String, Object> nameTable = new HashMap<String, Object>();
	
	protected KlucisDAO klucisDAO; 
	
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
		/**
		 * TODO kap: deal with servlet params
		if (servletRequest.getParameterMap().containsKey(cId)) {
			c.setInteractionState(servletRequest.getParameter(cId));
		}
		*/
		if (c instanceof LifecycleEventListener) {
			addLifecycleEventListener((LifecycleEventListener)c);
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
}