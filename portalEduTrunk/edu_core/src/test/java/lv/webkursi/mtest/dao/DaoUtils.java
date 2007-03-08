package lv.webkursi.mtest.dao;

import java.io.File;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.HSQLDialect;
import org.hibernate.dialect.MySQLDialect;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class DaoUtils {
	
	public static SessionFactory getHsqldbSessionFactory() {
		Configuration configuration = new Configuration();

		configuration.setProperty(Environment.DRIVER, "org.hsqldb.jdbcDriver");
		configuration.setProperty(Environment.URL,
				"jdbc:hsqldb:mem:portaledu");
		configuration.setProperty(Environment.DIALECT, HSQLDialect.class
				.getName());
		configuration.setProperty(Environment.USER, "sa");
		configuration.setProperty(Environment.HBM2DDL_AUTO, "create-drop");

		configuration.setProperty(Environment.SHOW_SQL, "true");
		configuration.addDirectory(new File("edu_core/src"));

		SessionFactory sessionFactory = configuration.buildSessionFactory();
		return sessionFactory;
	}

	public static HibernateTemplate getHsqldbTemplate() {
		return new HibernateTemplate(getHsqldbSessionFactory());		
	}
	
	public static SessionFactory getMysqlSessionFactory() {
		Configuration configuration = new Configuration();

		configuration.setProperty(Environment.DRIVER, "com.mysql.jdbc.Driver");
		configuration.setProperty(Environment.URL,
				"jdbc:mysql://localhost/portaledu");
		configuration.setProperty(Environment.DIALECT, MySQLDialect.class
				.getName());
		configuration.setProperty(Environment.USER, "root");
		configuration.setProperty(Environment.PASS, "");
		configuration.setProperty(Environment.HBM2DDL_AUTO, "update");

		configuration.setProperty(Environment.SHOW_SQL, "true");
		configuration.addDirectory(new File("edu_core/src"));

		SessionFactory sessionFactory = configuration.buildSessionFactory();
		return sessionFactory;
	}

	public static HibernateTemplate getMysqlTemplate() {
		return new HibernateTemplate(getMysqlSessionFactory());
	}
}
