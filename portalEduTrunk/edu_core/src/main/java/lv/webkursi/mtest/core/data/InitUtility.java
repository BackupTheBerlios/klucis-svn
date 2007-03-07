package lv.webkursi.mtest.core.data;

import java.io.InputStream;

import lv.webkursi.mtest.mvc.vocabulary.MARS;

import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

/**
 * This class erases the data model; make sure that it is called only once
 * 
 */
public class InitUtility {

	public static final String SEED_DATA = "edu_core/src/test/resources/seed_data.n3";

	public static final String TEST_CONTEXT = "edu_core/src/test/resources/testcontext.xml";

	public static Model getModel() {
		FileSystemXmlApplicationContext ctx = new FileSystemXmlApplicationContext(
				TEST_CONTEXT);
		return (Model) ctx.getBean("model");		
	}
	
	public static void initModel() {
		Model model = ModelFactory.createDefaultModel();
		InputStream in = FileManager.get().open(SEED_DATA);
		if (in == null) {
			throw new IllegalArgumentException("File: " + SEED_DATA
					+ " not found");
		}
		model.read(in, MARS.NS, "N3");
		Model model1 = getModel();
		model1.removeAll();
		model1.add(model);
	}

	public static void printModel() {
		Model model1 = getModel();
		System.out.println("Model size is " + model1.size());
		model1.write(System.out, "N3");
	}

	public static void main(String[] args) {
		initModel();
		printModel();
	}
}
