package lv.webkursi.web2007a.lab01;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import lv.webkursi.klucis.VelocityView;

import org.junit.Test;

public class VelocityViewTest {
	@Test
	public void rendering() {
		VelocityView view = new VelocityView();
		view.setTemplateName("TopComponent");
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("_content", new Renderable());
		view.setContextParams(model);
		assertTrue(view.render().contains("XXX"));
//		System.out.println(view.render());
	}
	
	public static class Renderable {
		public String render() {
			return "XXX";
		}
	}
}
