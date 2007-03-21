package lv.webkursi.mtest.lab02.mvc;

import javax.servlet.http.HttpServletRequest;

import lv.webkursi.mtest.lab02.dao.ICommonDao;
import lv.webkursi.mtest.lab02.domain.QuestionType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class QuestionTypeFormController extends SimpleFormController {
	private Log log = LogFactory.getLog(QuestionTypeFormController.class);
	
	protected ICommonDao dao;

	public QuestionTypeFormController() {
		setCommandClass(QuestionType.class);
	}
	
	protected Object formBackingObject(HttpServletRequest request) {
		QuestionType qt = new QuestionType();
		String idString = request.getParameter("id");
		if (idString != null && !idString.equals("")) {
			long id = Long.parseLong(request.getParameter("id"));
			qt = (QuestionType)dao.get(id);
		}
		log.info("Loaded qt " + qt);
		return qt;
	}

	protected void doSubmitAction(Object command) {
		QuestionType qt = (QuestionType) command;
		log.info("Updating qt " + qt);
		dao.saveOrUpdate(qt);
	}

	public ICommonDao getDao() {
		return dao;
	}

	public void setDao(ICommonDao dao) {
		this.dao = dao;
	}
}
