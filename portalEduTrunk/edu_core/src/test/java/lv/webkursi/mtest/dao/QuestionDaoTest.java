package lv.webkursi.mtest.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import lv.webkursi.mtest.domain.Question;
import lv.webkursi.mtest.domain.QuestionType;
import lv.webkursi.mtest.domain.Variant;

import org.hibernate.LazyInitializationException;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(value = { QuestionDaoTest.CommonDaoTest.class,
		QuestionDaoTest.LocalTests.class //, QuestionDaoTest.PersistenceTests.class 
		})
public class QuestionDaoTest {

	private static CommonDao dao = CommonDao.getInstance(Question.class);

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
			assertEquals("varD_desc",q1.getVariants().get(1).getDescription());
		}
		
		
		public Variant addDynamicObjectA(Question q) {
			Variant vA = q.createVariant();
			vA.setDescription("varA_desc");
			return vA;
		}

		public Variant addDynamicObjectB(Question q) {
			Variant vB = q.createVariant();
			vB.setDescription("varB_desc");
			return vB;
		}

		public Variant addDynamicObjectC(Question q) {
			Variant vC = q.createVariant();
			vC.setDescription("varC_desc");
			return vC;
		}

		public Variant addDynamicObjectD(Question q) {
			Variant vD = q.createVariant();
			vD.setDescription("varD_desc");
			return vD;
		}
	}	
}
