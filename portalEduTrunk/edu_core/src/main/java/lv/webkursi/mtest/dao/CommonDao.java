package lv.webkursi.mtest.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import lv.webkursi.mtest.domain.Image;
import lv.webkursi.mtest.domain.Module;
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

	private static Map<String, String> lastIdQueries = new HashMap<String, String>();

	static {
		getAllQueries.put(QuestionType.class,
				"from QuestionType qt order by qt.instruction");
		getAllQueries.put(Module.class, "from Module m order by m.name");
		getAllQueries.put(Question.class, "from Question q order by q.name");
		getAllQueries.put(Variant.class, "from Variant v order by v.name");
		getAllQueries.put(Image.class, "from Image i order by i.name");

		// two dialects of SQL - find the last generated ID
		lastIdQueries.put("Hsqldb", "CALL IDENTITY()");
		lastIdQueries.put("Mysql", "SELECT LAST_INSERT_ID()");
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
		getHibernateTemplate().saveOrUpdate(o);
		if (o instanceof QuestionType) {
			return ((QuestionType) o).getId();
		} else if (o instanceof Module) {
			return ((Module) o).getId();
		} else if (o instanceof Image) {
			return ((Image) o).getId();
		} else if (o instanceof Question) {
			return ((Question) o).getId();
		} else if (o instanceof Variant) {
			return ((Variant) o).getId();
		} else {
			throw new RuntimeException("Unsupported type: "
					+ o.getClass().getName());
		}
		// return ((Long) getHibernateTemplate().execute(
		// new SaveOrUpdateCallback(o))).longValue();
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

	public Question getQuestionWithVariants(long id) {

		return (Question) getHibernateTemplate().execute(
				new CallbackQuestionWithVariants(id));

	}

	public Module getModuleWithQuestions(long id) {
		List list = getHibernateTemplate()
				.find(
						"from Module as module left join fetch module.questions where module.id=?",
						id);
		return (Module) list.get(0);
	}

	public static class CallbackQuestionWithVariants implements
			HibernateCallback {

		private long questionId;

		public CallbackQuestionWithVariants(long questionId) {
			this.questionId = questionId;
		}

		public Object doInHibernate(Session session) throws HibernateException,
				SQLException {
			session.beginTransaction();
			Question result = (Question) session
					.get(Question.class, questionId);
			for (Iterator i = result.getVariants().iterator(); i.hasNext();) {
				i.next();
			}
			session.getTransaction().commit();
			return result;
		}

	}

	public static class CallbackModuleWithQuestions implements
			HibernateCallback {

		private long moduleId;

		public CallbackModuleWithQuestions(long moduleId) {
			this.moduleId = moduleId;
		}

		public Object doInHibernate(Session session) throws HibernateException,
				SQLException {
			session.beginTransaction();
			// session.
			session.getTransaction().commit();
			return null;
		}

	}

}
