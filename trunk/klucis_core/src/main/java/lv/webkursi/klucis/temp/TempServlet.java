package lv.webkursi.klucis.temp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class TempServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		out.println("<dl>");

		String[][] variables = {
				{ "request.getAuthType()", request.getAuthType() },
				{ "request.getContentLength()", "" + request.getContentLength() },
				{ "request.getContentType()", request.getContentType() },
				{ "getServletContext().getRealPath(\"/\")",
						getServletContext().getRealPath("/") },
				{ "request.getPathInfo()", request.getPathInfo() },
				{ "request.getPathTranslated()", request.getPathTranslated() },
				{ "request.getQueryString()", request.getQueryString() },
				{ "request.getRemoteAddr()", request.getRemoteAddr() },
				{ "request.getRemoteHost()", request.getRemoteHost() },
				{ "request.getRemoteUser()", request.getRemoteUser() },
				{ "request.getMethod()", request.getMethod() },
				{ "request.getServletPath()", request.getServletPath() },
				{ "request.getServerName()", request.getServerName() },
				{ "request.getServerPort()", "" + request.getServerPort() },
				{ "request.getProtocol()", request.getProtocol() },
				{ "getServletContext().getServerInfo()",
						getServletContext().getServerInfo() },
				{ "request.getRequestURI()", request.getRequestURI() },
				{ "request.getHeader(\"User-Agent\")",
						request.getHeader("User-Agent") }

		};

		for (int i = 0; i < variables.length; i++) {
			String varName = variables[i][0];
			String varValue = variables[i][1];
			if (varValue == null)
				varValue = "<i>Not specified</i>";
			out.println("<dt>" + varName + "</dt><dd>" + varValue + "</dd>");
		}
		out.println("</dl>");
	}
}
