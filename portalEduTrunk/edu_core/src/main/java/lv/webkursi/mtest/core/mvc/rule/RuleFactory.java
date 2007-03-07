package lv.webkursi.mtest.core.mvc.rule;

import lv.webkursi.mtest.core.components.Component;
import lv.webkursi.mtest.core.components.factories.ComponentFactory;
import lv.webkursi.mtest.core.components.factories.ComponentManager;
import lv.webkursi.mtest.core.mvc.ServiceFactory;
import lv.webkursi.mtest.core.mvc.ServiceName;
import lv.webkursi.mtest.mvc.vocabulary.MARS;

import com.hp.hpl.jena.rdf.model.Resource;



public class RuleFactory implements ComponentFactory {
	protected ServiceFactory serviceFactory; 
	protected ComponentManager componentManager;
	
	public Component getComponent(Resource rRule) {
		RuleImpl rule = new RuleImpl();
		Resource rAction = rRule.getRequiredProperty(MARS.action).getResource();
		ActionFactoryImpl actionFactory = new ActionFactoryImpl(); 
		actionFactory.setComponentManager(componentManager);
		Action action = actionFactory.getAction(rAction);
		rule.setAction(action);
		
		Resource rCondition = rRule.getRequiredProperty(MARS.condition).getResource();
		ConditionFactoryImpl conditionFactory = new ConditionFactoryImpl();
		conditionFactory.setComponentManager(componentManager);
		Predicate predicate = conditionFactory.getCondition(rCondition);
		rule.setPredicate(predicate);

		return rule;
	}

	public void setServiceFactory(ServiceFactory serviceFactory) {
		this.serviceFactory = serviceFactory;
		componentManager = (ComponentManager)serviceFactory.getService(ServiceName.ComponentManager);
	}
}
