package lv.webkursi.klucis.component;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This is a top class for all components
 * 
 * @author kap
 */
public abstract class AbstractComponent implements Component {

	protected ComponentManager componentManager;

	private final Log log = LogFactory.getLog(AbstractComponent.class);

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

	/**
	 * The default behavior is to ignore action and issue a warning; subclasses
	 * which support interaction state can override this
	 */
	public void doAction(String action) {
		log.warn("Action '" + action + "' ignored for component id=" + getId() + " of type " + getClass().getName());
	}

	/**
	 * The default behavior is to ignore state and issue a warning; subclasses
	 * which support interaction state can override this
	 */
	public void setInteractionState(String state) {
		log.warn("InteractionState '" + state + "' ignored for component id=" + getId() + " of type " + getClass().getName());
	}
}
