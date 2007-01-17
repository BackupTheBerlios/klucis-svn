package lv.webkursi.klucis.blocks;

import lv.webkursi.klucis.ComponentManager;

import com.hp.hpl.jena.rdf.model.Resource;

public abstract class ComponentFactory {

	protected ComponentManager componentManager;

	public void setComponentManager(ComponentManager componentManager) {
		this.componentManager = componentManager;
	}

	public abstract Component getComponent(Resource rComponent);
}
