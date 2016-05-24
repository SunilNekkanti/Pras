package com.pfchoice.springmvc.controller;

import static  com.pfchoice.common.SystemDefaultProperties.EMAIL_ATTACHMENTS_FILES_DIRECTORY_PATH;

import java.io.FileNotFoundException;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

import com.pfchoice.common.CommonMessageContent;
import com.pfchoice.common.util.JsonConverter;
import com.pfchoice.common.util.PrasUtil;
import com.pfchoice.common.util.TileDefinitions;
import com.pfchoice.core.entity.EmailTemplate;
import com.pfchoice.core.entity.EmailTemplatePlaceholder;
import com.pfchoice.core.service.EmailTemplatePlaceholderService;
import com.pfchoice.core.service.EmailTemplateService;
import com.pfchoice.springmvc.service.ApplicationMailer;

import ml.rugal.sshcommon.page.Pagination;
import ml.rugal.sshcommon.springmvc.util.Message;

@Controller
@SessionAttributes({ "username", "userpath" })
public class EmailTemplateController {

	private static final Logger logger = LoggerFactory.getLogger(EmailTemplateController.class);
	
	@Autowired
	ApplicationMailer  applicationMailer;

	@Autowired
	private EmailTemplateService emailTemplateService;

	@Autowired
	private EmailTemplatePlaceholderService emailTemplatePlaceholderService;

	
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
	@ModelAttribute("emailTemplate")
	public EmailTemplate createEmailTemplateModel() {
		return new EmailTemplate();
	}
	
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/emailTemplateList",
			"/user/emailTemplateList" }, method = RequestMethod.GET)
	public String viewEmailTemplateAction() {

		logger.info("Returning view.jsp page after create");
		return TileDefinitions.EMAILTEMPLATELIST.toString();
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
	@RequestMapping(value = { "/admin/emailTemplate/list",
			"/user/emailTemplate/list" }, method = RequestMethod.GET)
	public Message viewEmailTemplateList(@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sSearch,
			@RequestParam(required = false) String sort, @RequestParam(required = false) String sortdir) {

		Pagination pagination = emailTemplateService.getPage(pageNo, pageSize, sSearch, sort, sortdir);
		return Message.successMessage(CommonMessageContent.EMAIL_TEMPLATE_LIST, JsonConverter.getJsonObject(pagination));
	}

	
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/emailTemplate/new", "/user/emailTemplate/new"  })
	public String addEmailTemplatePage(Model model) {

		EmailTemplate emailTemplate = createEmailTemplateModel();
		model.addAttribute("emailTemplate", emailTemplate);
	    
		return TileDefinitions.EMAILTEMPLATENEW.toString();
	}
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = { "/admin/emailTemplate/{description}/details", "/user/emailTemplate/{description}/details" })
	public Message getEmailTemplateContent(@PathVariable Integer description) {
		EmailTemplate emailTemplate = emailTemplateService.findById(description);
		MessageFormat mf = new MessageFormat(""); 
		String template = emailTemplate.getTemplate();
	    mf.applyPattern(template);
	    Pagination page = emailTemplatePlaceholderService.findByEmailTemplateId(emailTemplate.getId());
	    List<EmailTemplatePlaceholder> emailTemplatePlaceholders = (List<EmailTemplatePlaceholder>) page.getList();
	    List<Object>	arguments     = emailTemplatePlaceholderService.getSQLScriptResults(emailTemplatePlaceholders,44);
	    Object[] objArray = arguments.toArray();
	    String content = mf.format(objArray);
	    List<Object[]> dataToExport = emailTemplatePlaceholderService.generateAttachmentFile(emailTemplate.getId(),44);
	    LocalDateTime dateTime = LocalDateTime.now();
	    String fileName= EMAIL_ATTACHMENTS_FILES_DIRECTORY_PATH+"test" + dateTime.format(DateTimeFormatter.ofPattern("yyyMMddHHmmss")) + ".csv";
	    try {
			PrasUtil.convertToCsv(dataToExport, fileName);  
		} catch (FileNotFoundException e) {
			logger.warn("emailTemplate FileNotFoundException" + e.getCause().getMessage());
		} 
		
		return Message.successMessage(CommonMessageContent.MEMBERSHIP_LIST, content);

	}

	/**
	 * @param id
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/emailTemplate/{id}", "/user/emailTemplate/{id}" }, method = RequestMethod.GET)
	public String updateEmailTemplatePage(@PathVariable Integer id, Model model) {

		EmailTemplate dbEmailTemplate = emailTemplateService.findById(id);
		logger.info("emailTemplate.getId()" + dbEmailTemplate.getId());

		model.addAttribute("emailTemplate", dbEmailTemplate);
		logger.info("Returning emailTemplate Save.jsp page");
		return TileDefinitions.EMAILTEMPLATEEDIT.toString();
	}

	/**
	 * @param emailTemplate
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/emailTemplate/save.do" }, method = RequestMethod.POST, params = { "add" })
	public String newEmailTemplateAction(@Validated EmailTemplate emailTemplate, BindingResult bindingResult, Model model,
			@ModelAttribute("username") String username) {

		if (bindingResult.hasErrors()) {
			logger.info("Returning email Template Edit.jsp page");
			return TileDefinitions.EMAILTEMPLATENEW.toString();
		}
		
		logger.info("Returning Email Template Success.jsp page after create");
		model.addAttribute("emailTemplate", emailTemplate);
		emailTemplate.setCreatedBy(username);
		emailTemplate.setUpdatedBy(username);
		emailTemplateService.save(emailTemplate);
		model.addAttribute("Message", "Email Template details added successfully");
		return TileDefinitions.EMAILTEMPLATELIST.toString();
	}

	/**
	 * @param id
	 * @param emailTemplate
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/emailTemplate/{id}/save.do", }, method = RequestMethod.POST, params = { "update" })
	public String updateEmailTemplateAction(@PathVariable Integer id, @ModelAttribute @Validated EmailTemplate emailTemplate,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		emailTemplate.setActiveInd('Y');
		logger.info("emailTemplate id is" + id);
		if (bindingResult.hasErrors()) {
			logger.info("Returning email Template Edit.jsp page");
			emailTemplate.setActiveInd('Y');
			return TileDefinitions.EMAILTEMPLATEEDIT.toString();
		}

		if (null != emailTemplate.getId()) {
			logger.info("Returning Email Template Success.jsp page after update");
			emailTemplate.setUpdatedBy(username);
			emailTemplateService.update(emailTemplate);
			model.addAttribute("Message", "Email Template Details Updated Successfully");
		}

		return TileDefinitions.EMAILTEMPLATELIST.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/emailTemplate/{id}/save.do" }, method = RequestMethod.POST, params = { "delete" })
	public String deleteEmailTemplateAction(@PathVariable Integer id, Model model,
			@ModelAttribute("username") String username) {

		EmailTemplate dbEmailTemplate = emailTemplateService.findById(id);
		dbEmailTemplate.setActiveInd(new Character('N'));
		dbEmailTemplate.setUpdatedBy(username);
		emailTemplateService.update(dbEmailTemplate);
		model.addAttribute("emailTemplate", dbEmailTemplate);
		model.addAttribute("Message", "Email Template details deleted successfully");
		logger.info("Returning Email Template Delete Success.jsp page after delete");
		return TileDefinitions.EMAILTEMPLATELIST.toString();
	}

	/**
	 * @return
	 */
	@ModelAttribute("activeIndMap")
	public Map<String, String> populateActiveIndList() {
		return PrasUtil.getActiveIndMap();
	}

}
