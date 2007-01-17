package lv.webkursi.klucis.blocks;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractComponent implements Component {
	protected Map<String,Object> model = new HashMap<String,Object>();
	
	protected String name;
	
	public void setName(String name) {
		this.name = name;
	}

	/*
	public void setModel(Map<String,Object> model) {
		this.model = model;
	}
	*/
	
	public void addObject(String key, Object value) {
		model.put(key, value);
	}

}
