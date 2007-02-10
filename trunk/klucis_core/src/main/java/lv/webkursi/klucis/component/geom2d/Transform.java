package lv.webkursi.klucis.component.geom2d;

import lv.webkursi.klucis.component.AbstractVisibleComponent;
import lv.webkursi.klucis.component.VisibleComponent;
import lv.webkursi.klucis.mvc.VelocityMerge;

public class Transform extends AbstractVisibleComponent {
	
	private int rotate = 0;
	
	private int size;
	
	private int scaleX = 1;

	private int scaleY = 1;

	private VisibleComponent content;
	
	public void setRotate(int rotate) {
		this.rotate = rotate;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public String render() {
		VelocityMerge view = new VelocityMerge();
		view.setTemplateName("Transform");
		// TODO kap: why the sign '-'? Could switch to the right-handed coordinates?
		if (rotate != 0) {
			this.addObject("_rotate", -rotate);
		}
		this.addObject("_size", size);
		this.addObject("_content", content);
		
		if (scaleX != 1) {
			this.addObject("_scaleX", scaleX);
		}
		if (scaleY != 1) {
			this.addObject("_scaleY", scaleY);
		}
		view.setContextParams(model);		
		return view.render();
	}

	public void setContent(VisibleComponent content) {
		this.content = content;
	}

	public void setScaleX(int scaleX) {
		this.scaleX = scaleX;
	}

	public void setScaleY(int scaleY) {
		this.scaleY = scaleY;
	}

	public float getHeight() {
		return content.getHeight();
	}

	public float getWidth() {
		return content.getWidth();
	}
}
