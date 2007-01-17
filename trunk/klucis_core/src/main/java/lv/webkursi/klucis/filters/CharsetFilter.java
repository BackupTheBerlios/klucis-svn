package lv.webkursi.klucis.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @web.filter name="CharsetFilter"
 * 
 * @web.filter-mapping url-pattern="/*"
 */
public class CharsetFilter implements Filter {
	private Log logger = LogFactory.getLog(getClass());

	public void init(FilterConfig config) throws ServletException {
		logger.info("Starting CharsetFilter.");
	}

	public void destroy() {
		logger.info("Destroying CharsetFilter.");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (request instanceof HttpServletRequest
				&& ((HttpServletRequest) request).getMethod().equals("GET")) {
			request.setCharacterEncoding("UTF-8");
			logger.info("Change encoding to UTF-8");
			chain.doFilter(new CharsetRequestWrapper(
					(HttpServletRequest) request), response);			
		} else {
			// leave POST requests unchanged
			request.setCharacterEncoding("UTF-8");
			chain.doFilter(request, response);
		}
		((HttpServletResponse)response).setHeader("expires", "-1"); 
	}
}