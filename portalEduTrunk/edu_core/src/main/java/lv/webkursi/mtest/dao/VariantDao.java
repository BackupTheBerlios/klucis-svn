package lv.webkursi.mtest.dao;

import java.util.List;

import lv.webkursi.mtest.domain.Variant;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class VariantDao extends HibernateDaoSupport implements IQuestionDao {
	public long saveOrUpdate(Object o) {
		if (!(o instanceof Variant)) {
			throw new IllegalArgumentException();
		}
		getHibernateTemplate().saveOrUpdate(o);
		return ((Variant)o).getId();
	}

	@SuppressWarnings("unchecked")
	public List getAll() {
		return (List<Variant>) getHibernateTemplate()
				.find("from Variant v order by v.name");
	}

	public Variant get(long id) {
		return (Variant) getHibernateTemplate().get(Variant.class, id);
	}

	public void delete(long id) {
		Variant var = get(id);
		getHibernateTemplate().delete(var);
	}
}
