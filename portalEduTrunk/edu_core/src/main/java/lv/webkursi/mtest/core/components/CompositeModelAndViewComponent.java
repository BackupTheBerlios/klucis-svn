package lv.webkursi.mtest.core.components;

import java.util.ArrayList;
import java.util.Map;

/**
 * This Component is a ModelAndView implementation for a component, whose
 * model can add other components with their modelKeys. It can be used, e.g. 
 * for the root component's model to hold a certain amount of named panes 
 * (navbar, menubar, resultset).
 */
public class CompositeModelAndViewComponent extends ModelAndViewComponent implements Component {
	
	/**
	 * Same as constructing ModelAndView
	 * @param viewName
	 * @param model
	 */
	public CompositeModelAndViewComponent(String viewName, Map<String, Object> model) {
		super(viewName, model);
		this.addObject("_subNodes", new ArrayList<Component>());
	}

	/**
	 * The actions are not supported by composite components 
	 * (except for special cases overriden in subclasses, 
	 * e.g. adding a new facet in the compositeFacet component)
	 */
	public void doAction(String action) {
	}

	/**
	 * Most likely don't need any action here, since all the leaf components
	 * are also registered with the stateManager and setting the state is
	 * done for each one of them individually
	 */
	public void setStateFromString(String state) {
	}
	
	@SuppressWarnings("unchecked")
	public void addComponent(String key, Component component) {
		addObject(key, component);
	}
}
