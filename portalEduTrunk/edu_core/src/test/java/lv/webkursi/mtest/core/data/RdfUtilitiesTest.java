package lv.webkursi.mtest.core.data;

import static org.junit.Assert.assertEquals;
import lv.webkursi.mtest.core.vocabulary.PORTAL;

import org.junit.Before;
import org.junit.Test;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Seq;

public class RdfUtilitiesTest {
	private Model model;

	private static final String rdfUtilitiesTestData = MockDescriptionFactory.PREFIXES
			+ "portal:Amos"
			+ "    rdf:type mtest:User ;"
			+ "    mtest:userName \"Amos\" ;"
			+ "    mtest:password \"Amos\" . "
			+ "portal:fixedCompositeFacet"
			+ "    rdf:type mtest:CompositeFacet ;"
			+ "    mtest:viewName \"temp/fixed_composite_facet\" ;"
			+ "    mtest:facetAddList ["
			+ "        a rdf:Seq ;"
			+ "        mtest:belongsTo portal:System ;"
			+ "        rdf:_1 ["
			+ "            mtest:facet portal:textFacet1 ;"
			+ "            mtest:state \"A\""
			+ "        ] ;"
			+ "        rdf:_2 ["
			+ "            mtest:facet portal:incDecFacet1 ;"
			+ "            mtest:state 10"
			+ "       ]"
			+ "    ] ;"
			+ "    mtest:facetAddList ["
			+ "        a rdf:Seq ;"
			+ "        mtest:belongsTo portal:Amos ;"
			+ "        rdf:_1 [ "
			+ "            mtest:facet portal:messageFacet1 ;"
			+ "            mtest:state \"Welcome, Amos!\""
			+ "        ]"
			+ "    ] ;"
			+ "    mtest:facetAddList ["
			+ "        a rdf:Seq ;"
			+ "        mtest:belongsTo portal:Ezekiel ;"
			+ "        rdf:_1 ["
			+ "            mtest:facet portal:messageFacet2 ;"
			+ "            mtest:state \"Welcome, Ezekiel!\""
			+ "        ] ;"
			+ "        rdf:_2 ["
			+ "            mtest:facet portal:incDecFacet3 ;"
			+ "            mtest:state \"17\""
			+ "        ]"
			+ "    ] ;"
			+ "    mtest:model ["
			+ "        a rdf:Bag ;"
			+ "        rdf:_1 ["
			+ "            mtest:modelKey \"message\" ;"
			+ "            mtest:value \"Active facets\""
			+ "        ] "
			+ "    ] ;" + "    mtest:facetCatalog portal:facetCatalog .";

	@Before
	public void setUp() {
		model = MockDescriptionFactory.getInstance().createModel(
				"rdfUtilitiesTest", rdfUtilitiesTestData);

	}

	@Test
	public void testFindFacetAddList() {
		Resource r = model.getResource(PORTAL.NS + "fixedCompositeFacet");
		Resource rAmos = model.getResource(PORTAL.NS + "Amos");
		Seq stuff = RdfUtilities.findFacetAddList(r, rAmos);
		assertEquals(1, stuff.size());
	}

}
