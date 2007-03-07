package lv.webkursi.mtest.core.mvc;

import javax.servlet.http.HttpServletRequest;

import lv.webkursi.mtest.core.components.Component;
import lv.webkursi.mtest.core.components.factories.ComponentManager;


/**
 * A component state manager manages component state from one request to the the
 * next in a given interaction.
 */
public interface ComponentStateManager {
	/**
	 * Set the context path associated with this component state.
	 * 
	 * @param path
	 *            the context path.
	 */
	public void setServletPath(String path);

	/**
	 * Get a link as a <code>String</code> that can be embedded in HTML and
	 * encodes the current state of the portal component structure and with an
	 * action on the <code>component</code>
	 * 
	 * @param component
	 *            the the component to perform the action
	 * @param action
	 *            a string encoding the action to be performed
	 * @param label
	 *            the label for the link
	 * 
	 * @return some http to represent the link.
	 */
	public String getLink(Component component, String action, String label);

	public String getLink(Component component, String action, String text,
			String pageSetPath);

	/**
	 * Reload the portal components state from an Http request.
	 * 
	 * @param request
	 *            the request containing the state representation.
	 */
	public void reloadState(HttpServletRequest request);

	/**
	 * Determine if this object stores state for a component.
	 * 
	 * @param component
	 *            the name of the component.
	 * @return <code>true</code> if this object stores state for the
	 *         component, and <code>false</code> otherwise.
	 */
	public boolean hasComponentState(Component component);

	public void registerComponent(Component component);

	/**
	 * return the stored state for a named component.
	 * 
	 * @param componentName
	 *            the name of the component whose state is to be returned.
	 * 
	 * @return the state of the component.
	 */
	public String getComponentState(Component component);

	/**
	 * Update the recorded state for a component.
	 * 
	 * @param componentName
	 *            the name of the component whose state is being recorded.
	 * @param state
	 *            the state that is recorded for the component.
	 */
	public void setComponentState(Component component, String state);

	/**
	 * Perform action associated with a request.
	 * 
	 * <p>
	 * There may be no action, in which case do nothing.
	 * </p>
	 * 
	 * @param request
	 *            requestion action to be performed.
	 */
	public void doAction(HttpServletRequest request);

	public void setComponentManager(ComponentManager manager);

	public ComponentManager getComponentManager();
}
