package lv.webkursi.klucis.component.geom2d;

import java.awt.geom.Point2D;

import lv.webkursi.klucis.component.AbstractVisibleComponent;
import lv.webkursi.klucis.component.LabeledComponent;
import lv.webkursi.klucis.component.VisibleComponent;
import lv.webkursi.klucis.event.LifecycleEvent;
import lv.webkursi.klucis.event.LifecycleEventListener;

/**
 * This component displays a rectangular area and fills it with appropriate
 * content (typically such boxes will be stacked together and/or connected); a
 * box can also have a label
 * 
 * @author kap
 */
public class Rectangle extends AbstractVisibleComponent implements
		LabeledComponent, LifecycleEventListener {

	/**
	 * Wheather to display the rectangle itself (true), or only its contents
	 * with optional label (false)
	 */
	protected boolean showRectangle;

	/**
	 * Full width of the element; the default is the width of the rectangle's
	 * content; rectangle could also be padded (larger than necessary for its
	 * content)
	 */
	protected float width;

	protected float coreWidth;

	protected float coreHeight;

	private float rotate;

	private float offsetX;

	private float offsetY;

	private VisibleComponent content;

	/**
	 * If no default value is configured, null is the hard-coded default value -
	 * no label is displayed in this case
	 */
	protected String label;

	public void setContent(VisibleComponent content) {
		this.content = content;
		// TODO kap: Copy the size down, so it can rotate
		// if (content instanceof Transform) {
		// ((Transform)content).setSize(size);
		// }
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setOffsetX(float offsetX) {
		this.offsetX = offsetX;
	}

	public void setOffsetY(float offsetY) {
		this.offsetY = offsetY;
	}

	public void setShowRectangle(boolean showRectangle) {
		this.showRectangle = showRectangle;
	}

	public void setRotate(float rotate) {
		this.rotate = rotate;
	}

	public float getWidth() {
		return coreWidth > 0 ? coreWidth : content.getWidth();
	}

	public float getHeight() {
		float labelHeight = (label != null && !label.equals("")) ? 60.0F : 0.0F;
		return (coreHeight > 0 ? coreHeight : content.getHeight())
				+ labelHeight;
	}

	/**
	 * Height of the rectangle; the default is the height of the rectangle's
	 * content; rectangle can also be padded
	 * 
	 * @return
	 */
	public float getCoreHeight() {
		return coreHeight > 0 ? coreHeight : content.getHeight();
	}

	public float getCoreWidth() {
		return coreWidth > 0 ? coreWidth : content.getWidth();
	}

	/**
	 * Width of the element without label
	 * 
	 * @param rectWidth
	 */
	public void setCoreWidth(float coreWidth) {
		this.coreWidth = coreWidth;
	}

	/**
	 * Set vertical size of the bounding rectangle
	 * 
	 * @param rectHeight
	 */
	public void setCoreHeight(float coreHeight) {
		this.coreHeight = coreHeight;
	}

	public void lifecycleEvent(LifecycleEvent event) {
		if (event.getKind().equals(LifecycleEvent.Kind.prepareToRender)) {
			Point2D.Float offset = enclosing.findOffset(this);
			offsetX = offset.x;
			offsetY = offset.y;

			addObject("_offsetX", offsetX);
			addObject("_offsetY", offsetY);
			addObject("_showRectangle", showRectangle);
			addObject("_width", getCoreWidth());
			addObject("_height", getCoreHeight());
			if (!label.equals("")) {
				addObject("_label", label);
			}
			addObject("_content", content);
			addObject("_rotate", -rotate);
		}
	}
}
