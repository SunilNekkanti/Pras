package com.pfchoice.springmvc.controller;


import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.pfchoice.common.CommonMessageContent;
import com.pfchoice.common.SystemDefaultProperties;
import com.pfchoice.common.util.JsonConverter;
import com.pfchoice.common.util.PrasUtil;
import com.pfchoice.common.util.TileDefinitions;
import com.pfchoice.core.entity.Email;
import com.pfchoice.core.entity.EmailTemplate;
import com.pfchoice.core.entity.FilesUpload;
import com.pfchoice.core.service.EmailService;
import com.pfchoice.core.service.EmailTemplateService;
import com.pfchoice.core.service.FilesUploadService;
import com.pfchoice.springmvc.service.ApplicationMailer;

import ml.rugal.sshcommon.page.Pagination;
import ml.rugal.sshcommon.springmvc.util.Message;

@Controller
@SessionAttributes({ "username", "userpath" })
public class EmailController {

	private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

	@Autowired
	ApplicationMailer  applicationMailer;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private EmailTemplateService emailTemplateService;
	
	@Autowired
	private FilesUploadService filesUploadService;

	
	/**

	@Autowired
	@Qualifier("insuranceValidator")
	private Validator validator;

	
	  @param binder
	
	@InitBinder("insurance")
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	 */

	/**
	 * @return
	 */
	@ModelAttribute("email")
	public Email createEmailModel() {
		return new Email();
	}
	
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/emailList",
			"/user/email/emailList" }, method = RequestMethod.GET)
	public String viewEmailAction() {

		logger.info("Returning view.jsp page after create");
		return TileDefinitions.EMAILLIST.toString();
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
	@RequestMapping(value = { "/admin/email/list",
			"/user/emailTemplate/list" }, method = RequestMethod.GET)
	public Message viewEmailTemplateList(@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sSearch,
			@RequestParam(required = false) String sort, @RequestParam(required = false) String sortdir) {

		Pagination pagination = emailService.getPage(pageNo, pageSize, sSearch, sort, sortdir);
		return Message.successMessage(CommonMessageContent.EMAIL_LIST, JsonConverter.getJsonObject(pagination));
	}

	
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/email/new" })
	public String addEmailPage(Model model) {

		Email email = createEmailModel();
		email.setEmailFrom(applicationMailer.getEmailId());
		email.setEmailCc(applicationMailer.getCc());
		model.addAttribute("email", email);
		
		return TileDefinitions.EMAILNEW.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/email/{id}", "/user/email/{id}" }, method = RequestMethod.GET)
	public String updateEmailPage(@PathVariable Integer id, Model model) {

		Email dbEmail = emailService.findById(id);
		logger.info("Email.getId()" + dbEmail.getId());

		model.addAttribute("email", dbEmail);
		logger.info("Returning Email Save.jsp page");
		return TileDefinitions.EMAILEDIT.toString();
	}

	/**
	 * @param email
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/email/save.do" }, method = RequestMethod.POST, params = { "add" })
	public String newEmailAction(@Validated Email email, BindingResult bindingResult, Model model,
			@ModelAttribute("username") String username, @RequestParam(required = false, value = "fileUpload") CommonsMultipartFile fileUpload) {

		if (bindingResult.hasErrors()) {
			logger.info("Returning email Template Edit.jsp page");
			return TileDefinitions.EMAILNEW.toString();
		}
		
		if (fileUpload != null && !"".equals(fileUpload.getOriginalFilename())) {
			FilesUpload uploadFile = new FilesUpload();
			uploadFile.setFileName(fileUpload.getOriginalFilename());
			uploadFile.setContentType(fileUpload.getContentType());
			uploadFile.setData(fileUpload.getBytes());
			uploadFile.setCreatedBy(username);
			uploadFile.setUpdatedBy(username);
			logger.info("Returning File Upload Success.jsp page after create");
		}
		
		
		logger.info("Returning Email Template Success.jsp page after create");
		model.addAttribute("email", email);
		email.setCreatedBy(username);
		email.setUpdatedBy(username);
		emailService.save(email);
		model.addAttribute("Message", "Email Template details added successfully");
		return TileDefinitions.EMAILLIST.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/email/{id}/details",
			"/user/email/{id}/details" }, method = RequestMethod.GET)
	public String viewProviderPage(@PathVariable Integer id, Model model) {

		Email dbEmail = emailService.findById(id);
		logger.info("Returning email.getId()" + dbEmail.getId());

		model.addAttribute("email", dbEmail);
		logger.info("Returning email Template Save.jsp page");
		return TileDefinitions.EMAILLIST.toString();
	}

	/**
	 * @param id
	 * @param email
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/email/{id}/save.do", }, method = RequestMethod.POST, params = { "update" })
	public String updateEmailAction(@PathVariable Integer id, @ModelAttribute @Validated Email email,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		email.setActiveInd('Y');
		logger.info("email id is" + id);
		if (bindingResult.hasErrors()) {
			logger.info("Returning email Template Edit.jsp page");
			email.setActiveInd('Y');
			return TileDefinitions.EMAILEDIT.toString();
		}

		if (null != email.getId()) {
			logger.info("Returning Email Template Success.jsp page after update");
			email.setUpdatedBy(username);
			emailService.update(email);
			model.addAttribute("Message", "Email Template Details Updated Successfully");
		}

		return TileDefinitions.EMAILLIST.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/email/{id}/save.do" }, method = RequestMethod.POST, params = { "delete" })
	public String deleteEmailAction(@PathVariable Integer id, Model model,
			@ModelAttribute("username") String username) {

		Email dbEmail = emailService.findById(id);
		dbEmail.setActiveInd(new Character('N'));
		dbEmail.setUpdatedBy(username);
		emailService.update(dbEmail);
		model.addAttribute("email", dbEmail);
		model.addAttribute("Message", "Email Template details deleted successfully");
		logger.info("Returning Email Template Delete Success.jsp page after delete");
		return TileDefinitions.EMAILLIST.toString();
	}

	/**
	 * @return
	 */
	@ModelAttribute("activeIndMap")
	public Map<String, String> populateActiveIndList() {
		return PrasUtil.getActiveIndMap();
	}
	
	@SuppressWarnings("unchecked")
	@ModelAttribute("emailTemplateList")
	public List<EmailTemplate> populateInsuranceList() {
		Pagination page = emailTemplateService.getPage(SystemDefaultProperties.DEFAULT_PAGE_NO,
				SystemDefaultProperties.MEDIUM_LIST_SIZE);
		return (List<EmailTemplate>) page.getList();
	}

}
