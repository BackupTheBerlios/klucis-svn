package lv.webkursi.mtest.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class FixedController implements Controller {


	private Log log = LogFactory.getLog(FixedController.class);

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String pageSetName = request.getServletPath();
		log.info("Processing pageSet " + pageSetName);
		return new ModelAndView("welcome");
	}
}
