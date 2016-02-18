package com.pfchoice.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionAttributeStore;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindingResult;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.pfchoice.common.SystemDefaultProperties;
import com.pfchoice.core.entity.Insurance;
import com.pfchoice.core.entity.Membership;
import com.pfchoice.core.entity.Provider;
import com.pfchoice.core.entity.User;
import com.pfchoice.core.service.InsuranceService;
import com.pfchoice.core.service.MembershipService;
import com.pfchoice.core.service.ProviderService;
import com.pfchoice.core.service.UserService;
import com.pfchoice.form.LoginForm;

@Controller
@RequestMapping(value = "*")
public class LoginController {
	
	@Autowired
	private MembershipService membershipService;
	
	@Autowired
	private ProviderService providerService;
	
	@Autowired
	private InsuranceService insuranceService;
	
	@Autowired
	private UserService userService;
	
	
	
	@RequestMapping(value = { "/",  "/index"}, method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout) {

		LoginForm loginForm = new LoginForm();

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}
	
		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("loginform");
		model.addObject("loginForm",loginForm);
	
		return model;

	}
	
	
	@RequestMapping(value = "/loginform.do", method =  RequestMethod.POST)
	public String processForm(@RequestParam(required = false) String error,
			@RequestParam(required = false) String logout,
			HttpServletRequest request, @Valid LoginForm loginForm, BindingResult result,
			ModelAndView model) {
		
		if (result.hasErrors()) 
		{
			return "loginform";
		}
		
		loginForm = (LoginForm) model.getModel().get("loginForm");
		final String login = loginForm.getUsername();
		final String password =  loginForm.getPassword();
		if ("".equals(login)|| "".equals(password)) 
		{
			return "loginform";
		}
		
		final boolean isValidUser = userService.isValidUser(login, password);
		if(!isValidUser)
		{
			return "loginform";
		}
		
		//enable below commented section is authentication interceptor is enabled
	//	request.getSession().setAttribute(SystemDefaultProperties.ID, login);
	//	request.getSession().setAttribute(SystemDefaultProperties.CREDENTIAL, password);
		
		return "forward:/home";
	}
	

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homePage(Map<String,Object> model) {
		
		return "home";
	}
	
	@ModelAttribute("membershipList")
	public List<Membership> populateMembershipList() {
		
		//Data referencing for Membership Status list box
		List<Membership> mbrList = membershipService.findAll();
		return mbrList;
	}
	
	@ModelAttribute("providerList")
	public List<Provider> populateProviderList() {
		
		//Data referencing for Membership Status list box
		List<Provider> prvdrList = providerService.findAll();
		return prvdrList;
	}
	
	@ModelAttribute("insuranceList")
	public List<Insurance> populateInsuranceList() {
		
		//Data referencing for Insurance  list box
		List<Insurance> insList = insuranceService.findAll();
		return insList;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String page( WebRequest request,  SessionStatus status) {
		
	    status.setComplete();
	          ///uncomment below section if you enable authentication interceptor
	  //  request.removeAttribute(SystemDefaultProperties.ID, WebRequest.SCOPE_SESSION);
	  //  request.removeAttribute(SystemDefaultProperties.CREDENTIAL, WebRequest.SCOPE_SESSION);
	    
	    return "redirect:/loginform";
	}
	
	
	//for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

	  ModelAndView model = new ModelAndView();
		
	  //check if user is login
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  if (!(auth instanceof AnonymousAuthenticationToken)) {
		UserDetails userDetail = (UserDetails) auth.getPrincipal();	
		model.addObject("username", userDetail.getUsername());
	  }
		
	  model.setViewName("ad403");
	  return model;

	}

}