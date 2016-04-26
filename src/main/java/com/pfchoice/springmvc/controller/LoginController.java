package com.pfchoice.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;

import com.pfchoice.common.SystemDefaultProperties;
import com.pfchoice.form.LoginForm;

@Controller
@RequestMapping(value = "*")
@SessionAttributes({ "username", "userpath" })
public class LoginController {

	/**
	 * @param error
	 * @param logout
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model) {

		LoginForm loginForm = new LoginForm();

		if (error != null) {
			model.addAttribute("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addAttribute("msg", "You've been logged out successfully.");
		}
		model.addAttribute("loginForm", loginForm);

		return "loginForm";

	}

	/**
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homePage(HttpSession session, Model model) {
		final String username = (String) session.getAttribute(SystemDefaultProperties.ID);
		if (!model.containsAttribute("username")) {
			model.addAttribute("username", username);
		}
		return "home";
	}

	/**
	 * @param request
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String page(WebRequest request, SessionStatus status) {

		status.setComplete();
		request.removeAttribute(SystemDefaultProperties.ID, WebRequest.SCOPE_SESSION);

		return "redirect:/loginform";
	}

	/**
	 * for 403 access denied page
	 * @return
	 */
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accesssDenied() {

		return "ad403";
	}

}