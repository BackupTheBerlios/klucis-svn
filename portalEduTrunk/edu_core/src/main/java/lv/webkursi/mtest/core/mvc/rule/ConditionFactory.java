package lv.webkursi.mtest.core.mvc.rule;

import lv.webkursi.mtest.core.components.factories.ComponentManager;

import com.hp.hpl.jena.rdf.model.Resource;

public interface ConditionFactory {
	public Predicate getCondition(Resource rCondition);
	public void setComponentManager(ComponentManager componentManager);
}
