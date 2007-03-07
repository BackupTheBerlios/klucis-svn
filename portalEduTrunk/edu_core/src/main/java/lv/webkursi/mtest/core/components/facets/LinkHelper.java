package lv.webkursi.mtest.core.components.facets;

import java.util.List;

import lv.webkursi.mtest.core.components.Component;


public class LinkHelper {
	private Component component;

	public LinkHelper(Component component) {
		this.component = component;
	}

	public String getActionLink(List param) {
		return component.getStateManager().getLink(component,
				(String) param.get(0), (String) param.get(1));
	}

	public String getCrossActionLink(List param) {
		return component.getStateManager().getLink(component,
				(String) param.get(0), (String) param.get(1), (String) param.get(2));
	}
}
