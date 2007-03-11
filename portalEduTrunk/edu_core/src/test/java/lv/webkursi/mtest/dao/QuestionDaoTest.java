package lv.webkursi.mtest.dao;

import static org.junit.Assert.assertEquals;

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

	//private static QuestionDao dao = new QuestionDao();
	
	private static CommonDao dao = CommonDao.getInstance(Question.class);

	public static class CommonDaoTest extends AbstractDaoTest {

		private QuestionType qt = (QuestionType) (new QuestionTypeDaoTest.CommonDaoTest()
				.getDynamicObjectA());
		

		@Before
		public void setUp() {
			dao.setSessionFactory(DaoUtils.getHsqldbSessionFactory());
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
		
		@Before
		public void setUp() throws Exception {
//			dao.setSessionFactory(DaoUtils.getMysqlSessionFactory());
			dao.setSessionFactory(DaoUtils.getHsqldbSessionFactory());
			dao.getHibernateTemplate().saveOrUpdate(qt);
		}
		
		@After
		public void tearDown() {
			
		}

		@Test(expected=LazyInitializationException.class)
		public void variantsGet() {
			Question q = (Question) new QuestionDaoTest.CommonDaoTest()
			 .getDynamicObjectA();
			q.setQuestionType(qt);
			VariantDaoTest.CommonDaoTest vdtCdt = new VariantDaoTest.CommonDaoTest();
			
			Variant vA = (Variant) vdtCdt.getDynamicObjectA();
			q.addVariant(vA);
			dao.getHibernateTemplate().saveOrUpdate(vA);
			
			long id = dao.saveOrUpdate(q);			
			Question q1 = (Question) dao.get(id);
			q1.getVariants().size();
			
			
		}
		
	}

	
	/*
	
	public static class PersistenceTests {
		
		public static void cleanAll() throws SQLException {
			Connection conn = dao.getSessionFactory().openSession().connection();			
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM question");
			stmt.executeUpdate("DELETE FROM question_type");
			stmt.executeUpdate("DELETE FROM content");
			stmt.close();
			conn.close();			
		}

		private QuestionType qt = (QuestionType) (new QuestionTypeDaoTest.CommonDaoTest()
				.getDynamicObjectA());
		
		@BeforeClass
		public static void onlyOnce() throws SQLException {
//			cleanAll();
		}
		
		@Before
		public void setUp() throws Exception {
//			cleanAll();
//			dao.setSessionFactory(DaoUtils.getMysqlSessionFactory());
//			dao.getHibernateTemplate().saveOrUpdate(qt);
		}
		
		@After
		public void tearDown() throws SQLException {
//			cleanAll();
		}
		
		
		@Ignore
		@Test
		public void storeWithVariants() throws SQLException {
			Question q = (Question) new QuestionDaoTest.CommonDaoTest()
			 .getDynamicObjectA();
			q.setQuestionType(qt);
			long id = dao.saveOrUpdate(q);
			dao.commit();
			
			Connection conn = dao.getSessionFactory().openSession().connection();
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM question");
			int count = 0; 
			while (rs.next()) {
				int newId = rs.getInt("content_id");
				assertEquals(id,(long)newId);		
				count++;
			}
			assertEquals(1,count);
		}
	}
	*/
}
