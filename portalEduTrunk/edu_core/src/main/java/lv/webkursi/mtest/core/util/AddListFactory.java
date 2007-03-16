package lv.webkursi.mtest.core.util;

import java.util.ArrayList;
import java.util.List;

import lv.webkursi.mtest.core.vocabulary.MTEST;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Seq;
import com.hp.hpl.jena.rdf.model.Statement;

public class AddListFactory {
	
	public List<FacetConfigurationEntry> getObject(Resource rAddList) {
		if (rAddList == null) { // for nonexistant arguments, return empty list
			return new ArrayList<FacetConfigurationEntry>();
		}
		Model rdfModel = rAddList.getModel();		
		
		Seq seq = rdfModel.getSeq(rAddList);
		NodeIterator i = seq.iterator();
		List<FacetConfigurationEntry> result = new ArrayList<FacetConfigurationEntry>();
		while (i.hasNext()) {
			Resource node = (Resource)i.nextNode();
			result.add(FacetConfigurationEntryFactory.getObject(node));
		}
		return result;
	}
	
	public static class FacetConfigurationEntryFactory {
		
		public static FacetConfigurationEntry getObject(Resource rEntry) {
			String facetName = rEntry.getRequiredProperty(MTEST.facet).getResource().getLocalName();
			Statement stateStatement = rEntry.getProperty(MTEST.state);
			FacetConfigurationEntry result = new FacetConfigurationEntry();
			result.setFacetName(facetName);
			if (stateStatement != null) {
				result.setState(stateStatement.getString());
			}
			return result;
		}
	}
}
