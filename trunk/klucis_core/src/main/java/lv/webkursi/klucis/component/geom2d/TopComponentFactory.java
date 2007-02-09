package lv.webkursi.klucis.component.geom2d;

import lv.webkursi.klucis.component.AbstractComponentFactory;
import lv.webkursi.klucis.component.Component;
import lv.webkursi.klucis.component.ComponentManager;
import lv.webkursi.klucis.vocabulary.KLUCIS;

import com.hp.hpl.jena.rdf.model.Resource;

public class TopComponentFactory extends AbstractComponentFactory {
	
	public Component localGetComponent(Resource r, ComponentManager componentManager, String id) {
		TopComponent result = new TopComponent();
		configureCommonProperties(result,r,id);
		String fileName = r.getRequiredProperty(KLUCIS.output)
		.getString();
		result.setFileName(fileName);
		Resource rContent = r.getRequiredProperty(KLUCIS.hasContent).getResource();
		Component content = componentManager.getStaticComponent(rContent);
		content.setParent(result);
		result.setContent(content);		
		return result;
	}
}
