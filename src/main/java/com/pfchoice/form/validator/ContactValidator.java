package com.pfchoice.form.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.pfchoice.core.entity.Contact;



@Component
public class ContactValidator implements Validator {
	
	private final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"  
 		   + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";  
	private Pattern pattern;  
	private Matcher matcher; 
	
	//String MOBILE_PATTERN = "[0-9]{10}";
	//String STRING_PATTERN = "[a-zA-Z]+"; 
 
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
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address1","address1", "Address1 Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "error.city","City Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "stateCode","error.stateCode", "StateCode Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zipCode","error.zipCode", "zipCode Required");
              
        if (cnt.getAddress1().length() < 10  && cnt.getAddress1().length() >= 1) {
            errors.rejectValue("address1", "address1.tooshort", "Address1 must be at least 10 characters.");
        }
        
        if (cnt.getCity().length() < 5  && cnt.getCity().length() >= 1) {
            errors.rejectValue("city", "city.tooshort", "city must be at least 5 characters.");
        }
        
     // email validation in spring  
        if (cnt.getEmail().length() > 1)
        {
        	if (cnt.getEmail() != null && !cnt.getEmail().isEmpty()) {  
        		pattern = Pattern.compile(EMAIL_PATTERN);  
        		matcher = pattern.matcher(cnt.getEmail());  
        		if (!matcher.matches()) {  
        			errors.rejectValue("email", "email.incorrect",  
        					"Enter a correct email");  
        		}
        	}		
        }  
       
    }
}
