package lv.webkursi.mtest.core.vocabulary; 

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
 
/**
 * Vocabulary definitions for MTest RDF data 
 */
public class MTEST {
    /** <p>The RDF model that holds the vocabulary terms</p> */
    private static Model m_model = ModelFactory.createDefaultModel();

    /** <p>The namespace of the vocabulary as a string ({@value})</p> */
    public static final String NS = "http://www.webkursi.lv/schema/mtest#";
    
    /** <p>The namespace of the vocabulary as a string</p>
     *  @see #NS */
    public static String getURI() {return NS;}
    
    /** <p>The namespace of the vocabulary as a resource</p> */
    public static final Resource NAMESPACE = m_model.createResource( NS );

    /**
     * Resources in the ontology
     */
	public static final Resource PageSet = m_model.createResource( NS + "PageSet");;
    public static final Resource User = m_model.createResource( NS + "User");

    /**
     * Properties in the ontology
     */
	public static final Property action = m_model.createProperty( NS + "action" );
	public static final Property belongsTo = m_model.createProperty( NS + "belongsTo" );
	public static final Property component = m_model.createProperty( NS + "component" );
	public static final Property compositeFacet = m_model.createProperty( NS + "compositeFacet" );
	public static final Property condition = m_model.createProperty( NS + "condition" );
	public static final Property conditionLowerBound = m_model.createProperty( NS + "conditionLowerBound" );
	public static final Property conditionSource = m_model.createProperty( NS + "conditionSource" );
	public static final Property conditionUpperBound = m_model.createProperty( NS + "conditionUpperBound" );
	public static final Property defaultState = m_model.createProperty( NS + "defaultState" );
	public static final Property defaultValue = m_model.createProperty( NS + "defaultValue" );
	public static final Property email = m_model.createProperty( NS + "email" );
	public static final Property facet = m_model.createProperty( NS + "facet" );
	public static final Property facetAddList = m_model.createProperty( NS + "facetAddList" );
    public static final Property hasComponents = m_model.createProperty( NS + "hasComponents" ); 
	public static final Property key = m_model.createProperty( NS + "key" );
	public static final Property model = m_model.createProperty( NS + "model" );
	public static final Property modelKey = m_model.createProperty(NS + "modelKey");
    public static final Property name = m_model.createProperty( NS + "name" );    
	public static final Property password = m_model.createProperty( NS + "password" );
	public static final Property resultMap = m_model.createProperty( NS + "resultMap" );
	public static final Property ruleList = m_model.createProperty( NS + "ruleList" );
    public static final Property source = m_model.createProperty( NS + "source" );
	public static final Property state = m_model.createProperty( NS + "state" );
    public static final Property title = m_model.createProperty( NS + "title" );
	public static final Property typeStringMap = m_model.createProperty( NS + "typeStringMap" );
	public static final Property resultMapper = m_model.createProperty( NS + "resultMapper" );
	public static final Property rootComponent = m_model.createProperty( NS + "rootComponent" );
	public static final Property userAddList = m_model.createProperty( NS + "userAddList" );
	public static final Property userName = m_model.createProperty( NS + "userName" );
	public static final Property value = m_model.createProperty( NS + "value" );
	public static final Property viewName = m_model.createProperty( NS + "viewName" );

}
