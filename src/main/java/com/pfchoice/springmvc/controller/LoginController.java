package com.pfchoice.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionAttributeStore;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.validation.BindingResult;

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
	public String showForm(Map<String,Object> model) {
		LoginForm loginForm = new LoginForm();
		model.put("loginForm", loginForm);
		return "loginform";
	}
	
	
	@RequestMapping(value = "/loginform", method = {RequestMethod.GET, RequestMethod.POST})
	public String processForm(HttpServletRequest request, @Valid LoginForm loginForm, BindingResult result,
			Map<String,Object> model) {
		
		if (result.hasErrors()) 
		{
			return "loginform";
		}
		
		loginForm = (LoginForm) model.get("loginForm");
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
		request.getSession().setAttribute(SystemDefaultProperties.ID, login);
		request.getSession().setAttribute(SystemDefaultProperties.CREDENTIAL, password);
		
		return "home";
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
		
		//Data referencing for Membership Status list box
		List<Insurance> insList = insuranceService.findAll();
		return insList;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String page( WebRequest request,  SessionStatus status) {
	    status.setComplete();
	    request.removeAttribute(SystemDefaultProperties.ID, WebRequest.SCOPE_SESSION);
	    request.removeAttribute(SystemDefaultProperties.CREDENTIAL, WebRequest.SCOPE_SESSION);
	  //  store.cleanupAttribute(request, "user");
	    return "redirect:/loginform";
	}
		
}