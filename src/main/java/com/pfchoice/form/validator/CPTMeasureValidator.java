package com.pfchoice.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.pfchoice.core.entity.CPTMeasure;



@Component("cPTMeasureValidator")
public class CPTMeasureValidator implements Validator {
 
    //which objects can be validated by this validator
    @Override
    public boolean supports(Class<?> paramClass) {
        return CPTMeasure.class.equals(paramClass);
    }
 
    @Override
    public void validate(Object obj, Errors errors) {
       
    	CPTMeasure cptMeasure= (CPTMeasure) obj;
        if(cptMeasure.getId() != null)
        {	
	        if(cptMeasure.getId() <=0){
	            errors.rejectValue("id", "negativeValue", new Object[]{"'id'"}, "id can't be negative");
	        }
	       
        }    
             
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "error.code","Code Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description","error.description", "Description Required");
        if(cptMeasure.getCode().length() <=5 && cptMeasure.getCode().length() >= 1){
        	
        	  errors.rejectValue("code", "code.tooShort","Code must be atleast 5 characters");
        	 
        }
        if(cptMeasure.getDescription().length() <=5 && cptMeasure.getCode().length() >= 1){
        	  errors.rejectValue("description", "error.description", "Description must be atleast 5 characters");
        }
              
    }
}
