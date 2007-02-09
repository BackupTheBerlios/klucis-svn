package lv.webkursi.klucis.event;

/**
 * A manager of the request lifecycle.
 * Objects that support this interface are required to
 * implement the request lifecycle contract.
 *    
 * @author kap
 * Courtesy of Brian McBride (HP, MARS)
 */
public interface LifecycleManager {
    
    /**
     * Record that an object wishes to receive lifecyle events.
     * 
     * @param listener the object to receive the events.
     */
    public void addLifecycleEventListener(LifecycleEventListener listener);
    
    /**
     * Record that an object no longer wishes to receive lifecycle events.
     * 
     * @param listener the object that should no longer receive lifecycle events.
     */
    public void removeLifecycleEventListener(LifecycleEventListener listener);

    /**
     * Does the announce() method really belong here?
     * @param source
     * @param kind
     */
    public void announce(Object source, LifecycleEvent.Kind kind);
}
