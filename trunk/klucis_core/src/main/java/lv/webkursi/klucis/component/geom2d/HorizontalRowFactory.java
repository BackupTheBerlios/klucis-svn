package lv.webkursi.klucis.component.geom2d;

import java.util.ArrayList;
import java.util.List;

import lv.webkursi.klucis.component.AbstractComponentFactory;
import lv.webkursi.klucis.component.Component;
import lv.webkursi.klucis.component.ComponentManager;
import lv.webkursi.klucis.component.VisibleComponent;
import lv.webkursi.klucis.data.KlucisDAO;
import lv.webkursi.klucis.vocabulary.KLUCIS;

import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Seq;

public class HorizontalRowFactory extends AbstractComponentFactory {
	
	public Component localGetComponent(Resource r, ComponentManager componentManager, String id) {
		HorizontalRow result = new HorizontalRow();
		configureCommonProperties(result,r,id);
		
		/*
		float gap = 60.0F; 
		Statement stmtGap = r.getProperty(KLUCIS.hasGap);
		if (stmtGap != null) {
			gap = stmtGap.getFloat();
		}
		result.setGap(gap);
		*/
		KlucisDAO dao = componentManager.getKlucisDAO();
		result.setGap(dao.getFloatProperty(r, KLUCIS.hasGap));
		result.setViewName(dao.getStringProperty(r, KLUCIS.hasViewName));
		

		Seq seq = (Seq)r.as(Seq.class);
		List<VisibleComponent> components = new ArrayList<VisibleComponent>();
		for (NodeIterator i = seq.iterator(); i.hasNext(); ) {
			Resource rChild = (Resource)i.nextNode();
			VisibleComponent child = (VisibleComponent)componentManager.getStaticComponent(rChild);
			child.setEnclosing(result);
			components.add(child);
		}
		result.setComponents(components);
		return result;
	}
}
