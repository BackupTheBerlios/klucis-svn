package lv.webkursi.mtest.core.components.factories;

import java.util.HashMap;
import java.util.Map;

import lv.webkursi.mtest.core.components.Component;
import lv.webkursi.mtest.core.components.ModelAndViewComponent;
import lv.webkursi.mtest.mvc.vocabulary.MARS;

import com.hp.hpl.jena.rdf.model.Bag;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;

/**
 * <p>Creates the component with empty model; can be used for trivial components without
 * any internal state, and also as a common initialization pattern in its subclasses.</p> 
 */
public class SimpleComponentFactory implements ComponentFactory {
	
	public Component getComponent(Resource rComponent) {
		Map<String,Object> model = new HashMap<String,Object>();
		String viewName = rComponent.getRequiredProperty(MARS.viewName).getString();
		Component result = new ModelAndViewComponent(viewName, model);
		populateModel(result,rComponent);
		return result;
	}
	
	public void setId(Component c, String _id, String _class) {
		((ModelAndViewComponent)c).addObject("_id", _id);
		if (_class != null) {
			// some components are created from blank nodes, so they do not have CLASS
			((ModelAndViewComponent)c).addObject("_class", _class);
		}
	}
	
	public void populateModel(Component c, Resource rComponent) {
		Statement stmt = rComponent.getProperty(MARS.model);
		if (stmt != null) {
			Bag itemBag = stmt.getBag();
			for (NodeIterator i = itemBag.iterator(); i.hasNext(); ) {
				Resource item = (Resource)i.nextNode();
				String key = item.getRequiredProperty(MARS.modelKey).getString();
				String value = item.getRequiredProperty(MARS.value).getString();
				((ModelAndViewComponent)c).addObject(key, value);
			}
		}
	}
}
