package lv.webkursi.mtest.dao;

import lv.webkursi.mtest.domain.QuestionType;

public class CheckDBUpdate {
	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		QuestionTypeDao dao = new QuestionTypeDao();
		dao.setSessionFactory(DaoUtils.getMysqlSessionFactory());
		QuestionType qt = new QuestionType("Blah!");
		dao.saveOrUpdate(qt);
		dao.commit();		
	}
}
