package lv.webkursi.klucis.event;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Simple implementation of LifecycleManager: Announces all kinds of events to
 * all the listeners; it is up to the listeners to take appropriate actions or
 * ignore the events.
 * 
 * @author kap 
 * Courtesy of Brian McBride (HP, MARS)
 */
public class LifecycleManagerImpl implements LifecycleManager {

	protected Set<LifecycleEventListener> listeners = new HashSet<LifecycleEventListener>();

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
		Iterator<LifecycleEventListener> iter = listeners.iterator();
		while (iter.hasNext()) {
			iter.next().lifecycleEvent(event);
		}
	}
}
