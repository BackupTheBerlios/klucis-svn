package lv.webkursi.mtest.core.components.factories;

import java.util.HashMap;
import java.util.Map;

import lv.webkursi.mtest.core.components.Component;
import lv.webkursi.mtest.core.components.CompositeModelAndViewComponent;
import lv.webkursi.mtest.core.mvc.ServiceFactory;
import lv.webkursi.mtest.core.mvc.ServiceFactoryAware;
import lv.webkursi.mtest.core.mvc.ServiceName;
import lv.webkursi.mtest.mvc.vocabulary.MARS;

import com.hp.hpl.jena.rdf.model.Bag;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 * Produces a CompositeModelAndViewComponent
 */
public class CompositeComponentFactory extends SimpleComponentFactory implements ServiceFactoryAware {
	
	protected ServiceFactory serviceFactory; 
	/** 
	 * Outside clients should call the getComponent method in ComponentManager instead. 
	 */
	public Component getComponent(Resource rComponent) { 
		Map<String, Object> model = new HashMap<String, Object>();
		String viewName = rComponent.getRequiredProperty(MARS.viewName)
		.getString();
		CompositeModelAndViewComponent result = new CompositeModelAndViewComponent(viewName, model);

		Bag componentBag = rComponent.getRequiredProperty(
				MARS.hasComponents).getBag();
		for (NodeIterator componentIterator = componentBag.iterator(); componentIterator
				.hasNext();) {
			Resource componentDesc = (Resource) componentIterator.nextNode();
			// TODO: This assumption of singleton is WRONG. Need to refactor, so 
			// that component manager itself goes through the tree. 
			Resource subComponent = componentDesc.getRequiredProperty(MARS.component).getResource();
			ComponentManager componentManager = (ComponentManager)serviceFactory.getService(ServiceName.ComponentManager);
			Component component = componentManager
					.getComponent(subComponent,true);
			String modelKey = componentDesc.getRequiredProperty(MARS.modelKey)
					.getString();
			result.addComponent(modelKey, component);
		}
		populateModel(result,rComponent);
		return result;
	}

	public ServiceFactory getServiceFactory() {
		return serviceFactory;
	}

	public void setServiceFactory(ServiceFactory serviceFactory) {
		this.serviceFactory = serviceFactory;
	}

}
