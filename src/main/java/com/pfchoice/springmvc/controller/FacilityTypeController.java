package com.pfchoice.springmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
import com.pfchoice.core.entity.FacilityType;
import com.pfchoice.core.service.FacilityTypeService;

import ml.rugal.sshcommon.page.Pagination;
import ml.rugal.sshcommon.springmvc.util.Message;

@Controller
@SessionAttributes({ "username", "userpath" })
public class FacilityTypeController {

	private static final Logger logger = LoggerFactory.getLogger(FacilityTypeController.class);

	@Autowired
	private FacilityTypeService facilityTypeService;

	/*
	 * @Autowired
	 * 
	 * @Qualifier("FacilityTypeValidator") private Validator validator;
	 * 
	 * /**
	 * 
	 * @param binder
	 *
	 * @InitBinder("facilityType") public void initBinder(WebDataBinder binder)
	 * { binder.setValidator(validator); }
	 * 
	 */

	/**
	 * @return
	 */
	@ModelAttribute("facilityType")
	public FacilityType createFacilityTypeModel() {
		return new FacilityType();
	}

	/**
	 * @return
	 */
	@RequestMapping(value = { "/admin/facilityTypeList", "/user/facilityTypeList" })
	public String handleRequest() {
		logger.info("returning facilityTypeList.jsp");
		return TileDefinitions.FACILITYTYPELIST.toString();
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
	@RequestMapping(value = { "/admin/facilityType/list", "/user/facilityType/list" }, method = RequestMethod.GET)
	public Message viewInsuranceList(@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sSearch,
			@RequestParam(required = false) String sort, @RequestParam(required = false) String sortdir) {

		Pagination pagination = facilityTypeService.getPage(pageNo, pageSize, sSearch, sort, sortdir);
		logger.info("returning facilityTypeList");
		return Message.successMessage(CommonMessageContent.FACILITYTYPE_LIST, JsonConverter.getJsonObject(pagination));
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/facilityType/new" })
	public String addFacilityTypePage(Model model) {

		FacilityType facilityType = createFacilityTypeModel();
		model.addAttribute("facilityType", facilityType);
		return TileDefinitions.FACILITYTYPENEW.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/facilityType/{id}", "/user/facilityType/{id}" }, method = RequestMethod.GET)
	public String updateFacilityTypePage(@PathVariable Integer id, Model model) {

		FacilityType dbFacilityType = facilityTypeService.findById(id);
		logger.info("FacilityType.getId()" + dbFacilityType.getId());

		model.addAttribute("facilityType", dbFacilityType);
		logger.info("Returning FacilityTypeSave.jsp page");
		return TileDefinitions.FACILITYTYPEEDIT.toString();
	}

	/**
	 * @param FacilityType
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/facilityType/save.do" }, method = RequestMethod.POST, params = { "add" })
	public String newFacilityTypeAction(@Validated FacilityType facilityType, BindingResult bindingResult, Model model,
			@ModelAttribute("username") String username) {

		if (bindingResult.hasErrors()) {
			logger.info("Returning FacilityTypeEdit.jsp page");
			return TileDefinitions.FACILITYTYPENEW.toString();
		}

		if (facilityTypeService.findByDescription(facilityType.getDescription()) != null) {
			FieldError facilityError = new FieldError("description", "description", facilityType.getDescription(),
					false, null, null, facilityType.getDescription() + " already exist");
			bindingResult.addError(facilityError);
			return TileDefinitions.FACILITYTYPENEW.toString();
		}

		logger.info("Returning FACILITYTYPESuccess.jsp page after create");
		model.addAttribute("facilityType", facilityType);
		facilityType.setCreatedBy(username);
		facilityType.setUpdatedBy(username);
		facilityTypeService.save(facilityType);
		model.addAttribute("Message", "FacilityType details added successfully");
		return TileDefinitions.FACILITYTYPELIST.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/facilityType/{id}/details",
			"/user/facilityType/{id}/details" }, method = RequestMethod.GET)
	public String viewProviderPage(@PathVariable Integer id, Model model) {

		FacilityType dbFacilityType = facilityTypeService.findById(id);
		logger.info("Returning FacilityType.getId()" + dbFacilityType.getId());

		model.addAttribute("facilityType", dbFacilityType);
		logger.info("Returning FacilityTypeSave.jsp page");
		return TileDefinitions.FACILITYTYPEEDIT.toString();
	}

	/**
	 * @param id
	 * @param FacilityType
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/facilityType/{id}/save.do", }, method = RequestMethod.POST, params = { "update" })
	public String updateFacilityTypeAction(@PathVariable Integer id,
			@ModelAttribute @Validated FacilityType facilityType, BindingResult bindingResult, Model model,
			@ModelAttribute("username") String username) {
		facilityType.setActiveInd('Y');
		logger.info("facility type id is" + id);
		if (bindingResult.hasErrors()) {
			logger.info("Returning facility type Edit.jsp page");
			facilityType.setActiveInd('Y');
			return TileDefinitions.FACILITYTYPEEDIT.toString();
		}

		if (facilityTypeService.findByDescription(facilityType.getDescription()) != null) {
			FieldError facilityError = new FieldError("description", "description", facilityType.getDescription(),
					false, null, null, facilityType.getDescription() + " already exist");
			bindingResult.addError(facilityError);
			return TileDefinitions.FACILITYTYPEEDIT.toString();
		}

		if (null != facilityType.getId()) {
			logger.info("Returning Facility Success.jsp page after update");
			facilityType.setUpdatedBy(username);
			facilityTypeService.update(facilityType);
			model.addAttribute("Message", "FACILITYTYPE Details Updated Successfully");
		}

		return TileDefinitions.FACILITYTYPELIST.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/facilityType/{id}/save.do" }, method = RequestMethod.POST, params = { "delete" })
	public String deleteFacilityTypeAction(@PathVariable Integer id, Model model,
			@ModelAttribute("username") String username) {

		FacilityType dbFacilityType = facilityTypeService.findById(id);
		dbFacilityType.setActiveInd(new Character('N'));
		dbFacilityType.setUpdatedBy(username);
		facilityTypeService.update(dbFacilityType);
		model.addAttribute("facilityType", dbFacilityType);
		model.addAttribute("Message", "FacilityType details deleted successfully");
		logger.info("Returning FacilityTypeDeleteSuccess.jsp page after delete");
		return TileDefinitions.FACILITYTYPELIST.toString();
	}

}
