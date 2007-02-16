package lv.webkursi.klucis.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lv.webkursi.klucis.KlucisAware;
import lv.webkursi.klucis.KlucisConfigurationException;
import lv.webkursi.klucis.component.ComponentManager;
import lv.webkursi.klucis.component.VisibleComponent;
import lv.webkursi.klucis.event.LifecycleEvent;
import lv.webkursi.klucis.vocabulary.KLUCIS;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.mvc.Controller;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.StmtIterator;

/**
 * The main controller serving dynamic images configured as klucis 
 * components.   
 * 
 * <p>
 * <b>Contract</b>
 * </p>
 * 
 * <ol>
 * <li>The controller constructs a <code>ComponentManager</code>
 * (scope=request) and initializes it anew for each request.</li>
 * <li>The controller extracts the servlet path name from the request URL and
 * uses it to look up a description of the image in the KLUCIS config file. can
 * retrieve from the injected portal object.</li>
 * <li>If the description does not exist, the controller returns a hard-coded
 * "404 image". If an exception occurs, the controller returns a "500 image".</li>
 * <li>The controller will construct the components defined in the description
 * and execute the request lifecycle.</li>
 * <li>On completion of the request lifecycle, the controller will return a
 * VisibleComponent for rendering through the regular Spring view resolving
 * mechanism.</li>
 * </ol>
 */
public class MainController implements Controller, KlucisAware {

	private ComponentManager componentManager = null;

	private Model model;

	private final Log log = LogFactory.getLog(MainController.class);

	private ViewResolver viewResolver;

	public void setViewResolver(ViewResolver viewResolver) {
		this.viewResolver = viewResolver;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String imageName = getImageName(request);
		// TODO: set componentManager bean scope to "request".

		componentManager.setServletRequest(request);
		componentManager.setServletResponse(response);
		VisibleComponent widget = null;
		// try {
		imageName = getImageName(request);
		Resource imageDescription = lookupImageDescription(imageName);
		widget = (VisibleComponent) componentManager
				.getStaticComponent(imageDescription);
		componentManager.doAction();
		doLifecycle(widget);
		// TODO kap:
		// } catch (Exception exception) {
		// widget = getExceptionWidget();
		// }

		log.info(" Before returning widget with viewName = '"
				+ widget.getViewName() + "'");
		ModelAndView result = new ModelAndView(widget.getViewName());
		result.addAllObjects(widget.getMap());				
		result.addObject("_renderContext", new RenderContext(componentManager,
				viewResolver));
		return result;
	}

	protected String getImageName(HttpServletRequest request) {
		String imageName = request.getPathInfo();
		int slashPos = imageName.lastIndexOf("/");
		if (slashPos >= 0 ) {
			imageName = imageName.substring(slashPos+1);
		}
		log.debug("  imageName =  '" + imageName + "'");
		return imageName;
	}

	protected Resource lookupImageDescription(String imageName) {
		StmtIterator iter = model.listStatements(null, KLUCIS.hasImageName,
				imageName);
		Resource pageSetDescription = null;
		while (iter.hasNext()) {
			Resource r = iter.nextStatement().getSubject();
			if (pageSetDescription == null) {
				pageSetDescription = r;
			} else {
				String message = "More than one image description for name '"
						+ imageName + "'";
				log.error(message);
				throw new KlucisConfigurationException(message);
			}
		}
		return pageSetDescription;
	}

	protected void doLifecycle(VisibleComponent rootWidget) {
		componentManager.announce(this, LifecycleEvent.Kind.execute);
		componentManager.announce(this, LifecycleEvent.Kind.prepareToRender);
	}

	public ComponentManager getComponentManager() {
		return componentManager;
	}

	public Model getModel() {
		return model;
	}

	public void setComponentManager(ComponentManager componentManager) {
		this.componentManager = componentManager;
	}

	public void setModel(Model model) {
		this.model = model;
	}
}
