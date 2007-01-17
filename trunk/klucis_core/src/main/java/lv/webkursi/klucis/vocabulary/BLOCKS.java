package lv.webkursi.klucis.vocabulary;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 * Vocabulary constants from the BLOCKS ontology
 */
public class BLOCKS {
    private static Model m_model = ModelFactory.createDefaultModel();

    public static final String NS = "http://www.mtest.lv/schema/20061008/blocks#";    
    public static String getURI() {return NS;}
    public static final Resource NAMESPACE = m_model.createResource( NS );

    /**
     * Resources in the ontology
     */

	public static final Resource Box = m_model.createResource( NS + "Box");
    public static final Resource SquareBox = m_model.createResource( NS + "SquareBox");

    /**
     * Properties in the ontology
     */
	public static final Property hasColor = m_model.createProperty( NS + "hasColor" );
	public static final Property hasContent = m_model.createProperty( NS + "hasContent" );
	public static final Property hasLabel = m_model.createProperty( NS + "hasLabel" );
	public static final Property hasPath = m_model.createProperty( NS + "hasPath" );
    public static final Property output = m_model.createProperty( NS + "output" );
	public static final Property rotate = m_model.createProperty( NS + "rotate" );
	public static final Property scaleX = m_model.createProperty( NS + "scaleX" );
	public static final Property scaleY = m_model.createProperty( NS + "scaleY" );
	public static final Property strokeWidth = m_model.createProperty( NS + "strokeWidth" );

}
