package lv.webkursi.mtest.lab02.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lv.webkursi.mtest.lab02.dao.ICommonDao;
import lv.webkursi.mtest.lab02.dao.InitUtility;
import lv.webkursi.mtest.lab02.domain.Person;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class DBInitController implements Controller {

	private Log log = LogFactory.getLog(DBInitController.class);
	
	protected ICommonDao dao;

	public ICommonDao getDao() {
		return dao;
	}

	public void setDao(ICommonDao dao) {
		this.dao = dao;
	}

	private void initModel() {
		Person admin = new Person("AdminFirstName","AdminLastName", "admin", "admin@admin.com");
		admin.setPassword("admin");
		dao.saveOrUpdate(admin);		
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView result = new ModelAndView("dbinit_message");
		initModel();
		return result;
	}

}
