package lv.webkursi.klucis.component.geom2d;

import lv.webkursi.klucis.component.AbstractVisibleComponent;
import lv.webkursi.klucis.event.LifecycleEvent;
import lv.webkursi.klucis.event.LifecycleEventListener;

/**
 * A class to manipulate vector graphic paths; can be used to populate svg:path. 
 * 
 * 
 * @author kap
 */
public class Path extends AbstractVisibleComponent implements LifecycleEventListener {

	protected String color;

	protected String path;

	protected float strokeWidth;
	
	protected float width;
	
	protected float height;

	public void setColor(String color) {
		this.color = color;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setStrokeWidth(float strokeWidth) {
		this.strokeWidth = strokeWidth;
	}

	public float getHeight() {
		return height;
	}

	public float getWidth() {
		return width;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public void lifecycleEvent(LifecycleEvent event) {
		if (event.getKind().equals(LifecycleEvent.Kind.prepareToRender)) {
			this.addObject("_id", id);
			this.addObject("_color", color);
			this.addObject("_path", path);
			this.addObject("_strokeWidth", strokeWidth);			
		}
	}	
}
