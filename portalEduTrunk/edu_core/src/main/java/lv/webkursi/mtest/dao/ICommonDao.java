package lv.webkursi.mtest.dao;

import java.util.List;

public interface ICommonDao {
	public long saveOrUpdate(Object q);
	public Object get(long id);
	public void delete(long id);
	public List getAll();
	public void deleteAll();
}