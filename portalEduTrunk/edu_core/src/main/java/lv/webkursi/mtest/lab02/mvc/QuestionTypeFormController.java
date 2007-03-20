package lv.webkursi.mtest.lab02.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lv.webkursi.mtest.lab02.dao.ICommonDao;
import lv.webkursi.mtest.lab02.data.QuestionTypeForm;
import lv.webkursi.mtest.lab02.domain.QuestionType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class QuestionTypeFormController extends SimpleFormController {
	private Log log = LogFactory.getLog(QuestionTypeFormController.class);
	
	protected ICommonDao dao;

	public QuestionTypeFormController() {
		setCommandClass(QuestionType.class);
	}
	
	protected Object formBackingObject(HttpServletRequest request) {
		QuestionType qt = new QuestionType();
		if (request.getParameter("id") != null) {
			long id = Long.parseLong(request.getParameter("id"));
			qt = (QuestionType)dao.get(id);
		}
		return qt;
	}

	protected void doSubmitAction(Object command) {
		QuestionType qt = (QuestionType) command;
		dao.saveOrUpdate(qt);
	}

	
	/*
	protected ModelAndView showForm(HttpServletRequest request,
			HttpServletResponse response, BindException errors) {
		String pageSetName = request.getServletPath();
		log.info("REQUEST FOR PAGESET " + pageSetName);

		ModelAndView result = new ModelAndView("questiontype_new");
		result.addAllObjects(errors.getModel());
		
		
		
		QuestionTypeForm qtForm = new QuestionTypeForm();
		if (request.getParameter("id") != null) {
			long id = Long.parseLong(request.getParameter("id"));
			QuestionType qt = (QuestionType)dao.get(id);
			qtForm.setLabel(qt.getLabel());
			qtForm.setInstruction(qt.getInstruction());
		}

		result.addObject("command", qtForm);
		
		
		
		return result;
	}

	protected ModelAndView processFormSubmission(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		String pageSetName = request.getServletPath();
		log.info("FORM SUBMIT FOR PAGESET " + pageSetName);

		QuestionTypeForm qtForm = (QuestionTypeForm) command;
		ModelAndView result = new ModelAndView("questiontype_new");
		if (!errors.hasErrors()) {
			QuestionType qt = null;
			if (request.getParameter("id") != null) {
				long id = Long.parseLong(request.getParameter("id"));
				qt = (QuestionType)dao.get(id);
			}
			else {
				qt = new QuestionType();
			}
			qt.setLabel(qtForm.getLabel());
			qt.setInstruction(qtForm.getInstruction());
			dao.saveOrUpdate(qt);
			result.addObject("success",  "QuestionType successfully added");
		}

		result.addAllObjects(errors.getModel());		
//		result.addObject("command", new PersonForm());
		return result;
	}
	*/

	public ICommonDao getDao() {
		return dao;
	}

	public void setDao(ICommonDao dao) {
		this.dao = dao;
	}
}
