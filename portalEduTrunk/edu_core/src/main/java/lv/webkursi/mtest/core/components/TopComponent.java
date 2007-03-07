package lv.webkursi.mtest.core.components;

import java.util.Map;

import lv.webkursi.mtest.core.components.facets.LinkHelper;
import lv.webkursi.mtest.core.mvc.ComponentStateManager;


public class TopComponent extends ModelAndViewComponent {

	public TopComponent(String viewName, Map<String, Object> model) {
		super(viewName, model);
	}

	public void preRender() {
		addObject("_name", getName());
		addObject("_linkHelper", new LinkHelper(this)); 
	}

	@Override
	public ComponentStateManager getStateManager() {
		return stateManager;
	}
	
	@Override
	public void doAction(String action) {
		
	}
}
