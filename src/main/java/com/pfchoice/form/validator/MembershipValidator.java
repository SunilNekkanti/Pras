package com.pfchoice.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
 
import com.pfchoice.core.entity.Membership;

@Component
public class MembershipValidator implements Validator {
 
    //which objects can be validated by this validator
    @Override
    public boolean supports(Class<?> paramClass) {
        return Membership.class.equals(paramClass);
    }
 
    @Override
    public void validate(Object obj, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "firstName.required");
         
        Membership mbr = (Membership) obj;
        if(mbr.getId() <=0){
            errors.rejectValue("id", "negativeValue", new Object[]{"'id'"}, "id can't be negative");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "lastName.required"); 
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "firstName.required");

        if (mbr.getFirstName().length() < 6) {
            errors.rejectValue("firstName", "firstName.tooshort", "FirstName must be at least 6 characters.");
        }
        
        if (mbr.getLastName().length() < 6) {
            errors.rejectValue("lastName", "lasttName.tooshort", "LasttName must be at least 6 characters.");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dob", "dob.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "medicaidNo", "medicaidNo.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "medicareNo", "medicareNo.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "genderId", "genderId.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "countyCode", "countyCode.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ethinicCode", "ethinicCode.required");
        
        System.out.println("end of validation");
    }
}
