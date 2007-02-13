package lv.webkursi.klucis.mvc;

import lv.webkursi.klucis.component.VisibleComponent;

import org.apache.log4j.Logger;

/**
 * Helper object to display composite trees of ModelAndView-s
 * 
 * @author kap
 */
public class OfflineRenderContext {

	protected final Logger log = Logger.getLogger(getClass());

//	private ViewResolver viewResolver;

//	protected ComponentManager componentManager;

	/*
	public RenderContextOffline(ComponentManager componentManager,
			ViewResolver viewResolver) {
		if (viewResolver == null) {
			log.warn("Unexpected: viewResolver should not be null");
		}
		this.componentManager = componentManager;
		this.viewResolver = viewResolver;
	}
	*/

	@SuppressWarnings("unchecked")
	public String render(VisibleComponent comp) throws Exception {
		return comp.render();
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
	/*
	public String getLinkToPageSet(String pageSetId, String linkObject,
			List<String> parameters) {
		return getLinkToPageSet(pageSetId, linkObject, parameters, null, null);
	}
	*/

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
	/*
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

	public ViewResolver getViewResolver() {
		return viewResolver;
	}
	*/
}
