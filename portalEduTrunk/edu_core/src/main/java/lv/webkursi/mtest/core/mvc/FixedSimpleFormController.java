package lv.webkursi.mtest.core.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lv.webkursi.mtest.core.components.ModelAndViewComponent;
import lv.webkursi.mtest.core.components.factories.ComponentManager;
import lv.webkursi.mtest.core.data.RdfUtilities;
import lv.webkursi.mtest.core.data.UserForm;
import lv.webkursi.mtest.core.data.UserService;
import lv.webkursi.mtest.mvc.vocabulary.MARS;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.mvc.AbstractFormController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;

public class FixedSimpleFormController extends AbstractFormController implements
		ServiceFactoryAware {
	private ServiceFactory serviceFactory;

	private Log log = LogFactory.getLog(FixedSimpleFormController.class);

	public FixedSimpleFormController() {
		setCommandClass(UserForm.class);
	}

	protected ModelAndView showForm(HttpServletRequest request,
			HttpServletResponse response, BindException errors) {
		Model portalDescription = (Model) serviceFactory
				.getService(ServiceName.PortalDescription);
		String pageSetName = request.getServletPath();
		Resource pageSet = RdfUtilities.getPageSetByPath(portalDescription,
				pageSetName);
		log.info("REQUEST FOR PAGESET " + pageSet.getURI());

		// Initialize and set stateManager and componentManager
		// Inject stateManager into componentManager
		ComponentStateManager stateManager = new URLStateManager();
		stateManager.setServletPath(request.getServletPath().substring(1));
		ComponentManager componentManager = (ComponentManager) serviceFactory
				.getService(ServiceName.ComponentManager);
		componentManager.setStateManager(stateManager);

		// Create the rootComponent and all the other standard components;
		// (they are implicitly registered with the ComponentManager)
		Resource rRootComponent = pageSet.getRequiredProperty(
				MARS.rootComponent).getResource();
		ModelAndViewComponent rootComponent = (ModelAndViewComponent) componentManager
				.getComponent(rRootComponent, true);

		log.info("root viewName is " + rootComponent.getViewName());

		ViewResolver viewResolver = (ViewResolver) serviceFactory
				.getService(ServiceName.ViewResolver);
		rootComponent.addObject("_renderContext", new RenderContext(request,
				response, viewResolver));

		// rootComponent.addAllObjects(errors.getModel());
		// rootComponent.addObject("command",new UserForm());
		ModelAndView content = (ModelAndView) rootComponent.getModel().get(
				"new_content");
		content.addAllObjects(errors.getModel());
		content.addObject("command", new UserForm());

		return rootComponent;
	}

	protected ModelAndView processFormSubmission(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		// Find the relevant pageset in the static RDF config file
		Model portalDescription = (Model) serviceFactory
				.getService(ServiceName.PortalDescription);
		String pageSetName = request.getServletPath();
		Resource pageSet = RdfUtilities.getPageSetByPath(portalDescription,
				pageSetName);
		log.info("REQUEST FOR PAGESET " + pageSet.getURI());

		// Initialize and set stateManager and componentManager
		// Inject stateManager into componentManager
		ComponentStateManager stateManager = new URLStateManager();
		stateManager.setServletPath(request.getServletPath().substring(1));
		ComponentManager componentManager = (ComponentManager) serviceFactory
				.getService(ServiceName.ComponentManager);
		componentManager.setStateManager(stateManager);

		// Create the rootComponent and all the other standard components;
		// (they are implicitly registered with the ComponentManager)
		Resource rRootComponent = pageSet.getRequiredProperty(
				MARS.rootComponent).getResource();
		ModelAndViewComponent rootComponent = (ModelAndViewComponent) componentManager
				.getComponent(rRootComponent, true);

		log.info("root viewName is " + rootComponent.getViewName());

		// Load the interaction state into these components
		stateManager.reloadState(request);

		// Execute the action, if any
		stateManager.doAction(request);

		UserForm userForm = (UserForm) command;
		String success = null;
		if (!errors.hasErrors()) {
			UserService userService = (UserService) serviceFactory
					.getService(ServiceName.UserService);
			userService.addUser(userForm);
			success = "User successfully registered";
		}

		componentManager.preRender();

		// Render
		ViewResolver viewResolver = (ViewResolver) serviceFactory
				.getService(ServiceName.ViewResolver);
		rootComponent.addObject("_renderContext", new RenderContext(request,
				response, viewResolver));
		ModelAndView content = (ModelAndView) rootComponent.getModel().get(
				"new_content");
		content.addAllObjects(errors.getModel());		
		content.addObject("command", new UserForm());
		if (success != null) {
			content.addObject("success", success);
		}

		return rootComponent;
	}

	public ServiceFactory getServiceFactory() {
		return serviceFactory;
	}

	public void setServiceFactory(ServiceFactory serviceFactory) {
		this.serviceFactory = serviceFactory;
	}

}
