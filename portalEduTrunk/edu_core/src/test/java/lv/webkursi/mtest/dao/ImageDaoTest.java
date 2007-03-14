package lv.webkursi.mtest.dao;

import lv.webkursi.mtest.domain.Image;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(value = { ImageDaoTest.CommonDaoTest.class 
		})
public class ImageDaoTest {

	private static CommonDao dao = CommonDao.getInstance(Image.class);

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
			Image iA = new Image();
			iA.setName("IA");
			iA.setDescription("IA_Desc");
			return iA;
		}

		@Override
		public Object getDynamicObjectB() {
			Image iB = new Image();
			iB.setName("IB");
			iB.setDescription("IB_Desc");
			return iB;
		}

		@Override
		public Object getDynamicObjectC() {
			Image iC = new Image();
			iC.setName("IC");
			iC.setDescription("IC_Desc");
			return iC;
		}

		@Override
		public Object getDynamicObjectD() {
			Image iD = new Image();
			iD.setName("ID");
			iD.setDescription("ID_Desc");
			return iD;
		}

	}

	public static class LocalTests {

		@Before
		public void setUp() throws Exception {
			dao.setSessionFactory(DaoUtils.getSessionFactory());			
		}
		
		@After
		public void tearDown() {
			dao.close();
			
		}
		
	
		
	}	
}
