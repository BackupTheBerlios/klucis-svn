package lv.webkursi.mtest.lab02.data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lv.webkursi.mtest.lab02.domain.Person;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class PersonValidator implements Validator {

	private static final String EMAIL_REGEXP = "^[a-z0-9]+([_\\.-][a-z0-9]+)*@([a-z0-9]+([\\.-][a-z0-9]+)*)+\\.[a-z]{2,}$";

	private static final String USERNAME_REGEXP = "^[a-zA-Z][a-zA-Z0-9]*$";

	private Log log = LogFactory.getLog(PersonValidator.class);

	public boolean supports(Class clazz) {
		return clazz.equals(Person.class);
	}

	public void validate(Object command, Errors errors) {
		Person userForm = (Person) command;

		ValidationUtils.rejectIfEmpty(errors, "login", "required.login",
				"Login is required");
		if (userForm.getLogin().length() < 2
				|| userForm.getLogin().length() > 12) {
			errors.rejectValue("login", "login.invalid.length",
					"Login should be between 2 and 12 characters");
		}
		if (!Pattern.matches(USERNAME_REGEXP, userForm.getLogin())) {
			errors
					.rejectValue("login", "login.invalid.chars",
							"Login contains only English letters and digits and starts with a letter");
		}

		ValidationUtils.rejectIfEmpty(errors, "firstName", "required.firstName",
				"Name is required");
		ValidationUtils.rejectIfEmpty(errors, "lastName", "required.lastName",
		"Name is required");
		ValidationUtils.rejectIfEmpty(errors, "email", "required.email",
				"Email is required");
		Pattern pEmail = Pattern
				.compile(EMAIL_REGEXP, Pattern.CASE_INSENSITIVE);

		Matcher m = pEmail.matcher(userForm.getEmail());
		if (!m.matches()) {
			errors.rejectValue("email", "invalid.email", "E-mail is invalid");
		}

		ValidationUtils.rejectIfEmpty(errors, "password", "required.password",
				"Password is required");
		if (userForm.getPassword().length() < 6
				|| userForm.getPassword().length() > 12) {
			errors.rejectValue("password", "password.invalid.length",
					"Password should be between 6 and 12 characters");
		}
		ValidationUtils.rejectIfEmpty(errors, "password2",
				"required.password2", "Password confirmation is required");
		if (!userForm.getPassword().equals(userForm.getPassword2())) {
			errors.rejectValue("password2", "password.dont.match",
					"Passwords do not match");
		}

	}

	public void validateEmail(String email, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "email", "required.email",
				"E-mail is required");
		Pattern pEmail = Pattern
				.compile(EMAIL_REGEXP, Pattern.CASE_INSENSITIVE);
		Matcher m = pEmail.matcher(email);
		if (m.matches()) {
			errors.reject("invalid.email", "E-mail is invalid");
		}
	}
}
