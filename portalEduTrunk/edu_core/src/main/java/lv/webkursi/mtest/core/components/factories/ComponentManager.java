package lv.webkursi.mtest.core.components.factories;

import java.util.Iterator;

import lv.webkursi.mtest.core.components.Component;
import lv.webkursi.mtest.core.components.DoStuffListener;
import lv.webkursi.mtest.core.mvc.ComponentStateManager;

import com.hp.hpl.jena.rdf.model.Resource;

/**
 * <p>Components are managed by a ComponentManager
 * ComponentManager creates the collection of components for a pageset - 
 * i.e. the RDF resource (for the given pageset) serves as an input and 
 * ComponentManager creates a top-level component for the controller.</p>
 *  
 * <p>ComponentManager also serves as a lookup service for the components. 
 * It can be created anew for each incoming request,  
 * but it could also be made more efficient.</p>
 * 
 */
public interface ComponentManager {
	
	/**
	 * Produce the component from the resource. 
	 * @param rComponent
	 * @param singleton is this created as a singleton
	 * @return
	 */
	public Component getComponent(Resource rComponent, boolean singleton);
		
	/**
	 * Return the component by its name  
	 * @param r
	 * @return
	 */
	public Component lookup(String name);

	/**
	 * This method assigns the name via component.setName() as a side-effect
	 * @param name the name of the comonent
	 * @param component the component to bind for that name 
	 */
	public void bind(String name, Component component);

	public String getUniqueName(Resource rComponent, boolean singleton);
	/**
	 * Translates names back to URIs
	 * @param name
	 * @return
	 */
	public String getResourceUri(String name);

	/**
	 * Controllers, which receive request parameters, can construct stateManager
	 * and set this up for their ComponentManager for the given request. 
	 * @param stateManager
	 */
	public void setStateManager(ComponentStateManager stateManager);
	
	public void preRender();
	
	public void doStuff();
	
	public Iterator<String> getComponentNameIterator();
	
	public void addDoStuffListener(DoStuffListener c);
}
