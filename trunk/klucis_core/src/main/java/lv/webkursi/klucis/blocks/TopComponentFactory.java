package lv.webkursi.klucis.blocks;

import lv.webkursi.klucis.vocabulary.BLOCKS;

import com.hp.hpl.jena.rdf.model.Resource;

public class TopComponentFactory extends ComponentFactory {
	
	public Component getComponent(Resource rComponent) {
		TopComponent result = new TopComponent();
		String fileName = rComponent.getRequiredProperty(BLOCKS.output)
		.getString();
		result.setFileName(fileName);
		Resource rContent = rComponent.getRequiredProperty(BLOCKS.hasContent).getResource();
		Component content = componentManager.getComponent(rContent);
		result.setContent(content);		
		return result;
	}
}
