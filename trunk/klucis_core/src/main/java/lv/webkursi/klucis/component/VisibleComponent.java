package lv.webkursi.klucis.component;

import java.awt.geom.Point2D;

/**
 * A visible component (a.k.a. a widget) - something which can be drawn and converted to SVG
 * 
 * @author kap
 */
public interface VisibleComponent extends Component {
	/**
	 * Add a key/value pair to the map accessible by Velocity template
	 * @param key 
	 * @param value
	 */
	public void addObject(String key, Object value);

	public float getWidth();
	
	public float getHeight();

	public String render();
	
	public void setEnclosing(VisibleComponent enclosing);
	
	public Point2D.Float findOffset(VisibleComponent box);
		
	public void setViewName(String viewName);
}
