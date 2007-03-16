package lv.webkursi.mtest.core.components.factories;

import java.util.HashMap;
import java.util.Map;

import lv.webkursi.mtest.core.components.Component;
import lv.webkursi.mtest.core.components.CompositeFacet;
import lv.webkursi.mtest.core.components.facets.FacetState;
import lv.webkursi.mtest.core.data.RdfUtilities;
import lv.webkursi.mtest.core.mvc.ServiceName;
import lv.webkursi.mtest.core.scopes.UserSettings;
import lv.webkursi.mtest.core.vocabulary.MTEST;
import lv.webkursi.mtest.core.vocabulary.PORTAL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Seq;

/**
 * Produces a CompositeModelAndViewComponent
 */
public class CompositeFacetFactory extends CompositeComponentFactory {

	private Log log = LogFactory.getLog(CompositeFacetFactory.class);

	private ComponentManager componentManager;

	/**
	 * Computes a component, which is unregistered with the ComponentManager.
	 * Outside clients should call the corresponding method in ComponentManager
	 * instead.
	 */
	public Component getComponent(Resource rComponent) {
		Map<String, Object> model = new HashMap<String, Object>();
		String viewName = rComponent.getRequiredProperty(MTEST.viewName)
				.getString();
		CompositeFacet result = new CompositeFacet(viewName, model);
		Seq systemComponentSeq = RdfUtilities.findFacetAddList(rComponent,
				PORTAL.System);
		UserSettings userSettings = (UserSettings) serviceFactory
				.getService(ServiceName.UserSettings);
		Seq userComponentSeq = RdfUtilities.findFacetAddList(rComponent,
				userSettings.getUserResource());
		componentManager = (ComponentManager) serviceFactory
				.getService(ServiceName.ComponentManager);
		addFacetsFromSeq(result, systemComponentSeq);
		log.info("Adding user facets " + userSettings.getUserResource());
		addFacetsFromSeq(result, userComponentSeq);
		populateModel(result, rComponent);
		return result;
	}

	private void addFacetsFromSeq(CompositeFacet result, Seq seq) {
		if (seq == null) {
			return;
		}
		NodeIterator componentIterator = seq.iterator();
		while (componentIterator.hasNext()) {
			Resource componentDesc = (Resource) componentIterator.nextNode();
			Resource rFacet = componentDesc.getRequiredProperty(MTEST.facet)
					.getResource();
			log.info("rFacet = " + rFacet);
			FacetState component = (FacetState) componentManager.getComponent(
					rFacet, true);
			component.setStateFromLiteral((Literal) componentDesc
					.getRequiredProperty(MTEST.state).getObject());
			result.addFacet(component);
		}
	}
}
