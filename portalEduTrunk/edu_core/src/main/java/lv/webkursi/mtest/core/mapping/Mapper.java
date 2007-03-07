package lv.webkursi.mtest.core.mapping;

import lv.webkursi.mtest.core.components.Component;

/**
 * A Mapper is a special kind of component - it is invisible
 * and can be used to map objects to display into components 
 * (e.g. lines on resultpane).
 */
public interface Mapper extends Component {
	
	/**
	 * returns a ModelAndView given a key: an arbitrary object
	 */
//	public ModelAndView map(Object key);

	public Component getComponent(Object object);
	
//	public Resource getComponentType(Object object);

}
