package lv.webkursi.mtest.core.util;

/**
 * The data structure which participates in facet+state lists at various scopes; 
 * the 2nd cascade combines these lists and generates a single CompositeFacet
 * 
 */
public class FacetConfigurationEntry implements Comparable {

	private String facetName;

	private Object state;

	private int order;

	public int compareTo(Object o) {
		FacetConfigurationEntry arg = (FacetConfigurationEntry)o;
		if (this.order != arg.order) {
			return this.order - arg.order;
		}
		else {
			return this.facetName.compareTo(arg.facetName);
		}
	}

	public String getFacetName() {
		return facetName;
	}

	public void setFacetName(String facetName) {
		this.facetName = facetName;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public Object getState() {
		return state;
	}

	public void setState(Object state) {
		this.state = state;
	}
}
