package lv.webkursi.mtest.core.components;

import java.util.Map;

import lv.webkursi.mtest.core.mvc.ComponentStateManager;

import org.springframework.web.servlet.ModelAndView;


public class ModelAndViewComponent extends ModelAndView implements Component {
	private String name;
	
	protected ComponentStateManager stateManager;
	
	public ModelAndViewComponent(String viewName, Map<String,Object> model) {
		super(viewName,model);		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void doAction(String action) {
	}
	
	
	/**
	 * Should be overriden in subclasses
	 */
	public void setStateFromString(String state) {
	}

	public void setStateManager(ComponentStateManager stateManager) {
		this.stateManager = stateManager;
        stateManager.registerComponent(this);
        if (stateManager.hasComponentState(this)) {
            setStateFromString(stateManager.getComponentState(this));
        } 	
    }

	/**
	 * Subclasses (especially facetStates) should override this
	 */
	public String getStateAsString() {		
		return null;
	}

	public ComponentStateManager getStateManager() {
		return stateManager;
	}

	public void preRender() {
		// TODO Auto-generated method stub
		
	}
}
