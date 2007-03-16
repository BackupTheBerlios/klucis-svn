package lv.webkursi.mtest.core.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lv.webkursi.mtest.core.data.DBModelFactory;
import lv.webkursi.mtest.core.data.InitUtility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class DBInitController implements Controller {
	private String dbDriver;

	private String dbType;

	private String dbUrl;

	private String dbUser;

	private String dbPasswd;
	
	private Log log = LogFactory.getLog(DBInitController.class);

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

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView result = new ModelAndView("dbinit_message");
		DBModelFactory factory = new DBModelFactory();
		factory.setDbDriver(dbDriver);
		factory.setDbPasswd(dbPasswd);
		factory.setDbType(dbType);
		factory.setDbUrl(dbUrl);
		factory.setDbUser(dbUser);
		factory.setModelName("portaledu");
		InitUtility initUtility = new InitUtility();
		initUtility.setFactory(factory);
		try {
			initUtility.initModel();
		} catch (Exception e) {
			log.error(e);
			result.addObject("error", e);
		}		
		return result;
	}
}
