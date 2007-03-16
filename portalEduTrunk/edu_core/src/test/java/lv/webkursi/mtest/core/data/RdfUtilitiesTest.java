package lv.webkursi.mtest.core.data;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import lv.webkursi.mtest.core.util.UnitTestUtils;
import lv.webkursi.mtest.core.vocabulary.MTEST;
import lv.webkursi.mtest.core.vocabulary.PORTAL;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Seq;

public class RdfUtilitiesTest {
	private Model model;

	@Before
	public void setUp() {

		model = ModelFactory.createFileModelMaker(
				UnitTestUtils.getRdfDataDir()).openModel(
				"portalDescription.n3", false);

	}
	
	@Test
	public void testFindFacetAddList() {
		Resource r = model.getResource(PORTAL.NS + "fixedCompositeFacet");
		Resource rAmos = model.getResource(PORTAL.NS + "Amos");
		Seq stuff = RdfUtilities.findFacetAddList(r, rAmos);
		assertEquals(1,stuff.size());
	}

}
