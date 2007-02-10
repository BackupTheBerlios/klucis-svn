package lv.webkursi.klucis;

import lv.webkursi.klucis.component.ComponentManager;
import lv.webkursi.klucis.component.geom2d.TopComponent;
import lv.webkursi.klucis.event.LifecycleEvent;
import lv.webkursi.klucis.vocabulary.KLUCIS;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 * A command-line utility to call KLUCIS. Currently it allows one
 * parameter (--_context=[Spring context file path]) and processes 
 * all images configured in RDF description file (specified in Spring's config)
 * @author kap
 *
 */
public class KlucisMain {
	
	private static Log log = LogFactory.getLog(KlucisMain.class);

	protected Model model;

	protected ComponentManager componentManager;

	public void setModel(Model model) {
		this.model = model;
	}

	public void setComponentManager(ComponentManager componentManager) {
		this.componentManager = componentManager;
	}

	public static void main(String[] args) {
		ApplicationParams params = new ApplicationParams(args);
		String contextPath = (String) params.getParam("_context");
		FileSystemXmlApplicationContext ctx = new FileSystemXmlApplicationContext(
				contextPath);
		KlucisMain converter = (KlucisMain) ctx.getBean("converter");
		ResIterator i = converter.model.listSubjectsWithProperty(KLUCIS.hasFileName);
		while (i.hasNext()) {
			Resource rComponent = i.nextResource();
			log.info("Output component " + rComponent);
			TopComponent component = (TopComponent) converter.componentManager
					.getStaticComponent(rComponent);
			converter.doLifecycle();
			component.output();
		}
	}

	protected void doLifecycle() {
		componentManager.announce(this, LifecycleEvent.Kind.prepareToRender);
	}
}
