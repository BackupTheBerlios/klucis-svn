package lv.webkursi.mtest.lab02.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DummyDao implements ICommonDao {
	private Log log = LogFactory.getLog(DummyDao.class);

	public void delete(long id) {
		log.info("delete(" + id + ")");
	}

	public void deleteAll() {
		log.info("deleteAll()");		
	}

	public Object get(long id) {
		log.info("get(" + id + ")");
		return null;
	}

	public List getAll() {
		log.info("deleteAll()");
		return new ArrayList();
	}

	public long saveOrUpdate(Object q) {
		log.info("saveOrUpdate(" + q + ")");
		return -1;
	}

}
