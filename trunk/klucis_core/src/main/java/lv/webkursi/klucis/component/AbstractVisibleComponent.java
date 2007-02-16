package lv.webkursi.klucis.component;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

import lv.webkursi.klucis.mvc.VelocityMerge;

/**
 * Common implementations of VisibleComponent interface methods
 * 
 * @author kap
 */
public abstract class AbstractVisibleComponent extends AbstractComponent implements VisibleComponent {
	
	protected Map<String, Object> map = new HashMap<String, Object>();

	protected String viewName;
	
	protected VisibleComponent enclosing;
	
	protected int priority;
	
	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;		
	}
	
	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public void addObject(String key, Object value) {
		map.put(key, value);
	}
	
	public Map<String,Object> getMap() {
		return map;
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
	
	public String render() {
		VelocityMerge view = new VelocityMerge();
		view.setTemplateName(viewName);
		view.setContextParams(map);		
		return view.render();		
	}
}
