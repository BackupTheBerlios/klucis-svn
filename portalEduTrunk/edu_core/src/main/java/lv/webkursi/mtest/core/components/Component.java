package lv.webkursi.mtest.core.components;

import lv.webkursi.mtest.core.mvc.ComponentStateManager;

/**
 * Component is something that's handled by the ComponentManager. 
 * In case of portalCore, the web layer is built from components.
 */
public interface Component {
	/**
	 * Each component has a unique name
	 * @return the string name stored within the component (should coincide with binding
	 * name inside the ComponentManager
	 */
    public String getName();

    /**
     * @@REVIEW: Added by kap
     * @return
     */    
    public void setName(String name);
    
    /**
     * A convenience method, we're not using PropertyEditors yet.
     * @param state
     */
    public void setStateFromString(String state);
    
    /**
     * TODO Perhaps should return array of strings?
     * @return
     */
    public String getStateAsString();
    
    /**
     * Component registers itself with the stateManager as well
     * (could be done during the registration process with the ComponentManager)
     * @param stateManager
     */
    public void setStateManager(ComponentStateManager stateManager);
    
    /**
     * @@REVIEW: Added by kap
     * @return
     */
    public ComponentStateManager getStateManager();
    
    /**
     * Components may support actions with string parameters
     * @param action
     */
    public void doAction(String action);
    
    
    public void preRender();
}
