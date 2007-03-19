package lv.webkursi.mtest.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import lv.webkursi.mtest.lab02.dao.CommonDao;
import lv.webkursi.mtest.lab02.dao.DaoFactory;
import lv.webkursi.mtest.lab02.dao.ICommonDao;
import lv.webkursi.mtest.lab02.domain.Question;
import lv.webkursi.mtest.lab02.domain.QuestionType;
import lv.webkursi.mtest.lab02.domain.Variant;

import org.hibernate.LazyInitializationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(value = { QuestionDaoTest.CommonDaoTest.class,
		QuestionDaoTest.LocalTests.class  
		})
public class QuestionDaoTest {

	private static DaoFactory factory;
	private static CommonDao dao;	
	static {
		factory = new DaoFactory(Question.class);
		try {
			dao = (CommonDao) factory.getObject();
		}
		catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}

	public static class CommonDaoTest extends AbstractDaoTest {

		private QuestionType qt = (QuestionType) (new QuestionTypeDaoTest.CommonDaoTest()
				.getDynamicObjectA());
		

		@Before
		public void setUp() {
			dao.setSessionFactory(DaoUtils.getSessionFactory());
			dao.getHibernateTemplate().saveOrUpdate(qt);
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
			Question qA = new Question();
			qA.setDescription("A");
			qA.setQuestionType(qt);
			return qA;
		}

		@Override
		public Object getDynamicObjectB() {
			Question qB = new Question();
			qB.setDescription("B");
			qB.setQuestionType(qt);
			return qB;
		}

		@Override
		public Object getDynamicObjectC() {
			Question qC = new Question();
			qC.setDescription("C");
			qC.setQuestionType(qt);
			return qC;
		}

		@Override
		public Object getDynamicObjectD() {
			Question qD = new Question();
			qD.setDescription("D");
			qD.setQuestionType(qt);
			return qD;
		}

	}

	public static class LocalTests {

		private QuestionType qt = (QuestionType) (new QuestionTypeDaoTest.CommonDaoTest()
				.getDynamicObjectA());		
		private Question q = (Question) (new QuestionDaoTest.CommonDaoTest().getDynamicObjectA());
		
		@Before
		public void setUp() throws Exception {
			dao.setSessionFactory(DaoUtils.getSessionFactory());			
			dao.saveOrUpdate(qt);
			q.setQuestionType(qt);
			dao.saveOrUpdate(q);
		}
		
		@After
		public void tearDown() {
			dao.close();
			
		}

		@Test(expected=LazyInitializationException.class)
		public void lazyGetVariants() {
			addDynamicObjectA(q);
			addDynamicObjectB(q);

			long id = dao.saveOrUpdate(q);			
			Question q1 = (Question) dao.get(id);
			q1.getVariants().size();
		}
		
		
		@Test
		public void explicitGetVariants() {
			addDynamicObjectA(q);
			addDynamicObjectB(q);
			long id = dao.saveOrUpdate(q);						
			Question q1 = (Question) dao.getQuestionWithVariants(id);
			assertEquals(2,q1.getVariants().size());
		}
		
		
		@Test
		public void deleteVariants() {
			// add 4 variants
			Variant vA = addDynamicObjectA(q);
			addDynamicObjectB(q);
			Variant vC = addDynamicObjectC(q);
			addDynamicObjectD(q);			
			dao.saveOrUpdate(q);
			
			// remove 2 of them 
			assertTrue(q.removeVariant(vA));
			assertTrue(q.removeVariant(vC));
			
			// should have 2 elements in the original question
			assertEquals(2,q.getVariants().size());
			long id  = dao.saveOrUpdate(q);
			
			// retrieve that question from the DB 
			Question q1 = (Question) dao.getQuestionWithVariants(id);
			assertEquals(2,q1.getVariants().size());			
			assertEquals("varB_desc",q1.getVariants().get(0).getDescription());
			assertEquals("B",q1.getVariants().get(0).getLabel());
			assertEquals("varD_desc",q1.getVariants().get(1).getDescription());
			assertEquals("D",q1.getVariants().get(1).getLabel());
		}
		
		
		public static Variant addDynamicObjectA(Question q) {
			Variant vA = q.createVariant("A");
			vA.setDescription("varA_desc");
			vA.setLabel("A");
			return vA;
		}

		public static Variant addDynamicObjectB(Question q) {
			Variant vB = q.createVariant("B");
			vB.setDescription("varB_desc");
			vB.setLabel("B");
			return vB;
		}

		public static Variant addDynamicObjectC(Question q) {
			Variant vC = q.createVariant("C");
			vC.setDescription("varC_desc");
			vC.setLabel("C");
			return vC;
		}

		public static Variant addDynamicObjectD(Question q) {
			Variant vD = q.createVariant("D");
			vD.setDescription("varD_desc");
			vD.setLabel("D");
			return vD;
		}
	}	
}
