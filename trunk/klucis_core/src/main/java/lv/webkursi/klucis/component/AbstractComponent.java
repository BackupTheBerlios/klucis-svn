package lv.webkursi.klucis.component;


/**
 * This is a top class for all components
 * 
 * @author kap
 */
public abstract class AbstractComponent implements Component {

	protected ComponentManager componentManager;

	public void setComponentManager(ComponentManager componentManager) {
		this.componentManager = componentManager;
	}

	protected String id;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}
