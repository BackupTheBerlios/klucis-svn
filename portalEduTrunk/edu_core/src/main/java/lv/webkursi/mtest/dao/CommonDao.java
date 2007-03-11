package lv.webkursi.mtest.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import lv.webkursi.mtest.domain.Question;
import lv.webkursi.mtest.domain.QuestionType;
import lv.webkursi.mtest.domain.Variant;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CommonDao extends HibernateDaoSupport implements ICommonDao {
	private Class clazz;

	private static Map<Class, String> getAllQueries = new HashMap<Class, String>();

	private static Map<Class, CommonDao> cache = new HashMap<Class, CommonDao>();

	static {
		getAllQueries.put(QuestionType.class,
				"from QuestionType qt order by qt.instruction");
		getAllQueries.put(Question.class, "from Question q order by q.name");
		getAllQueries.put(Variant.class, "from Variant v order by v.name");
	}

	public static CommonDao getInstance(Class clazz) {
		if (!cache.containsKey(clazz)) {
			CommonDao instance = new CommonDao();
			instance.clazz = clazz;
			cache.put(clazz, instance);
		}
		return cache.get(clazz);
	}
	
	public void close() {
		getSessionFactory().close();
	}

	public long saveOrUpdate(Object o) {
		// getHibernateTemplate().saveOrUpdate(o);
		// return ((QuestionType) o).getId();
		return ((Long) getHibernateTemplate().execute(
				new SaveOrUpdateCallback(o))).longValue();
	}

	public Object get(long id) {
		return getHibernateTemplate().get(clazz, id);
	}

	public void delete(long id) {
		Object o = get(id);
		getHibernateTemplate().delete(o);
	}

	public List getAll() {
		String query = getAllQueries.get(clazz);
		if (query == null) {
			query = "from " + clazz.getName();
		}
		return getHibernateTemplate().find(query);
	}

	public void deleteAll() {
		getHibernateTemplate().deleteAll(getAll());
	}

	protected void commit() {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				session.beginTransaction();
				session.getTransaction().commit();
				return null;
			}
		});
	}

	public static class SaveOrUpdateCallback implements HibernateCallback {
		Object o;

		public SaveOrUpdateCallback(Object o) {
			this.o = o;
		}

		public Long doInHibernate(Session session) throws HibernateException,
				SQLException {
			Long result = -1L;
			session.beginTransaction();
			session.saveOrUpdate(o);
			Connection conn = session.connection();
			Statement stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");
			ResultSet rs = stmt.executeQuery("CALL IDENTITY()");
			if (rs.next()) {
				result = rs.getLong(1);
			}
			rs.close();
			stmt.close();
			conn.close();
			session.getTransaction().commit();
			return result;
		}
	}
}
