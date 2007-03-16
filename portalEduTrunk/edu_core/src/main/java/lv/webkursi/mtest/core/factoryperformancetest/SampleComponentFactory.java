package lv.webkursi.mtest.core.factoryperformancetest;

import java.util.HashMap;
import java.util.Map;

import lv.webkursi.mtest.core.vocabulary.MTEST;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

public class SampleComponentFactory {

	Model rdfContext;

	private Property pName1;

	private Property pName2;

	private Property pScope;

	private Map<Resource, SampleComponent> cache = new HashMap<Resource, SampleComponent>();

	public SampleComponentFactory(Model rdfContext) {
		this.rdfContext = rdfContext;
		pName1 = rdfContext.getProperty(MTEST.NS + "name1");
		pName2 = rdfContext.getProperty(MTEST.NS + "name2");
		pScope = rdfContext.getProperty(MTEST.NS + "scope");
	}

	public SampleComponent getComponent(Resource rComponent) {
		SampleComponent result = cache.get(rComponent);
		if (result != null) {
			return result;
		}

		result = new SampleComponent();
		String scope = rComponent.getRequiredProperty(pScope).getString();
		String name1 = rComponent.getRequiredProperty(pName1).getString();
		String name2 = rComponent.getRequiredProperty(pName2).getString();
		result.setName1(name1);
		result.setName2(name2);
		if (scope.equals("singleton")) {
			cache.put(rComponent, result);
		}
		return result;
	}
}
