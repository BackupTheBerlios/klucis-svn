/**
 * <p>Some essential classes from the portal's core domain are given 
 * here - e.g. the basic concepts of User, Role, Pageset, etc. The services to 
 * load, search, remove these domain objects are given in a sibling package "services".</p>
 * 
 * <p>On one hand there is the core domain - some finite set of classes, which ensure
 * correct functioning of the portal; their definitions certainly  
 * belong in this package. On the other hand, there may be unlimited number of 
 * "subject area" classes and beans, holding values of some custom RDF data; 
 * which may include people, projects and other data items. 
 * Such subject area related class definitions do not belong in core, but
 * rather in various extension packages.</p>
 * 
 * <p>A question arises: what if we want the user/role discovery and authentication 
 * be part of the same RDF-access framework, which is used to load 
 * the portal's other data? In such cases we might need to redefine the
 * class used for user authentication to point somewhere else. It could
 * even be useful to have an interface "User", which can be implemented
 * by various classes. But so far these core domain classes are hard-coded 
 * and cannot be replaced by anything else. (The first step to ensure
 * flexibility would be to distinguish "User" interface from "UserImpl" - 
 * its standard implementation.</p> 
 * 
 */
package lv.webkursi.mtest.core.data;