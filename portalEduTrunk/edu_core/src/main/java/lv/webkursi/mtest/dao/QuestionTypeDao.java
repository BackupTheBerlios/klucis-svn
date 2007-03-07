package lv.webkursi.mtest.dao;

import java.util.List;

import lv.webkursi.mtest.domain.QuestionType;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class QuestionTypeDao extends HibernateDaoSupport implements IQuestionTypeDao {

	/**
	 * If "qt" is a new object, its id is null, then insert; 
	 * otherwise update.
	 */
	public long saveOrUpdate(Object o) {
		if (!(o instanceof QuestionType)) {
			throw new IllegalArgumentException();
		}
		getHibernateTemplate().saveOrUpdate(o);		
		return ((QuestionType)o).getId();
	}

	/**
	 * Return a questionType by its id or return null, if such 
	 * questionType does not exist.
	 */
	public QuestionType get(long id) {
		return (QuestionType) getHibernateTemplate().get(QuestionType.class, id);

	}
	
	/**
	 * Get a list of all questionTypes. If table is empty, return
	 * an empty list (but not null). 
	 */
	@SuppressWarnings("unchecked")
	public List<QuestionType> getAll() {
		return (List<QuestionType>)getHibernateTemplate().find(
				"from QuestionType qt order by qt.instruction");
	}
	
}
