package lv.webkursi.klucis.blocks;

import lv.webkursi.klucis.VelocityView;

public class SquareBox extends AbstractComponent {

	private int size = 50;
	
	private int num;
	
	private int rotate = 0; 

	private Component content;

	private String label;

	public void setSize(int size) {
		this.size = size;
	}

	public void setContent(Component content) {
		this.content = content;
		// TODO: Copy the size down, so it can rotate
		if (content instanceof Transform) {
			((Transform)content).setSize(size);
		}
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public void setNum(int num) {
		this.num = num;
	}

	public String render() {
		VelocityView view = new VelocityView();
		view.setTemplateName("SquareBox");
		this.addObject("_offset", (size+20)*num);
		this.addObject("_size", size);
		this.addObject("_label", label);
		this.addObject("_content", content);
		if (rotate != 0) {
			// TODO: find out about the sign of '-'
			this.addObject("_rotate", -rotate);
		}
		view.setContextParams(model);		
		return view.render();
	}

	public void setRotate(int rotation) {
		this.rotate = rotation;
	}

}
