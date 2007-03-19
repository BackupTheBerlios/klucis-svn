package lv.webkursi.mtest.lab02.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import lv.webkursi.mtest.lab02.domain.Person;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.dao.DataIntegrityViolationException;

@RunWith(Suite.class)
@Suite.SuiteClasses(value = { PersonDaoTest.CommonDaoTest.class,
		PersonDaoTest.LocalTests.class })
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

		private Person p0 = (Person) (new PersonDaoTest.CommonDaoTest()
				.getDynamicObjectA());

		@Before
		public void setUp() throws Exception {
			dao.setSessionFactory(DaoUtils.getSessionFactory());
			dao.saveOrUpdate(p0);
		}

		/**
		 * Should not allow two users with identical logins
		 */
		@Test(expected = DataIntegrityViolationException.class)
		public void insertDuplicateLogin() throws Exception {
			Person p1 = new Person();
			p1.setEmail("x.x@a.com");
			p1.setFirstName("xFirst");
			p1.setLastName("xLast");
			p1.setLogin("aLogin"); // same login as for p0
			p1.setPassword("something");
			dao.saveOrUpdate(p1);
		}

		/**
		 * Should not allow two users with identical emails 
		 */
		@Test(expected = DataIntegrityViolationException.class)
		public void insertDuplicateEmail() throws Exception {
			Person p1 = new Person();
			p1.setEmail("a.a@a.com"); // same email as for p0
			p1.setFirstName("yFirst");
			p1.setLastName("yLast");
			p1.setLogin("yLogin");
			p1.setPassword("another");
			dao.saveOrUpdate(p1);
		}
		
		/**
		 * As long as email and login are different, 
		 * everything else (firstName, lastName, password) 
		 * can repeat. 
		 */
		public void insertDuplicateEverythingElse() {
			Person p1 = new Person();
			p1.setEmail("z.a@a.com"); // differs from p0
			p1.setFirstName("aFirst");
			p1.setLastName("aLast");
			p1.setLogin("zLogin"); // differs from p0
			p1.setPassword("aPassword"); 
			dao.saveOrUpdate(p1);
			
		}

		/**
		 * If a user exists, "getPersonByLogin" should return it
		 */
		@Test
		public void getPersonByLogin() {
			Person p2 = dao.getPersonByLogin("aLogin");
			assertEquals(p0, p2);
			assertNotSame(p0, p2);
		}

		/**
		 * If a login does not exist, should return null
		 */
		@Test
		public void getPersonByLogin2() {
			Person p2 = dao.getPersonByLogin("blah");
			assertNull(p2);
		}

	}

}
