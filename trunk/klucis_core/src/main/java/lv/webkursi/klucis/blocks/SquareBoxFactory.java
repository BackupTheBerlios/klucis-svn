package lv.webkursi.klucis.blocks;

import lv.webkursi.klucis.vocabulary.BLOCKS;

import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;

public class SquareBoxFactory extends ComponentFactory {
	
	public Component getComponent(Resource rComponent) {
		SquareBox result = new SquareBox();
		Statement stmtLabel = rComponent.getProperty(BLOCKS.hasLabel);
		if (stmtLabel != null) {
			result.setLabel(stmtLabel.getString());
		}

		Statement stmtRotation = rComponent.getProperty(BLOCKS.rotate);
		if (stmtRotation != null) {
			result.setRotate(stmtRotation.getInt());
		}
		
		Resource rContent = rComponent.getRequiredProperty(BLOCKS.hasContent).getResource();
		Component content = componentManager.getComponent(rContent);
		result.setContent(content);		
		return result;
	}
}
