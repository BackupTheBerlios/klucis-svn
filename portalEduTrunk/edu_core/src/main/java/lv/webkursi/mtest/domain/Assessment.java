package lv.webkursi.mtest.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Assessment {
	public Date startDate;
	
	public Date endDate;
	
	public Map<Module,Integer> weights = new HashMap<Module,Integer>();
	
	public void addModule(Module m, int weight) {
		weights.put(m, weight);
	}
	
	public void removeModule(Module m) {
		weights.remove(m);
	}

	
	// GET/SET methods
	
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Map<Module, Integer> getWeights() {
		return weights;
	}

	public void setWeights(Map<Module, Integer> weights) {
		this.weights = weights;
	}	
}