package lv.webkursi.klucis.event;

import java.util.EventObject;

/**
 * A custom variation on the Java's built-in event object - it remembers event
 * source and currently allows two kinds of events (execute, prepareToRender). 
 * 
 * <ul>
 * <li>"execute" event means the action should be executed on components (e.g. a 'rotate'
 * or 'translate' action on some component, which was created and initialized during the 
 * Web request).</li>
 * <li>"prepareToRender" event means that the components should do some last-minute
 * communication to enable their rendering. During this phase relative component 
 * offsets can be calculated, before the component data is sent to the Velocity Engine</li>
 * </ul>
 * 
 * More complex request lifecycle may support other kinds of events as well. 
 */
@SuppressWarnings("serial")
public class LifecycleEvent extends EventObject {
    
    public enum Kind {execute, prepareToRender};
    
    Kind kind;
    
    /**
     * @param source source of the event
     */
    protected LifecycleEvent(Object source) {
        super(source);
    }
    
    /**
     * 
     * @param source the source of the event
     * @param kind the kind of event
     */   
    public LifecycleEvent(Object source, Kind kind) {
        this(source);
        this.kind = kind;
    } 

    /**
     * @return Returns the kind.
     */
    public Kind getKind() {
        return kind;
    }
    
    /**
     * @param kind The kind to set.
     */
    public void setKind(Kind kind) {
        this.kind = kind;
    }
    
    public String toString() {
    	if (kind == Kind.execute) {
    		return "execute";
    	}
    	else if (kind == Kind.prepareToRender) {
    		return "prepareToRender";
    	}
    	else throw new RuntimeException("Unexpected value for LifecycleEvent.kind");
    }
}
