package lv.webkursi.klucis.component;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

/**
 * This is a top class for all components
 * 
 * @author kap
 */
public abstract class AbstractComponent implements Component {
	protected Map<String, Object> model = new HashMap<String, Object>();

	protected ComponentManager componentManager;

	protected Component parent;

	public void setComponentManager(ComponentManager componentManager) {
		this.componentManager = componentManager;
	}

	public void setParent(Component parent) {
		this.parent = parent;
	}

	protected String id;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void addObject(String key, Object value) {
		model.put(key, value);
	}

	/**
	 * By default, child component's offsets do not change compared to their
	 * parent (i.e. components are layered on top of each other). If offsets do
	 * change, this method should be overriden
	 */
	public Point2D.Float findOffset(Component box) {
		return new Point2D.Float(0.0F, 0.0F);
	}
}
