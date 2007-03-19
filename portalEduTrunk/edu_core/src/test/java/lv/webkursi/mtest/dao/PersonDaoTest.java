package lv.webkursi.mtest.dao;

import lv.webkursi.mtest.lab02.dao.CommonDao;
import lv.webkursi.mtest.lab02.dao.DaoFactory;
import lv.webkursi.mtest.lab02.dao.ICommonDao;
import lv.webkursi.mtest.lab02.domain.Person;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(value = { PersonDaoTest.CommonDaoTest.class })
public class PersonDaoTest {

	private static CommonDao dao = DaoUtils.getDao(Person.class);

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
			Person pA = new Person();
			pA.setEmail("a.a@a.com");
			pA.setFirstName("aFirst");
			pA.setLastName("aLast");
			pA.setLogin("aLogin");
			pA.setPassword("aPassword");
			return pA;
		}

		@Override
		public Object getDynamicObjectB() {
			Person pB = new Person();
			pB.setEmail("b.b@b.com");
			pB.setFirstName("bFirst");
			pB.setLastName("bLast");
			pB.setLogin("bLogin");
			pB.setPassword("bPassword");
			return pB;
		}

		@Override
		public Object getDynamicObjectC() {
			Person pC = new Person();
			pC.setEmail("c.c@c.com");
			pC.setFirstName("cFirst");
			pC.setLastName("cLast");
			pC.setLogin("cLogin");
			pC.setPassword("cPassword");
			return pC;
		}

		@Override
		public Object getDynamicObjectD() {
			Person pD = new Person();
			pD.setEmail("d.d@d.com");
			pD.setFirstName("dFirst");
			pD.setLastName("dLast");
			pD.setLogin("dLogin");
			pD.setPassword("dPassword");
			return pD;
		}
	}

	public static class LocalTests {

		@Before
		public void setUp() throws Exception {
			dao.setSessionFactory(DaoUtils.getSessionFactory());
		}
		
		@Test
		public void insertDuplicate() throws Exception {
			
		}
	}
}
