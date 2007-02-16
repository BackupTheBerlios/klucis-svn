package lv.webkursi.klucis.component.control;

import java.util.HashMap;
import java.util.Map;

import lv.webkursi.klucis.component.AbstractVisibleComponent;
import lv.webkursi.klucis.component.VisibleComponent;

/**
 * SimpleComposite
 *
 */
public class CompositeWidget extends AbstractVisibleComponent {
	Map<String,VisibleComponent> components = new HashMap<String,VisibleComponent>();
	
	protected float height;
	
	protected float width;
	
	public CompositeWidget() {		
	}

	public Map<String, VisibleComponent> getWidgets() {
		return components;
	}

	public void setWidgets(Map<String, VisibleComponent> widgets) {
		this.components = widgets;
	}
	
	public void addWidget(String key, VisibleComponent component) {
		components.put(key, component);
	}
	
	

	public void setHeight(float height) {
		this.height = height;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	/**
	 * This is not used, if it is HTML; otherwise 
	 * it is set explicitly rather than computed from its children
	 */
	public float getHeight() {
		return height;
	}

	/**
	 * This is not used, if it is HTML; otherwise 
	 * it is set explicitly
	 */
	public float getWidth() {
		return width;
	}
}
