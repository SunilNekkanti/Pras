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
import com.pfchoice.core.entity.PlanType;
import com.pfchoice.core.service.PlanTypeService;

import ml.rugal.sshcommon.page.Pagination;
import ml.rugal.sshcommon.springmvc.util.Message;

@Controller
@SessionAttributes({ "username", "userpath" })
public class PlanTypeController {

	@Autowired
	private PlanTypeService planTypeService;

	private static final Logger logger = LoggerFactory.getLogger(PlanTypeController.class);

	/**
	 * @return
	 */
	@ModelAttribute("newPlanType")
	public PlanType createPlanTypeModel() {
		return new PlanType();
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/planType/new" })
	public String addUserPage(final Model model) {

		PlanType planType = createPlanTypeModel();
		planType.setActiveInd('Y');
		model.addAttribute("planType", planType);
		return TileDefinitions.PLANTYPENEW.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/planType/{id}", "/user/planType/{id}" }, method = RequestMethod.GET)
	public String updateUserPage(@PathVariable Integer id, Model model) {

		PlanType dbPlanType = planTypeService.findById(id);
		logger.info("Returning planType.getId()" + dbPlanType.getId());

		model.addAttribute("planType", dbPlanType);
		logger.info("Returning planTypeEdit.jsp page");
		return TileDefinitions.PLANTYPEEDIT.toString();
	}

	/**
	 * @param planType
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/planType/save.do",
			"/user/planType/save.do" }, method = RequestMethod.POST, params = { "add" })
	public String newUserAction(@ModelAttribute("planType") @Validated PlanType planType, BindingResult bindingResult,
			Model model, @ModelAttribute("username") String username) {
		if (bindingResult.hasErrors()) {
			logger.info("Returning planTypeEdit.jsp page");
			return TileDefinitions.PLANTYPENEW.toString();
		}

		logger.info("Returning planTypeSuccess.jsp page after create");
		planType.setCreatedBy(username);
		planType.setUpdatedBy(username);
		planTypeService.save(planType);
		model.addAttribute("Message", "New plan type added successfully");
		return TileDefinitions.PLANTYPELIST.toString();
	}

	/**
	 * @param id
	 * @param planType
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/planType/{id}/save.do",
			"/user/planType/{id}/save.do" }, method = RequestMethod.POST, params = { "update" })
	public String updateUserAction(@PathVariable Integer id, @Validated PlanType planType, BindingResult bindingResult,
			Model model, @ModelAttribute("username") String username) {
		planType.setActiveInd('Y');
		logger.info("plan type id is" + id);
		if (bindingResult.hasErrors()) {
			logger.info("Returning planTypeEdit.jsp page");
			return TileDefinitions.PLANTYPEEDIT.toString();
		}
		if (null != planType.getId()) {
			logger.info("Returning planTypeEditSuccess.jsp page after update");
			planType.setUpdatedBy(username);
			model.addAttribute("Message", "Plan Type  updated successfully");
			planTypeService.update(planType);
		}
		return TileDefinitions.PLANTYPELIST.toString();
	}

	/**
	 * @param id
	 * @param planType
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/planType/{id}/save.do",
			"/user/planType/{id}/save.do" }, method = RequestMethod.POST, params = { "delete" })
	public String deleteInsuranceAction(@PathVariable Integer id, @Validated PlanType planType,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		logger.info("plan type id is" + id);
		if (bindingResult.hasErrors()) {
			planType.setActiveInd('Y');
			logger.info("Returning planTypeEdit.jsp page");
			return TileDefinitions.PLANTYPEEDIT.toString();
		}

		if (null != planType.getId()) {
			logger.info("Returning planTypeSuccess.jsp page after update");
			planType.setActiveInd('N');
			planType.setUpdatedBy(username);
			model.addAttribute("Message", "Plan Type  deleted successfully");
			planTypeService.update(planType);
		}
		return TileDefinitions.PLANTYPELIST.toString();
	}

	/**
	 * @return
	 */
	@RequestMapping(value = { "/admin/planTypeList", "/user/planTypeList" })
	public String handleRequest() {

		return TileDefinitions.PLANTYPELIST.toString();
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
	@RequestMapping(value = { "/admin/planType/list", "/user/planType/list" }, method = RequestMethod.GET)
	public Message viewProviderList(@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sSearch,
			@RequestParam(required = false) String sort, @RequestParam(required = false) String sortdir) {

		Pagination pagination = planTypeService.getPage(pageNo, pageSize, sSearch, sort, sortdir);

		return Message.successMessage(CommonMessageContent.PLAN_TYPE_LIST, JsonConverter.getJsonObject(pagination));
	}

}
