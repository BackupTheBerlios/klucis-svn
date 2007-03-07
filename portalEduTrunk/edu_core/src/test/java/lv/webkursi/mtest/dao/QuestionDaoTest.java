package lv.webkursi.mtest.dao;

import lv.webkursi.mtest.domain.Question;
import lv.webkursi.mtest.domain.QuestionType;
import lv.webkursi.mtest.domain.Variant;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.orm.hibernate3.HibernateTemplate;

@RunWith(Suite.class)
@Suite.SuiteClasses(value = { QuestionDaoTest.CommonDaoTest.class,
		QuestionDaoTest.LocalTests.class })
public class QuestionDaoTest {

	private static QuestionDao dao = new QuestionDao();

	public static class CommonDaoTest extends AbstractDaoTest {

		private QuestionType qt = (QuestionType) (new QuestionTypeDaoTest.CommonDaoTest()
				.getDynamicObjectA());
		
		

		@Before
		public void setUp() {
			super.setUp();
			dao.setSessionFactory(DaoUtils.getHsqldbSessionFactory());
			dao.getHibernateTemplate().saveOrUpdate(qt);
		}

		@After
		public void tearDown() {
		}

		@Override
		public ICommonDao getDao(HibernateTemplate hibernateTemplate) {
			return dao;
		}

		@Override
		public Object getDynamicObjectA() {
			Question qA = new Question();
			qA.setName("A");
			qA.setDescription("A");
			qA.setQuestionType(qt);
			return qA;
		}

		@Override
		public Object getDynamicObjectB() {
			Question qB = new Question();
			qB.setName("B");
			qB.setDescription("B");
			qB.setQuestionType(qt);
			return qB;
		}

		@Override
		public Object getDynamicObjectC() {
			Question qC = new Question();
			qC.setName("C");
			qC.setDescription("C");
			qC.setQuestionType(qt);
			return qC;
		}

		@Override
		public Object getDynamicObjectD() {
			Question qD = new Question();
			qD.setName("D");
			qD.setDescription("D");
			qD.setQuestionType(qt);
			return qD;
		}

	}

	public static class LocalTests {

		private QuestionType qt = (QuestionType) (new QuestionTypeDaoTest.CommonDaoTest()
				.getDynamicObjectA());
		
		@Before
		public void setUp() throws Exception {
			dao.setSessionFactory(DaoUtils.getMysqlSessionFactory());
			dao.getHibernateTemplate().saveOrUpdate(qt);
		}
		
		@After
		public void tearDown() {
		}

		@Test
		public void storeWithVariants() {
			Question q = (Question) new QuestionDaoTest.CommonDaoTest()
			 .getDynamicObjectA();
			q.setQuestionType(qt);
			VariantDaoTest.CommonDaoTest vdtCdt = new VariantDaoTest.CommonDaoTest();
			q.addVariant((Variant) vdtCdt.getDynamicObjectA());
			q.addVariant((Variant) vdtCdt.getDynamicObjectB());
			q.addVariant((Variant) vdtCdt.getDynamicObjectC());
			q.addVariant((Variant) vdtCdt.getDynamicObjectD());
			

			long id = dao.saveOrUpdate(q);
			Question q1 = dao.get(id);
			System.err.println("variants = " + q1.getVariants());
			//assertNull(q1.getVariants());
		}
	}
}
