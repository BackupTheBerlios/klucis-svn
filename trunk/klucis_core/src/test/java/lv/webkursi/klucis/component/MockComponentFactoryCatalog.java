package lv.webkursi.klucis.component;

import java.util.HashMap;
import java.util.Map;

import lv.webkursi.klucis.component.geom2d.HorizontalRowFactory;
import lv.webkursi.klucis.component.geom2d.PathFactory;
import lv.webkursi.klucis.component.geom2d.RectangleFactory;
import lv.webkursi.klucis.component.geom2d.TopComponentFactory;
import lv.webkursi.klucis.component.geom2d.TransformFactory;
import lv.webkursi.klucis.vocabulary.KLUCIS;

public class MockComponentFactoryCatalog {

	public static FactoryCatalog getCatalog() {
		FactoryCatalog catalog = new FactoryCatalog();
		Map<String, ComponentFactory> map = new HashMap<String, ComponentFactory>();

		map.put(KLUCIS.TopComponent.getURI(), new TopComponentFactory());
		map.put(KLUCIS.HorizontalRow.getURI(), new HorizontalRowFactory());
		map.put(KLUCIS.Rectangle.getURI(), new RectangleFactory());
		map.put(KLUCIS.Transform.getURI(), new TransformFactory());
		map.put(KLUCIS.Path.getURI(), new PathFactory());

		catalog.setCatalog(map);
		return catalog;
	}
}
