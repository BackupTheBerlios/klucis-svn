/**
 * Copyright 2006 Hewlett-Packard Development Company, LP
 */
package lv.webkursi.klucis.mvc;

import java.util.List;
import java.util.Map;

import lv.webkursi.klucis.component.ComponentManager;
import lv.webkursi.klucis.component.VisibleComponent;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.support.RequestContext;

/**
 * Helper object to display composite trees of ModelAndView-s
 * 
 * @author kap
 */
public class RenderContext {

	protected final Logger log = Logger.getLogger(getClass());

	private ViewResolver viewResolver;

	protected ComponentManager componentManager;

	public RenderContext(ComponentManager componentManager,
			ViewResolver viewResolver) {
		if (viewResolver == null) {
			log.warn("Unexpected: viewResolver should not be null");
		}
		this.componentManager = componentManager;
		this.viewResolver = viewResolver;
	}

	@SuppressWarnings("unchecked")
	public void render(VisibleComponent comp) throws Exception {
		// view has to be initialized explicitly, since this does not
		// go through the normal Spring MVC rendering loop
		RequestContext requestContext = new RequestContext(componentManager
				.getServletRequest());
		String viewName = comp.getViewName();
		Map<String, Object> map = comp.getMap();
		map.put("_renderContext", this);
		View view = viewResolver.resolveViewName(viewName, requestContext
				.getLocale());
		view.render(map, componentManager.getServletRequest(), componentManager
				.getServletResponse());
	}

	/**
	 * Create a link to a pageSet with some specific parameters.
	 * 
	 * @param pageSetId
	 *            the id of the pageSet to link to.
	 * @param linkObject
	 *            the html around which the link tag will be created - this can
	 *            be plain text or html, e.g. an <code>img</code> link.
	 * @param parameters
	 *            the parameters to the pageSet. This is a list containing an
	 *            even number of strings. The 2nth string is a parameter name
	 *            and the (2n+1)th string is its value.
	 * @return a link tag in HTML.
	 */
	public String getLinkToPageSet(String pageSetId, String linkObject,
			List<String> parameters) {
		return getLinkToPageSet(pageSetId, linkObject, parameters, null, null);
	}

	/**
	 * Create a link to a pageSet with some specific parameters and an action.
	 * 
	 * @param pageSetId
	 *            the id of the pageSet to link to.
	 * @param linkObject
	 *            the html around which the link tag will be created - this can
	 *            be plain text or html, e.g. an <code>img</code> link.
	 * @param parameters
	 *            the parameters to the pageSet. This is a list containing an
	 *            even number of strings. The 2nth string is a parameter name
	 *            and the (2n+1)th string is its value.
	 * @param actionComponent
	 *            the component on which an action is to be performed
	 * @param action
	 *            the action to be performed
	 * @return a link tag in HTML.
	 */
	public String getLinkToPageSet(String pageSetId, String linkObject,
			List<String> parameters, String actionComponent, String action) {
		return componentManager.getLinkToPageSet(pageSetId, linkObject,
				parameters, actionComponent, action);
	}

	public String getUrlToPageSet(String pageSetId, List<String> parameters,
			String actionComponent, String action) {
		return componentManager.getUrlToPageSet(pageSetId, parameters,
				actionComponent, action);
	}
	
	public String getBrowserName() {
		String result = null;
		String userAgent = componentManager.getServletRequest().getHeader("User-Agent");
		System.err.println("userAgent = " + userAgent);
		if (userAgent.indexOf("MSIE") != -1) {
			result = "MSIE";
		}
		else if (userAgent.indexOf("Firefox") != -1) {
			result = "Firefox";
		}
		System.err.println("browserName = " + result);
		return result;
	}
	
	public boolean isFirefox() {
		return componentManager.getServletRequest().getHeader("User-Agent").contains("Firefox");
	}

	public ViewResolver getViewResolver() {
		return viewResolver;
	}
}
