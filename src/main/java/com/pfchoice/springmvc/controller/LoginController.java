package com.pfchoice.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import com.pfchoice.core.entity.Insurance;
import com.pfchoice.core.entity.Membership;
import com.pfchoice.core.entity.Provider;
import com.pfchoice.core.service.InsuranceService;
import com.pfchoice.core.service.MembershipService;
import com.pfchoice.core.service.ProviderService;
import com.pfchoice.form.LoginForm;

@Controller
@RequestMapping(value = "*")
public class LoginController {
	
	@Autowired
    MembershipService membershipService;
	
	@Autowired
    ProviderService providerService;
	
	@Autowired
    InsuranceService insuranceService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showForm(Map<String,Object> model) {
		LoginForm loginForm = new LoginForm();
		model.put("loginForm", loginForm);
		return "loginform";
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String loginForm(Map<String,Object> model) {
		LoginForm loginForm = new LoginForm();
		model.put("loginForm", loginForm);
		return "loginform";
	}

	@RequestMapping(value = "/loginform", method = RequestMethod.POST)
	public String processForm(@Valid LoginForm loginForm, BindingResult result,
			Map<String,Object> model) {
		String userName = "sarath";
		String password = "password";
		if (result.hasErrors()) {
			return "loginform";
		}
		loginForm = (LoginForm) model.get("loginForm");
		if (!loginForm.getUsername().equals(userName)
				|| !loginForm.getPassword().equals(password)) {
			return "loginform";
		}
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

}