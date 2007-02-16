package lv.webkursi.klucis.event;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple implementation of LifecycleManager: Announces all kinds of events to
 * all the listeners; it is up to the listeners to take appropriate actions or
 * ignore the events.
 * 
 * This must ensure that the events are fired in the same order, in which 
 * the components were inserted (i.e. registered with the COmponentManager).
 * A more robust approach would be to assign priorities to the components
 * (the components with bigger depth in the inclusion tree should be evaluated first). 
 */
public class LifecycleManagerImpl implements LifecycleManager {

	protected List<LifecycleEventListener> listeners = new ArrayList<LifecycleEventListener>();

	/**
	 * Adds a new listener to the set of listeners
	 */
	public void addLifecycleEventListener(LifecycleEventListener listener) {
		listeners.add(listener);
	}

	/**
	 * Removes a new listener from the set of listeners
	 */
	public void removeLifecycleEventListener(LifecycleEventListener listener) {
		listeners.remove(listener);
	}

	/**
	 * Announce to all listeners that a lifecycle event has occurred.
	 * 
	 * @param source
	 *            the source of the event.
	 * @param kind
	 *            the kind of event
	 */
	public void announce(Object source, LifecycleEvent.Kind kind) {
		LifecycleEvent event = new LifecycleEvent(source, kind);
		for (int i = 0; i < listeners.size(); i++) {
			listeners.get(i).lifecycleEvent(event);
		}
	}
}
