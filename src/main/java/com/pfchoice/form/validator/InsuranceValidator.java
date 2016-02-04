package com.pfchoice.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.pfchoice.core.entity.Insurance;


@Component
public class InsuranceValidator implements Validator {
 
    //which objects can be validated by this validator
    @Override
    public boolean supports(Class<?> paramClass) {
        return Insurance.class.equals(paramClass);
    }
 
    @Override
    public void validate(Object obj, Errors errors) {
     
         
        Insurance ins = (Insurance) obj;
        if(ins.getId() != null)
        {	
        	if(ins.getId() <=0){
        		errors.rejectValue("id", "negativeValue", new Object[]{"'id'"}, "id can't be negative");
        	}
        }       

        if (ins.getName().length() < 6) {
            errors.rejectValue("name", "name.tooshort", "Name must be at least 6 characters.");
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "activeInd",  "error.activeInd",  "ActiveIndicator Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "createdDate",  "error.createdDate",  "Created Date  Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "updatedDate",  "error.updatedDate",  "Updated Date  Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "createdBy",  "error.createdBy",  "Created By  Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "updatedBy",  "error.updatedBy",   "Updated By  Required");
        
    }
}
