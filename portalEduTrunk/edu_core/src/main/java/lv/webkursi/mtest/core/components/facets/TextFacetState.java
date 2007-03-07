package lv.webkursi.mtest.core.components.facets;

import java.util.Map;

import lv.webkursi.mtest.core.components.ModelAndViewComponent;
import lv.webkursi.mtest.core.mvc.ComponentStateManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hp.hpl.jena.rdf.model.Literal;

public class TextFacetState extends ModelAndViewComponent implements FacetState {

	private String state;
	private Facet facet;
	
	private Log log = LogFactory.getLog(TextFacetState.class);
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
		if (stateManager != null) {
			stateManager.setComponentState(this, getStateAsString());
		}
	}

	public TextFacetState(String viewName, Map<String, Object> model) {
		super(viewName, model);
	}

	/**
	 * Put things, which are necessary to display to the model
	 */
	public void preRender() {
		log.info("state is " + state + "(" + ((state == null) ? -1 : state.length()) + ") title is " + facet.getTitle());
		if (state != null) {
			addObject("_state", state);
		}
		if (facet.getTitle() != null) {
			addObject("_title", facet.getTitle());
		}
		addObject("_name", getName());
	}
	
	public void doAction(String action) {
		if (action.equals("set") && stateManager.hasComponentState(this)) {
			setStateFromString(stateManager.getComponentState(this));
		}
	}

	public Facet getFacet() {
		return facet;
	}

	public void setFacet(Facet facet) {
		this.facet = facet;
	}
	
	@Override
	public void setStateFromString(String state) {
		setState(state);
	}

	@Override
	public String getStateAsString() {
		return getState();
	}	

	public void setStateFromLiteral(Literal literal) {
		setState(literal.getString());
	}

	public ComponentStateManager getStateManager() {
		return stateManager;
	}
}
