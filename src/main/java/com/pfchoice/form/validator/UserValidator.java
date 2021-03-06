package com.pfchoice.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.pfchoice.core.entity.User;

@Component
public class UserValidator implements Validator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> paramClass) {
		return User.class.equals(paramClass);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object obj, Errors errors) {

		User user = (User) obj;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.username", "Username Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.password", "Password Required");
		if (user.getUsername().length() >= 1 && user.getUsername().length() < 6) {
			errors.rejectValue("username", "error.username", "Username must be at least 6 characters.");
		} else if (user.getUsername().length() > 10) {
			errors.rejectValue("username", "error.username", "Username must be less than  11 characters.");
		}
		if (user.getPassword().length() >= 1 && user.getPassword().length() < 6) {
			errors.rejectValue("password", "error.password", "Login must be at least 6 characters.");
		}
	}
}