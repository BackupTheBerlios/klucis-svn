package lv.webkursi.mtest.lab02.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lv.webkursi.mtest.lab02.dao.ICommonDao;
import lv.webkursi.mtest.lab02.data.UserForm;
import lv.webkursi.mtest.lab02.domain.Person;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;

public class UserNewFormController extends AbstractFormController {
	private Log log = LogFactory.getLog(UserNewFormController.class);
	
	protected ICommonDao dao;

	public UserNewFormController() {
		setCommandClass(UserForm.class);
	}

	protected ModelAndView showForm(HttpServletRequest request,
			HttpServletResponse response, BindException errors) {
		String pageSetName = request.getServletPath();
		log.info("REQUEST FOR PAGESET " + pageSetName);


		ModelAndView result = new ModelAndView("newUser");
		result.addAllObjects(errors.getModel());
		result.addObject("command", new UserForm());

		return result;
	}

	protected ModelAndView processFormSubmission(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		String pageSetName = request.getServletPath();
		log.info("FORM SUBMIT FOR PAGESET " + pageSetName);

		UserForm userForm = (UserForm) command;
		String success = null;
		System.err.println("AA");
		if (!errors.hasErrors()) {
			System.err.println("BB");

			Person person = new Person();
			person.setEmail(userForm.getEmail());
			person.setFirstName(userForm.getFirstName());
			person.setLastName(userForm.getLastName());
			person.setLogin(userForm.getLogin());
			person.setPassword(userForm.getPassword());
			dao.saveOrUpdate(person);
			success = "User successfully registered";
		}
		else {
		System.err.println("CC");
		System.err.println("errorCount = " + errors.getErrorCount());
		System.err.println("errorMessage = " + errors.getMessage());
		}
		System.err.println("DD");

		ModelAndView result = new ModelAndView("newUser");
		result.addAllObjects(errors.getModel());		
		result.addObject("command", new UserForm());
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
