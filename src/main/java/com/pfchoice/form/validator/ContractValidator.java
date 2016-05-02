package com.pfchoice.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.pfchoice.core.entity.Contract;

@Component
public class ContractValidator implements Validator {

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> paramClass) {
		
		return Contract.class.equals(paramClass);
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object obj, Errors errors) {
		
		Contract contract = (Contract) obj;
		
		if (contract.getId() != null && contract.getId() <= 0) {
			
				errors.rejectValue("id", "negativeValue", new Object[] { "'id'" }, "id can't be negative");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contractNBR", "error.contractNBR", "Contract NBR Required");

		if (contract.getContractNBR().length() < 5 && contract.getContractNBR().length() >= 1) {
			errors.rejectValue("contractNBR", "contractNBR.tooshort",
					"Contract NBR must be at least 5 characters and less than 20 characters.");
		}
		if (contract.getReferenceContract() != null && contract.getReferenceContract().getIns() != null) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pmpm", "error.PMPM", "PMPM Required");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startDate", "error.startDate", "Start Date Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endDate", "error.endDate", "End Date Required");

	}
}
