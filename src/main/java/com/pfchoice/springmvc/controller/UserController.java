package com.pfchoice.springmvc.controller;

import java.util.List;
import java.util.Map;
import java.util.SortedSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
import com.pfchoice.common.SystemDefaultProperties;
import com.pfchoice.common.util.JsonConverter;
import com.pfchoice.common.util.PrasUtil;
import com.pfchoice.common.util.TileDefinitions;
import com.pfchoice.core.entity.Role;
import com.pfchoice.core.entity.User;
import com.pfchoice.core.service.RoleService;
import com.pfchoice.core.service.UserService;

import ml.rugal.sshcommon.page.Pagination;
import ml.rugal.sshcommon.springmvc.util.Message;

@Controller
@SessionAttributes({ "username", "userpath", "roleId" , "userId" })
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	@Qualifier("userValidator")
	private Validator validator;

	/**
	 * @param binder
	 */
	@InitBinder("user")
	public void initBinder(final WebDataBinder binder) {
		binder.setValidator(validator);
	}

	/**
	 * @return
	 */
	@ModelAttribute("newUser")
	public User createUserModel() {
		return new User();
	}

	/**
	 * @return
	 */
	@ModelAttribute("activeIndMap")
	public Map<String, String> populateActiveIndList() {
		return PrasUtil.getActiveIndMap();
	}

	/**
	 * @return
	 */
	@ModelAttribute("effYearList")
	public SortedSet<Integer> populateEffectiveYearList() {
		return PrasUtil.getEffectiveYearList();
	}
	
	
	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/profile", "/user/profile" }, method = RequestMethod.GET)
	public String updateUserPage(Model model, @ModelAttribute("username") String username) {

		User dbUser = userService.findByUserName(username);
		logger.info("Returning user.getId()" + dbUser.getId());
		model.addAttribute("id", dbUser.getId());
		model.addAttribute("user", dbUser);
		logger.info("Returning userEdit.jsp page");
		return TileDefinitions.PROFILE.toString();
	}
	
	
	@RequestMapping(value = { "/admin/profile/save.do",
	"/user/profile/save.do" }, method = RequestMethod.POST, params = { "update" })
	public String updateUserProfile(@Validated User user1, BindingResult bindingResult,
		Model model, @ModelAttribute("username") String username,
		@RequestParam("password") String newPassword) {
		User user = userService.findByUserName(username);
		user.setActiveInd('Y');
		user.setUpdatedBy(username);
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(newPassword);
		user.setPassword(encodedPassword);
		logger.info("user id is" + username);
		if (bindingResult.hasErrors()) {
			logger.info("Returning userEdit.jsp page");
			return TileDefinitions.PROFILE.toString();
		}
		
		if (!userService.isUserUnique(user.getId(), user.getUsername())) {
			FieldError userError = new FieldError("username", "username", user.getUsername(), false, null, null,
					user.getUsername() + " already exist");
			bindingResult.addError(userError);
			return TileDefinitions.PROFILE.toString();
		}
		
		if (null != user.getId()) {
			logger.info("Returning userEditSuccess.jsp page after update");
			user.setUpdatedBy(username);
			model.addAttribute("Message", "Successfully changed your password. Please relogin with new password");
			userService.update(user);
		}
		return "redirect:/logout?profile";
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/user/new" })
	public String addUserPage(final Model model) {

		User user = createUserModel();
		user.setActiveInd('Y');
		model.addAttribute("user", user);
		return TileDefinitions.USERNEW.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/user/{id}", "/user/user/{id}" }, method = RequestMethod.GET)
	public String updateUserPage(@PathVariable Integer id, Model model) {

		User dbUser = userService.findById(id);
		logger.info("Returning user.getId()" + dbUser.getId());

		model.addAttribute("user", dbUser);
		logger.info("Returning userEdit.jsp page");
		return TileDefinitions.USEREDIT.toString();
	}

	/**
	 * @param user
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/user/save.do", "/user/user/save.do" }, method = RequestMethod.POST, params = {
			"add" })
	public String newUserAction(@ModelAttribute("user") @Validated User user, BindingResult bindingResult, Model model,
			@ModelAttribute("username") String username) {
		if (bindingResult.hasErrors()) {
			logger.info("Returning userEdit.jsp page");
			return TileDefinitions.USERNEW.toString();
		}

		if (!userService.isUserUnique(user.getId(), user.getUsername())) {
			FieldError userError = new FieldError("username", "username", user.getUsername(), false, null, null,
					user.getUsername() + " already exist");
			bindingResult.addError(userError);
			return TileDefinitions.USERNEW.toString();
		}

		String password = user.getPassword();
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(password);
		user.setPassword(encodedPassword);
		logger.info("Returning userSuccess.jsp page after create");
		user.setCreatedBy(username);
		user.setUpdatedBy(username);
		userService.save(user);
		model.addAttribute("Message", "New user added successfully");
		return TileDefinitions.USERLIST.toString();
	}

	/**
	 * @param id
	 * @param user
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/user/{id}/save.do",
			"/user/user/{id}/save.do" }, method = RequestMethod.POST, params = { "update" })
	public String updateUserAction(@PathVariable Integer id, @Validated User user, BindingResult bindingResult,
			Model model, @ModelAttribute("username") String username) {
		user.setActiveInd('Y');
		logger.info("user id is" + id);
		if (bindingResult.hasErrors()) {
			logger.info("Returning userEdit.jsp page");
			return TileDefinitions.USEREDIT.toString();
		}

		if (!userService.isUserUnique(user.getId(), user.getUsername())) {
			FieldError userError = new FieldError("username", "username", user.getUsername(), false, null, null,
					user.getUsername() + " already exist");
			bindingResult.addError(userError);
			return TileDefinitions.USEREDIT.toString();
		}

		if (null != user.getId()) {
			logger.info("Returning userEditSuccess.jsp page after update");
			user.setUpdatedBy(username);
			model.addAttribute("Message", "User updated successfully");
			userService.update(user);
		}
		return TileDefinitions.USERLIST.toString();
	}

	/**
	 * @param id
	 * @param user
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/user/{id}/save.do",
			"/user/user/{id}/save.do" }, method = RequestMethod.POST, params = { "delete" })
	public String deleteInsuranceAction(@PathVariable Integer id, @Validated User user, BindingResult bindingResult,
			Model model, @ModelAttribute("username") String username) {
		logger.info("user id is" + id);
		if (bindingResult.hasErrors()) {
			user.setActiveInd('Y');
			logger.info("Returning userEdit.jsp page");
			return TileDefinitions.USEREDIT.toString();
		}

		if (!userService.isUserUnique(user.getId(), user.getUsername())) {
			FieldError userError = new FieldError("username", "username", user.getUsername(), false, null, null,
					user.getUsername() + " already exist");
			bindingResult.addError(userError);
			return TileDefinitions.USEREDIT.toString();
		}

		if (null != user.getId()) {
			logger.info("Returning userSuccess.jsp page after update");
			user.setActiveInd('N');
			user.setUpdatedBy(username);
			model.addAttribute("Message", "User deleted successfully");
			userService.update(user);
		}
		return TileDefinitions.USERLIST.toString();
	}

	/**
	 * @return
	 */
	@RequestMapping(value = { "/admin/userList", "/user/userList" })
	public String handleRequest() {

		return TileDefinitions.USERLIST.toString();
	}

	/**
	 * @param pageNo
	 * @param pageSize
	 * @param sSearch
	 * @param sort
	 * @param sortdir
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/admin/user/list", "/user/user/list" }, method = RequestMethod.GET)
	public Message viewProviderList(@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sSearch,
			@RequestParam(required = false) String sort, @RequestParam(required = false) String sortdir) {

		Pagination pagination = userService.getPage(pageNo, pageSize);

		return Message.successMessage(CommonMessageContent.USER_LIST, JsonConverter.getJsonObject(pagination));
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ModelAttribute("rolesList")
	public List<Role> populateRoles() {
		Pagination page = roleService.getPage(SystemDefaultProperties.DEFAULT_PAGE_NO,
				SystemDefaultProperties.LARGE_LIST_SIZE);
		return (List<Role>) page.getList();
	}
}
