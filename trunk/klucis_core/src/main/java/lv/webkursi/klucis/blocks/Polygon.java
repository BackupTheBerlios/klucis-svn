package lv.webkursi.klucis.blocks;

import lv.webkursi.klucis.VelocityView;

public class Polygon extends AbstractComponent {

	private String color;

	private String path;
	
	private int strokeWidth;

	public void setColor(String color) {
		this.color = color;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public void setStrokeWidth(int strokeWidth) {
		this.strokeWidth = strokeWidth;
	}
	
	public String render() {
		VelocityView view = new VelocityView();
		view.setTemplateName("Polygon");
		this.addObject("_name", name);
		this.addObject("_color", color);
		this.addObject("_path", path);
		if (strokeWidth != 0) {
			this.addObject("_strokeWidth", strokeWidth);			
		}
		view.setContextParams(model);
		return view.render();
	}
}
