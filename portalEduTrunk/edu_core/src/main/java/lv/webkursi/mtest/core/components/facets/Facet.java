package lv.webkursi.mtest.core.components.facets;

/**
 * <p>Embodies unchanging aspects of facets for facetted browse</p>
 * 
 * <p>This should be immutable. It does not have mutator methods to set different
 * attribute values; attribute values themselves are immutable. Nobody
 * can override this class to make it mutable. Please keep the things that way.
 * There is one caveat - someone can create a facet with a mutable defaultState
 * and then change its internal representation.</p>
 */
public final class Facet {
	private final String title;	
	private final Object defaultState;
	
	public Facet(String title, Object defaultState) {
		this.title = title;
		this.defaultState = defaultState;
	}

	public String getTitle() {
		return title;
	}

	public Object getDefaultState() {
		return defaultState;
	}
}
