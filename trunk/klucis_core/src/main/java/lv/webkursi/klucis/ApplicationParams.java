package lv.webkursi.klucis;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A simple class to initialize application parameters 
 * from command-line arguments, a property file or Servlet Parameters. 
 * 
 * @author kap
 */
public class ApplicationParams {
	
	private final Log log = LogFactory.getLog(ApplicationParams.class);
	
	private Map<String,Object> params;
	
	
	/**
	 * Initialize default values of params; all the system parameters should start with "_"
	 */
	public ApplicationParams() {
		params = new HashMap<String,Object>();		
		params.put("_context", "context.xml");
	}
	
	public ApplicationParams(String[] args) {
		this();
		for (int i = 0; i < args.length ; i++) {
			String[] pair = args[i].split("=");
			if (pair.length == 2) {
				String key = pair[0];
				if (key.startsWith("--")) {
					params.put(pair[0].substring(2), pair[1]);
				}
				else {
					log.warn("Commandline parameter # " + i + " was ignored, should have syntax --key=value");					
				}
			}
			else {
				log.warn("Commandline parameter # " + i + " was ignored, should have syntax --key=value");
			}
		}
		
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	
	public Object getParam(String key) {
		return params.get(key);
	}
	


}
