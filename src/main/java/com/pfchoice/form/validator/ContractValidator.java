package com.pfchoice.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


import com.pfchoice.core.entity.Contract;



@Component
public class ContractValidator implements Validator {
 
    //which objects can be validated by this validator
    @Override
    public boolean supports(Class<?> paramClass) {
        return Contract.class.equals(paramClass);
    }
 
    @Override
    public void validate(Object obj, Errors errors) {
       
         
        Contract contract = (Contract) obj;
        if(contract.getId() != null)
        {
	        if(contract.getId() <=0){
	            errors.rejectValue("id", "negativeValue", new Object[]{"'id'"}, "id can't be negative");
	        }
        }    
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contractNBR", 		"error.contractNBR", 		"Contract NBR Required");
       if (contract.getContractNBR().length() < 5 && contract.getContractNBR().length() >=1) {
            errors.rejectValue("contractNBR", "contractNBR.tooshort",     "Contract NBR must be at least 5 characters and less than 20 characters.");
        }
                 
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "PMPM", 		"error.PMPM", 		"PMPM Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startDate",  "error.startDate",  "Start Date Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endDate", 	"error.endDate",    "End Date Required");
        
    }
}
