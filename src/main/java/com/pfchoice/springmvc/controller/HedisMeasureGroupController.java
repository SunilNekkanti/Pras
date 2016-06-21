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
import com.pfchoice.core.entity.HedisMeasureGroup;
import com.pfchoice.core.service.HedisMeasureGroupService;

import ml.rugal.sshcommon.page.Pagination;
import ml.rugal.sshcommon.springmvc.util.Message;

@Controller
@SessionAttributes({ "username", "userpath" })
public class HedisMeasureGroupController {

	private static final Logger logger = LoggerFactory.getLogger(HedisMeasureGroupController.class);

	@Autowired
	private HedisMeasureGroupService hedisMeasureGroupService;

	/**
	 * @return
	 */
	@ModelAttribute("newHedisMeasureGroup")
	public HedisMeasureGroup createHedisMeasureGroupModel() {
		return new HedisMeasureGroup();
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/hedisMeasureGroup/new" })
	public String addUserPage(final Model model) {

		HedisMeasureGroup hedisMeasureGroup = createHedisMeasureGroupModel();
		hedisMeasureGroup.setActiveInd('Y');
		model.addAttribute("hedisMeasureGroup", hedisMeasureGroup);
		return TileDefinitions.HEDISMEASUREGROUPNEW.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/hedisMeasureGroup/{id}",
			"/user/hedisMeasureGroup/{id}" }, method = RequestMethod.GET)
	public String updateUserPage(@PathVariable Integer id, Model model) {

		HedisMeasureGroup dbHedisMeasureGroup = hedisMeasureGroupService.findById(id);
		logger.info("Returning hedisMeasureGroup.getId()" + dbHedisMeasureGroup.getId());

		model.addAttribute("hedisMeasureGroup", dbHedisMeasureGroup);
		logger.info("Returning hedisMeasureGroupEdit.jsp page");
		return TileDefinitions.HEDISMEASUREGROUPEDIT.toString();
	}

	/**
	 * @param hedisMeasureGroup
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/hedisMeasureGroup/save.do",
			"/user/hedisMeasureGroup/save.do" }, method = RequestMethod.POST, params = { "add" })
	public String newUserAction(@ModelAttribute("hedisMeasureGroup") @Validated HedisMeasureGroup hedisMeasureGroup,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		if (bindingResult.hasErrors()) {
			logger.info("Returning hedisMeasureGroupEdit.jsp page");
			return TileDefinitions.HEDISMEASUREGROUPNEW.toString();
		}

		logger.info("Returning hedisMeasureGroupSuccess.jsp page after create");
		hedisMeasureGroup.setCreatedBy(username);
		hedisMeasureGroup.setUpdatedBy(username);
		hedisMeasureGroupService.save(hedisMeasureGroup);
		model.addAttribute("Message", "New hedis measure group added successfully");
		return TileDefinitions.HEDISMEASUREGROUPLIST.toString();
	}

	/**
	 * @param id
	 * @param hedisMeasureGroup
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/hedisMeasureGroup/{id}/save.do",
			"/user/hedisMeasureGroup/{id}/save.do" }, method = RequestMethod.POST, params = { "update" })
	public String updateUserAction(@PathVariable Integer id, @Validated HedisMeasureGroup hedisMeasureGroup,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		hedisMeasureGroup.setActiveInd('Y');
		logger.info("hedisMeasureGroup id is" + id);
		if (bindingResult.hasErrors()) {
			logger.info("Returning hedisMeasureGroupEdit.jsp page");
			return TileDefinitions.HEDISMEASUREGROUPEDIT.toString();
		}
		if (null != hedisMeasureGroup.getId()) {
			logger.info("Returning hedisMeasureGroupEditSuccess.jsp page after update");
			hedisMeasureGroup.setUpdatedBy(username);
			model.addAttribute("Message", "Hedis measure group updated successfully");
			hedisMeasureGroupService.update(hedisMeasureGroup);
		}
		return TileDefinitions.HEDISMEASUREGROUPLIST.toString();
	}

	/**
	 * @param id
	 * @param hedisMeasureGroup
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/hedisMeasureGroup/{id}/save.do",
			"/user/hedisMeasureGroup/{id}/save.do" }, method = RequestMethod.POST, params = { "delete" })
	public String deleteInsuranceAction(@PathVariable Integer id, @Validated HedisMeasureGroup hedisMeasureGroup,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		logger.info("hedisMeasureGroup id is" + id);
		if (bindingResult.hasErrors()) {
			hedisMeasureGroup.setActiveInd('Y');
			logger.info("Returning hedisMeasureGroupEdit.jsp page");
			return TileDefinitions.HEDISMEASUREGROUPEDIT.toString();
		}

		if (null != hedisMeasureGroup.getId()) {
			logger.info("Returning hedisMeasureGroupSuccess.jsp page after update");
			hedisMeasureGroup.setActiveInd('N');
			hedisMeasureGroup.setUpdatedBy(username);
			model.addAttribute("Message", "Hedis measure group deleted successfully");
			hedisMeasureGroupService.update(hedisMeasureGroup);
		}
		return TileDefinitions.HEDISMEASUREGROUPLIST.toString();
	}

	/**
	 * @return
	 */
	@RequestMapping(value = { "/admin/hedisMeasureGroupList", "/user/hedisMeasureGroupList" })
	public String handleRequest() {

		return TileDefinitions.HEDISMEASUREGROUPLIST.toString();
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
	@RequestMapping(value = { "/admin/hedisMeasureGroup/list",
			"/user/hedisMeasureGroup/list" }, method = RequestMethod.GET)
	public Message viewProviderList(@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sSearch,
			@RequestParam(required = false) String sort, @RequestParam(required = false) String sortdir) {

		Pagination pagination = hedisMeasureGroupService.getPage(pageNo, pageSize);

		return Message.successMessage(CommonMessageContent.HEDIS_MEASURE_GROUP_LIST,
				JsonConverter.getJsonObject(pagination));
	}

}
