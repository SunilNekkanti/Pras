package com.pfchoice.form.validator;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.pfchoice.core.entity.LeadMembership;

@Component
public class LeadMembershipValidator implements Validator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> paramClass) {
		return LeadMembership.class.equals(paramClass);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object obj, Errors errors) {

		LeadMembership mbr = (LeadMembership) obj;
		if (mbr.getId() != null && mbr.getId() <= 0) {
			errors.rejectValue("id", "negativeValue", new Object[] { "'id'" }, "id can't be negative");
		}

		if (mbr.getFirstName().length() < 2) {
			errors.rejectValue("firstName", "firstName.tooshort", "FirstName must be at least 2 characters.");
		}

		if (mbr.getLastName().length() < 2) {
			errors.rejectValue("lastName", "lastName.tooshort", "LastName must be at least 2 characters.");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dob", "error.dob", "DOB Required");
		if (mbr.getDob() != null && mbr.getDob().after(new Date())) {
			errors.rejectValue("dob", "error.date.future", "Date must be less than current date");
		}

		if (mbr.getMedicaidNo().length() < 1 || mbr.getMedicareNo().length() < 1) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "medicaidNo", "error.medicaidNo",
					"Medicaid / Medicare Number Required");
		}
		if (mbr.getMedicaidNo().length() >= 1 && mbr.getMedicaidNo().length() < 10) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "medicaidNo", "error.medicaidNo",
					"Medicaid Number must be  at least 10 characters");
		}

		if (mbr.getMedicareNo().length() >= 1 && mbr.getMedicareNo().length() < 10) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "medicareNo", "error.medicareNo",
					"Medicare Number must be at least 10 characters");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "genderId", "error.genderId", "Gender Required");

	}
}