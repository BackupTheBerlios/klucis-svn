package lv.webkursi.klucis.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * A simple Spring's controller, which returns just one configured image view
 * configured with all the right properties; actions and additional parameters
 * are not supported
 * 
 * @author kap
 */
public class ImageController implements Controller {

	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		return new ModelAndView("bilde");
	}
}
