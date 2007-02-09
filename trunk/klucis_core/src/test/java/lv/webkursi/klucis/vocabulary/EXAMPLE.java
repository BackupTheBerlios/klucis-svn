package lv.webkursi.klucis.vocabulary;

import com.hp.hpl.jena.rdf.model.*;

/**
 * Vocabulary definitions for example namespace used in tests 
 * @author kap
 */
public class EXAMPLE {
    /** <p>The RDF model that holds the vocabulary terms</p> */
    private static Model m_model = ModelFactory.createDefaultModel();
    /** <p>The namespace of the vocabulary as a string ({@value})</p> */
    public static final String NS = "http://example.com/bildes#";
    
    /** <p>The namespace of the vocabulary as a string</p>
     *  @see #NS */
    public static String getURI() {return NS;}
    
    /** <p>The namespace of the vocabulary as a resource</p> */
    public static final Resource NAMESPACE = m_model.createResource( NS );

    /**
     * Resources in the ontology
     */

	public static final Resource Class1 = m_model.createResource( NS + "Class1");
	public static final Resource Class2 = m_model.createResource( NS + "Class2");

    
    /**
     * Properties in the ontology
     */
	
}
