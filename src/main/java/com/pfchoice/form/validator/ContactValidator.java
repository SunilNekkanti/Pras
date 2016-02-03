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
        if(cnt.getId() <=0){
            errors.rejectValue("id", "negativeValue", new Object[]{"'id'"}, "id can't be negative");
        }
        

        if (cnt.getHomePhone().length() < 6) {
            errors.rejectValue("homePhone", "homePhone.tooshort",     "PhoneNumber must be at least 10 Digits.");
        }
        if (cnt.getMobilePhone().length() < 2) {
            errors.rejectValue("mobilePhone", "mobilePhone.tooshort", "Mobile Number must be at least 10 Digits.");
        }
        
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "faxNumber", "faxNumber.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address1", "address1.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address2", "address2.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "city.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "refContact", "refContact.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "stateCode", "stateCode.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zipCode", "zipCode.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fileId", "fileId.required");
        
        if (cnt.getAddress1().length() < 10) {
            errors.rejectValue("address1", "address1.tooshort", "Address1 must be at least 10 characters.");
        }
        
        if (cnt.getAddress2().length() < 10) {
            errors.rejectValue("address2", "address2.tooshort", "Address2 must be at least 10 characters.");
        }
        
        if (cnt.getCity().length() < 5) {
            errors.rejectValue("city", "city.tooshort", "city must be at least 5 characters.");
        }
        
       
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "createdDate", "createdDate.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "updateDate", "updatedDate.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "createdBy", "createdBy.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "updatedBy", "updatedBy.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "activeInd", "activeInd.required");
        
        System.out.println("end of validation");
    }
}
