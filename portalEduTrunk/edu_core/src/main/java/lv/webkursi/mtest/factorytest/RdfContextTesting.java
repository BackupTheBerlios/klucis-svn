package lv.webkursi.mtest.factorytest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;

public class RdfContextTesting extends ContextTesting {
	private ApplicationContext ctx;

	private long[] N;

	private Resource[] components;

	private Model model;

	private SampleComponentFactory componentFactory;

	public RdfContextTesting() {
		ctx = new FileSystemXmlApplicationContext(
				"src/test/resources/testcontext.xml");
		model = (Model) ctx.getBean("rdfContext");
		componentFactory = new SampleComponentFactory(model);
		N = new long[] { 1000000L, 100000000L };
		components = new Resource[] {
				model
						.getResource("http://www.hpl.hp.com/schema/portal#component_prototype"),
				model
						.getResource("http://www.hpl.hp.com/schema/portal#component_singleton") };
	}

	public static void main(String[] args) {
		RdfContextTesting ctxTesting = new RdfContextTesting();
		for (int j = 0; j < ctxTesting.N.length; j++) {
			ctxTesting.testingTemplate(ctxTesting.N[j],ctxTesting.components[j]);
		}
	}

	@Override
	public SampleComponent lookup(Object comonentId) {
		return componentFactory.getComponent((Resource)comonentId);
	}
}
