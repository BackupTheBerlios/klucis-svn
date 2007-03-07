package lv.webkursi.mtest.core.scopes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Could be used to store some request-scoped objects, which are needed in various places.
 * This is a request-scoped bean and available through the Spring dependency injection
 * mechanism. So far there is no use for it. HttpServletRequest and Response could potentially 
 * be useful objects in the request context.
 * 
 */
public class RequestHolder {
	private HttpServletRequest request;

	private HttpServletResponse response;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
}
