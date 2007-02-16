package lv.webkursi.klucis.component.control;

import lv.webkursi.klucis.component.AbstractComponentFactory;
import lv.webkursi.klucis.component.Component;
import lv.webkursi.klucis.component.ComponentManager;
import lv.webkursi.klucis.data.KlucisDAO;
import lv.webkursi.klucis.vocabulary.KLUCIS;

import com.hp.hpl.jena.rdf.model.Resource;

public class PlainComponentFactory extends AbstractComponentFactory {
	
	public Component localGetComponent(Resource r, ComponentManager componentManager, String id) {
		PlainComponent result = new PlainComponent();
		configureCommonProperties(result,r,id);
		KlucisDAO dao = componentManager.getKlucisDAO();
		result.setViewName(dao.getStringProperty(r, KLUCIS.hasViewName));
		return result;
	}
}
