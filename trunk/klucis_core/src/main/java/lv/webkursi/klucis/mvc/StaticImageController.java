package lv.webkursi.klucis.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lv.webkursi.klucis.component.ComponentManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.mvc.Controller;

/**
 * A simple Spring's controller, which returns a static Velocity template; 
 * actions and additional parameters are not supported
 * 
 * @author kap
 */
public class StaticImageController implements Controller {
	
	private static Log log = LogFactory.getLog(StaticImageController.class);
	
	private ComponentManager componentManager = null;

	private ViewResolver viewResolver;


	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String path = request.getPathInfo().substring(1);
		int extensionPosition = path.lastIndexOf(".");
		if (extensionPosition > 0) {
			path = path.substring(0,extensionPosition);
		}
		int filenamePosition = path.lastIndexOf("/");
		if (filenamePosition >= 0) {
			path = path.substring(filenamePosition+1);
		}
		
		log.info("Trying to find a template '" + path + "'");
		ModelAndView result = new ModelAndView(path);
		result.addObject("_renderContext", new RenderContext(componentManager,
				viewResolver));
		return result;

	}


	public ComponentManager getComponentManager() {
		return componentManager;
	}


	public void setComponentManager(ComponentManager componentManager) {
		this.componentManager = componentManager;
	}


	public ViewResolver getViewResolver() {
		return viewResolver;
	}


	public void setViewResolver(ViewResolver viewResolver) {
		this.viewResolver = viewResolver;
	}
	
	
}
