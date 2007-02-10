package lv.webkursi.klucis.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lv.webkursi.klucis.KlucisConfigurationException;
import lv.webkursi.klucis.component.MockComponentFactoryCatalog;
import lv.webkursi.klucis.testutil.MockDescriptionFactory;
import lv.webkursi.klucis.vocabulary.EXAMPLE;
import lv.webkursi.klucis.vocabulary.KLUCIS;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

@RunWith(Suite.class)
@Suite.SuiteClasses(value = { KlucisDAOTest.TypeUtilities.class,
		KlucisDAOTest.CatalogTypeUtilities.class,
		KlucisDAOTest.AbstractComponentUtilities.class })
public class KlucisDAOTest {
	private static KlucisDAO klucisDAO = new KlucisDAO("/");


	private static Model model = null;

	/**
	 * Tests for utilities dealing with general RDF types
	 */
	public static class TypeUtilities {

		private static final String typeUtilities = MockDescriptionFactory.PREFIXES
				+ ":r1 klucis:width \"200\" . "
				+ ":r2 a klucis:TopComponent ; a klucis:Rectangle . "
				+ ":r3 a klucis:B ; a klucis:C ; a klucis:A ; a klucis:D ; a klucis:E . "
				+ ":skipLiteralTypes a \"literalType\" ; a klucis:F .";

		@Before
		public void setUp() {
			model = MockDescriptionFactory.getInstance().createModel(
					"typeUtilities", typeUtilities);
		}

		/**
		 * If no types are specified, getAllTypes() should return non-null
		 * iterator with no resources
		 * 
		 */
		@Test
		public void testGetAllTypes1() {
			Resource r1 = model.createResource(EXAMPLE.NS + "r1");
			ExtendedIterator i = KlucisDAO.getAllTypes(r1);
			Set<String> set = new HashSet<String>();
			while (i.hasNext()) {
				Resource ri = (Resource) i.next();
				set.add(ri.getLocalName());
			}
			assertEquals(0, set.size());
		}

		/**
		 * If a resource has two types, getAllTypes() should return iterator
		 * with two resources.
		 * 
		 */
		@Test
		public void testGetAllTypes2() {
			Resource r2 = model.createResource(EXAMPLE.NS + "r2");
			ExtendedIterator i = KlucisDAO.getAllTypes(r2);
			Set<String> set = new HashSet<String>();
			while (i.hasNext()) {
				Resource ri = (Resource) i.next();
				set.add(ri.getLocalName());
			}
			assertEquals(2, set.size());
			Set<String> expectedSet = new HashSet<String>();
			expectedSet.addAll(Arrays.asList(new String[] { "Rectangle",
					"TopComponent" }));
			assertEquals(expectedSet, set);
		}

		/**
		 * By getSortedTypes() the types are returned alphabetically sorted.
		 * 
		 */
		@Test
		public void getSortedTypes() {
			Resource r3 = model.createResource(EXAMPLE.NS + "r3");
			Set<Resource> set = KlucisDAO.getSortedTypes(r3);
			String[] names = new String[] { "A", "B", "C", "D", "E" };
			int count = 0;
			for (Resource r : set) {
				assertEquals(names[count++], r.getLocalName());
			}

		}

		/**
		 * If any "rdf:type" values are literals, then ignore them; leave only
		 * those, which are resources.
		 * 
		 */
		@Test
		public void skipLiteralTypes() {
			Resource resource = model.getResource(EXAMPLE.NS
					+ "skipLiteralTypes");
			Set<Resource> list = KlucisDAO.getSortedTypes(resource);
			Resource rType0 = model.getResource(KLUCIS.NS + "F");
			assertTrue(list.contains(rType0));
			assertEquals(1, list.size());
		}
	}

	/**
	 * Tests for utilities dealing with FactoryCatalog types and finding
	 * properties.
	 */
	public static class CatalogTypeUtilities {
		private static final String catalogTypeUtilities = MockDescriptionFactory.PREFIXES
				+ ":r2 a klucis:TopComponent ; a klucis:Rectangle . "
				+ ":r3 a klucis:InvalidType . "
				+ ":r1 a klucis:InvalidType ; a klucis:Rectangle . "
				+ ":r11 klucis:parent :r1 . " + ":r111 klucis:parent :r11 . "
				+ ":r31 klucis:parent :r3 . ";

