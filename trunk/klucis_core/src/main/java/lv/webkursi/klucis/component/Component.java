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
}
