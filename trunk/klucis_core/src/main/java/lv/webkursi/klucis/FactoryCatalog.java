/**
 * Copyright 2006 Hewlett-Packard Development Company, LP
 */
package lv.webkursi.klucis;

import java.util.HashMap;
import java.util.Map;

import lv.webkursi.klucis.blocks.ComponentFactory;

/**
 * A catalog of <code>Component</code> factory objects.
 * 
 * @author bwm
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
