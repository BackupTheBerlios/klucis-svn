package lv.webkursi.mtest.core.mvc.rule;

import java.util.HashMap;
import java.util.Map;

import lv.webkursi.mtest.core.components.factories.ComponentManager;

import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

public class ConditionFactoryImpl {
	protected ComponentManager componentManager;
	private Map<String,ConditionFactory> conditionFactoryCatalog = new HashMap<String,ConditionFactory>();
	
	public ConditionFactoryImpl() {
		conditionFactoryCatalog.put("http://www.webkursi.lv/schema/mtest#IntegerRangeCondition", new IntegerRangeConditionFactory());
	}

	
	public Predicate getCondition(Resource rCondition) {
		ConditionFactory currentFactory = conditionFactoryCatalog.get(rCondition.getRequiredProperty(RDF.type).getResource().getURI());
		currentFactory.setComponentManager(componentManager);
		Predicate result = currentFactory.getCondition(rCondition);
		
		return result;		
		
	}

	public void setComponentManager(ComponentManager componentManager) {
		this.componentManager = componentManager;
	}

	public void setConditionFactoryCatalog(
			Map<String, ConditionFactory> conditionFactoryCatalog) {
		this.conditionFactoryCatalog.putAll(conditionFactoryCatalog);
	}
	
	

}
