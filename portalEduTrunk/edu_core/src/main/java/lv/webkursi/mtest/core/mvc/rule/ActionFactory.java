package lv.webkursi.mtest.core.mvc.rule;

import lv.webkursi.mtest.core.components.factories.ComponentManager;

import com.hp.hpl.jena.rdf.model.Resource;

public interface ActionFactory {
	public Action getAction(Resource rAction);
	public void setComponentManager(ComponentManager componentManager);
}
