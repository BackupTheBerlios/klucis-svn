package lv.webkursi.mtest.core.data;

import lv.webkursi.mtest.core.vocabulary.MTEST;

import com.hp.hpl.jena.rdf.model.EmptyListException;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Seq;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.RDF;

public class RdfUtilities {
	/**
	 * This is a utility method to find the pageset resource and a few other
	 * things
	 * 
	 * @param c
	 * @param p
	 * @param value
	 * @return
	 */
	public static Resource getResourceByProperty(Model portalDescription,
			Resource c, Property p, String value) {
		Resource result = null;
		StmtIterator iter = portalDescription.listStatements(null, p, value);
		while (iter.hasNext()) {
			result = iter.nextStatement().getSubject();
			Statement sType = result.getProperty(RDF.type);
			if (sType != null && sType.getResource().equals(c)) {
				return result;
			}
		}
		
		throw new EmptyListException("Resource of class '" + c.getLocalName()
				+ "' with property '" + p.getLocalName() + "of value '" + value
				+ "' not found");
				
//		return result;
	}

	public static Resource getPageSetByPath(Model portalDescription,
			String pageSetName) {
		return getResourceByProperty(portalDescription, MTEST.PageSet,
				MTEST.name, pageSetName);
	}
	
	public static Seq findFacetAddList(Resource rComponent, Resource owner) {
		if (owner == null) {
			return null;
		}
		StmtIterator iter = rComponent.listProperties(MTEST.facetAddList);
		while (iter.hasNext()) {
			Statement candidate = iter.nextStatement();
			Resource rCandidate = candidate.getResource();
			Resource owner1 = rCandidate.getRequiredProperty(MTEST.belongsTo)
					.getResource();
			if (owner1.getLocalName().equals(owner.getLocalName())) {
				return candidate.getSeq();
			}
		}
		throw new EmptyListException("FacetAddList for owner '"
				+ owner.getURI() + "' not found");
	}

}
