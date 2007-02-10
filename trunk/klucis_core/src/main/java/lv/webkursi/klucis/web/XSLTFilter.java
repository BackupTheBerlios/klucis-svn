package lv.webkursi.klucis.web;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XSLTFilter implements Filter {
	private FilterConfig filterConfig = null;

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.err.println("Running XSLT Filter!!!");
		String contentType = "text/html";
		String styleSheet = "/xslt/html.xsl";

		// String type = null;

		// request.getParameter("type");
		/*
		 * String pathInfo = ((HttpServletRequest) request).getPathInfo(); int
		 * lastIndex = pathInfo.lastIndexOf("."); if (lastIndex > 0) { type =
		 * pathInfo.substring(lastIndex + 1); } System.err.println("Extension is = '" +
		 * type + "'"); if (type == null || type.equals("html")) { contentType =
		 * "text/html"; styleSheet = "/xslt/html.xsl"; }
		 */

		response.setContentType(contentType);
		String stylePath = filterConfig.getServletContext().getRealPath(
				styleSheet);
		Source styleSource = new StreamSource(stylePath);
		PrintWriter out = response.getWriter();
		CharResponseWrapper wrapper = new CharResponseWrapper(
				(HttpServletResponse) response);
		chain.doFilter(request, wrapper);
		StringReader sr = new StringReader(wrapper.toString());
		Source xmlSource = new StreamSource((Reader) sr);
		try {
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory
					.newTransformer(styleSource);
			CharArrayWriter caw = new CharArrayWriter();
			StreamResult result = new StreamResult(caw);
			transformer.transform(xmlSource, result);
			response.setContentLength(caw.toString().length());
			out.write(caw.toString());
		} catch (Exception ex) {
			out.println(ex.toString());
			out.write(wrapper.toString());
		}
	}

	public void init(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	public void destroy() {
		this.filterConfig = null;
	}
}
