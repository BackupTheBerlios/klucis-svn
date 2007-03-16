package lv.webkursi.mtest.core.components.factories;

import java.util.HashMap;
import java.util.Map;

import lv.webkursi.mtest.core.components.Component;
import lv.webkursi.mtest.core.components.TopComponent;
import lv.webkursi.mtest.core.mvc.ServiceFactory;
import lv.webkursi.mtest.core.mvc.ServiceFactoryAware;
import lv.webkursi.mtest.core.mvc.ServiceName;
import lv.webkursi.mtest.core.scopes.UserSettings;
import lv.webkursi.mtest.core.vocabulary.MTEST;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hp.hpl.jena.rdf.model.Resource;

public class TopComponentFactory extends SimpleComponentFactory implements
		ServiceFactoryAware {
	private Log log = LogFactory.getLog(TopComponentFactory.class);

	private ServiceFactory serviceFactory;

	public Component getComponent(Resource rComponent) {

		Map<String, Object> model = new HashMap<String, Object>();
		String viewName = rComponent.getRequiredProperty(MTEST.viewName)
				.getString();
		TopComponent result = new TopComponent(viewName, model);

		UserSettings userSettings = (UserSettings)serviceFactory.getService(ServiceName.UserSettings);
		log.info("Processing user " + userSettings.getUserResource());

		if (userSettings.getUser() != null) {
			result.addObject("_currentUser", userSettings.getUser());
		}
		return result;
	}

	public ServiceFactory getServiceFactory() {
		return serviceFactory;
	}

	public void setServiceFactory(ServiceFactory serviceFactory) {
		this.serviceFactory = serviceFactory;
	}
}
