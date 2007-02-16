package lv.webkursi.klucis.vocabulary;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 * Vocabulary constants for the KLUCIS ontology
 */
public class KLUCIS {
    private static Model m_model = ModelFactory.createDefaultModel();

    public static final String NS = "http://www.webkursi.lv/schema/20061008/klucis#";    
    public static String getURI() {return NS;}
    public static final Resource NAMESPACE = m_model.createResource( NS );

    /*
     * Resources in the ontology
     */
	public static final Resource AbstractComponent = m_model.createResource( NS + "AbstractComponent");
	public static final Resource CompositeWidget = m_model.createResource( NS + "CompositeWidget" );
	public static final Resource HorizontalRow = m_model.createResource( NS + "HorizontalRow");
	public static final Resource Immutable = m_model.createResource( NS + "Immutable");
	public static final Resource Path = m_model.createResource( NS + "Path" );
	public static final Resource PlainComponent = m_model.createResource( NS + "PlainComponent" );
    public static final Resource Rectangle = m_model.createResource( NS + "Rectangle");
	public static final Resource TopComponent = m_model.createResource( NS + "TopComponent");
	public static final Resource Transform = m_model.createResource( NS + "Transform" );
    
    /*
     * Properties in the ontology
     */
	public static final Property context = m_model.createProperty( NS + "context" );
	public static final Property hasColor = m_model.createProperty( NS + "hasColor" );
	public static final Property hasComponents = m_model.createProperty( NS + "hasComponents" );
	public static final Property hasContent = m_model.createProperty( NS + "hasContent" );
	public static final Property hasGap = m_model.createProperty( NS + "hasGap" );
	public static final Property hasHeight = m_model.createProperty( NS + "hasHeight" );
	public static final Property hasImageName = m_model.createProperty( NS + "hasImageName" );
	public static final Property hasLabel = m_model.createProperty( NS + "hasLabel" );
	public static final Property hasPath = m_model.createProperty( NS + "hasPath" );
	public static final Property hasCoreHeight =  m_model.createProperty( NS + "hasCoreHeight" );
	public static final Property hasCoreWidth =  m_model.createProperty( NS + "hasCoreWidth" );
	public static final Property hasStrokeWidth = m_model.createProperty( NS + "hasStrokeWidth" );
	public static final Property hasViewName =  m_model.createProperty( NS + "hasViewName" );
	public static final Property hasWidth = m_model.createProperty( NS + "hasWidth" );
	public static final Property id = m_model.createProperty( NS + "id" );
//    public static final Property hasFileName = m_model.createProperty( NS + "hasFileName" );
	public static final Property key =  m_model.createProperty( NS + "key" );
	public static final Property parent = m_model.createProperty( NS + "parent" );
	public static final Property rotate = m_model.createProperty( NS + "rotate" );
	public static final Property scaleX = m_model.createProperty( NS + "scaleX" );
	public static final Property scaleY = m_model.createProperty( NS + "scaleY" );
	public static final Property showRectangle = m_model.createProperty( NS + "showRectangle" );
	public static final Property value =  m_model.createProperty( NS + "value" );





}
