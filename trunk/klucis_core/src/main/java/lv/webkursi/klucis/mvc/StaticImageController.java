package lv.webkursi.klucis.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * A simple Spring's controller, which returns a static Velocity template; 
 * actions and additional parameters are not supported
 * 
 * @author kap
 */
public class StaticImageController implements Controller {
	
	private static Log log = LogFactory.getLog(StaticImageController.class);

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String path = request.getServletPath().substring(1);
		log.info("Trying to find a template '" + path + "'");
		return new ModelAndView(path);
	}
}
