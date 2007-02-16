package lv.webkursi.klucis.component.control;

import lv.webkursi.klucis.KlucisConfigurationException;
import lv.webkursi.klucis.component.AbstractComponentFactory;
import lv.webkursi.klucis.component.Component;
import lv.webkursi.klucis.component.ComponentManager;
import lv.webkursi.klucis.component.VisibleComponent;
import lv.webkursi.klucis.vocabulary.KLUCIS;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hp.hpl.jena.rdf.model.Bag;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Resource;

public class CompositeWidgetFactory extends AbstractComponentFactory {

	private final Log log = LogFactory.getLog(CompositeWidgetFactory.class);

	@Override
	protected Component localGetComponent(Resource r,
			ComponentManager componentManager, String id) {
		CompositeWidget w = new CompositeWidget();
		configureCommonProperties(w, r, id);
		try {
			if (r.getProperty(KLUCIS.hasComponents) != null) {
				Bag bag = r.getRequiredProperty(KLUCIS.hasComponents).getBag();
				for (NodeIterator i = bag.iterator(); i.hasNext();) {
					Resource rEntry = (Resource) i.nextNode();
					String modelName = rEntry.getRequiredProperty(
							KLUCIS.key).getString();
					Resource rWidget = rEntry.getRequiredProperty(
							KLUCIS.value ).getResource();
					VisibleComponent subWidget = (VisibleComponent)(componentManager.getStaticComponent(rWidget));
					w.addWidget(modelName,subWidget);
				}
			}
		} catch (Exception e) {
			log.error("Misconfigured CompositeWidget " + r.getURI(), e);
			throw new KlucisConfigurationException(
					"Misconfigured CompositeWidget " + r.getURI());
		}
//		w.addAllObjects(w.getWidgets());
		return w;
	}
}
