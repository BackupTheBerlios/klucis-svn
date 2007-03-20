package lv.webkursi.mtest.lab02.data;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class QuestionTypeValidator implements Validator {

	public boolean supports(Class clazz) {
		return clazz.equals(QuestionTypeForm.class);
	}

	public void validate(Object command, Errors errors) {
		QuestionTypeForm qtForm = (QuestionTypeForm) command;
		ValidationUtils.rejectIfEmpty(errors, "label", "required.label",
				"Label is required");
		if (qtForm.getLabel().length() < 2 || qtForm.getLabel().length() > 20) {
			errors.rejectValue("label", "label.invalid.length",
					"Label should be between 2 and 20 characters");
		}
	}
}