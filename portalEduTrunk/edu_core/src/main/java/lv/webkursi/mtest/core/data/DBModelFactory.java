package lv.webkursi.mtest.core.data;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.FactoryBean;

import com.hp.hpl.jena.db.DBConnection;
import com.hp.hpl.jena.db.IDBConnection;
import com.hp.hpl.jena.db.ModelRDB;
import com.hp.hpl.jena.rdf.model.Model;

public class DBModelFactory implements FactoryBean {
	private static final String DB_URL = "jdbc:mysql://localhost/portaledu_rdf";

	private static final String DB_USER = "root";

	private static final String DB_PASSWD = "root";

	private static final String DB_TYPE = "MySQL";

	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";

	private static final String MODEL_NAME = "portaledu";

	private Log logger = LogFactory.getLog(DBModelFactory.class);

	IDBConnection conn = null;
	
	private static ModelRDB model = null;

	public boolean isSingleton() {
		return true;
	}

	public Class getObjectType() {
		return Model.class;
	}

	public Object getObject() throws Exception {
		if (model != null) {
			return model;
		}
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		conn = new DBConnection(DB_URL, DB_USER, DB_PASSWD, DB_TYPE);
		logger.info("Opened JDBC database connection");
		if (!conn.containsModel(MODEL_NAME)) {
			model = ModelRDB.createModel(conn, MODEL_NAME);
		} else {
			model = ModelRDB.open(conn, MODEL_NAME);
		}

		return model;
	}
}
