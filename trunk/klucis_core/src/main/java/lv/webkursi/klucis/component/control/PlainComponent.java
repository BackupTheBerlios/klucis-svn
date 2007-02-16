package lv.webkursi.klucis.component.control;

import lv.webkursi.klucis.component.AbstractVisibleComponent;
import lv.webkursi.klucis.event.LifecycleEvent;
import lv.webkursi.klucis.event.LifecycleEventListener;

/**
 * Component which displays some static template
 * 
 * @author kap
 */
public class PlainComponent extends AbstractVisibleComponent implements
		LifecycleEventListener {
	
	protected float height;
	
	protected float width;

	public void lifecycleEvent(LifecycleEvent event) {
		// so far no event processing
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}
}
