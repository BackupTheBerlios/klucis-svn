package lv.webkursi.klucis.component.geom2d;

import java.awt.geom.Point2D;

import lv.webkursi.klucis.component.AbstractVisibleComponent;
import lv.webkursi.klucis.component.VisibleComponent;
import lv.webkursi.klucis.event.LifecycleEvent;
import lv.webkursi.klucis.event.LifecycleEventListener;
import lv.webkursi.klucis.mvc.VelocityMerge;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Component which is ready to be output as an SVG image; it manages all the
 * global SVG parameters
 * 
 * @author kap
 */
public class TopComponent extends AbstractVisibleComponent implements
		LifecycleEventListener {

	/**
	 * TODO kap: should come from config.
	 * Local horizontal coordinate unit size
	 * in mm
	 */
	protected float unitSizeX = 0.1F;

	/**
	 * TODO kap: should come from config:
	 * Local vertical coordinate unit size in
	 * mm
	 */
	protected float unitSizeY = 0.1F;

	/**
	 * At which local x coordinate the image starts
	 */
	protected float x0 = 0.0F;

	/**
	 * At which local y coordinate the image starts
	 */
	protected float y0 = 0.0F;

	protected String imageName;

	protected VisibleComponent content;

	private Log log = LogFactory.getLog(TopComponent.class);

	public void setContent(VisibleComponent content) {
		this.content = content;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public void output() {
		VelocityMerge view = new VelocityMerge();
		view.setTemplateName(viewName);
		view.setContextParams(map);
		log.info("Writing file '" + imageName + "'");
		view.write(imageName + ".svg");
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

	public Point2D.Float findOffset(VisibleComponent box) {
		float dx = getWidth() / 2.0F;
		float dy = getHeight() / 2.0F;
		return new Point2D.Float(dx, dy);
	}

	public void lifecycleEvent(LifecycleEvent event) {
		if (event.getKind().equals(LifecycleEvent.Kind.prepareToRender)) {
			addObject("_content", content);
			float width = getWidth();
			float height = getHeight();

			// surround all pictures with 5 unit padding
			addObject("_sizeX", (width + 10) * unitSizeX);
			addObject("_sizeY", (height + 10) * unitSizeY);
			addObject("_width", width + 10);
			addObject("_height", height + 10);
			addObject("_x0", x0 - 5);
			addObject("_y0", y0 - 5);
		}
	}
}
