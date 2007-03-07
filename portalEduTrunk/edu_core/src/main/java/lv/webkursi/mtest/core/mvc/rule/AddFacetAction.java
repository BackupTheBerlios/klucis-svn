package lv.webkursi.mtest.core.mvc.rule;

import lv.webkursi.mtest.core.components.CompositeFacet;
import lv.webkursi.mtest.core.components.facets.FacetState;
import lv.webkursi.mtest.core.mvc.ComponentStateManager;


/**
 * 
 */
public class AddFacetAction implements Action {

	private ComponentStateManager stateManager;
	private FacetState facet;
	private CompositeFacet compositeFacet;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hp.hpl.mars.portal.core.mvc.rule.Action#doAction()
	 */
	public void doAction() {
//		ComponentManager componentManager = stateManager.getComponentManager();
//		ComponentManager componentManager = (ComponentManager) serviceFactory
//				.getService(ServiceName.ComponentManager);
		/*
		Model portalDescription = (Model) serviceFactory
		.getService(ServiceName.PortalDescription);
		Resource rFacet = portalDescription.getResource(facetUrl);
		FacetState facetState = (FacetState) componentManager.getComponent(rFacet,true);
		CompositeFacet compositeFacet = (CompositeFacet)componentManager.lookup("fixedCompositeFacet_");
		*/		
		compositeFacet.addFacet(facet);
	}

	/**
	 * @param facetUrl
	 *            The facetName to set.
	 */
//	public void setFacetUrl(String facetUrl) {
//		this.facetUrl = facetUrl;
//	}
	
	
	public void setStateManager(ComponentStateManager stateManager) {
		this.stateManager = stateManager;
	}

	public void setCompositeFacet(CompositeFacet compositeFacet) {
		this.compositeFacet = compositeFacet;
	}

	public void setFacet(FacetState facet) {
		this.facet = facet;
	}

	/*
	public ServiceFactory getServiceFactory() {
		return serviceFactory;
	}

	public void setServiceFactory(ServiceFactory serviceFactory) {
		this.serviceFactory = serviceFactory;
	}
	*/
}
