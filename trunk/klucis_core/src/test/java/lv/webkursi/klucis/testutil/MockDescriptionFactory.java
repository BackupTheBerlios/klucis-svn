package lv.webkursi.klucis.testutil;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

/**
 * Cache of various small RDF models used for JUnit testing
 * 
 * @author kap
 * 
 */
public class MockDescriptionFactory {
	
	public static final String PREFIXES = "@prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> . "
		+ "@prefix klucis: <http://www.webkursi.lv/schema/20061008/klucis#> . "
		+ "@prefix : <http://example.com/bildes#> . ";

	private Map<String, Model> modelCache = new HashMap<String, Model>();

	private static MockDescriptionFactory instance = new MockDescriptionFactory();

	/*
	private static final String COMPONENT_DEFAULTS = ":applicationDefaultPath "
			+ "a klucis:AbstractComponent ; " + "a klucis:Path ; "
			+ "klucis:context \"/\" ; " + "klucis:hasColor \"black\" ; "
			+ "klucis:hasViewName \"Path\" ; " + "klucis:hasWidth \"200\" ; "
			+ "klucis:hasHeight \"200\" ; " + "klucis:hasStrokeWidth \"0\" . "

			+ ":applicationDefaultHorizontalRow "
			+ "a klucis:AbstractComponent ; " + "a klucis:HorizontalRow ; "
			+ "klucis:context \"/\" ; " + "klucis:hasGap \"60\" ; "
			+ "klucis:hasViewName \"HorizontalRow\" . "

			+ ":applicationDefaultRectangle " + "a klucis:AbstractComponent ; "
			+ "a klucis:Rectangle ; " + "klucis:context \"/\" ; "
			+ "klucis:hasLabel \"\" ; " + "klucis:hasRectWidth \"200\" ; "
			+ "klucis:hasRectHeight \"200\" ; " + "klucis:rotate \"0\" ; "
			+ "klucis:showRectangle \"true\" ; "
			+ "klucis:hasViewName \"Rectangle\" . ";
			*/
	
	
	/*
	 * After Vladimir Potapov's correction
	 */
	private static final String COMPONENT_DEFAULTS = ":applicationDefaultPath "
		   + "a klucis:AbstractComponent ; " + "a klucis:Path ; "
		   + "klucis:context \"/\" ; " + "klucis:hasColor \"black\" ; " 
		   + "klucis:hasViewName \"Path\" ; " + "klucis:hasWidth \"200\" ; "
		   + "klucis:hasHeight \"200\" ; " + "klucis:hasStrokeWidth \"0\" . " 

		   + ":applicationDefaultHorizontalRow "
		   + "a klucis:AbstractComponent ; " + "a klucis:HorizontalRow ; "
		   + "klucis:context \"/\" ; " + "klucis:hasGap \"60\" ; " 
		   + "klucis:hasViewName \"HorizontalRow\" . "
		            /*!!!!!some changes hasRectHeight->hasCoreHeight possible errors
		                    hasRectWidth->hasCoreWidth
		           */ 
		   + ":applicationDefaultRectangle " + "a klucis:AbstractComponent ; "
		   + "a klucis:Rectangle ; " + "klucis:context \"/\" ; "
		/*!!!*/     + "klucis:hasLabel \"\" ; " + "klucis:hasCoreWidth \"200\" ; " 
		/*!!!*/  + "klucis:hasCoreHeight \"200\" ; " + "klucis:rotate \"0\" ; "
		   + "klucis:showRectangle \"true\" ; "
		   + "klucis:hasViewName \"Rectangle\" . "; 


	/**
	 * Singleton - constructor called only once per class initialization.
	 * 
	 */
	private MockDescriptionFactory() {
	}

	/**
	 * Get the unique instance
	 * 
	 * @return
	 */
	public static MockDescriptionFactory getInstance() {
		return instance;
	}

	/**
	 * Create a new model from the description in N3 notation (or return a
	 * previously created one, which was associated with the specified key).
	 */
	public Model createModel(String key, String description) {
		if (modelCache.containsKey(key)) {
			return modelCache.get(key);
		}
		Model result = ModelFactory.createDefaultModel();
		ByteArrayInputStream bis = new ByteArrayInputStream(description
				.getBytes());
		result.read(bis, null, "N3");

		modelCache.put(key, result);
		return result;
	}

	public Model createModelWithDefaults(String key, String description) {
		if (modelCache.containsKey(key)) {
			return modelCache.get(key);
		}
		Model result = ModelFactory.createDefaultModel();
		ByteArrayInputStream bis = new ByteArrayInputStream(description
				.getBytes());
		result.read(bis, null, "N3");
		bis = new ByteArrayInputStream((PREFIXES + COMPONENT_DEFAULTS).getBytes());
		result.read(bis, null, "N3");
		modelCache.put(key, result);
		return result;
	}

	/**
	 * Find model by the specified key
	 * 
	 * @param key
	 * @return
	 */
	public Model findModel(String key) {
		return modelCache.get(key);
	}

	/**
	 * Remove a key from the model (e.g. prior to store another model by the
	 * same key)
	 * 
	 * @param key
	 */
	public void removeModel(String key) {
		modelCache.remove(key);
	}
}
