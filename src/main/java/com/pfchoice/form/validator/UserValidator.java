package com.pfchoice.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.pfchoice.core.entity.User;

@Component
public class UserValidator implements Validator {
	
	
 
    //which objects can be validated by this validator
    @Override
    public boolean supports(Class<?> paramClass) {
        return User.class.equals(paramClass);
    }
 
    @Override
    public void validate(Object obj, Errors errors) {
       
         
        User user = (User) obj;
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", 	"error.login",	"Login Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", 	"error.password",	"Password Required");
        if(user.getLogin().length() >= 1 && user.getLogin().length() < 3)
        {
        	 errors.rejectValue("login", "error.login", "Login must be at least 3 characters.");
        }
        else if(user.getLogin().length() >= 1 && user.getLogin().length() > 9)
        {
       	 errors.rejectValue("login", "error.login", "Login must be less than  9 characters.");
       }
       if(user.getPassword().length() >= 1 && user.getPassword().length() < 4)
       {
        	 errors.rejectValue("password", "error.password", "Login must be at least 4 characters.");
       }
       else if(user.getPassword().length() >= 1 && user.getPassword().length() > 10)
       {
       	   errors.rejectValue("password", "error.password", "Login must be less than  10 characters.");
       } 

    }
} 