package lv.webkursi.mtest.core.mvc;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import lv.webkursi.mtest.core.components.Component;
import lv.webkursi.mtest.core.components.factories.ComponentManager;

import org.apache.log4j.Logger;


/**
 * 
 */
public class URLStateManager implements ComponentStateManager {

	protected Logger logger = Logger.getLogger(getClass());

	protected String servletPath;

	protected Map<String, String> states = new HashMap<String, String>();

	protected Map<String, String[]> parameterMap;

	protected boolean actionDone;
	
	ComponentManager componentManager;

	public void setServletPath(String servletPath) {
		this.servletPath = servletPath;
	}

	// HttpServletRequest doesn't use generics
	@SuppressWarnings("unchecked")
	public void reloadState(HttpServletRequest request) {

		parameterMap = request.getParameterMap();
		for (String key : parameterMap.keySet()) {
			Component component = componentManager.lookup(key);
			if (component != null) {
				String parameter = parameterMap.get(key)[0];
				states.put(key, parameter);
				component.setStateFromString(parameter);
			}
			else {
				// Just ignore - probably these are "_ac" and "_action" parameters
			}
		}
	}
	
	public String getLink(Component component, String action, String text, String pageSetPath) {
		String url = pageSetPath + "?";
		Iterator<String> iter = componentManager.getComponentNameIterator();
		String sep = "";
		String actionComponentName = null;
		while (iter.hasNext()) {
			String cName = iter.next();
			if (cName.equals(component.getName())) {
				actionComponentName = cName;
			}
			String state = states.get(cName);
			if (state != null) {
			url = url + sep + encodeAsUtf(cName) + "="
					+ encodeAsUtf(state);
			sep = "&amp;";
			}
		}

		// now add the action part
		url = url + sep + "_ac=" + actionComponentName;
		url = url + "&amp;_action=" + encodeAsUtf(action);
		return "<a href=\"" + url + "\">" + text + "</a>";		
	}

	public String getLink(Component component, String action, String text) {
		return getLink( component,  action,  text, servletPath);
	}
	
	private String encodeAsUtf(String arg) {
		if (arg == null) {
			logger.info("Argument to encode should not be null");
			return "null";
		}
		try {
			String result = URLEncoder.encode(arg, "UTF-8");
			return result;
		}
		catch (Exception e) {
			logger.info("Unexpected: Bad encoding UTF-8");
		}
		return "error";
	}

	public boolean hasComponentState(Component component) {
		return states.containsKey(component.getName());
	}

	public String getComponentState(Component component) {
		return decode(states.get(component.getName()));
	}

	/**
	 * Can ignore, since all components have their labels assigned by the ComponentManager
	 */
	public void registerComponent(Component component) {
		if (component.getStateAsString() != null) {
			states.put(component.getName(), component.getStateAsString());
		}
	}

	public void setComponentState(Component component, String state) {
		registerComponent(component);
		states.put(component.getName(), encode(state));
	}

	public void doAction(HttpServletRequest request) {
		if (actionDone) {
			return; // @@ bodge alert
		}
		String componentName = request.getParameter("_ac");
		String action = request.getParameter("_action");
		Component component = componentManager.lookup(componentName);
		if (component != null) {
			component.doAction(action);
			actionDone = true;
		}
	}

	/**
	 * Could be used for obfuscating values? 
	 * @param state
	 * @return
	 */
	protected String encode(String state) {
		return state; // @@TODO
	}

	/**
	 * Could be used for obfuscating values? 
	 * @param state
	 * @return
	 */
	protected String decode(String encoding) {
		return encoding; // @@TODO
	}

	public void setComponentManager(ComponentManager componentManager) {
		this.componentManager = componentManager;
	}

	public ComponentManager getComponentManager() {
		return componentManager;
	}
}