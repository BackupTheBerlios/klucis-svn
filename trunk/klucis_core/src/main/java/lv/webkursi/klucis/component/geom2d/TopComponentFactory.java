package lv.webkursi.klucis.component.geom2d;

import lv.webkursi.klucis.component.AbstractComponentFactory;
import lv.webkursi.klucis.component.Component;
import lv.webkursi.klucis.component.ComponentManager;
import lv.webkursi.klucis.component.VisibleComponent;
import lv.webkursi.klucis.data.KlucisDAO;
import lv.webkursi.klucis.vocabulary.KLUCIS;

import com.hp.hpl.jena.rdf.model.Resource;

public class TopComponentFactory extends AbstractComponentFactory {
	
	public Component localGetComponent(Resource r, ComponentManager componentManager, String id) {
		TopComponent result = new TopComponent();
		configureCommonProperties(result,r,id);
		KlucisDAO dao = componentManager.getKlucisDAO();
		result.setFileName(dao.getStringProperty(r, KLUCIS.hasFileName));
		result.setViewName(dao.getStringProperty(r, KLUCIS.hasViewName));
		Resource rContent = r.getRequiredProperty(KLUCIS.hasContent).getResource();
		VisibleComponent content = (VisibleComponent)componentManager.getStaticComponent(rContent);
		content.setEnclosing(result);
		result.setContent(content);		
		return result;
	}
}
