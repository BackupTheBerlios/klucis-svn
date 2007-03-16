package lv.webkursi.mtest.core.components.factories;

import java.util.HashMap;
import java.util.Map;

import lv.webkursi.mtest.core.components.Component;
import lv.webkursi.mtest.core.components.facets.Facet;
import lv.webkursi.mtest.core.components.facets.IncDecFacetState;
import lv.webkursi.mtest.core.vocabulary.MTEST;

import com.hp.hpl.jena.rdf.model.Resource;

public class IncDecFacetFactory extends SimpleComponentFactory {
	public Component getComponent(Resource rComponent) {
		Map<String,Object> model = new HashMap<String,Object>();
		String viewName = rComponent.getRequiredProperty(MTEST.viewName).getString();
		IncDecFacetState result = new IncDecFacetState(viewName, model);
		
		Integer defaultState = rComponent.getRequiredProperty(MTEST.defaultState).getInt();
		String title = rComponent.getRequiredProperty(MTEST.title).getString();
		Facet facet = new Facet(title,defaultState);
		result.setFacet(facet);
		result.setState(defaultState);
		
		return result;		
	}
	
	
}
