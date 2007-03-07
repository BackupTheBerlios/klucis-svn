package lv.webkursi.mtest.core.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lv.webkursi.mtest.core.data.User;
import lv.webkursi.mtest.core.data.UserService;
import lv.webkursi.mtest.core.scopes.UserSettings;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.Controller;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;

public class LoginController extends AbstractController implements
		ServiceFactoryAware {

	private ServiceFactory serviceFactory;

	protected final Logger logger = Logger.getLogger(getClass());

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Model portalDescription = (Model) serviceFactory
				.getService(ServiceName.PortalDescription);

		String userName = request.getParameter("_action");
		logger.info("Logging in user " + userName);
		UserService userService = (UserService) serviceFactory
				.getService(ServiceName.UserService);

		// Create a new session, i.e. logout
		request.getSession().invalidate();
		request.getSession(true);

		try {
			User user = userService.getUserByName(portalDescription, userName);
			Resource uResource = userService.getUserResourceByName(
					portalDescription, userName);
			UserSettings userSettings = (UserSettings) serviceFactory
					.getService(ServiceName.UserSettings);
			userSettings.setUser(user);
			userSettings.setUserResource(uResource);
			logger.info("Found user " + userSettings.getUserResource());
		} catch (Exception e) {
			logger.info("User not found");			
			// TODO: process unsuccessful login
		}
		Controller controller = (Controller) serviceFactory
				.getService(ServiceName.FixedController);
		return controller.handleRequest(request, response);
	}

	public void setServiceFactory(ServiceFactory serviceFactory) {
		this.serviceFactory = serviceFactory;
	}

	public ServiceFactory getServiceFactory() {
		return serviceFactory;
	}
}
