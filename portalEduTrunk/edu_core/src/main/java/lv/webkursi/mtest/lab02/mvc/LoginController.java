package lv.webkursi.mtest.lab02.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lv.webkursi.mtest.lab02.dao.CommonDao;
import lv.webkursi.mtest.lab02.domain.Person;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class LoginController implements Controller {
	
	Log log = LogFactory.getLog(LoginController.class);

	protected CommonDao dao;

	public CommonDao getDao() {
		return dao;
	}

	public void setDao(CommonDao dao) {
		this.dao = dao;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView result = new ModelAndView("login");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		if (login == null || login.equals("")) {
			return result;
		}
		Person p = dao.getPersonByLogin(login);
		if (p.getPassword().equals(password)) {
			request.getSession().invalidate();
			HttpSession session = request.getSession(true);
			session.setAttribute("user", p);
			return new ModelAndView("welcome");
		}
		else {
			log.warn("Unsuccessful login for person " + p);
			log.warn("...Using incorrect password " + password);
			result.addObject("error", "Username and Password don't match");
			return result;
		}
	}
}
