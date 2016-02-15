package com.pfchoice.springmvc.controller;


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pfchoice.common.util.PrasUtil;
import com.pfchoice.core.entity.User;
import com.pfchoice.core.service.UserService;

@Controller
public class UserController{
	
    @Autowired
    UserService userService;
    
 
   /* @InitBinder("provider")
    private void initBinder(final WebDataBinder binder) {
        binder.setValidator(validator);
    }*/
    
    private static final Logger logger = LoggerFactory
            .getLogger(UserController.class);
 
	
	@ModelAttribute("user")
    public User createUserModel() {
        // ModelAttribute value should be same as used in the empSave.jsp
        return new User();
    }
	
	@ModelAttribute("activeIndMap")
	public Map<String,String> populateActiveIndList() {
		//Data referencing for ActiveMap box
		return PrasUtil.getActiveIndMap();
	}
	
	@RequestMapping(value = "/user/new")
    public String addUserPage(final Model model) {
		
		User user = createUserModel();
		user.setCreatedBy("sarath");
		user.setUpdatedBy("sarath");
		model.addAttribute("user", user);
        return "userEdit";
    }
 
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String updateUserPage(@PathVariable Integer id,Model model) {
		
		User dbUser = userService.findById(id);
		 logger.info("Returning user.getId()"+dbUser.getId());
			
        logger.info("Returning userSave.jsp page");
        return "userEdit";
    }
	
	@RequestMapping(value = "/user/save.do", method = RequestMethod.POST, params ={"add"})
    public String newUserAction(@ModelAttribute("user") @Validated User user,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            logger.info("Returning userEdit.jsp page");
            return "userEdit";
        }
        else
        {
	        	logger.info("Returning userSuccess.jsp page after create");
	        	user.setCreatedBy("sarath");
	        	user.setUpdatedBy("sarath");
	 	      	userService.save(user);
	 	       return "userEditSuccess";
        }    
    }
	
	@RequestMapping(value = "/user/{id}/save.do", method = RequestMethod.POST, params ={"update"})
    public String updateUserAction( @PathVariable Integer id,@Validated User user,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
        	user.setActiveInd('Y');
            logger.info("Returning userEdit.jsp page");
            return "userEdit";
        }
        else
        {
	        if (null != user.getId())
	        {
	        	logger.info("Returning UserSuccess.jsp page after update");
	        	user.setUpdatedBy("Mohanasundharam");
	        	user.setActiveInd('Y');
	        	userService.update(user);
	        }
	        return "userEditSuccess";
        }    
    }
	
	
	@RequestMapping(value = "/user/{id}/save.do", method = RequestMethod.POST, params ={"delete"})
    public String deleteInsuranceAction(@PathVariable Integer id, @Validated User user,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            logger.info("Returning userEdit.jsp page");
            return "userEdit";
        }
        else
        {
	        	logger.info("Returning userSuccess.jsp page after update");
	        	user.setActiveInd('N');
	        	userService.update(user);
	        return "userEditSuccess";
        }    
    }
	
}
