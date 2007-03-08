package lv.webkursi.mtest.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SuppressWarnings("serial")
/**
 * Just a sample servlet class to check how compilation 
 * is done within 
 */
public class MyServlet extends HttpServlet {

	private Log logger = LogFactory.getLog(MyServlet.class);

	@Override
	public void init() {
		logger.info("Initializing servlet");
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		System.out.println("TestServlet in action");
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		out.println("This is servlet with name 'TestServlet'");
		out.println("Its class: lv.webkursi.training.web.TestServlet");
		out.println("Its URL  : /servlet/TestServlet");
		out.println("It is sending log messages only to the ROLLING appender");
		logger.trace("test only");
		logger.debug("test only");
		logger.info("test only");
		logger.warn("test only");
		logger.error("test only");
		logger.fatal("test only");		
	}
}