		@Before
		public void setUp() {
			KlucisDAO.setFactoryCatalog(MockComponentFactoryCatalog
					.getCatalog());
			model = MockDescriptionFactory.getInstance().createModel(
					"catalogTypeUtilities", catalogTypeUtilities);
		}

		/**
		 * Exception, if several types in the FactoryCatalog
		 */
		@Test(expected = KlucisConfigurationException.class)
		public void testAmbiguousType() {
			Resource r2 = model.createResource(EXAMPLE.NS + "r2");
			klucisDAO.getComponentType(r2);
		}

		/**
		 * Exception, if no types are in the FactoryCatalog
		 * 
		 */
		@Test(expected = KlucisConfigurationException.class)
		public void testNoType() {
			Resource r3 = model.createResource(EXAMPLE.NS + "r3");
			klucisDAO.getComponentType(r3);
		}

		/**
		 * OK, if exactly one type is in the FactoryCatalog
		 */
		@Test
		public void testOneType() {
			Resource r1 = model.createResource(EXAMPLE.NS + "r1");
			Resource rType = klucisDAO.getComponentType(r1);
			Resource expectedType = model.createResource(KLUCIS.NS
					+ "Rectangle");
			assertEquals(expectedType, rType);
		}

		/**
		 * OK, if exactly one type for the grandparent
		 */
		@Test
		public void testGrandparentWithOneType() {
			Resource r111 = model.createResource(EXAMPLE.NS + "r111");
			Resource rType = klucisDAO.getComponentType(r111);
			Resource expectedType = model.createResource(KLUCIS.NS
					+ "Rectangle");
			assertEquals(expectedType, rType);
		}

		/**
		 * Exception, if no types for the entry, and none for the parent (which
		 * has no parent of its own)
		 */
		@Test(expected = KlucisConfigurationException.class)
		public void testParentWithNoType() {
			Resource r31 = model.createResource(EXAMPLE.NS + "r31");
			Resource rType = klucisDAO.getComponentType(r31);
			Resource expectedType = model.createResource(KLUCIS.NS
					+ "Rectangle");
			assertEquals(expectedType, rType);
		}

	}

	public static class AbstractComponentUtilities {
		public static final String abstractComponentUtilities = MockDescriptionFactory.PREFIXES
				+ ":rootDefaultRectangle a klucis:AbstractComponent ; a klucis:Rectangle ; klucis:context \"/\" . "
				+ ":septinjJudzuMezhaRectangle a klucis:AbstractComponent ; a klucis:Rectangle ; klucis:context \"/sjm/\" . "
				+ ":puukaRectangle a klucis:AbstractComponent ; a klucis:Rectangle ; klucis:context \"/sjm/puuks/\" . "
				+ ":puukaSession2Rectangle a klucis:AbstractComponent ; a klucis:Rectangle ; klucis:context \"/sjm/puuks/session2/\" . "
				+ ":sivnaRectangle a klucis:AbstractComponent ; a klucis:Rectangle ; klucis:context \"/sjm/sivns/\" . ";

		@Before
		public void setUp() {
			KlucisDAO.setFactoryCatalog(MockComponentFactoryCatalog
					.getCatalog());
			model = MockDescriptionFactory.getInstance().createModel(
					"abstractComponentUtilities", abstractComponentUtilities);
			klucisDAO = new KlucisDAO("/sjm/puuks/session2/");
		}
		@Test
		public void testParentWithNoType() {
			Resource typeRectangle = model.createResource(KLUCIS.NS + "Rectangle");
			List<Resource> list = klucisDAO.getAbstractComponents(typeRectangle);
			assertEquals(4,list.size());
			assertEquals("http://example.com/bildes#rootDefaultRectangle",list.get(0).getURI());
			assertEquals("http://example.com/bildes#puukaSession2Rectangle",list.get(3).getURI());
		}
	}
}
