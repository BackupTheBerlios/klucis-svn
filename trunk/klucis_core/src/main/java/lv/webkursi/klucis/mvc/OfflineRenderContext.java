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

	@SuppressWarnings("unchecked")
	public String render(VisibleComponent comp) throws Exception {
		return comp.render();
	}
}
