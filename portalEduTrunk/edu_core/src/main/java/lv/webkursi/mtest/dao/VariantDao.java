package lv.webkursi.mtest.dao;

import java.util.List;

import lv.webkursi.mtest.domain.Variant;

import org.springframework.orm.hibernate3.HibernateTemplate;

public class VariantDao implements IQuestionDao {
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public long saveOrUpdate(Object o) {
		if (!(o instanceof Variant)) {
			throw new IllegalArgumentException();
		}
		hibernateTemplate.saveOrUpdate(o);
		return ((Variant)o).getId();
	}

	@SuppressWarnings("unchecked")
	public List getAll() {
		return (List<Variant>) hibernateTemplate
				.find("from Variant v order by v.name");
	}

	public Variant get(long id) {
		return (Variant) hibernateTemplate.get(Variant.class, id);
	}
}
