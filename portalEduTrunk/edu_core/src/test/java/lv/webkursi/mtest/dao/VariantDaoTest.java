package lv.webkursi.mtest.dao;

import lv.webkursi.mtest.domain.Question;
import lv.webkursi.mtest.domain.QuestionType;
import lv.webkursi.mtest.domain.Variant;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.orm.hibernate3.HibernateTemplate;

//@RunWith(Suite.class)
//@Suite.SuiteClasses(value={VariantDaoTest.CommonDaoTest.class})
public class VariantDaoTest {
	
	
	public static class CommonDaoTest extends AbstractDaoTest {
		
		private VariantDao variantDao = new VariantDao();
		private QuestionType qt = (QuestionType) (new QuestionTypeDaoTest.CommonDaoTest().getDynamicObjectA());
		private Question q = (Question) (new QuestionDaoTest.CommonDaoTest().getDynamicObjectA());
		
		@Before 
		public void setUp() {
			super.setUp();
			hibernateTemplate.saveOrUpdate(qt);
			q.setQuestionType(qt);
			hibernateTemplate.saveOrUpdate(q);
			variantDao.setHibernateTemplate(hibernateTemplate);
		}
		
		@After
		public void tearDown() {
//			super.tearDown();
		}

		@Override
		public ICommonDao getDao(HibernateTemplate hibernateTemplate) {
			return variantDao;
		}

		@Override
		public Object getDynamicObjectA() {
			Variant vA = new Variant();
			vA.setName("varA");
			vA.setDescription("varA_desc");
			q.addVariant(vA);
			return vA;
		}

		@Override
		public Object getDynamicObjectB() {
			Variant vB = new Variant();
			vB.setName("varB");
			vB.setDescription("varB_desc");
			q.addVariant(vB);
			return vB;
		}

		@Override
		public Object getDynamicObjectC() {
			Variant vC = new Variant();
			vC.setName("varC");
			vC.setDescription("varC_desc");
			q.addVariant(vC);			
			return vC;
		}
		
		@Override
		public Object getDynamicObjectD() {
			Variant vD = new Variant();
			vD.setName("varD");
			vD.setDescription("varD_desc");
			q.addVariant(vD);			
			return vD;
		}
		
	}
	
}
