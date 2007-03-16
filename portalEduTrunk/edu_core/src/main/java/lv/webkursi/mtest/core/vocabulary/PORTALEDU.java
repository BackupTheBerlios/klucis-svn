package lv.webkursi.mtest.core.vocabulary; 

import com.hp.hpl.jena.rdf.model.*;
 
/**
 * Vocabulary definitions for portalTest RDF data 
 */
public class PORTALEDU {
    /** <p>The RDF model that holds the vocabulary terms</p> */
    private static Model m_model = ModelFactory.createDefaultModel();


    /** <p>The namespace of the vocabulary as a string ({@value})</p> */
    public static final String NS = "http://www.somedomain.com/portaledu#";
    
    /** <p>The namespace of the vocabulary as a string</p>
     *  @see #NS */
    public static String getURI() {return NS;}
    
    /** <p>The namespace of the vocabulary as a resource</p> */
    public static final Resource NAMESPACE = m_model.createResource( NS );


//	public static final Resource System = m_model.createResource( NS + "System");
    

/*    
	public static final Property name = m_model.createProperty( NS + "name" );
	public static final Property nick = m_model.createProperty( NS + "nick" );
	public static final Property mbox = m_model.createProperty( NS + "mbox" );
	public static final Property password = m_model.createProperty( NS + "password" );
*/	
}
