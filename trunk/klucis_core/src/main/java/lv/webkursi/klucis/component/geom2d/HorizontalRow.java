package lv.webkursi.klucis.component.geom2d;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import lv.webkursi.klucis.component.AbstractVisibleComponent;
import lv.webkursi.klucis.component.LabeledComponent;
import lv.webkursi.klucis.component.VisibleComponent;
import lv.webkursi.klucis.event.LifecycleEvent;
import lv.webkursi.klucis.event.LifecycleEventListener;
import lv.webkursi.klucis.mvc.VelocityMerge;

public class HorizontalRow extends AbstractVisibleComponent implements
		LifecycleEventListener {

	protected float gap;

	protected float offsetX;

	protected float offsetY;
	
	protected String viewName;

	protected List<VisibleComponent> components = new ArrayList<VisibleComponent>();

	public void setComponents(List<VisibleComponent> components) {
		this.components = components;
	}

	public void setGap(float gap) {
		this.gap = gap;
	}

	/** 
	 * Initialized to something unreasonable to help find uninitialized values
	 */
	protected float width = -1.0F;

	protected float height = -1.0F;

	public String render() {
		VelocityMerge view = new VelocityMerge();
		view.setTemplateName(viewName);
		model.put("_components", components);
		// TODO this assumes that horiz.row is the only component in the world!
		model.put("_offsetX", offsetX);
		model.put("_offsetY", offsetY);

		model.put("_width", getWidth());
		model.put("_height", getHeight());
		view.setContextParams(model);
		return view.render();
	}

	public void lifecycleEvent(LifecycleEvent event) {
		if (event.getKind().equals(LifecycleEvent.Kind.prepareToRender)) {
			// could compute offset from the parent and call its findOffset(),
			// but right now it is not necessary since this is always the top
			// element anyway and is centered in its middle point.
			offsetX = getWidth() / 2.0F;
			offsetY = getHeight() / 2.0F;
		}
	}

	/**
	 * This is here to compute offsets for the child elements of the horizontal
	 * row; children are evently spaced and aligned on the same horizontal line
	 * (i.e. the "core" parts of all children are alligned, not counting the
	 * labels).
	 */
	public Point2D.Float findOffset(VisibleComponent box) {
		float dx = -getWidth() / 2.0F;
		for (VisibleComponent comp : components) {
			if (comp.getId().equals(box.getId())) {
				dx += box.getWidth() / 2.0F;
				break;
			}
			dx += comp.getWidth() + gap;
		}
		float dy = -getHeight() / 2.0F + getMaxCoreHeight() / 2.0F;
		return new Point2D.Float(dx, dy);
	}

	/**
	 * Compute the biggest core height of all the components (needed to compute
	 * offsets)
	 * 
	 * @return
	 */
	private float getMaxCoreHeight() {
		float maxCoreHeight = 0.0F;
		for (VisibleComponent comp : components) {
			float currentHeight = comp.getHeight();
			if (comp instanceof LabeledComponent) {
				currentHeight = ((LabeledComponent) comp).getCoreHeight();
			}
			maxCoreHeight = (currentHeight > maxCoreHeight) ? currentHeight
					: maxCoreHeight;
		}
		return maxCoreHeight;
	}

	/**
	 * Return the maximum of component heights
	 */
	public float getHeight() {
		// lazy evaluation pattern
		if (height < 0.0F) {
			height = 0.0F;
			for (VisibleComponent comp : components) {
				float newHeight = comp.getHeight();
				height = height < newHeight ? newHeight : height;
			}
		}
		return height;
	}

	/**
	 * Add component widths and gaps
	 */
	public float getWidth() {
		if (width < 0.0F) {
			width = 0.0F;
			for (VisibleComponent comp : components) {
				width += comp.getWidth();
			}
			width += gap * (components.size() - 1);
		}
		return width;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public float getGap() {
		return gap;
	}

	public String getViewName() {
		return viewName;
	}

	public List<VisibleComponent> getComponents() {
		return components;
	}
	
	
}
