package lv.webkursi.klucis.blocks;

import lv.webkursi.klucis.vocabulary.BLOCKS;

import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;

public class TransformFactory extends ComponentFactory {

	@Override
	public Component getComponent(Resource rComponent) {
		Transform result = new Transform();
		Statement stmtRotate = rComponent.getProperty(BLOCKS.rotate);
		if (stmtRotate != null) {
			result.setRotate(stmtRotate.getInt());
		}
		Statement stmtScaleX = rComponent.getProperty(BLOCKS.scaleX);
		if (stmtScaleX != null) {
			result.setScaleX(stmtScaleX.getInt());
		}
		
		Resource rContent = rComponent.getRequiredProperty(BLOCKS.hasContent).getResource();
		Component content = componentManager.getComponent(rContent);
		result.setContent(content);
		return result;
	}
}
