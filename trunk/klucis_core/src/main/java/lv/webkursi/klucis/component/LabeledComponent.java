package lv.webkursi.klucis.component;

/**
 * Common supertype for all those graphics components, which may have labels, i.e. which 
 * should be alligned ignoring the attached labels. 
 * @author kap
 */
public interface LabeledComponent extends Component {
	public String getLabel();
	
	public float getCoreHeight();
	
	public float getCoreWidth();
}
