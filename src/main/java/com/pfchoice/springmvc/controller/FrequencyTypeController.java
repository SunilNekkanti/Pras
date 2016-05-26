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
import com.pfchoice.common.util.TileDefinitions;
import com.pfchoice.core.entity.FrequencyType;
import com.pfchoice.core.service.FrequencyTypeService;

import ml.rugal.sshcommon.page.Pagination;
import ml.rugal.sshcommon.springmvc.util.Message;

@Controller
@SessionAttributes({ "username", "userpath" })
public class FrequencyTypeController {

	private static final Logger logger = LoggerFactory.getLogger(FrequencyTypeController.class);

	@Autowired
	private FrequencyTypeService frequencyTypeService;

	/**
	 * @return
	 */
	@ModelAttribute("newFrequencyType")
	public FrequencyType createFrequencyTypeModel() {
		return new FrequencyType();
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/frequencyType/new" })
	public String addUserPage(final Model model) {

		FrequencyType frequencyType = createFrequencyTypeModel();
		frequencyType.setActiveInd('Y');
		model.addAttribute("frequencyType", frequencyType);
		return TileDefinitions.FREQUENCYTYPENEW.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/frequencyType/{id}", "/user/frequencyType/{id}" }, method = RequestMethod.GET)
	public String updateUserPage(@PathVariable Integer id, Model model) {

		FrequencyType dbFrequencyType = frequencyTypeService.findById(id);
		logger.info("Returning frequencyType.getId()" + dbFrequencyType.getId());

		model.addAttribute("frequencyType", dbFrequencyType);
		logger.info("Returning frequencyTypeEdit.jsp page");
		return TileDefinitions.FREQUENCYTYPEEDIT.toString();
	}

	/**
	 * @param frequencyType
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/frequencyType/save.do",
			"/user/frequencyType/save.do" }, method = RequestMethod.POST, params = { "add" })
	public String newUserAction(@ModelAttribute("frequencyType") @Validated FrequencyType frequencyType,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		if (bindingResult.hasErrors()) {
			logger.info("Returning frequencyTypeEdit.jsp page");
			return TileDefinitions.FREQUENCYTYPENEW.toString();
		}

		logger.info("Returning frequencyTypeSuccess.jsp page after create");
		frequencyType.setCreatedBy(username);
		frequencyType.setUpdatedBy(username);
		frequencyTypeService.save(frequencyType);
		model.addAttribute("Message", "New Frequency type added successfully");
		return TileDefinitions.FREQUENCYTYPELIST.toString();
	}

	/**
	 * @param id
	 * @param frequencyType
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/frequencyType/{id}/save.do",
			"/user/frequencyType/{id}/save.do" }, method = RequestMethod.POST, params = { "update" })
	public String updateUserAction(@PathVariable Integer id, @Validated FrequencyType frequencyType,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		frequencyType.setActiveInd('Y');
		logger.info("frequency type id is" + id);
		if (bindingResult.hasErrors()) {
			logger.info("Returning frequencyTypeEdit.jsp page");
			return TileDefinitions.FREQUENCYTYPEEDIT.toString();
		}
		if (frequencyType.getId() != null) {
			logger.info("Returning frequencyTypeEditSuccess.jsp page after update");
			frequencyType.setUpdatedBy(username);
			frequencyTypeService.update(frequencyType);
			model.addAttribute("Message", "Frequency Type  updated successfully");
		}
		return TileDefinitions.FREQUENCYTYPELIST.toString();
	}

	/**
	 * @param id
	 * @param frequencyType
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */

	@RequestMapping(value = { "/admin/frequencyType/{id}/save.do",
			"/user/frequencyType/{id}/save.do" }, method = RequestMethod.POST, params = { "delete" })
	public String deleteInsuranceAction(@PathVariable Integer id, @Validated FrequencyType frequencyType,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		frequencyType.setActiveInd('Y');
		logger.info("frequency type id is" + id);
		if (bindingResult.hasErrors()) {
			logger.info("Returning frequencyTypeEdit.jsp page");
			return TileDefinitions.FREQUENCYTYPEEDIT.toString();
		}
		if (frequencyType.getId() != null) {
			logger.info("Returning frequencyTypeSuccess.jsp page after update");
			frequencyType.setActiveInd('N');
			frequencyType.setUpdatedBy(username);
			model.addAttribute("Message", "Frequency Type  deleted successfully");
			frequencyTypeService.update(frequencyType);
		}
		return TileDefinitions.FREQUENCYTYPELIST.toString();
	}

	/**
	 * @return
	 */
	@RequestMapping(value = { "/admin/frequencyTypeList", "/user/frequencyTypeList" })
	public String handleRequest() {

		return TileDefinitions.FREQUENCYTYPELIST.toString();
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
	@RequestMapping(value = { "/admin/frequencyType/list", "/user/frequencyType/list" }, method = RequestMethod.GET)
	public Message viewProviderList(@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sSearch,
			@RequestParam(required = false) String sort, @RequestParam(required = false) String sortdir) {

		Pagination pagination = frequencyTypeService.getPage(pageNo, pageSize, sSearch, sort, sortdir);

		return Message.successMessage(CommonMessageContent.FILE_TYPE_LIST, JsonConverter.getJsonObject(pagination));
	}

}
