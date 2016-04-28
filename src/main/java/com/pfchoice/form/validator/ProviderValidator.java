package com.pfchoice.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.pfchoice.core.entity.Provider;

@Component
public class ProviderValidator implements Validator {

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> paramClass) {
		return Provider.class.equals(paramClass);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object obj, Errors errors) {

		Provider prvdr = (Provider) obj;
		if (prvdr.getId() != null && prvdr.getId() <= 0) {
				errors.rejectValue("id", "negativeValue", new Object[] { "'id'" }, "id can't be negative");
		}

		if (prvdr.getName().length() < 6) {
			errors.rejectValue("name", "name.tooshort", "Name must be at least 6 characters.");
		}
		if (prvdr.getCode().length() < 2) {
			errors.rejectValue("code", "cpde.tooshort", "Code must be at least 6 characters.");
		}
	}
}
