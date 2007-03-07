package lv.webkursi.mtest.core.mapping;

import java.util.Map;

import lv.webkursi.mtest.core.components.Component;
import lv.webkursi.mtest.core.components.ModelAndViewComponent;
import lv.webkursi.mtest.core.components.factories.ComponentManager;
import lv.webkursi.mtest.core.mvc.ComponentStateManager;

import com.hp.hpl.jena.rdf.model.Resource;

/**
 * A mapper, which builds a 'typeString' from a give object and
 * then looks up the appropriate component type, retrieves it and
 * initializes it with the object provided.
 */
public class TypeStringMapper implements Mapper, Component {

	private ComponentStateManager stateManager;

	private String name;

	private Map<String, Resource> typeStringMap;
	
	private Resource defaultValue;

	public void setTypeStringMap(Map<String, Resource> typeStringMap) {
		this.typeStringMap = typeStringMap;
	}

	public void setDefaultValue(Resource defaultValue) {
		this.defaultValue = defaultValue;
	}

	private String getTypeString(Object object) {
		return object.getClass().getName();
	}

	/**
	 * Find component from the result object to display. The algorithm is based
	 * on 'typeString' - string, which characterizes internal settings of the
	 * mapper, etc.
	 */
	public Component getComponent(Object object) {
		String typeString = getTypeString(object);
		Resource r = typeStringMap.get(typeString);
		if (r == null) {
			r = defaultValue;
		}		
		ComponentManager componentManager = stateManager.getComponentManager();
		ModelAndViewComponent c = (ModelAndViewComponent) componentManager
				.getComponent(r, false);
		c.addObject("object", object);
		return c;
	}

	public void doAction(String action) {
	}

	public String getName() {
		return name;
	}

	public String getStateAsString() {
		return null;
	}

	public ComponentStateManager getStateManager() {
		return stateManager;
	}

	public void preRender() {
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStateFromString(String state) {
	}

	public void setStateManager(ComponentStateManager stateManager) {
		this.stateManager = stateManager;
	}
}
