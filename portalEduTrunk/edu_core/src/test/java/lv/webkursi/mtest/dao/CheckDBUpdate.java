package lv.webkursi.mtest.dao;

import lv.webkursi.mtest.domain.QuestionType;

public class CheckDBUpdate {
	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		CommonDao dao = CommonDao.getInstance(QuestionType.class);
		dao.setSessionFactory(DaoUtils.getMysqlSessionFactory());
		QuestionType qt = new QuestionType("Blah!");
		dao.saveOrUpdate(qt);
		dao.commit();		
	}
}
