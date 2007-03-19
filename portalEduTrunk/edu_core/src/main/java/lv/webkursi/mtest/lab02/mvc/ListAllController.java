package lv.webkursi.mtest.lab02.mvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lv.webkursi.mtest.lab02.dao.ICommonDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class ListAllController implements Controller {

	Log log = LogFactory.getLog(ListAllController.class);
	protected ICommonDao dao;

	public ICommonDao getDao() {
		return dao;
	}

	public void setDao(ICommonDao dao) {
		this.dao = dao;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {		
		ModelAndView result = new ModelAndView("listall");
		List list = dao.getAll();
		result.addObject("list",list);
		return result;
	}

}
