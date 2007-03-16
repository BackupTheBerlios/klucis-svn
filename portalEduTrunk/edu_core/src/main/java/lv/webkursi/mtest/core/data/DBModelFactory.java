package lv.webkursi.mtest.core.data;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.FactoryBean;

import com.hp.hpl.jena.db.DBConnection;
import com.hp.hpl.jena.db.IDBConnection;
import com.hp.hpl.jena.db.ModelRDB;
import com.hp.hpl.jena.rdf.model.Model;

public class DBModelFactory implements FactoryBean {
	private String dbUrl = "jdbc:mysql://localhost/portaledu_rdf";

	private String dbUser = "root";

	private String dbPasswd = "root";

	private String dbType = "MySQL";

	private String dbDriver = "com.mysql.jdbc.Driver";

	private String modelName = "portaledu";

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
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		conn = new DBConnection(dbUrl, dbUser, dbPasswd, dbType);
		logger.info("Opened JDBC database connection");
		if (!conn.containsModel(modelName)) {
			model = ModelRDB.createModel(conn, modelName);
		} else {
			model = ModelRDB.open(conn, modelName);
		}

		return model;
	}

	public String getDbDriver() {
		return dbDriver;
	}

	public void setDbDriver(String dbDriver) {
		this.dbDriver = dbDriver;
	}

	public String getDbPasswd() {
		return dbPasswd;
	}

	public void setDbPasswd(String dbPasswd) {
		this.dbPasswd = dbPasswd;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	
	
}
