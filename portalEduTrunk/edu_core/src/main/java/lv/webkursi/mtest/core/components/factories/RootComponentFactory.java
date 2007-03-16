package lv.webkursi.mtest.core.components.factories;

import lv.webkursi.mtest.core.components.Component;
import lv.webkursi.mtest.core.components.CompositeModelAndViewComponent;
import lv.webkursi.mtest.core.vocabulary.MTEST;

import com.hp.hpl.jena.rdf.model.Resource;

/**
 * A factory for the root-components, i.e. those, which create the root element of HTML 
 */
public class RootComponentFactory extends CompositeComponentFactory {
	@Override
	public Component getComponent(Resource rComponent) {
		CompositeModelAndViewComponent result = (CompositeModelAndViewComponent)super.getComponent(rComponent);
		String title = rComponent.getRequiredProperty(MTEST.title)
				.getString();
		result.addObject("title", title);
		return result;
	}
}
