package com.pfchoice.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.pfchoice.core.entity.ICDMeasure;

@Component("iCDMeasureValidator")
public class ICDMeasureValidator implements Validator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> paramClass) {
		return ICDMeasure.class.equals(paramClass);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object obj, Errors errors) {

		ICDMeasure icdMeasure = (ICDMeasure) obj;
		if (icdMeasure.getId() != null && icdMeasure.getId() <= 0) {
			errors.rejectValue("id", "negativeValue", new Object[] { "'id'" }, "id can't be negative");

		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "error.code", "Code Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.description", "Description Required");
		if (icdMeasure.getCode().length() < 2) {

			errors.rejectValue("code", "error.code", "Code must be atleast 3 characters");
		}
		if (icdMeasure.getCode().length() > 10) {

			errors.rejectValue("code", "error.code", "Code must be atleast 3 characters and less than 10 characters");
		}
		if (icdMeasure.getDescription().length() <= 5) {
			errors.rejectValue("description", "error.description", "Description must be atleast 5 characters");
		}
		if (icdMeasure.getDescription().length() > 500) {
			errors.rejectValue("description", "error.description",
					"Description must be atleast 5 characters and less than 500 characters");
		}

	}
}
