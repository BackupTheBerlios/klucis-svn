package lv.webkursi.mtest.core.mvc.rule;

import lv.webkursi.mtest.core.components.CompositeFacet;
import lv.webkursi.mtest.core.components.facets.FacetState;
import lv.webkursi.mtest.core.components.factories.ComponentManager;
import lv.webkursi.mtest.mvc.vocabulary.MARS;

import com.hp.hpl.jena.rdf.model.Resource;

public class AddFacetActionFactory implements ActionFactory {
	
	private ComponentManager componentManager;
	
	public Action getAction(Resource rAction) {
		AddFacetAction result = new AddFacetAction();
		Resource rCompositeFacet = rAction.getRequiredProperty(MARS.compositeFacet).getResource();
		CompositeFacet compositeFacet = (CompositeFacet)componentManager.getComponent(rCompositeFacet, true);
		result.setCompositeFacet(compositeFacet);
		Resource rFacet = rAction.getRequiredProperty(MARS.facet).getResource();
		FacetState facet = (FacetState)componentManager.getComponent(rFacet, true);
		result.setFacet(facet);
		return result;
	}

	public void setComponentManager(ComponentManager componentManager) {
		this.componentManager = componentManager;
	}
}
