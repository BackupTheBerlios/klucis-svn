package lv.webkursi.klucis.blocks;

import java.util.ArrayList;
import java.util.List;

import lv.webkursi.klucis.VelocityView;

public class HorizontalRow extends AbstractComponent {
	
	private List<Component> components = new ArrayList<Component>();
	
	public void setComponents(List<Component> components) {
		this.components = components;
	}
		

	public String render() {
		VelocityView view = new VelocityView();
		view.setTemplateName("HorizontalRow");
		int count = 0;
		for (Component c: components) {
			if (c instanceof SquareBox) {
				((SquareBox)c).setNum(count++);
			}
		}		
		model.put("_components", components);
		view.setContextParams(model);
		return view.render();
	}

}
