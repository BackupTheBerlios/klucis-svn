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
		result.setCoreWidth(dao.getFloatProperty(r, KLUCIS.hasCoreWidth)); 
		result.setCoreHeight(dao.getFloatProperty(r, KLUCIS.hasCoreHeight));
		result.setRotate(dao.getFloatProperty(r, KLUCIS.rotate)); 
		result.setShowRectangle(dao.getBooleanProperty(r,KLUCIS.showRectangle)); 
		result.setViewName(dao.getStringProperty(r, KLUCIS.hasViewName)); 				
		Resource rContent = r.getRequiredProperty(KLUCIS.hasContent).getResource();
		VisibleComponent content = (VisibleComponent)componentManager.getStaticComponent(rContent);
		content.setEnclosing(result);
		result.setContent(content);				
		return result;
	}
}
