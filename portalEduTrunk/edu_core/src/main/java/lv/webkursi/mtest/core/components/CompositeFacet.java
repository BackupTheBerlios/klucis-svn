package lv.webkursi.mtest.core.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lv.webkursi.mtest.core.components.facets.FacetState;
import lv.webkursi.mtest.core.data.ResultsSource;


/**
 * Extend CompositeModelAndViewComponent, since this may contain also other subcomponents, 
 * which are not facets.
 */
public class CompositeFacet extends CompositeModelAndViewComponent implements ResultsSource {
	
	public static final String MODELKEY_SUBNODES = "_subNodes";
	
	private List<FacetState> facetStates = new ArrayList<FacetState>();

	public CompositeFacet(String viewName, Map<String, Object> model) {
		super(viewName, model);
	}
	
    @SuppressWarnings("unchecked")
	public void addFacet(FacetState facetState) {
    	facetStates.add(facetState);
    }
    
    public void preRender() {
    	addObject(MODELKEY_SUBNODES, facetStates);
    }

    public List<Object> getResults() {
    	List<Object> results = new ArrayList<Object>();
        for (FacetState facetState : facetStates) {
//            results.add(facetState.getState());
        	results.add(facetState);
        }
        return results;
    }
}
