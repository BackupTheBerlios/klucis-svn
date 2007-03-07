package lv.webkursi.mtest.core.components.factories;

import java.util.HashMap;
import java.util.Map;

import lv.webkursi.mtest.core.components.Component;
import lv.webkursi.mtest.core.components.ResultPane;
import lv.webkursi.mtest.core.data.ResultsSource;
import lv.webkursi.mtest.core.mapping.Mapper;
import lv.webkursi.mtest.core.mvc.ServiceName;
import lv.webkursi.mtest.mvc.vocabulary.MARS;

import com.hp.hpl.jena.rdf.model.Resource;

/**
 * A factory, which upon getObject() returns the ModelAndView - 
 * possibly composite object to be displayed in the results pane. 
 */
public class ResultPaneFactory extends CompositeComponentFactory {
	
	public Component getComponent(Resource rComponent) {
		Map<String, Object> model = new HashMap<String, Object>();
		String viewName = rComponent.getRequiredProperty(MARS.viewName)
		.getString();
		ResultPane result = new ResultPane(viewName, model);	
		
        Resource rSource = rComponent.getRequiredProperty(MARS.source).getResource();
        ComponentManager componentManager = (ComponentManager)serviceFactory.getService(ServiceName.ComponentManager);
        Component component = componentManager.getComponent(rSource,true);
        result.setSource((ResultsSource)component);
        
        Resource rMapper = rComponent.getRequiredProperty(MARS.resultMapper).getResource();
        Mapper mapper = (Mapper)componentManager.getComponent(rMapper, true);
        result.setMapper(mapper);

        this.populateModel(result, rComponent);
        componentManager.addDoStuffListener(result);
        return result;
	}	
}
