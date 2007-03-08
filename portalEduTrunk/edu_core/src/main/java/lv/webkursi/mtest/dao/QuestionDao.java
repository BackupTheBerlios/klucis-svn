package lv.webkursi.mtest.dao;

import java.util.List;

import lv.webkursi.mtest.domain.Question;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class QuestionDao extends HibernateDaoSupport implements IQuestionDao {
	public long saveOrUpdate(Object o) {
		if (!(o instanceof Question)) {
			throw new IllegalArgumentException();
		}
		getHibernateTemplate().saveOrUpdate(o);
		return ((Question)o).getId();
	}

	@SuppressWarnings("unchecked")
	public List getAll() {
		return (List<Question>) getHibernateTemplate()
				.find("from Question q order by q.name");
	}

	public Question get(long id) {
		return (Question) getHibernateTemplate().get(Question.class, id);
	}

	public void delete(long id) {
		Question q = get(id);
		getHibernateTemplate().delete(q);
	}

	public void deleteAll() {
		getHibernateTemplate().deleteAll(getAll());
	}
}
