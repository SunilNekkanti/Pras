package com.pfchoice.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.pfchoice.core.entity.HedisMeasureRule;




@Component
public class HedisMeasureRuleValidator implements Validator {
 
    //which objects can be validated by this validator
    @Override
    public boolean supports(Class<?> paramClass) {
        return HedisMeasureRule.class.equals(paramClass);
    }
 
    @Override
    public void validate(Object obj, Errors errors) {
     
         
    	HedisMeasureRule hedisMeasureRule = (HedisMeasureRule) obj;
        if(hedisMeasureRule.getId() != null)
        {	
        	if(hedisMeasureRule.getId() <=0){
        		errors.rejectValue("id", "negativeValue", new Object[]{"'id'"}, "id can't be negative");
        	}
        }       

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hedisMeasure",  "error.hedisMeasure",  	"Hedis Measure Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cptMeasure",  	"error.cptMeasure",  	"CPT Measure Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "icdMeasure",  	"error.icdMeasure",  	"ICD Measure Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "effectiveYear",  "error.effectiveYear",  "Effective Year Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dueDate",  		"error.dueDate",  		"Due Date Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vistDate",  		"error.vistDate",  		"Vist Date Required");
       
    }
}
