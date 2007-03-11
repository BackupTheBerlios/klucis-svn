package lv.webkursi.mtest.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.util.List;

import org.junit.Test;
import org.springframework.orm.hibernate3.HibernateTemplate;

public abstract class AbstractDaoTest {
	
	protected HibernateTemplate hibernateTemplate;
	
	protected ICommonDao commonDao = getDao();
		
	public abstract ICommonDao getDao();
	
	public abstract Object getDynamicObjectA();
	
	public abstract Object getDynamicObjectB();
	
	public abstract Object getDynamicObjectC();
	
	public abstract Object getDynamicObjectD();
	
	public void setUp() {}
	
	public void tearDown() {}
	
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
	
	@Test
	public void deleteOne() {
		long idA = commonDao.saveOrUpdate(getDynamicObjectA());
		commonDao.saveOrUpdate(getDynamicObjectD());
		commonDao.delete(idA);
		List result = commonDao.getAll();
		assertEquals(1,result.size());
		assertEquals(getDynamicObjectD(),result.get(0));		
	}	
	
	
	@Test
	public void deleteAll() {
		commonDao.saveOrUpdate(getDynamicObjectA());
		commonDao.saveOrUpdate(getDynamicObjectD());
		commonDao.saveOrUpdate(getDynamicObjectB());
		commonDao.deleteAll();
		List result = commonDao.getAll();
		assertEquals(0,result.size());
	}
			
		
}
