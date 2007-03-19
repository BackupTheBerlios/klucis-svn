package lv.webkursi.mtest.lab02.data;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class QuestionTypeValidator implements Validator {

	public boolean supports(Class clazz) {
		return clazz.equals(QuestionTypeForm.class);
	}

	public void validate(Object command, Errors errors) {
		QuestionTypeForm userForm = (QuestionTypeForm) command;
		
	}
}