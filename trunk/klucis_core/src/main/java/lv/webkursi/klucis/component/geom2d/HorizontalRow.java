package lv.webkursi.klucis.component.geom2d;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import lv.webkursi.klucis.component.AbstractComponent;
import lv.webkursi.klucis.component.Component;
import lv.webkursi.klucis.component.LabeledComponent;
import lv.webkursi.klucis.event.LifecycleEvent;
import lv.webkursi.klucis.event.LifecycleEventListener;
import lv.webkursi.klucis.mvc.VelocityMerge;

public class HorizontalRow extends AbstractComponent implements
		LifecycleEventListener {

	protected float gap;

	protected List<Component> components = new ArrayList<Component>();

	public void setComponents(List<Component> components) {
		this.components = components;
	}

	public void setGap(float gap) {
		this.gap = gap;
	}

	protected float width = -1.0F;

	protected float height = -1.0F;

	public String render() {
		VelocityMerge view = new VelocityMerge();
		view.setTemplateName("HorizontalRow");
		model.put("_components", components);
		// TODO this assumes that horiz.row is the only component in the world!
		model.put("_offsetX", getWidth() / 2.0F);
		model.put("_offsetY", getHeight() / 2.0F);

		model.put("_width", getWidth());
		model.put("_height", getHeight());
		view.setContextParams(model);
		return view.render();
	}

	/*
	public String getLabel() {
		StringBuffer result = new StringBuffer();
		for (Component comp : components) {
			if (comp instanceof LabeledComponent
					&& ((LabeledComponent) comp).getLabel() != null) {
				result.append(((LabeledComponent) comp).getLabel());
				result.append(" ");
			}
		}
		int length = result.length();
		if (length > 0) {
			return result.delete(length - 1, length).toString();
		} else {
			return null;
		}
	}
	*/
	
	public void lifecycleEvent(LifecycleEvent event) {
		if (event.getKind().equals(LifecycleEvent.Kind.prepareToRender)) {
			// ...
		}
	}

	public Point2D.Float findOffset(Component box) {
		float dx = -getWidth()/2.0F;
		for (Component comp : components) {
			if (comp.getId().equals(box.getId())) {
				dx += box.getWidth()/2.0F;
				break;
			}
			dx += comp.getWidth() + gap;
		}
		float dy = -getHeight()/2.0F + getMaxCoreHeight()/2.0F;
		return new Point2D.Float(dx, dy);
	}
	
	/**
	 * Compute the biggest core height of all the components (needed to compute offsets)
	 * @return
	 */
	private float getMaxCoreHeight() {
		float maxCoreHeight = 0.0F;
		for (Component comp : components) {
			float currentHeight = comp.getHeight();
			if (comp instanceof LabeledComponent) {
				currentHeight = ((LabeledComponent)comp).getCoreHeight();
			}
			maxCoreHeight = (currentHeight > maxCoreHeight) ? currentHeight : maxCoreHeight;
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
			for (Component comp : components) {
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
			for (Component comp : components) {
				width += comp.getWidth();
			}
			width += gap * (components.size() - 1);
		}
		return width;
	}
}
