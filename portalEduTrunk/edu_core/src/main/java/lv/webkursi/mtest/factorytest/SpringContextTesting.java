package lv.webkursi.mtest.factorytest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class SpringContextTesting extends ContextTesting {
	private ApplicationContext ctx;

	private long[] N;

	private String[] components;

	public SpringContextTesting() {
		ctx = new FileSystemXmlApplicationContext(
				"src/test/resources/testcontext.xml");
		N = new long[] { 100000L, 10000000L };
		components = new String[] { "component_prototype",
				"component_singleton" };
	}

	public static void main(String[] args) {
		SpringContextTesting ctxTesting = new SpringContextTesting();
		for (int j = 0; j < ctxTesting.N.length; j++) {
			ctxTesting.testingTemplate(ctxTesting.N[j],
					ctxTesting.components[j]);
		}
	}

	@Override
	public SampleComponent lookup(Object comonentId) {
		return (SampleComponent) ctx.getBean((String) comonentId);
	}
}
