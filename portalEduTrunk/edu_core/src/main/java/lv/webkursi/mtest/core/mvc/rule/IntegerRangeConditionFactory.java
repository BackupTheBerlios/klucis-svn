package lv.webkursi.mtest.core.mvc.rule;

import lv.webkursi.mtest.core.components.facets.IncDecFacetState;
import lv.webkursi.mtest.core.components.factories.ComponentManager;
import lv.webkursi.mtest.core.vocabulary.MTEST;

import com.hp.hpl.jena.rdf.model.Resource;

public class IntegerRangeConditionFactory implements ConditionFactory {
	private ComponentManager componentManager;
	public Predicate getCondition(Resource rCondition) {
		IntegerRangeCondition result = new IntegerRangeCondition();
		Resource rConditionSource = rCondition.getRequiredProperty(MTEST.conditionSource).getResource();
		IncDecFacetState facetState = (IncDecFacetState)componentManager.getComponent(rConditionSource, true);
		result.setFacetState(facetState);		
		int conditionLowerBound = rCondition.getRequiredProperty(MTEST.conditionLowerBound).getInt();
		result.setLower(conditionLowerBound);
		int conditionUpperBound = rCondition.getRequiredProperty(MTEST.conditionUpperBound).getInt();
		result.setUpper(conditionUpperBound);
		return result;
	}

	public void setComponentManager(ComponentManager componentManager) {
		this.componentManager = componentManager;
	}
}
