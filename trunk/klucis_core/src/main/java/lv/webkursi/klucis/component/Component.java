package lv.webkursi.klucis.component;

import java.awt.geom.Point2D;




/**
 * Common supertype for all drawable things; a component may contain other
 * components
 * 
 * @author kap
 */
public interface Component {
    /**
     * Set the manager that manages this component.
     * 
     * @param componentManager the manager of this component.
     */    
    public void setComponentManager(ComponentManager componentManager);
	
	/**
	 * Add a key/value pair to the map accessible by Velocity template
	 * @param key 
	 * @param value
	 */
	public void addObject(String key, Object value);

	public float getWidth();
	
	public float getHeight();

	public String render();
	
	public void setId(String id);
	
	public String getId();
	
	public void setParent(Component parent);

	public Point2D.Float findOffset(Component box);
}
