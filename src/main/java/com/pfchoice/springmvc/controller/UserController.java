package com.pfchoice.springmvc.controller;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.pfchoice.common.CommonMessageContent;
import com.pfchoice.common.util.JsonConverter;
import com.pfchoice.common.util.PrasUtil;
import com.pfchoice.core.entity.Role;
import com.pfchoice.core.entity.User;
import com.pfchoice.core.service.RoleService;
import com.pfchoice.core.service.UserService;

import ml.rugal.sshcommon.page.Pagination;
import ml.rugal.sshcommon.springmvc.util.Message;

@Controller
@SessionAttributes("username")
public class UserController{
	
    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    @Qualifier("userValidator")
    private Validator validator;
    
    @InitBinder("user")
    private void initBinder(final WebDataBinder binder) {
        binder.setValidator(validator);
    }
    
    private static final Logger logger = LoggerFactory
            .getLogger(UserController.class);
 
	
	@ModelAttribute("newUser")
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
		user.setActiveInd('Y');
		model.addAttribute("user", user);
        return "userNew";
    }
 
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String updateUserPage(@PathVariable Integer id,Model model) {
		
		User dbUser = userService.findById(id);
		logger.info("Returning user.getId()"+dbUser.getId());
		
		model.addAttribute("user", dbUser);
        logger.info("Returning userEdit.jsp page");
        return "userEdit";
    }
	
	@RequestMapping(value = "/user/save.do", method = RequestMethod.POST, params ={"add"})
	public String newUserAction( @ModelAttribute("user") @Validated User user,
            BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
        if (bindingResult.hasErrors()) {
            logger.info("Returning userEdit.jsp page");
            return "userNew";
        }
        
    	logger.info("Returning userSuccess.jsp page after create");
    	user.setCreatedBy(username);
    	user.setUpdatedBy(username);
    	userService.save(user);
       return "userEditSuccess";
    }
	
	@RequestMapping(value = "/user/{id}/save.do", method = RequestMethod.POST, params ={"update"})
    public String updateUserAction( @PathVariable Integer id,@Validated User user,
            BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
        if (bindingResult.hasErrors()) {
        	user.setActiveInd('Y');
            logger.info("Returning userEdit.jsp page");
            return "userEdit";
        }
        if (null != user.getId())
        {
        	logger.info("Returning userEditSuccess.jsp page after update");
        	user.setUpdatedBy(username);
        	userService.update(user);
        }
        return "userEditSuccess";
    }
	
	
	@RequestMapping(value = "/user/{id}/save.do", method = RequestMethod.POST, params ={"delete"})
    public String deleteInsuranceAction(@PathVariable Integer id, @Validated User user,
            BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
        if (bindingResult.hasErrors()) {
        	user.setActiveInd('Y');
            logger.info("Returning userEdit.jsp page");
            return "userEdit";
        }
        
        if (null != user.getId())
        {
	    	logger.info("Returning userSuccess.jsp page after update");
	    	user.setActiveInd('N');
	    	user.setUpdatedBy(username);
	    	userService.update(user);
        }
        return "userEditSuccess";
    }
	
	@RequestMapping(value = "/userList")
    public String handleRequest() throws Exception {
 
		return "userList";
	}
   
    @ResponseBody
    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
	public Message viewProviderListJsonTest(Model model,@RequestParam(required = false) Integer pageNo,
					@RequestParam(required = false) Integer pageSize,
					@RequestParam(required = false) String sSearch,
					@RequestParam(required = false) String sort,
					@RequestParam(required = false) String sortdir) throws Exception{
		
		
		Pagination pagination = userService.getPage(pageNo, pageSize);
		
      return Message.successMessage(CommonMessageContent.USER_LIST, JsonConverter.getJsonObject(pagination));
  }
    
	@ModelAttribute("rolesList")
	public List<Role> populateRoles() {
		//Data referencing for Role list box
		List<Role> rolesList = roleService.findAll();
		return rolesList;
	}
}
