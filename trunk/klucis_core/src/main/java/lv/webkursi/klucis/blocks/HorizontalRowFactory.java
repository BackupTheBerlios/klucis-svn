package lv.webkursi.klucis.blocks;

import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Seq;

public class HorizontalRowFactory extends ComponentFactory {
	
	public Component getComponent(Resource rComponent) {
		HorizontalRow result = new HorizontalRow();
		Seq seq = (Seq)rComponent.as(Seq.class);
		List<Component> components = new ArrayList<Component>();
		for (NodeIterator i = seq.iterator(); i.hasNext(); ) {
			Resource rChild = (Resource)i.nextNode();
			Component child = componentManager.getComponent(rChild);
			components.add(child);
		}
		result.setComponents(components);
		return result;

	}

}
