package lv.webkursi.mtest.dao;

import java.util.List;

public interface ICommonDao {
	public long saveOrUpdate(Object q);
	public List getAll();
	public Object get(long id);
}