package lv.webkursi.klucis.blocks;

import lv.webkursi.klucis.VelocityView;

public class Transform extends AbstractComponent {
	
	private int rotate = 0;
	
	private int size;
	
	private int scaleX = 1;

	private int scaleY = 1;

	private Component content;
	
	public void setRotate(int rotate) {
		this.rotate = rotate;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public String render() {
		VelocityView view = new VelocityView();
		view.setTemplateName("Transform");
		// TODO: understand, why the sign -
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

	public void setContent(Component content) {
		this.content = content;
	}

	public void setScaleX(int scaleX) {
		this.scaleX = scaleX;
	}

	public void setScaleY(int scaleY) {
		this.scaleY = scaleY;
	}
}