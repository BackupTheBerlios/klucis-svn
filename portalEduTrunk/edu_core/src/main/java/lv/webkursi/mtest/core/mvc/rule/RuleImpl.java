package lv.webkursi.mtest.core.mvc.rule;

import lv.webkursi.mtest.core.components.Component;
import lv.webkursi.mtest.core.mvc.ComponentStateManager;



/**
 *
 */
public class RuleImpl implements Rule, Component {
    
    private Predicate predicate;
    private Action action;
    
    private ComponentStateManager stateManager;
    private String name;
    
    public void run() {
        if (predicate.isTrue()) {
            action.doAction();
        }        
    }

    /**
     * @param action The action to set.
     */
    public void setAction(Action action) {
        this.action = action;
    }
    
    public Action getAction() {
        return action;
    }

    /**
     * @param predicate The predicate to set.
     */
    public void setPredicate(Predicate predicate) {
        this.predicate = predicate;
    }
    
    public Predicate getPredicate() {
        return predicate;
    }

    /**
     * This is not needed for rules; rules themselves do not have actions.
     */
	public void doAction(String action) {
	}

	public String getName() {
		return name;
	}

	public String getStateAsString() {		
		return null;
	}

	public ComponentStateManager getStateManager() {
		return null;
	}

	public void preRender() {
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStateFromString(String state) {
	}

	public void setStateManager(ComponentStateManager stateManager) {
		this.stateManager = stateManager;
	}
}
