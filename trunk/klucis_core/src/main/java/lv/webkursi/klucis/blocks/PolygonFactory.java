package lv.webkursi.klucis.blocks;

import lv.webkursi.klucis.vocabulary.BLOCKS;

import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;

public class PolygonFactory extends ComponentFactory {

	public Component getComponent(Resource rComponent) {
		Polygon result = new Polygon();
		// TODO: name currently unused - > should introduce SVG refid mechanism
		result.setName(rComponent.getLocalName());
		String color = rComponent.getRequiredProperty(BLOCKS.hasColor).getString();
		result.setColor(color);
		String path = rComponent.getRequiredProperty(BLOCKS.hasPath).getString();
		result.setPath(path);
		Statement stmtStrokeWidth = rComponent.getProperty(BLOCKS.strokeWidth);
		if (stmtStrokeWidth != null) {
			result.setStrokeWidth(stmtStrokeWidth.getInt());
		}
		return result;
	}
}
