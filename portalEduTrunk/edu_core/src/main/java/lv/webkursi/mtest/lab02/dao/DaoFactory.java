package lv.webkursi.mtest.lab02.dao;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.FactoryBean;

public class DaoFactory implements FactoryBean {
	
	protected Class clazz;
	
	protected SessionFactory sessionFactory;

	public DaoFactory() {		
	}
	
	public DaoFactory(Class clazz) {
		this.clazz = clazz;
	}	
	
	private static Map<Class, CommonDao> cache = new HashMap<Class, CommonDao>();

	public Object getObject() throws Exception {
		if (!cache.containsKey(clazz)) {
			CommonDao instance = new CommonDao();
			instance.setClazz(clazz);
			instance.setSessionFactory(sessionFactory);
			cache.put(clazz, instance);
		}
		return cache.get(clazz);
	}

	public Class getObjectType() {
		return CommonDao.class;
	}

	public boolean isSingleton() {
		return true;
	}

	public Class getClazz() {
		return clazz;
	}

	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
