package com.pfchoice.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.pfchoice.core.entity.ICDMeasure;

@Component("iCDMeasureValidator")
public class ICDMeasureValidator implements Validator {
 
    //which objects can be validated by this validator
    @Override
    public boolean supports(Class<?> paramClass) {
        return ICDMeasure.class.equals(paramClass);
    }
 
    @Override
    public void validate(Object obj, Errors errors) {
       
    	ICDMeasure icdMeasure= (ICDMeasure) obj;
        if(icdMeasure.getId() != null)
        {	
	        if(icdMeasure.getId() <=0){
	            errors.rejectValue("id", "negativeValue", new Object[]{"'id'"}, "id can't be negative");
	        }
	       
        }    
             
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "error.code","Code Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description","error.description", "Description Required");
        if(icdMeasure.getCode().length() < 4 && icdMeasure.getCode().length() >= 1){
        	
        	  errors.rejectValue("code", "error.code","Code must be atleast 5 characters");
        }
        if(icdMeasure.getCode().length() > 20 && icdMeasure.getCode().length() >= 1){
        	
        	  errors.rejectValue("code", "error.code","Code must be atleast 5 characters and less than 20 characters");
        }
        if(icdMeasure.getDescription().length() <=5 && icdMeasure.getDescription().length() >= 1){
        	  errors.rejectValue("description", "error.description", "Description must be atleast 5 characters");
        }
        if(icdMeasure.getDescription().length() > 50 && icdMeasure.getDescription().length() >= 1){
        	  errors.rejectValue("description", "error.description", "Description must be atleast 5 characters and less than 50 characters");
        }
              
    }
}
