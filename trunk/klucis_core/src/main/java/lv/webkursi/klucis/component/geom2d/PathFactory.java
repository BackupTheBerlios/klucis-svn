package lv.webkursi.klucis.component.geom2d;

import lv.webkursi.klucis.component.AbstractComponentFactory;
import lv.webkursi.klucis.component.Component;
import lv.webkursi.klucis.component.ComponentManager;
import lv.webkursi.klucis.data.KlucisDAO;
import lv.webkursi.klucis.vocabulary.KLUCIS;

import com.hp.hpl.jena.rdf.model.Resource;

public class PathFactory extends AbstractComponentFactory {

	public Component localGetComponent(Resource r, ComponentManager componentManager, String id) {
		Path result = new Path();
		configureCommonProperties(result,r,id);
		KlucisDAO dao = componentManager.getKlucisDAO();		
		result.setPath(dao.getStringProperty(r, KLUCIS.hasPath));
		result.setViewName(dao.getStringProperty(r, KLUCIS.hasViewName));		
		result.setColor(dao.getStringProperty(r, KLUCIS.hasColor));
		result.setStrokeWidth(dao.getFloatProperty(r, KLUCIS.hasStrokeWidth));
		result.setWidth(dao.getFloatProperty(r, KLUCIS.hasWidth));
		result.setHeight(dao.getFloatProperty(r, KLUCIS.hasHeight));
		return result;
	}
}
