package lv.webkursi.mtest.lab02.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import lv.webkursi.mtest.lab02.dao.CommonDao;
import lv.webkursi.mtest.lab02.dao.ICommonDao;
import lv.webkursi.mtest.lab02.domain.QuestionType;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.dao.DataIntegrityViolationException;

@RunWith(Suite.class)
@Suite.SuiteClasses(value = { QuestionTypeDaoTest.CommonDaoTest.class,
		QuestionTypeDaoTest.LocalTests.class })
public class QuestionTypeDaoTest {

	private static CommonDao dao = DaoUtils.getDao(QuestionType.class);

	public static class CommonDaoTest extends AbstractDaoTest {

		@Before
		public void setUp() {
			super.setUp();
			dao.setSessionFactory(DaoUtils.getSessionFactory());
		}

		public void tearDown() {
			dao.close();
		}

		public ICommonDao getDao() {
			return dao;
		}

		@Override
		public Object getDynamicObjectA() {
			return new QuestionType("Instruction A");
		}

		@Override
		public Object getDynamicObjectB() {
			return new QuestionType("Instruction B");
		}

		@Override
		public Object getDynamicObjectC() {
			return new QuestionType("Instruction C");
		}

		@Override
		public Object getDynamicObjectD() {
			return new QuestionType("Instruction D");
		}
	}

	public static class LocalTests {

		@Before
		public void setUp() throws Exception {
			dao.setSessionFactory(DaoUtils.getSessionFactory());
		}

		@SuppressWarnings("unchecked")
		@Test
		public void updatingInstruction() {
			QuestionType qt0 = new QuestionType("Instr0");
			long id0 = dao.saveOrUpdate(qt0);

			QuestionType qt1 = (QuestionType) dao.get(id0);
			qt1.setInstruction("Instr1");
			long id1 = dao.saveOrUpdate(qt1);

			List<QuestionType> list = dao.getAll();
			assertEquals(1, list.size());
			assertEquals(id0, id1);
			QuestionType qt2 = (QuestionType) dao.get(id1);
			assertEquals("Instr1", qt2.getInstruction());
		}

		@Test(expected = DataIntegrityViolationException.class)
		public void testNotNull() {
			QuestionType qt = new QuestionType();
			dao.saveOrUpdate(qt);
		}

		/*
		 * Should not allow inserting too long instruction strings for
		 * QuestionType (max is 255 unicode chars), but Hibernate does not seem
		 * to care...
		 * 
		 * TODO correct this testcase, so that for too long instruction values
		 * (>255 or something like that), an exception is thrown even for HSQLDB
		 */
		@Ignore
		@Test
		public void testMaxLength() {
			String s36 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

			String s255 = s36 + s36 + s36 + s36 + s36 + s36 + s36 + "012";
			QuestionType questionType = new QuestionType();
			questionType.setInstruction(s255);
			long id = dao.saveOrUpdate(questionType);
			QuestionType qt1 = (QuestionType) dao.get(id);
			assertEquals(255, qt1.getInstruction().length());
			try {
				QuestionType qt2 = new QuestionType(s255 + ".");
				dao.saveOrUpdate(qt2);
				fail("Should fail for too long instructions");
			} catch (Exception expected) {
			}
		}

		@Test
		public void testNonAscii() {
			String s16 = "0123456789\u0100\u0101\u0102\u0103\u0104\u0105";
			String s240 = s16 + s16 + s16 + s16 + s16 + s16 + s16 + s16 + s16
					+ s16 + s16 + s16 + s16 + s16 + s16;
			QuestionType questionType = new QuestionType();
			questionType.setInstruction(s240);
			long id = dao.saveOrUpdate(questionType);
			QuestionType qt1 = (QuestionType) dao.get(id);
			assertEquals(s240, qt1.getInstruction());
			assertEquals(240, qt1.getInstruction().length());
		}
	}
}
