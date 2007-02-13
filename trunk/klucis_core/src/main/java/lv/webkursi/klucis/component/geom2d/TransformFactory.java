package lv.webkursi.klucis.component.geom2d;

import lv.webkursi.klucis.component.AbstractComponentFactory;
import lv.webkursi.klucis.component.Component;
import lv.webkursi.klucis.component.ComponentManager;
import lv.webkursi.klucis.component.VisibleComponent;
import lv.webkursi.klucis.data.KlucisDAO;
import lv.webkursi.klucis.vocabulary.KLUCIS;

import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;

public class TransformFactory extends AbstractComponentFactory {

	@Override
	public Component localGetComponent(Resource r, ComponentManager componentManager, String id) {
		Transform result = new Transform();
		configureCommonProperties(result,r,id);
		KlucisDAO dao = componentManager.getKlucisDAO();
		result.setViewName(dao.getStringProperty(r, KLUCIS.hasViewName));
		Statement stmtRotate = r.getProperty(KLUCIS.rotate);
		if (stmtRotate != null) {
			result.setRotate(stmtRotate.getInt());
		}
		Statement stmtScaleX = r.getProperty(KLUCIS.scaleX);
		if (stmtScaleX != null) {
			result.setScaleX(stmtScaleX.getInt());
		}
		
		Resource rContent = r.getRequiredProperty(KLUCIS.hasContent).getResource();
		VisibleComponent content = (VisibleComponent)componentManager.getStaticComponent(rContent);
		content.setEnclosing(result);
		result.setContent(content);
		return result;
	}
}
