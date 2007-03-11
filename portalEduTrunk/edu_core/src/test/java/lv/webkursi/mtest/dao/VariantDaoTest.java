package lv.webkursi.mtest.dao;

import lv.webkursi.mtest.domain.Question;
import lv.webkursi.mtest.domain.QuestionType;
import lv.webkursi.mtest.domain.Variant;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.orm.hibernate3.HibernateTemplate;

@RunWith(Suite.class)
@Suite.SuiteClasses(value={VariantDaoTest.CommonDaoTest.class})
public class VariantDaoTest {
	
	
	public static class CommonDaoTest extends AbstractDaoTest {
		
		private static CommonDao dao = CommonDao.getInstance(Variant.class);
		private QuestionType qt = (QuestionType) (new QuestionTypeDaoTest.CommonDaoTest().getDynamicObjectA());
		private Question q = (Question) (new QuestionDaoTest.CommonDaoTest().getDynamicObjectA());
		
		@Before 
		public void setUp() {
			super.setUp();
			dao.setSessionFactory(DaoUtils.getSessionFactory());
			dao.saveOrUpdate(qt);
			q.setQuestionType(qt);
			dao.saveOrUpdate(q);
		}
		
		@After
		public void tearDown() {
			dao.close();
		}

		@Override
		public ICommonDao getDao() {
			return dao;
		}

		@Override
		public Object getDynamicObjectA() {
			Variant vA = new Variant();
			vA.setName("varA");
			vA.setDescription("varA_desc");
			q.addVariant(vA);
			System.out.println("vA=" + vA);
			return vA;
		}

		@Override
		public Object getDynamicObjectB() {
			Variant vB = new Variant();
			vB.setName("varB");
			vB.setDescription("varB_desc");
			q.addVariant(vB);
			System.out.println("vB=" + vB);
			return vB;
		}

		@Override
		public Object getDynamicObjectC() {
			Variant vC = new Variant();
			vC.setName("varC");
			vC.setDescription("varC_desc");
			q.addVariant(vC);			
			System.out.println("vC=" + vC);
			return vC;
		}
		
		@Override
		public Object getDynamicObjectD() {
			Variant vD = new Variant();
			vD.setName("varD");
			vD.setDescription("varD_desc");
			q.addVariant(vD);
			System.out.println("vD=" + vD);
			return vD;
		}
		
	}
	
}
