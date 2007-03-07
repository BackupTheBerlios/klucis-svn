/**
 * <p>All kinds of facets and facet states go here.</p>
 * 
 * <h3>Facets</h3>
 * 
 * <p>A single instance of a Facet is a parametrized predicate
 * P(p,x), which maps all objects x in some domain X
 * to booleans. The parameter "p:Object" is the current state of the
 * facet (may depend on what FacetState class/configuration is 
 * used for this Facet). If facet is applied to arguments outside
 * its domain, an exception is thrown.</p>
 * 
 * <p>For example, AgeIsAtLeast(p:int,x:Person) has a parameter p 
 * (representing age in years), and the predicate returns true, iff individual's
 * x's age in years is equal to "p" or is more than that.</p>
 * 
 * <p>Facets are immutable singleton classes, which all inherit from the
 * same parent class (Facet) and are configured in the portal as singletons, 
 * i.e. there cannot be two copies of the same facet. One may have
 * several facet's for the same RDF property though. For example, 
 * "AgeIsEqual(p,x)" and "AgeIsAtLeast(p,x)" are two  predicates, 
 * which differ by the relation between the parameter and the domain
 * variable x.</p>
 * 
 * <p>Parameters (e.g. the age "p" in the example above) 
 * are not stored within the facet itself, since facets need to be immutable. 
 * But these parameters (which constitute the "current value" of the facet)
 * are stored in another, mutable object - "FacetState", which is 
 * associated with one particular facet.  Facet may have a method though, 
 * which can receive the "current value p" as an explicit parameter
 * and build a SQL or SPARQL clause, a boolean comparison class
 * or any other artifact, which is useful for composite filtering process.</p>
 * 
 * 
 * <h3>FacetStates</h3>
 * 
 * <p>FacetState is a (mutable) object, which is typically created for
 * some User session. Some of FacetStates may be persisted (e.g. to 
 * preserve user's preferences for navigation). 
 * FacetState holds the current value (and, possibly, default value) for
 * the given facet and particular user's session. User may deactivate
 * certain facetstates, in this case they disappear from the UI and
 * do not participate in the filtering process.</p>
 * 
 * <p>Each FacetState class has methods to alter the state of the facet. 
 * The behaviour of the state-altering methods may depend on underlying
 * ontology or any other restrictions. For example, we can have
 * Increment-Decrement facet state, which can hold a single integer
 * as its current value, and allows only two operations (possibly available
 * through the users UI) - increment by one and decrement by one. 
 * A different facetState class may also hold a single integer as its current value, 
 * but it allow arbitrary changes to this value (e.g. through a textbox in user's UI).</p>
 * 
 * <p>FacetStates may also need to be associated with ActionObjects 
 * (holding input values provided by the users for each interaction),
 * client-side and server-side validators, Velocity templates, etc. 
 * But these are independent objects, which should be 
 * associated with a facetstate through a configuration file, 
 * and should not be hard-coded.</p>
 */
package lv.webkursi.mtest.core.components.facets;