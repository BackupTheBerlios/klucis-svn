package lv.webkursi.mtest.mvc.util;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

/**
 * A component state manager manages component state from one request to the
 * the next in a given session.
 *
 */
public interface PageStateManager {
    /**
     * Set the context path associated with this component state.
     * 
     * @param path the context path.
     */
    public void setServletPath(String path);
    /**
     * Get a URL as a <code>String</code> that encode the current state
     * of the portal component structure but with the state of the
     * named component updated to a new state.
     * 
     * @param componentName the name of the component with new state
     * @param newState the new state for the named component
     * 
     * @return the URL encoding the state and the command.
     */
    public String getURL(String componentName, String[] newState);
    
    /**
     * Reload the portal components state from an Http request.
     * 
     * @param request the request containing the state representation.
     */
    public void reloadState(HttpServletRequest request);
    
    /**
     * Get the names of components for which there is state.
     * 
     * @return an iterator over the names of components for which there
     *         is state managed in this object.
     */
    public Iterator<String> getComponentNames();
    
    /**
     * Determine if this object stores state for a component.
     * 
     * @param componentName the name of the component.
     * @return <code>true</code> if this object stores state for the 
     *         named component, and <code>false</code> otherwise.
     */
    public boolean hasComponentState(String componentName);
    
    /**
     * return the stored state for a named component.
     * 
     * @param componentName the name of the component whose state is to be
     *        returned.
     *        
     * @return the state of the component.
     */
    public String[] getComponentState(String componentName);
    
    /**
     * Update the recorded state for a component.
     * 
     * @param componentName the name of the component whose state is being
     *        recorded.
     * @param state the state that is recorded for the component.
     */
    public void setComponentState(String componentName, String[] state);
}
