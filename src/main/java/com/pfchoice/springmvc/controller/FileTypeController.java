package com.pfchoice.springmvc.controller;

import java.util.HashMap;
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
import com.pfchoice.common.SystemDefaultProperties;
import com.pfchoice.common.util.JsonConverter;
import com.pfchoice.common.util.TileDefinitions;
import com.pfchoice.core.entity.FileType;
import com.pfchoice.core.entity.Insurance;
import com.pfchoice.core.service.FileTypeService;
import com.pfchoice.core.service.InsuranceService;

import ml.rugal.sshcommon.page.Pagination;
import ml.rugal.sshcommon.springmvc.util.Message;

@Controller
@SessionAttributes({ "username", "userpath" })
public class FileTypeController {

	private static final Logger logger = LoggerFactory.getLogger(FileTypeController.class);

	@Autowired
	private FileTypeService fileTypeService;
	
	@Autowired
	private InsuranceService insuranceService;

	/**
	 * @return
	 */
	@ModelAttribute("newFileType")
	public FileType createFileTypeModel() {
		return new FileType();
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/fileType/new" })
	public String addUserPage(final Model model) {

		FileType fileType = createFileTypeModel();
		fileType.setActiveInd('Y');
		model.addAttribute("fileType", fileType);
		return TileDefinitions.FILETYPENEW.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/fileType/{id}", "/user/fileType/{id}" }, method = RequestMethod.GET)
	public String updateUserPage(@PathVariable Integer id, Model model) {

		FileType dbFileType = fileTypeService.findById(id);
		logger.info("Returning fileType.getCode()" + dbFileType.getCode());

		model.addAttribute("fileType", dbFileType);
		logger.info("Returning fileTypeEdit.jsp page");
		return TileDefinitions.FILETYPEEDIT.toString();
	}

	/**
	 * @param fileType
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/fileType/save.do",
			"/user/fileType/save.do" }, method = RequestMethod.POST, params = { "add" })
	public String newUserAction(@ModelAttribute("fileType") @Validated FileType fileType, BindingResult bindingResult,
			Model model, @ModelAttribute("username") String username) {
		if (bindingResult.hasErrors()) {
			logger.info("Returning fileTypeEdit.jsp page");
			return TileDefinitions.FILETYPENEW.toString();
		}

		logger.info("Returning fileTypeSuccess.jsp page after create");
		fileType.setCreatedBy(username);
		fileType.setUpdatedBy(username);
		fileTypeService.save(fileType);
		model.addAttribute("Message", "New plan type added successfully");
		return TileDefinitions.FILETYPELIST.toString();
	}

	/**
	 * @param id
	 * @param fileType
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/fileType/{id}/save.do",
			"/user/fileType/{id}/save.do" }, method = RequestMethod.POST, params = { "update" })
	public String updateUserAction(@PathVariable Integer id, @Validated FileType fileType, BindingResult bindingResult,
			Model model, @ModelAttribute("username") String username) {
		fileType.setActiveInd('Y');
		logger.info("file type id is" + id);
		if (bindingResult.hasErrors()) {
			logger.info("Returning fileTypeEdit.jsp page");
			return TileDefinitions.FILETYPEEDIT.toString();
		}
		if (null != fileType.getCode()) {
			logger.info("Returning fileTypeEditSuccess.jsp page after update");
			fileType.setUpdatedBy(username);
			model.addAttribute("Message", "Plan Type  updated successfully");
			fileTypeService.update(fileType);
		}
		return TileDefinitions.FILETYPELIST.toString();
	}

	/**
	 * @param id
	 * @param fileType
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/fileType/{id}/save.do",
			"/user/fileType/{id}/save.do" }, method = RequestMethod.POST, params = { "delete" })
	public String deleteInsuranceAction(@PathVariable Integer id, @Validated FileType fileType,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		logger.info("file type id is" + id);
		if (bindingResult.hasErrors()) {
			fileType.setActiveInd('Y');
			logger.info("Returning fileTypeEdit.jsp page");
			return TileDefinitions.FILETYPEEDIT.toString();
		}

		if (null != fileType.getCode()) {
			logger.info("Returning fileTypeSuccess.jsp page after update");
			fileType.setActiveInd('N');
			fileType.setUpdatedBy(username);
			model.addAttribute("Message", "Plan Type  deleted successfully");
			fileTypeService.update(fileType);
		}
		return TileDefinitions.FILETYPELIST.toString();
	}

	/**
	 * @return
	 */
	@RequestMapping(value = { "/admin/fileTypeList", "/user/fileTypeList" })
	public String handleRequest() {

		return TileDefinitions.FILETYPELIST.toString();
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
	@RequestMapping(value = { "/admin/fileType/list", "/user/fileType/list" }, method = RequestMethod.GET)
	public Message viewProviderList(@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sSearch,
			@RequestParam(required = false) String sort, @RequestParam(required = false) String sortdir) {

		Pagination pagination = fileTypeService.getPage(pageNo, pageSize, sSearch, sort, sortdir);

		return Message.successMessage(CommonMessageContent.FILE_TYPE_LIST, JsonConverter.getJsonObject(pagination));
	}
	
	@ResponseBody
	@RequestMapping(value = { "/admin/systemPropeties",
			"/user/systemPropeties" }, method = RequestMethod.GET)
	public Message systemProperties() {

		Map<String, String> m1 = new HashMap<String, String>(); 
		m1.put("FILE_TYPE_AMG_MBR_CLAIM",SystemDefaultProperties.FILE_TYPE_AMG_MBR_CLAIM);
		m1.put("FILE_TYPE_BH_MBR_CLAIM",SystemDefaultProperties.FILE_TYPE_BH_MBR_CLAIM);
		m1.put("FILE_TYPE_AMG_MBR_ROSTER",SystemDefaultProperties.FILE_TYPE_AMG_MBR_ROSTER);
		m1.put("FILE_TYPE_BH_MBR_ROSTER",SystemDefaultProperties.FILE_TYPE_BH_MBR_ROSTER);
		m1.put("FILE_TYPE_AMG_MBR_HOSPITALIZATION",SystemDefaultProperties.FILE_TYPE_AMG_MBR_HOSPITALIZATION);
		return Message.successMessage(CommonMessageContent.FILE_TYPE_UPLOAD,
				JsonConverter.getJsonObject(m1));
	}
	
	/**
	 * @return
	 */
	@RequestMapping(value = { "/admin/fileUpload", "/user/fileUpload" })
	public String viewFileUpload() {
		return TileDefinitions.FILEUPLOAD.toString();
	}
	
	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ModelAttribute("insList")
	public List<Insurance> populateInsuranceList() {
		Pagination page = insuranceService.getPage(SystemDefaultProperties.DEFAULT_PAGE_NO,
				SystemDefaultProperties.MEDIUM_LIST_SIZE);
		return (List<Insurance>) page.getList();
	}


}
