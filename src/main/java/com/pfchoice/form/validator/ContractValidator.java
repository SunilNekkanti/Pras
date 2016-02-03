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
        if(contract.getId() <=0){
            errors.rejectValue("id", "negativeValue", new Object[]{"'id'"}, "id can't be negative");
        }
        

        if (contract.getContractNBR().length() < 5) {
            errors.rejectValue("contractNBR", "contractNBR.tooshort",     "Contract NBR must be at least 5 characters.");
        }
        
        
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "PMPM", "error.PMPM", "PMPM.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "createdDate",  "error.createdDate",  "Created Date  Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "updatedDate",  "error.updatedDate",  "Updated Date  Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "createdBy",  "error.createdBy",  "Created By  Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "updatedBy",  "error.updatedBy",   "Updated By  Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "activeInd",  "error.activeInd",  "ActiveIndicator Required");
        
        System.out.println("end of validation");
    }
}
