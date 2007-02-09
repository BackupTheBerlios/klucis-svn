package lv.webkursi.klucis.component;


import com.hp.hpl.jena.rdf.model.Resource;

public interface ComponentFactory {
	public Component getComponent(Resource description, ComponentManager componentManager, String id);
}
