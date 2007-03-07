package lv.webkursi.mtest.core.components.factories;

import lv.webkursi.mtest.core.components.Component;

import com.hp.hpl.jena.rdf.model.Resource;

public interface ComponentFactory {
	/**
	 * <p>The unique component currently associated with the RDF resource in the given scope, 
	 * or if the resource does not have such, create one. 
	 * Only componentManager should be calling this 'getComponent' method</p>
	 *  
	 * @param rComponent Some resource for which to find/create the component
	 * @return the Component, which is properly registered with the ComponentManager
	 */
	public Component getComponent(Resource rComponent);
	
}
