package com.pfchoice.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.pfchoice.common.SystemDefaultProperties;
import com.pfchoice.core.entity.Insurance;
import com.pfchoice.core.entity.Membership;
import com.pfchoice.core.entity.MembershipProvider;
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
	public String login(@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout, Model model) {

		LoginForm loginForm = new LoginForm();

		if (error != null) {
			model.addAttribute("error", "Invalid username and password!");
		}
	
		if (logout != null) {
			model.addAttribute("msg", "You've been logged out successfully.");
		}
		model.addAttribute("loginForm",loginForm);
	
		return "loginForm";

	}
	
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homePage(HttpServletRequest request, Map<String,Object> model) {
		
		request.getSession().setAttribute(SystemDefaultProperties.ID, getPricipal());
		
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
	    request.removeAttribute(SystemDefaultProperties.ID, WebRequest.SCOPE_SESSION);
	    
	          ///uncomment below section if you enable authentication interceptor
	  //  request.removeAttribute(SystemDefaultProperties.CREDENTIAL, WebRequest.SCOPE_SESSION);
	    
	    return "redirect:/loginform";
	}
	
	
	//for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accesssDenied(Model model) {

	  //check if user is login
		model.addAttribute("username", getPricipal());
		
	  return "ad403";

	}
	
	private String getPricipal(){
	 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  if (!(auth instanceof AnonymousAuthenticationToken)) {
		UserDetails userDetail = (UserDetails) auth.getPrincipal();	
		final String  loginUser = userDetail.getUsername();
		return loginUser;
	  }
      
	  return null;
	}
}