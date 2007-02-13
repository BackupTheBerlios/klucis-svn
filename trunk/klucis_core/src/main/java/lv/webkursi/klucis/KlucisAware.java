package lv.webkursi.klucis;

import lv.webkursi.klucis.component.ComponentManager;

import com.hp.hpl.jena.rdf.model.Model;

public interface KlucisAware {
	
	public ComponentManager getComponentManager();
	
	public Model getModel();

	public void setComponentManager(ComponentManager componentManager);

	public void setModel(Model model);
}
