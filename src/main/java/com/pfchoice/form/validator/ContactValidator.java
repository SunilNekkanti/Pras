package com.pfchoice.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.pfchoice.core.entity.Contact;



@Component
public class ContactValidator implements Validator {
 
    //which objects can be validated by this validator
    @Override
    public boolean supports(Class<?> paramClass) {
        return Contact.class.equals(paramClass);
    }
 
    @Override
    public void validate(Object obj, Errors errors) {
       
         
        Contact cnt = (Contact) obj;
        if(cnt.getId() != null)
        {	
	        if(cnt.getId() <=0){
	            errors.rejectValue("id", "negativeValue", new Object[]{"'id'"}, "id can't be negative");
	        }
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "refContact","error.refContact", "ReferenceContact Required");
        }    
        

        if (cnt.getHomePhone().length() < 6) {
            errors.rejectValue("homePhone", "homePhone.tooshort",     "PhoneNumber must be at least 10 Digits.");
        }
        if (cnt.getMobilePhone().length() < 2) {
            errors.rejectValue("mobilePhone", "mobilePhone.tooshort", "Mobile Number must be at least 10 Digits.");
        }
        
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "faxNumber","error.faxNumber", "FaxNumber Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.email","Email Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address1","address1", "Address1 Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "error.city","city.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "stateCode","error.stateCode", "StateCode Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zipCode","error.zipCode", "zipCodeRequired");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "createdDate",  "error.createdDate",  "Created Date  Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "updatedDate",  "error.updatedDate",  "Updated Date  Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "createdBy",  "error.createdBy",  "Created By  Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "updatedBy",  "error.updatedBy",   "Updated By  Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "activeInd",  "error.activeInd",  "ActiveIndicator Required");
        
        
        if (cnt.getAddress1().length() < 10) {
            errors.rejectValue("address1", "address1.tooshort", "Address1 must be at least 10 characters.");
        }
        
        if (cnt.getAddress2().length() < 10) {
            errors.rejectValue("address2", "address2.tooshort", "Address2 must be at least 10 characters.");
        }
        
        if (cnt.getCity().length() < 5) {
            errors.rejectValue("city", "city.tooshort", "city must be at least 5 characters.");
        }
        
       
       
        
        System.out.println("end of validation");
    }
}
