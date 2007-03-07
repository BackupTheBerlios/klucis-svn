package lv.webkursi.mtest.core.components.facets;

import java.util.Map;

import lv.webkursi.mtest.core.components.ModelAndViewComponent;
import lv.webkursi.mtest.core.mvc.ComponentStateManager;

import com.hp.hpl.jena.rdf.model.Literal;

public class IncDecFacetState extends ModelAndViewComponent implements FacetState {
	private Integer state;
	private Facet facet;

	public Integer getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
		if (stateManager != null) {
			stateManager.setComponentState(this, getStateAsString());
		}
	}

	public IncDecFacetState(String viewName, Map<String, Object> model) {
		super(viewName, model);
	}

	public void preRender() {
		if (state != null) {
			addObject("_state", state);
		}
		if (facet.getTitle() != null) {
			addObject("_title", facet.getTitle());
		}
		addObject("_name", getName());
		addObject("_linkHelper", new LinkHelper(this));
	}

	public Facet getFacet() {
		return facet;
	}

	public void setFacet(Facet facet) {
		this.facet = facet;
	}

	public void setStateFromLiteral(Literal literal) {
		setState(literal.getInt());
	}
	
	public ComponentStateManager getStateManager() {
		return stateManager;
	}

	@Override
	public void setStateFromString(String state) {
		setState(Integer.parseInt(state));
	}
	
	@Override
	public String getStateAsString() {
		return getState().toString();
	}	

	@Override
	public void doAction(String action) {
		if (action.equals("inc")) {
			state++;
		} else if (action.equals("dec")) {
			state--;
		}
		setState(state);
	}
}
