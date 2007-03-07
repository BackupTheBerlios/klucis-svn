package lv.webkursi.mtest.core.components.facets;

import lv.webkursi.mtest.core.components.Component;
import lv.webkursi.mtest.core.mvc.ComponentStateManager;

import com.hp.hpl.jena.rdf.model.Literal;

public interface FacetState extends Component {
	public void preRender();
	public void setStateFromLiteral(Literal r);
	public Object getState();
	public void setFacet(Facet facet);
	public Facet getFacet();
	public ComponentStateManager getStateManager();
}
