package lv.webkursi.klucis.event;

import java.util.EventListener;

/**
 * Interface for all those components, which are affected by the events. 
 */
public interface LifecycleEventListener extends EventListener {
    
    void lifecycleEvent(LifecycleEvent event);  
}
