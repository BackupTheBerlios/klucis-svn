package lv.webkursi.klucis.component.geom2d;

import lv.webkursi.klucis.component.AbstractComponentFactory;
import lv.webkursi.klucis.component.Component;
import lv.webkursi.klucis.component.ComponentManager;
import lv.webkursi.klucis.component.VisibleComponent;
import lv.webkursi.klucis.data.KlucisDAO;
import lv.webkursi.klucis.vocabulary.KLUCIS;

import com.hp.hpl.jena.rdf.model.Resource;

public class RectangleFactory extends AbstractComponentFactory {
	
	public Component localGetComponent(Resource r, ComponentManager componentManager, String id) {
		Rectangle result = new Rectangle();
		configureCommonProperties(result,r,id);
		
		KlucisDAO dao = componentManager.getKlucisDAO();
		result.setLabel(dao.getStringProperty(r, KLUCIS.hasLabel)); 
		result.setCoreWidth(dao.getFloatProperty(r, KLUCIS.hasRectWidth)); 
		result.setCoreHeight(dao.getFloatProperty(r, KLUCIS.hasRectHeight));
		result.setRotate(dao.getFloatProperty(r, KLUCIS.rotate)); 
		result.setShowRectangle(dao.getBooleanProperty(r,KLUCIS.showRectangle)); 
		result.setViewName(dao.getStringProperty(r, KLUCIS.hasViewName)); 
		
		/*
		Statement stmtLabel = r.getProperty(KLUCIS.hasLabel);
		if (stmtLabel != null) {
			result.setLabel(stmtLabel.getString());
		}
		
		float rectWidth = 200.0F;
		Statement stmtRectWidth = r.getProperty(KLUCIS.hasRectWidth);
		if (stmtRectWidth != null) {
			rectWidth = stmtRectWidth.getFloat();			
		}
		result.setCoreWidth(rectWidth);

		float rectHeight = 200.0F;
		Statement stmtRectHeight = r.getProperty(KLUCIS.hasRectHeight);
		if (stmtRectHeight != null) {
			rectHeight = stmtRectHeight.getFloat();			
		}
		result.setCoreHeight(rectHeight);

		float rotate = 0.0F;
		Statement stmtRotate = r.getProperty(KLUCIS.rotate);
		if (stmtRotate != null) {
			rotate = stmtRotate.getFloat();			
		}
		result.setRotate(rotate);
		
		boolean showRectangle = true;
		Statement stmtShowRectangle = r.getProperty(KLUCIS.showRectangle);
		if (stmtShowRectangle != null) {
			showRectangle = stmtShowRectangle.getBoolean(); 
		}
		result.setShowRectangle(showRectangle);
		*/
		
		Resource rContent = r.getRequiredProperty(KLUCIS.hasContent).getResource();
		VisibleComponent content = (VisibleComponent)componentManager.getStaticComponent(rContent);
		content.setEnclosing(result);
		result.setContent(content);				
		return result;
	}
}
