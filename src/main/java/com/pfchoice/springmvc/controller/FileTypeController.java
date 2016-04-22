package com.pfchoice.springmvc.controller;

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
import com.pfchoice.core.entity.FileType;
import com.pfchoice.core.service.FileTypeService;

import ml.rugal.sshcommon.page.Pagination;
import ml.rugal.sshcommon.springmvc.util.Message;

@Controller
@SessionAttributes({ "username", "userpath" })
public class FileTypeController {

	@Autowired
	private FileTypeService fileTypeService;

	private static final Logger logger = LoggerFactory.getLogger(FileTypeController.class);

	@ModelAttribute("newFileType")
	public FileType createFileTypeModel() {
		// ModelAttribute value should be same as used in the empSave.jsp
		return new FileType();
	}

	@RequestMapping(value = { "/admin/fileType/new" })
	public String addUserPage(final Model model) {

		FileType fileType = createFileTypeModel();
		fileType.setActiveInd('Y');
		model.addAttribute("fileType", fileType);
		return "fileTypeNew";
	}

	@RequestMapping(value = { "/admin/fileType/{id}", "/user/fileType/{id}" }, method = RequestMethod.GET)
	public String updateUserPage(@PathVariable Integer id, Model model) {

		FileType dbFileType = fileTypeService.findById(id);
		logger.info("Returning fileType.getCode()" + dbFileType.getCode());

		model.addAttribute("fileType", dbFileType);
		logger.info("Returning fileTypeEdit.jsp page");
		return "fileTypeEdit";
	}

	@RequestMapping(value = { "/admin/fileType/save.do",
			"/user/fileType/save.do" }, method = RequestMethod.POST, params = { "add" })
	public String newUserAction(@ModelAttribute("fileType") @Validated FileType fileType, BindingResult bindingResult,
			Model model, @ModelAttribute("username") String username) {
		if (bindingResult.hasErrors()) {
			logger.info("Returning fileTypeEdit.jsp page");
			return "fileTypeNew";
		}

		logger.info("Returning fileTypeSuccess.jsp page after create");
		fileType.setCreatedBy(username);
		fileType.setUpdatedBy(username);
		fileTypeService.save(fileType);
		model.addAttribute("Message", "New plan type added successfully");
		return "fileTypeList";
	}

	@RequestMapping(value = { "/admin/fileType/{id}/save.do",
			"/user/fileType/{id}/save.do" }, method = RequestMethod.POST, params = { "update" })
	public String updateUserAction(@PathVariable Integer id, @Validated FileType fileType, BindingResult bindingResult,
			Model model, @ModelAttribute("username") String username) {
		fileType.setActiveInd('Y');
		if (bindingResult.hasErrors()) {
			logger.info("Returning fileTypeEdit.jsp page");
			return "fileTypeEdit";
		}
		if (null != fileType.getCode()) {
			logger.info("Returning fileTypeEditSuccess.jsp page after update");
			fileType.setUpdatedBy(username);
			model.addAttribute("Message", "Plan Type  updated successfully");
			fileTypeService.update(fileType);
		}
		return "fileTypeList";
	}

	@RequestMapping(value = { "/admin/fileType/{id}/save.do",
			"/user/fileType/{id}/save.do" }, method = RequestMethod.POST, params = { "delete" })
	public String deleteInsuranceAction(@PathVariable Integer id, @Validated FileType fileType,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		if (bindingResult.hasErrors()) {
			fileType.setActiveInd('Y');
			logger.info("Returning fileTypeEdit.jsp page");
			return "fileTypeEdit";
		}

		if (null != fileType.getCode()) {
			logger.info("Returning fileTypeSuccess.jsp page after update");
			fileType.setActiveInd('N');
			fileType.setUpdatedBy(username);
			model.addAttribute("Message", "Plan Type  deleted successfully");
			fileTypeService.update(fileType);
		}
		return "fileTypeList";
	}

	@RequestMapping(value = { "/admin/fileTypeList", "/user/fileTypeList" })
	public String handleRequest() throws Exception {

		return "fileTypeList";
	}

	@ResponseBody
	@RequestMapping(value = { "/admin/fileType/list", "/user/fileType/list" }, method = RequestMethod.GET)
	public Message viewProviderListJsonTest(Model model, @RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sSearch,
			@RequestParam(required = false) String sort, @RequestParam(required = false) String sortdir)
					throws Exception {

		Pagination pagination = fileTypeService.getPage(pageNo, pageSize);

		return Message.successMessage(CommonMessageContent.FILE_TYPE_LIST, JsonConverter.getJsonObject(pagination));
	}

}
