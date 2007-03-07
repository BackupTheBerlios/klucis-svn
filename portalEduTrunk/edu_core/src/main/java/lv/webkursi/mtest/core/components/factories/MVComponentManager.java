package lv.webkursi.mtest.core.components.factories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import lv.webkursi.mtest.core.components.Component;
import lv.webkursi.mtest.core.components.DoStuffListener;
import lv.webkursi.mtest.core.mvc.ComponentStateManager;
import lv.webkursi.mtest.mvc.vocabulary.PORTAL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

/**
 * An implementation of ComponentManager, which has some additional
 * functionality to deal with ModelAndViewComponents (so, sometimes there is
 * assuption that the component is implemented as a ModelAndView).
 * 
 * This object also serves as the proxy to all componentfactories - i.e.
 * getComponent() is delegated to an appropriate component factory.
 */
public class MVComponentManager implements ComponentManager {
	
	private List<DoStuffListener> doStuffListeners = new ArrayList<DoStuffListener>();
	
	private Log log = LogFactory.getLog(MVComponentManager.class);

	private Map<String, Component> componentCatalog = new HashMap<String, Component>();

	private Map<String, Integer> uniqueExtensions = new HashMap<String, Integer>();

	private ComponentStateManager stateManager;

	private Map<String, ComponentFactory> factoryCatalog;

	public void setFactoryCatalog(Map<String, ComponentFactory> factoryCatalog) {
		this.factoryCatalog = factoryCatalog;
	}

	public void setStateManager(ComponentStateManager stateManager) {
		this.stateManager = stateManager;
		stateManager.setComponentManager(this);
	}

	public void bind(String name, Component component) {
		componentCatalog.put(name, component);
	}

	public Component lookup(String name) {
		return componentCatalog.get(name);
	}

	/**
	 * <p>All component initialization should go through this place.
	 * Get the component from a particular factory, set its state; for
	 * convenience put two things (unique name and its classname) to its model</p>
	 */
	public Component getComponent(Resource rComponent, boolean singleton) {
		log.info("Getting component for resource " + rComponent.getURI());
		Component temp = lookup(getUniqueName(rComponent,singleton));
		if (temp != null && singleton) {
			log.info("Singleton component '" + temp.getName() + "' already created");
			return temp;
		}
		
		String componentTypeURI = rComponent.getRequiredProperty(RDF.type)
				.getResource().getURI();
		ComponentFactory componentFactory = factoryCatalog
				.get(componentTypeURI);
		String name = getUniqueName(rComponent, singleton);
		Component result = componentFactory.getComponent(rComponent);
		
		// this is an optional step, which exposes the component's name in its model
		if (componentFactory instanceof SimpleComponentFactory) {
			((SimpleComponentFactory) componentFactory).setId(result, name,
					rComponent.getLocalName());
		}
		bind(name, result);
		result.setName(name);
		result.setStateManager(stateManager);
		stateManager.registerComponent(result);
		return result;
	}

	public String getResourceUri(String name) {
		String lName = name.substring(0, name.lastIndexOf('_'));
		return PORTAL.NS + "#" + lName;
	}

	public String getUniqueName(Resource rComponent, boolean singleton) {
		String lName = rComponent.getLocalName();
		if (singleton) {
			return lName + "_";
		} else {
			if (uniqueExtensions.containsKey(lName)) {
				uniqueExtensions.put(lName, uniqueExtensions.get(lName) + 1);
			} else {
				uniqueExtensions.put(lName, 1);
			}
			return lName + "_" + uniqueExtensions.get(lName);
		}
	}

	public void preRender() {
		for (String key : componentCatalog.keySet()) {
			Component component = componentCatalog.get(key);
//			if (component instanceof FacetState) {
//				((FacetState)component).preRender();
//			}
			component.preRender();
		}		
	}
	
	public Iterator<String> getComponentNameIterator() {
		return componentCatalog.keySet().iterator();
	}

	public void doStuff() {
		for (DoStuffListener l : doStuffListeners) {
			l.doStuff();
		}	
	}
	
	public void addDoStuffListener(DoStuffListener c) {
		doStuffListeners.add(c);
	}
}
