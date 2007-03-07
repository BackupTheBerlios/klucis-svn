package lv.webkursi.mtest.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.util.List;

import lv.webkursi.mtest.domain.Variant;

import org.junit.Test;
import org.springframework.orm.hibernate3.HibernateTemplate;

public abstract class AbstractDaoTest {
	
	protected HibernateTemplate hibernateTemplate;
	
	protected ICommonDao commonDao;
	
	public void setUp() {
		hibernateTemplate = DaoUtils.getHsqldbTemplate();
//		hibernateTemplate = DaoUtils.getMysqlTemplate();
		commonDao = getDao(hibernateTemplate);
	}
	
	
	public void tearDown() {
	}
	
	public abstract ICommonDao getDao(HibernateTemplate hibernateTemplate);
	
	public abstract Object getDynamicObjectA();
	
	public abstract Object getDynamicObjectB();
	
	public abstract Object getDynamicObjectC();
	
	public abstract Object getDynamicObjectD();
	
	@Test
	public void testSaveAndGet() {
		Object o1 = getDynamicObjectD();
		long id = commonDao.saveOrUpdate(o1);
		Object o2 = commonDao.get(id);
		assertEquals(o1, o2);
		assertNotSame(o1, o2);
	}
	
	/**
	 * Insert domain objects in arbitrary order, but getAll() 
	 * should return the list canonically sorted (each domain 
	 * object defines how to sort the list). 
	 *
	 */
	@Test
	public void listOrdering() {
		commonDao.saveOrUpdate(getDynamicObjectC());
		commonDao.saveOrUpdate(getDynamicObjectA());
		commonDao.saveOrUpdate(getDynamicObjectB());
		List result = commonDao.getAll();
		assertEquals(3,result.size());
		assertEquals(getDynamicObjectA(),result.get(0));
		assertEquals(getDynamicObjectB(),result.get(1));
		assertEquals(getDynamicObjectC(),result.get(2));
	}	
}
