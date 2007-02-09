package lv.webkursi.klucis.event;

import java.util.EventListener;

/**
 * @author kap
 * 
 * Courtesy of Brian McBride (HP, MARS)
 */
public interface LifecycleEventListener extends EventListener {
    
    void lifecycleEvent(LifecycleEvent event);  
}
