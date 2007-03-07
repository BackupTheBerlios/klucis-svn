package lv.webkursi.mtest.core.mvc.rule;

import lv.webkursi.mtest.core.components.facets.IncDecFacetState;

/**
 *
 */
public class IntegerRangeCondition implements Predicate {
    
    private int lower = Integer.MIN_VALUE;
    private int upper = Integer.MAX_VALUE;
    private IncDecFacetState facetState;
    
    public boolean isTrue() {
        int val = ((Integer)facetState.getState()).intValue();
        return (lower <= val) && (val <= upper);
    }

    /**
     * @param facetState The facetState to set.
     */
    public void setFacetState(IncDecFacetState facetState) {
        this.facetState = facetState;
    }

    /**
     * @param lower The lower to set.
     */
    public void setLower(int lower) {
        this.lower = lower;
    }

    /**
     * @param upper The upper to set.
     */
    public void setUpper(int upper) {
        this.upper = upper;
    }
}
