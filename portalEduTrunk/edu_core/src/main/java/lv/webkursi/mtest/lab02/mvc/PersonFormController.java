package lv.webkursi.mtest.lab02.mvc;

import javax.servlet.http.HttpServletRequest;

import lv.webkursi.mtest.lab02.dao.ICommonDao;
import lv.webkursi.mtest.lab02.domain.Person;
import lv.webkursi.mtest.lab02.domain.QuestionType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class PersonFormController extends SimpleFormController {
	private Log log = LogFactory.getLog(PersonFormController.class);
	
	protected ICommonDao dao;

	public PersonFormController() {
		setCommandClass(Person.class);
	}
	
	protected Object formBackingObject(HttpServletRequest request) {
		Person p = new Person();
		String idString = request.getParameter("id");
		if (idString != null && !idString.equals("")) {
			long id = Long.parseLong(request.getParameter("id"));
			p = (Person)dao.get(id);
			// copy password to password2
			p.setPassword2(p.getPassword());
		}
		log.info("Loading p " + p);
		return p;
	}

	protected void doSubmitAction(Object command) {
		Person p = (Person) command;
		log.info("Updating p " + p);
		dao.saveOrUpdate(p);
	}

	public ICommonDao getDao() {
		return dao;
	}

	public void setDao(ICommonDao dao) {
		this.dao = dao;
	}
}
