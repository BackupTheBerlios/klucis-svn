package lv.webkursi.mtest.lab02.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lv.webkursi.mtest.lab02.dao.CommonDao;
import lv.webkursi.mtest.lab02.domain.Person;
import lv.webkursi.mtest.lab02.domain.QuestionType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class DBInitController extends AbstractController {
	
	public DBInitController() {
		setSynchronizeOnSession(true);
	}

	private Log log = LogFactory.getLog(DBInitController.class);

	protected CommonDao personDao;

	protected CommonDao questionTypeDao;

	public CommonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(CommonDao personDao) {
		this.personDao = personDao;
	}

	public CommonDao getQuestionTypeDao() {
		return questionTypeDao;
	}

	public void setQuestionTypeDao(CommonDao questionTypeDao) {
		this.questionTypeDao = questionTypeDao;
	}

	private void initModel() {
		Person admin = new Person("AdminFirstName", "AdminLastName", "admin",
				"admin@admin.com");
		admin.setPassword("admin");
		personDao.saveOrUpdate(admin);
		QuestionType qt = new QuestionType("1 of N",
				"Select one correct answer!");
		questionTypeDao.saveOrUpdate(qt);
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = new ModelAndView("dbinit");
		initModel();
		log
				.info("Database initialized to contain admin user and one question type");
		return result;
	}
}
