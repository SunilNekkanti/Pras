package com.pfchoice.form.validator;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
 
import com.pfchoice.core.entity.MembershipInsurance;

@Component
public class MembershipInsuranceValidator implements Validator {
 
    //which objects can be validated by this validator
    @Override
    public boolean supports(Class<?> paramClass) {
        return MembershipInsurance.class.equals(paramClass);
    }
 
    @Override
    public void validate(Object obj, Errors errors) {
       
         
        MembershipInsurance mbrIns = (MembershipInsurance) obj;
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "insId", 	"error.insId",	"Insurance Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "activityDate", 	"error.activityDate",	"Activity date Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "activityMonth", 	"error.activityMonth",	"Activity Month Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "effStartDate", 	"error.effStartDate",	"Effective Start Date Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "effEndDate", 	"error.effEndDate",	"Effective End Date Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "effEndDate", 	"error.effEndDate",	"Effective End Date Required");
       
     
    }
}
