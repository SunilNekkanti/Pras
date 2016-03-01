package com.pfchoice.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.pfchoice.core.entity.HedisMeasure;




@Component
public class HedisMeasureValidator implements Validator {
 
    //which objects can be validated by this validator
    @Override
    public boolean supports(Class<?> paramClass) {
        return HedisMeasure.class.equals(paramClass);
    }
 
    @Override
    public void validate(Object obj, Errors errors) {
     
         
    	HedisMeasure hedisMeasure = (HedisMeasure) obj;
        if(hedisMeasure.getId() != null)
        {	
        	if(hedisMeasure.getId() <=0){
        		errors.rejectValue("id", "negativeValue", new Object[]{"'id'"}, "id can't be negative");
        	}
        }       

        if (hedisMeasure.getCode().length() < 2 && hedisMeasure.getCode().length() >= 1) {
            errors.rejectValue("code", "code.tooshort", "Code must be at least 1 character.");
        }
        else if (hedisMeasure.getCode().length() > 3 ) {
            errors.rejectValue("code", "code.toolong", "Code must be at less than 4 characters.");
        }
        
        if (hedisMeasure.getDescription().length() < 5 && hedisMeasure.getDescription().length()>= 1) {
            errors.rejectValue("description", "description.tooshort", "Description must be at least 5 characters.");
        }
        
        if (hedisMeasure.getDescription().length() > 50) {
            errors.rejectValue("description", "description.tooshort", "Description must be  less than 50 characters.");
        }
       
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code",  "error.code",  "Code Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description",  "error.description",  "Description Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hedisMsrGrp",  "error.hedisMsrGrp",  "Hedis Measure Group Required");
        
    }
}
