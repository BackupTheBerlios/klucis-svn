package lv.webkursi.mtest.dao;

import lv.webkursi.mtest.domain.QuestionType;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

public class CheckDBUpdate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		QuestionTypeDao dao = new QuestionTypeDao();
		dao.setSessionFactory(DaoUtils.getMysqlSessionFactory());
		QuestionType qt = new QuestionType("Blah!");
		dao.saveOrUpdate(qt);
		
		
		dao.getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
            	session.beginTransaction();
            	session.save(new QuestionType("AAA"));
            	session.getTransaction().commit();
            	return null;
            }
        });
        

		
	}

}
