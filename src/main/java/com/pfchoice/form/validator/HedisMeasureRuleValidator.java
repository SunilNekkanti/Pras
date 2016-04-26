package com.pfchoice.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.pfchoice.core.entity.HedisMeasureRule;

@Component
public class HedisMeasureRuleValidator implements Validator {

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> paramClass) {
		return HedisMeasureRule.class.equals(paramClass);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object obj, Errors errors) {

		HedisMeasureRule hedisMeasureRule = (HedisMeasureRule) obj;
		if (hedisMeasureRule.getId() != null) {
			if (hedisMeasureRule.getId() <= 0) {
				errors.rejectValue("id", "negativeValue", new Object[] { "'id'" }, "id can't be negative");
			}
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hedisMeasure", "error.hedisMeasure",
				"Hedis Measure Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.description", "Descritpion Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cptCodes", "error.cptCodes", "CPT Measure Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "icdCodes", "error.icdCodes", "ICD Measure Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "effectiveYear", "error.effectiveYear",
				"Effective Year Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "insId", "error.insId", "Insurance Required");
	}
}
