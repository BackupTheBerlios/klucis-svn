package lv.webkursi.mtest.core.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lv.webkursi.mtest.core.data.ResultsSource;
import lv.webkursi.mtest.core.mapping.Mapper;


/**
 *
 */
public class ResultPane extends CompositeModelAndViewComponent implements DoStuffListener {
    private ResultsSource source;
    private Mapper mapper;
    private ArrayList <Component> resultMAVs = new ArrayList<Component> ();
    
    public ResultPane(String viewName, Map<String, Object> model) {
		super(viewName, model);
	}
    
    public void doStuff() {
    	resultMAVs = new ArrayList<Component> ();
    	List<Object> results = source.getResults();
    	for (Object object: results) {
    		addResult(mapper.getComponent(object));
    	}
    }
    
    public void addResult(Component c) {
    	resultMAVs.add(c);
    }

	public void preRender() {
        this.addObject("results", resultMAVs);
	}

    
    /**
     * @param source The source to set.
     */
    public void setSource(ResultsSource source) {
        this.source = source;
    }

	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

}
