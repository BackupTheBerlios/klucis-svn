package lv.webkursi.mtest.lab02.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lv.webkursi.mtest.lab02.dao.ICommonDao;
import lv.webkursi.mtest.lab02.data.PersonForm;
import lv.webkursi.mtest.lab02.domain.Person;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;

public class PersonNewFormController extends AbstractFormController {
	private Log log = LogFactory.getLog(PersonNewFormController.class);
	
	protected ICommonDao dao;

	public PersonNewFormController() {
		setCommandClass(PersonForm.class);
	}

	protected ModelAndView showForm(HttpServletRequest request,
			HttpServletResponse response, BindException errors) {
		String pageSetName = request.getServletPath();
		log.info("REQUEST FOR PAGESET " + pageSetName);
		ModelAndView result = new ModelAndView("person_new");
		result.addAllObjects(errors.getModel());
		PersonForm personForm = new PersonForm();
		if (request.getParameter("id") != null) {
			long id = Long.parseLong(request.getParameter("id"));
			Person p = (Person)dao.get(id);
			log.info("Editing person " + p);
			personForm.setFirstName(p.getFirstName());
			personForm.setLastName(p.getLastName());
			personForm.setLogin(p.getLogin());
			personForm.setEmail(p.getEmail());
			personForm.setPassword(p.getPassword());
			personForm.setPassword2(p.getPassword());
		}
		
		result.addObject("command", personForm);

		return result;
	}

	protected ModelAndView processFormSubmission(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		String pageSetName = request.getServletPath();
		log.info("FORM SUBMIT FOR PAGESET " + pageSetName);

		PersonForm userForm = (PersonForm) command;
		String success = null;
		if (!errors.hasErrors()) {			
			Person person = null;
			if (request.getParameter("id") != null) {
				long id = Long.parseLong(request.getParameter("id"));
				person = (Person)dao.get(id);
			}
			else {
				person = new Person();
			}
			person.setEmail(userForm.getEmail());
			person.setFirstName(userForm.getFirstName());
			person.setLastName(userForm.getLastName());
			person.setLogin(userForm.getLogin());
			person.setPassword(userForm.getPassword());
			dao.saveOrUpdate(person);
			success = "User successfully registered";
		}

		ModelAndView result = new ModelAndView("person_new");
		result.addAllObjects(errors.getModel());		
		result.addObject("command", new PersonForm());
		if (success != null) {
			result.addObject("success", success);
		}

		return result;
	}

	public ICommonDao getDao() {
		return dao;
	}

	public void setDao(ICommonDao dao) {
		this.dao = dao;
	}
}
