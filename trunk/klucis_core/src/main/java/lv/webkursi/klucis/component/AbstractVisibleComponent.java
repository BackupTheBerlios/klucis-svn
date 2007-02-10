package lv.webkursi.klucis.component;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

/**
 * Common implementations of VisibleComponent interface methods
 * 
 * @author kap
 */
public abstract class AbstractVisibleComponent extends AbstractComponent implements VisibleComponent {
	
	protected Map<String, Object> model = new HashMap<String, Object>();

	protected String viewName;
	
	protected VisibleComponent enclosing;

	public void setViewName(String viewName) {
		this.viewName = viewName;		
	}
	
	public void addObject(String key, Object value) {
		model.put(key, value);
	}
	
	public void setEnclosing(VisibleComponent enclosing) {
		this.enclosing = enclosing;
	}

	/**
	 * By default, child component's offsets do not change compared to their
	 * parent (i.e. components are layered on top of each other). If offsets do
	 * change, this method should be overriden
	 */
	public Point2D.Float findOffset(VisibleComponent box) {
		return new Point2D.Float(0.0F, 0.0F);
	}
}
