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
       
         
        Membership mbr = (Membership) obj;
        if(mbr.getId() <=0){
            errors.rejectValue("id", "negativeValue", new Object[]{"'id'"}, "id can't be negative");
        }
       
       

        if (mbr.getFirstName().length() < 6) {
            errors.rejectValue("firstName", "firstName.tooshort", "FirstName must be at least 6 characters.");
        }
        
        if (mbr.getLastName().length() < 6) {
            errors.rejectValue("lastName", "lasttName.tooshort", "LasttName must be at least 6 characters.");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dob", 		"error.dob",		"DOB Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "medicaidNo", "error.medicaidNo",	"Medicaid Number Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "medicareNo",	"error.medicareNo",	"Medicare Number Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "genderId", 	"error.genderId",	"Gender Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "countyCode",	"error.countyCode",	"County Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ethinicCode","error.ethinicCode","Ethinicity Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "activeInd",  "error.activeInd",  "ActiveIndicator Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fileId",     "error.fileId",     "File Id  Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "createdDate",  "error.createdDate",  "Created Date  Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "updatedDate",  "error.updatedDate",  "Updated Date  Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "createdBy",  "error.createdBy",  "Created By  Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "updatedBy",  "error.updatedBy",   "Updated By  Required");
        
        System.out.println("end of validation");
    }
}
