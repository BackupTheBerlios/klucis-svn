package lv.webkursi.mtest.core.mvc.rule;

import java.util.HashMap;
import java.util.Map;

import lv.webkursi.mtest.core.components.factories.ComponentManager;

import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

public class ActionFactoryImpl implements ActionFactory {
	
	protected ComponentManager componentManager;
	private Map<String,ActionFactory> actionFactoryCatalog = new HashMap<String,ActionFactory>();
	
	public ActionFactoryImpl() {
		actionFactoryCatalog.put("http://www.hpl.hp.com/schema/mars#AddFacetAction", new AddFacetActionFactory());
	}
	
	public Action getAction(Resource rAction) {
		
		ActionFactory currentFactory = actionFactoryCatalog.get(rAction.getRequiredProperty(RDF.type).getResource().getURI());
		
		currentFactory.setComponentManager(componentManager);
		Action result = currentFactory.getAction(rAction);		
		return result;		
	}

	public void setActionFactoryCatalog(
			Map<String, ActionFactory> actionFactoryCatalog) {
		this.actionFactoryCatalog.putAll(actionFactoryCatalog);
	}

	public void setComponentManager(ComponentManager componentManager) {
		this.componentManager = componentManager;
	}
	
}
