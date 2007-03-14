package lv.webkursi.mtest.core.mvc;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lv.webkursi.mtest.core.components.ModelAndViewComponent;
import lv.webkursi.mtest.core.components.factories.ComponentManager;
import lv.webkursi.mtest.core.data.RdfUtilities;
import lv.webkursi.mtest.core.mvc.rule.Rule;
import lv.webkursi.mtest.core.mvc.rule.RuleList;
import lv.webkursi.mtest.mvc.vocabulary.MARS;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.mvc.Controller;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Seq;

public class FixedController implements Controller, ServiceFactoryAware {

	private ServiceFactory serviceFactory;

	private Log log = LogFactory.getLog(FixedController.class);
	
	protected String getPageSetName(HttpServletRequest request) {
		String pageSetName = request.getPathInfo();
		int slashPos = pageSetName.lastIndexOf("/");
		if (slashPos >= 0 ) {
			pageSetName = pageSetName.substring(slashPos+1);
		}
		log.debug("  imageName =  '" + pageSetName + "'");
		return pageSetName;
	}


	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// Find the relevant pageset in the static RDF config file
		Model portalDescription = (Model) serviceFactory
				.getService(ServiceName.PortalDescription);
		//String pageSetName = request.getServletPath();
		String pageSetName = getPageSetName(request);
		System.err.println("== pageSetName = " + pageSetName);
		Resource pageSet = RdfUtilities.getPageSetByPath(portalDescription,
				pageSetName);
		log.info("REQUEST FOR PAGESET " + pageSet.getURI());

		// Initialize and set stateManager and componentManager
		// Inject stateManager into componentManager
		ComponentStateManager stateManager = new URLStateManager();
		stateManager.setServletPath(request.getServletPath().substring(1));
		ComponentManager componentManager = (ComponentManager) serviceFactory
				.getService(ServiceName.ComponentManager);
		componentManager.setStateManager(stateManager);

		// Create the rootComponent and all the other standard components;
		// (they are implicitly registered with the ComponentManager)
		Resource rRootComponent = pageSet.getRequiredProperty(
				MARS.rootComponent).getResource();
		ModelAndViewComponent rootComponent = (ModelAndViewComponent) componentManager
				.getComponent(rRootComponent, true);

		// Load the interaction state into these components
		stateManager.reloadState(request);

		// Execute the action, if any
		stateManager.doAction(request);

		// ///////////////////////////////////////////////////////////////////
		// Load the rules for the pageset and run them
		// this is a temporary fixup to connect rules to the right components
		// ///////////////////////////////////////////////////////////////////
		Seq rRuleList = pageSet.getRequiredProperty(MARS.ruleList).getSeq();
		List<Rule> ruleList = new ArrayList<Rule>();
		for (NodeIterator i = rRuleList.iterator(); i.hasNext();) {
			Rule rule = (Rule) componentManager.getComponent((Resource) i
					.nextNode(), true);
			ruleList.add(rule);
		}
		RuleList rules = new RuleList();
		rules.setRules(ruleList);
		rules.ApplyRules();

		// Execute remaining action on newly added components
		stateManager.doAction(request);

		// Execute queries, generate resultsets
		componentManager.doStuff();

		// Prepare to render. E.g. execute queries and load their results into
		// relevant components
		componentManager.preRender();

		// Render
		ViewResolver viewResolver = (ViewResolver) serviceFactory
				.getService(ServiceName.ViewResolver);
		rootComponent.addObject("_renderContext", new RenderContext(request,
				response, viewResolver));
		return rootComponent;
	}

	public void setServiceFactory(ServiceFactory serviceFactory) {
		this.serviceFactory = serviceFactory;
	}

	public ServiceFactory getServiceFactory() {
		return serviceFactory;
	}
}
