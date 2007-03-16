package lv.webkursi.mtest.core.components.factories;

import java.util.HashMap;
import java.util.Map;

import lv.webkursi.mtest.core.components.Component;
import lv.webkursi.mtest.core.components.facets.Facet;
import lv.webkursi.mtest.core.components.facets.TextFacetState;
import lv.webkursi.mtest.core.vocabulary.MTEST;

import com.hp.hpl.jena.rdf.model.Resource;

public class TextFacetFactory extends SimpleComponentFactory {
	public Component getComponent(Resource rComponent) {
		Map<String,Object> model = new HashMap<String,Object>();
		String viewName = rComponent.getRequiredProperty(MTEST.viewName).getString();
		TextFacetState result = new TextFacetState(viewName, model);
//		result.setStateManager(getStateManager());
		
		String defaultState = rComponent.getRequiredProperty(MTEST.defaultState).getString();
		String title = rComponent.getRequiredProperty(MTEST.title).getString();
		Facet facet = new Facet(title,defaultState);
		result.setFacet(facet);
		result.setState(defaultState);
		
		return result;
	}
	
}
