package lv.webkursi.klucis.component.geom2d;

import static org.junit.Assert.assertEquals;

import java.awt.geom.Point2D;
import java.util.List;

import lv.webkursi.klucis.component.ComponentManager;
import lv.webkursi.klucis.component.MockComponentFactoryCatalog;
import lv.webkursi.klucis.component.VisibleComponent;
import lv.webkursi.klucis.data.KlucisDAOTest;
import lv.webkursi.klucis.testutil.MockDescriptionFactory;
import lv.webkursi.klucis.vocabulary.EXAMPLE;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;

@RunWith(Suite.class)
@Suite.SuiteClasses(value = { HorizontalRowTest.OffsetTests.class })
public class HorizontalRowTest {

	private static Model model;

	private static ComponentManager manager;

	public static class OffsetTests {
		private static final String offsetTests = MockDescriptionFactory.PREFIXES
				+ ":r1 a klucis:HorizontalRow ; "
				+ "  a rdf:Seq ; "
				+ "  rdf:_1 [ a klucis:Rectangle ; "
				+ "    klucis:id \"r11\" ; "
				+ "    klucis:hasLabel \"A\" ; "
				+ "    klucis:hasContent [a klucis:Path ; klucis:hasPath \"\" ] ] ; "
				+ "  rdf:_2 [ a klucis:Rectangle ; "
				+ "    klucis:id \"r12\" ; "
				+ "    klucis:hasLabel \"B\" ; "
				+ "    klucis:hasContent [a klucis:Path ; klucis:hasPath \"\" ] ] .";

		@Before
		public void setUp() {
			model = MockDescriptionFactory.getInstance().createModelWithDefaults(
					"offsetTests", offsetTests);
			manager = new ComponentManager();
			manager.setFactoryCatalog(MockComponentFactoryCatalog.getCatalog());
		}

		@Ignore
		@Test
		public void unlabeledOffset() {
			Resource r1 = model.createResource(EXAMPLE.NS + "r1");
			HorizontalRow row = (HorizontalRow) manager.getStaticComponent(r1);
			assertEquals(60.0F,row.getGap(),1E-7);
			List<VisibleComponent> list = row.getComponents();
			assertEquals(2,list.size());
			assertEquals("r11", list.get(0).getId());
			assertEquals("r12", list.get(1).getId());
			Point2D.Float point0 = row.findOffset(list.get(0));
			assertEquals(-130.0F,point0.x,1E-7);
			assertEquals(-30.0F,point0.y,1E-7);
			Point2D.Float point1 = row.findOffset(list.get(1));
			assertEquals(130.0F,point1.x,1E-7);
			assertEquals(-30.0F,point1.y,1E-7);
		}
	}
}
