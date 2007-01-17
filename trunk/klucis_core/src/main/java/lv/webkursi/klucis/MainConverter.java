package lv.webkursi.klucis;

import lv.webkursi.klucis.blocks.TopComponent;
import lv.webkursi.klucis.vocabulary.BLOCKS;

import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;

public class MainConverter {

	private Model model;

	private ComponentManager componentManager;

	public void setModel(Model model) {
		this.model = model;
	}

	public void setComponentManager(ComponentManager componentManager) {
		this.componentManager = componentManager;
	}

	public static void main(String[] args) {
		FileSystemXmlApplicationContext ctx = new FileSystemXmlApplicationContext(
				"src/main/resources/context.xml");
		MainConverter converter = (MainConverter) ctx.getBean("converter");
		ResIterator i = converter.model.listSubjectsWithProperty(BLOCKS.output);
		while (i.hasNext()) {
			Resource rComponent = i.nextResource();
			TopComponent component = (TopComponent)converter.componentManager.getComponent(rComponent);
			component.output();
		}
	}
}
