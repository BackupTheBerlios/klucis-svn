package lv.webkursi.mtest.core.data;

import java.io.ByteArrayInputStream;

import lv.webkursi.mtest.core.vocabulary.MTEST;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

/**
 * This class erases the data model in the relational database; replaces it with
 * "seed model" containing just one administrator user.
 * 
 */
public class InitUtility {

	public String seedModel = "@prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> . "
			+ "@prefix mtest: <http://www.webkursi.lv/schema/mtest#> . "
			+ "mtest:admin a mtest:User ; "
			+ "  mtest:email \"admin@admin.com\" ; "
			+ "  mtest:name \"Admin\" ; "
			+ "  mtest:userName \"admin\" ; "
			+ "  mtest:password \"admin\" .";
	
	protected DBModelFactory factory;

	public Model getModel() throws Exception {
		return (Model)factory.getObject();
	}

	public void initModel() throws Exception {
		Model model = ModelFactory.createDefaultModel();
		ByteArrayInputStream bis = new ByteArrayInputStream(seedModel
				.getBytes());
		model.read(bis, MTEST.NS, "N3");
		Model model1 = getModel();
		model1.removeAll();
		model1.add(model);
	}

	public void printModel() throws Exception {
		Model model1 = getModel();
		System.out.println("Model size is " + model1.size());
		model1.write(System.out, "N3");
	}

	public DBModelFactory getFactory() {
		return factory;
	}

	public void setFactory(DBModelFactory factory) {
		this.factory = factory;
	}

	public String getSeedModel() {
		return seedModel;
	}

	public void setSeedModel(String seedModel) {
		this.seedModel = seedModel;
	}
	
	/**
	 * This can be used to initialize the RDF Data model from 
	 * command line. 
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		InitUtility initUtility = new InitUtility();
		initUtility.setFactory(new DBModelFactory());
		initUtility.initModel();
		initUtility.printModel();
	}
}
