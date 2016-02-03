package com.pfchoice.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.pfchoice.core.entity.Provider;


@Component
public class ProviderValidator implements Validator {
 
    //which objects can be validated by this validator
    @Override
    public boolean supports(Class<?> paramClass) {
        return Provider.class.equals(paramClass);
    }
 
    @Override
    public void validate(Object obj, Errors errors) {
       
         
        Provider prvdr = (Provider) obj;
        if(prvdr.getId() != null)
        {
	        if(prvdr.getId() <=0){
	            errors.rejectValue("id", "negativeValue", new Object[]{"'id'"}, "id can't be negative");
	        }
        }

        if (prvdr.getName().length() < 6) {
            errors.rejectValue("name", "name.tooshort", "Name must be at least 6 characters.");
        }
        if (prvdr.getCode().length() < 2) {
            errors.rejectValue("code", "cpde.tooshort", "Code must be at least 6 characters.");
        }
        
       
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "activeInd",  "error.activeInd",  "ActiveIndicator Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "createdDate",  "error.createdDate",  "Created Date  Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "updatedDate",  "error.updatedDate",  "Updated Date  Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "createdBy",  "error.createdBy",  "Created By  Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "updatedBy",  "error.updatedBy",   "Updated By  Required");
        
        
        System.out.println("end of validation");
    }
}
