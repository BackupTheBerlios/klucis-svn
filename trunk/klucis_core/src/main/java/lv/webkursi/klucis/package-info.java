/**
 * Issues:
 * (1) Default values are passed in unnecessary complex way
 * (something only added to the context, if some non-default 
 * condition applies). Should be 2-stage or 3-stage 
 * inheritance mechanism to deal with the defaults. 
 * 
 * (2) Code could be shorter and more consistent, if 
 * whole widgets/components would be passed to the template
 * (rather than individual properties)
 * 
 * (3) Could do hasComponent-type components as 
 * an interface (or abstract class), which would
 * allow to center any component on top of any other one. 
 * 
 * (4) Unclear how this mechanism of velocity template
 * calling relates to one, which can be used in a Web application.
 * 
 * (5) Superfluous fileName - could use localName of the resource.
 * 
 * (6) Kerning (accurate horizontal spacing); vertical align
 * and horizontal align could improve the look of the pictures.
 * 
 * <h3>Technical improvements</h3>
 * 
 * <ul> 
 * <li>TODO list could be generated by XDoclet</li>
 * </ul>
 * 
 */

package lv.webkursi.klucis;

