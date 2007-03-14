package lv.webkursi.mtest.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import lv.webkursi.mtest.lab02.dao.CommonDao;
import lv.webkursi.mtest.lab02.dao.ICommonDao;
import lv.webkursi.mtest.lab02.domain.Module;
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
@Suite.SuiteClasses(value = { ModuleDaoTest.CommonDaoTest.class,
		ModuleDaoTest.LocalTests.class})
public class ModuleDaoTest {

	private static CommonDao dao = CommonDao.getInstance(Module.class);

	public static class CommonDaoTest extends AbstractDaoTest {

		@Before
		public void setUp() {
			dao.setSessionFactory(DaoUtils.getSessionFactory());
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
			Module mA = new Module();
			mA.setName("MA");
			mA.setTitle("MA_Title");
			mA.setDescription("MA_Description");
			return mA;
		}

		@Override
		public Object getDynamicObjectB() {
			Module mA = new Module();
			mA.setName("MB");
			mA.setTitle("MB_Title");
			mA.setDescription("MB_Description");
			return mA;
		}

		@Override
		public Object getDynamicObjectC() {
			Module mA = new Module();
			mA.setName("MC");
			mA.setTitle("MC_Title");
			mA.setDescription("MC_Description");
			return mA;
		}

		@Override
		public Object getDynamicObjectD() {
			Module mA = new Module();
			mA.setName("MD");
			mA.setTitle("MD_Title");
			mA.setDescription("MD_Description");
			return mA;
		}

	}

	public static class LocalTests {
		
		private QuestionType qt = (QuestionType) (new QuestionTypeDaoTest.CommonDaoTest()
		.getDynamicObjectA());		
		private Module m = (Module) (new ModuleDaoTest.CommonDaoTest().getDynamicObjectA());
		
		@Before
		public void setUp() throws Exception {
			dao.setSessionFactory(DaoUtils.getSessionFactory());			
			dao.saveOrUpdate(m);
			dao.saveOrUpdate(qt);
		}
		
		@After
		public void tearDown() {
			dao.close();
			
		}

		@Test(expected=LazyInitializationException.class)
		public void lazyGetQuestions() {
			addDynamicObjectA(m);
			addDynamicObjectB(m);
			long id = dao.saveOrUpdate(m);			
			Module m1 = (Module) dao.get(id);
			m1.getQuestions().size();
		}
		
		
		@Test
		public void explicitGetQuestions() {
			addDynamicObjectA(m);
			addDynamicObjectB(m);
			long id = dao.saveOrUpdate(m);						
			Module m1 = (Module) dao.getModuleWithQuestions(id);
			assertEquals(2,m1.getQuestions().size());
			assertEquals("QA_desc",m1.getQuestions().get(0).getDescription());
			assertEquals("QA",m1.getQuestions().get(0).getName());
			assertEquals("QB_desc",m1.getQuestions().get(1).getDescription());
			assertEquals("QB",m1.getQuestions().get(1).getName());
			
		}

		/**
		 * When saving module, it is saved with all its questions; 
		 * and each question is saved with all its variants
		 *
		 */
		@Test
		public void cascadingSave() {
			addDynamicObjectA(m);
			addDynamicObjectB(m);
			Question q0 = m.getQuestions().get(0);
			QuestionDaoTest.LocalTests.addDynamicObjectA(q0);
			QuestionDaoTest.LocalTests.addDynamicObjectB(q0);			
			long id = dao.saveOrUpdate(m);						
			Module m1 = (Module) dao.getModuleWithQuestions(id);
			assertEquals(2,m1.getQuestions().size());
			long idQ0 = m1.getQuestions().get(0).getId();
			Question q1 = dao.getQuestionWithVariants(idQ0);
			assertEquals(2,q1.getVariants().size());
			assertEquals("varA_desc",q1.getVariants().get(0).getDescription());
			assertEquals("QA_A",q1.getVariants().get(0).getName());
			assertEquals("varB_desc",q1.getVariants().get(1).getDescription());
			assertEquals("QA_B",q1.getVariants().get(1).getName());
			
			
		}

		/**
		 * When loading a module with all its questions happens, 
		 * then the variants should NOT be loaded. Attempt to 
		 * access the variables for a question should cause
		 * LazyInitializationException
		 */
		
		@Test(expected=LazyInitializationException.class)
		public void explicitGetQuestionsButNoVariants() {
			addDynamicObjectA(m);
			addDynamicObjectB(m);
			Question q0 = m.getQuestions().get(0);
			QuestionDaoTest.LocalTests.addDynamicObjectA(q0);
			QuestionDaoTest.LocalTests.addDynamicObjectB(q0);			
			long id = dao.saveOrUpdate(m);						
			Module m1 = (Module) dao.getModuleWithQuestions(id);
			assertEquals(2,m1.getQuestions().size());
			m1.getQuestions().get(0).getVariants().size();
		}
		
		
		@Test
		public void deleteQuestions() {
			// add 4 variants
			Question qA = addDynamicObjectA(m);
			addDynamicObjectB(m);
			Question qC = addDynamicObjectC(m);
			addDynamicObjectD(m);			
			dao.saveOrUpdate(m);
			
			// remove 2 of them 
			assertTrue(m.removeQuestion(qA));
			assertTrue(m.removeQuestion(qC));
			
			// should have 2 elements in the original question
			assertEquals(2,m.getQuestions().size());
			long id  = dao.saveOrUpdate(m);
			
			// retrieve that question from the DB 
			Module m1 = (Module) dao.getModuleWithQuestions(id);
			assertEquals(2,m1.getQuestions().size());			
			assertEquals("QB_desc",m1.getQuestions().get(0).getDescription());
			assertEquals("QB",m1.getQuestions().get(0).getName());
			assertEquals("QD_desc",m1.getQuestions().get(1).getDescription());
			assertEquals("QD",m1.getQuestions().get(1).getName());
		}
		
		
		public Question addDynamicObjectA(Module m) {
			Question qA = m.createQuestion();
			qA.setName("QA");
			qA.setQuestionType(qt);
			qA.setDescription("QA_desc");
			return qA;
		}

		public Question addDynamicObjectB(Module m) {
			Question qB = m.createQuestion();
			qB.setName("QB");
			qB.setQuestionType(qt);
			qB.setDescription("QB_desc");
			return qB;
		}

		public Question addDynamicObjectC(Module m) {
			Question qC = m.createQuestion();
			qC.setName("QC");
			qC.setQuestionType(qt);
			qC.setDescription("QC_desc");
			return qC;
		}

		public Question addDynamicObjectD(Module m) {
			Question qD = m.createQuestion();
			qD.setName("QD");
			qD.setQuestionType(qt);
			qD.setDescription("QD_desc");
			return qD;
		}

	}	
}
