package lv.webkursi.klucis.component.geom2d;

import lv.webkursi.klucis.component.AbstractComponent;
import lv.webkursi.klucis.component.Component;
import lv.webkursi.klucis.mvc.VelocityMerge;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Component which is ready to be output as an SVG image; it manages  
 * all the global SVG parameters
 * @author kap
 */
public class TopComponent extends AbstractComponent {
	
	/**
	 * TODO should come from config
	 * Local horizontal coordinate unit size in mm
	 */
	protected float unitSizeX = 0.1F;
	
	/**
	 * TODO should come from config
	 * Local vertical coordinate unit size in mm
	 */
	protected float unitSizeY = 0.1F;
	
	/**
	 * TODO should come from config
	 * Each integer box is 200x200 in local coordinates - a coordinate
	 * square of [-100,100] by [-100,100].
	 */
	protected float localUnits = 200F;
	
	// TODO 
	protected float x0 = 0.0F;
	
	// TODO 
	protected float y0 = 0.0F;

	
	protected String fileName;
	
	protected Component content;
	
	private Log log = LogFactory.getLog(TopComponent.class);

	public void setContent(Component content) {
		this.content = content;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void output() {
		VelocityMerge view = new VelocityMerge();
		view.setTemplateName("TopComponent");
		addObject("_content", content);
		float width = getWidth();
		float height = getHeight();
		
		// surround all pictures with 5 unit padding
		addObject("_sizeX", (width+10)*unitSizeX);
		addObject("_sizeY", (height+10)*unitSizeY);
		addObject("_width", width+10);
		addObject("_height", height+10);
		addObject("_x0", x0-5);
		addObject("_y0", y0-5);
		view.setContextParams(model);
		log.info("Writing file '" + fileName + "'");
		view.write(fileName);
	}

	// TODO this is never called!
	public String render() {
		/*
		VelocityView view = new VelocityView();
		view.setTemplateName("TopComponent");
		addObject("_content", content);
		view.setContextParams(model);
		return view.render();
		*/
		return null;
	}

	public float getY0() {
		return y0;
	}

	public void setY0(float y0) {
		this.y0 = y0;
	}

	public float getX0() {
		return x0;
	}

	public void setX0(float x0) {
		this.x0 = x0;
	}
	
	public float getWidth() {
		return content.getWidth();
	}
	
	public float getHeight() {
		return content.getHeight();
	}
}
