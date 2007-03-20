package lv.webkursi.mtest.lab02.mvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lv.webkursi.mtest.lab02.dao.ICommonDao;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class DeleteController extends AbstractController {

	protected ICommonDao dao;

	protected String viewName;

	public ICommonDao getDao() {
		return dao;
	}

	public void setDao(ICommonDao dao) {
		this.dao = dao;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id = Long.parseLong(request.getParameter("id"));
		dao.delete(id);
		List list = dao.getAll();
		ModelAndView result = new ModelAndView(viewName);
		result.addObject("list", list);
		return result;
	}
}
