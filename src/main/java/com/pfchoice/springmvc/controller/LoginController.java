package com.pfchoice.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.validation.BindingResult;
import java.util.Map;
import javax.validation.Valid;

import com.pfchoice.form.LoginForm;
import com.pfchoice.form.MembershipForm;

@Controller
@RequestMapping(value = "/")
public class LoginController {
	
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String showForm(Map<String,Object> model) {
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
		MembershipForm membershipForm = new MembershipForm();
		model.put("membershipForm", membershipForm);
		return "membership";
	}

}