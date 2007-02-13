package lv.webkursi.klucis.component;

/**
 * Common supertype for all things managed by ComponentManager
 * 
 * @author kap
 */
public interface Component {
    /**
     * Set the manager that manages this component.
     * 
     * @param componentManager the manager of this component.
     */    
    public void setComponentManager(ComponentManager componentManager);
	
	public void setId(String id);
	
	public String getId();
	
    /**
     * Perform an action
     * 
     * @param action the action to be performed
     */
    public  void doAction(String action);
    
    /**
     * Set interaction state
     * 
     * @param state the interaction state to be set
     */
    public void setInteractionState(String state);

}
