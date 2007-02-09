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
	private Map<String, Model> modelCache = new HashMap<String, Model>();

	private static MockDescriptionFactory instance = new MockDescriptionFactory();

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
