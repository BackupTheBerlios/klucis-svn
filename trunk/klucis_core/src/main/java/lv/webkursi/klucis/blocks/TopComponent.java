package lv.webkursi.klucis.blocks;

import lv.webkursi.klucis.VelocityView;

public class TopComponent extends AbstractComponent implements Writeable {
	
	private String fileName;
	
	private Component content;

	public void setContent(Component content) {
		this.content = content;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void output() {
		VelocityView view = new VelocityView();
		view.setTemplateName("TopComponent1");
		this.addObject("_content", content);
		view.setContextParams(model);
		view.write(fileName);
	}

	public String render() {
		VelocityView view = new VelocityView();
		view.setTemplateName("TopComponent");
		this.addObject("_content", content);
		view.setContextParams(model);
		return view.render();
	}

}
