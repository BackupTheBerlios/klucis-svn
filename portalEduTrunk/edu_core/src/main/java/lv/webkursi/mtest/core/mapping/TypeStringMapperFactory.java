package lv.webkursi.mtest.core.mapping;

import java.util.HashMap;
import java.util.Map;

import lv.webkursi.mtest.core.components.Component;
import lv.webkursi.mtest.core.components.factories.ComponentFactory;
import lv.webkursi.mtest.mvc.vocabulary.MARS;

import com.hp.hpl.jena.rdf.model.Bag;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 * Produces a CompositeModelAndViewComponent
 */
public class TypeStringMapperFactory implements ComponentFactory {
	
	/**
	 * Outside clients should call the getComponent method in ComponentManager instead. 
	 */
	public Component getComponent(Resource rComponent) { 
		TypeStringMapper result = new TypeStringMapper();
		Bag componentBag = rComponent.getRequiredProperty(
				MARS.typeStringMap).getBag();
		Map<String, Resource> typeStringMap = new HashMap<String,Resource>();
		for (NodeIterator componentIterator = componentBag.iterator(); componentIterator
				.hasNext();) {
			Resource entry = (Resource) componentIterator.nextNode();
			String key = entry.getRequiredProperty(MARS.key).getString();
			Resource value = entry.getRequiredProperty(MARS.value).getResource();
			typeStringMap.put(key, value);
		}
		result.setTypeStringMap(typeStringMap);
		Resource defaultValue = rComponent.getRequiredProperty(
				MARS.defaultValue).getBag();
		result.setDefaultValue(defaultValue);
		return result;
	}

}
