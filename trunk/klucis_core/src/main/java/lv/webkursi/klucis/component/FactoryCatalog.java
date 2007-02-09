package lv.webkursi.klucis.component;

import java.util.HashMap;
import java.util.Map;


/**
 * A catalog of <code>Component</code> factory objects, all statically configured 
 * vector graphics components are initialized by RDF-aware factories. This catalog
 * allows to find a factory by the type of a resource it should initialize.  
 * 
 * @author kap
 * 
 */
public class FactoryCatalog {
	Map<String, ComponentFactory> map = new HashMap<String, ComponentFactory>();

	/**
	 * Get the component factory for a given component type.
	 * 
	 * @param url
	 *            the URL of the component type.
	 * @return the component factory
	 */
	public ComponentFactory getFactory(String url) {
		return map.get(url);
	}

	/**
     * Set the map for the catalog.
     * 
	 * @param map
	 *            a map from URLs to factories.
	 */
	public void setCatalog(Map<String, ComponentFactory> map) {
		this.map = map;
	}
}
