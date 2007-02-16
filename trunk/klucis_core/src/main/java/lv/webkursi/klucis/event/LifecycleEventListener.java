package lv.webkursi.klucis.event;

import java.util.EventListener;

/**
 * Interface for all those components, which are affected by the events. 
 */
public interface LifecycleEventListener extends EventListener {
    
    public void lifecycleEvent(LifecycleEvent event);  
    
    /*
     * TODO kap: priorities could be used in the queue for notification
     * to make sure for more robust initialization; currently 
     * it relies on order of adding those listeners. 
    public void setPriority(int priority);
    
    public int getPriority();
    */
}
