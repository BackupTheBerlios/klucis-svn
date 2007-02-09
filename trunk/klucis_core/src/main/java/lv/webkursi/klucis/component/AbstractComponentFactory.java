package lv.webkursi.klucis.component;

import java.util.HashMap;
import java.util.Map;

import lv.webkursi.klucis.data.KlucisDAO;
import lv.webkursi.klucis.vocabulary.KLUCIS;

import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

/**
 * Common parent class for all the component factories. 
 * Some common initialization routines which apply to all components (e.g. assigning an id) 
 * 
 * @author kap
 */
public abstract class AbstractComponentFactory implements ComponentFactory {
    
	
    protected Map<Resource, Component> map = null;
    protected static Resource defaultDescription =
        ModelFactory.createDefaultModel().createResource();
        
    /**
     * Get a component described by resource r.
     * 
     * @param r an RDF resource describing the component to be returned.
     * @param componentManager from which subcomponents can be requested.
     * @param id to give the component (null for components initialized from config)
     * @return the required component.
     */
    public final Component getComponent(Resource r, ComponentManager componentManager, String id) {
        if (r.hasProperty(RDF.type, KLUCIS.Immutable)) {
            // then the object can be cached
            if (map == null) {
                map = new HashMap<Resource,Component>();
            }
            if (!map.containsKey(r)) {
                Component result = localGetComponent(r, componentManager, id);
                map.put(r, result);
                return result;
            } else {
                return map.get(r);                
            }            
        } else {
            return localGetComponent(r, componentManager, id);
        }
    }

    
    /**
     * Creates a new component instance.
     * 
     * <p>The method is intended to be overridden to implement component
     * specific interpretation of the component description.</p>
     * 
     * @param r a description of the component instance to create
     * @param componentManager from which subcomponents can be requested.
     * @param id is for component (should be null for static components)
     * @return the new component instance.
     */
    
    protected abstract Component localGetComponent(Resource r, ComponentManager componentManager, String id);
    
    /**
     * Configure common properties for components.
     * 
     * @param c the component being configured
     * @param r the configuration resource
     * @param id the id of the resource if provided
     */
    protected void configureCommonProperties(Component c, Resource r, String id) {
        configureId(c, r, id);        
    }
    
    /**
     * Configure the ID of a component.
     * 
     * @param c the component to be configured
     * @param r the configuration resource
     * @param id the id of the resource, if not null.
     * 
     * <p>
     * <b>Contract</b>
     * </p>
     * 
     * <ul>
     *   <li> if id is not null, then use it, otherwise  </li>
     *   <li> if r has one portal:id property, the value of that property is id.</li>
     *   <li> if r has more than one portal:id property, that is a configuration error.</li>
     *   <li> if r has no portal:id property, but does have a URI with a fragment identifier
     *        then id is the fragment identifier. </li>
     *   <li> if r has no portal:id property and a URI without a fragment identifier
     *        then id is the URI.  </li>
     *   <li> if r has no portal:id property and no URI
     *        then the bnode id of the r is the id prefixed with a constant to ensure uniqueness.  </li>
     * </ul>
     * 
     */
    protected void configureId(Component c, Resource r, String id) {
        if (id != null) {
        	c.setId(id);
        	return;
        }
        // should throw an exception, if KLUCIS.id is not unique        
        id = KlucisDAO.getStringProperty(r, KLUCIS.id, null, true);
        if (id != null) {
        	c.setId(id);
        	return;
        }
        id = KlucisDAO.getImplicitId(r);
        c.setId(id);
    }
}
