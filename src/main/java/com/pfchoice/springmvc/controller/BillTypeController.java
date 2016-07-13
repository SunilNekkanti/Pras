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
import com.pfchoice.core.entity.BillType;
import com.pfchoice.core.service.BillTypeService;

import ml.rugal.sshcommon.page.Pagination;
import ml.rugal.sshcommon.springmvc.util.Message;

@Controller
@SessionAttributes({ "username", "userpath" })
public class BillTypeController {

	private static final Logger logger = LoggerFactory.getLogger(BillTypeController.class);

	@Autowired
	private BillTypeService billTypeService;

	/*
	 * @Autowired
	 * 
	 * @Qualifier("BillTypeValidator") private Validator validator;
	 * 
	 * /**
	 * 
	 * @param binder
	 *
	 * @InitBinder("billType") public void initBinder(WebDataBinder binder) {
	 * binder.setValidator(validator); }
	 * 
	 */

	/**
	 * @return
	 */
	@ModelAttribute("billType")
	public BillType createBillTypeModel() {
		return new BillType();
	}

	/**
	 * @return
	 */
	@RequestMapping(value = { "/admin/billTypeList", "/user/billTypeList" })
	public String handleRequest() {
		logger.info("returning billTypeList.jsp");
		return TileDefinitions.BILLTYPELIST.toString();
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
	@RequestMapping(value = { "/admin/billType/list", "/user/billType/list" }, method = RequestMethod.GET)
	public Message viewInsuranceList(@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sSearch,
			@RequestParam(required = false) String sort, @RequestParam(required = false) String sortdir) {

		Pagination pagination = billTypeService.getPage(pageNo, pageSize, sSearch, sort, sortdir);
		logger.info("returning billTypeList");
		return Message.successMessage(CommonMessageContent.BILLTYPE_LIST, JsonConverter.getJsonObject(pagination));
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/billType/new" })
	public String addBillTypePage(Model model) {

		BillType billType = createBillTypeModel();
		model.addAttribute("billType", billType);
		return TileDefinitions.BILLTYPENEW.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/billType/{id}", "/user/billType/{id}" }, method = RequestMethod.GET)
	public String updateBillTypePage(@PathVariable Integer id, Model model) {

		BillType dbBillType = billTypeService.findById(id);
		logger.info("billType.getId()" + dbBillType.getId());

		model.addAttribute("billType", dbBillType);
		logger.info("Returning billTypeSave.jsp page");
		return TileDefinitions.BILLTYPEEDIT.toString();
	}

	/**
	 * @param billType
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/billType/save.do" }, method = RequestMethod.POST, params = { "add" })
	public String newBillTypeAction(@Validated BillType billType, BindingResult bindingResult, Model model,
			@ModelAttribute("username") String username) {

		if (bindingResult.hasErrors()) {
			logger.info("Returning billTypeEdit.jsp page");
			return TileDefinitions.BILLTYPENEW.toString();
		}

		if (billTypeService.findByDescription(billType.getDescription()) != null) {
			FieldError insError = new FieldError("name", "name", billType.getDescription(), false, null, null,
					billType.getDescription() + " already exist");
			bindingResult.addError(insError);
			return TileDefinitions.BILLTYPENEW.toString();
		}

		logger.info("Returning BillTypeSuccess.jsp page after create");
		model.addAttribute("billType", billType);
		billType.setCreatedBy(username);
		billType.setUpdatedBy(username);
		billTypeService.save(billType);
		model.addAttribute("Message", "BillType details added successfully");
		return TileDefinitions.BILLTYPELIST.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/billType/{id}/details",
			"/user/billType/{id}/details" }, method = RequestMethod.GET)
	public String viewProviderPage(@PathVariable Integer id, Model model) {

		BillType dbBillType = billTypeService.findById(id);
		logger.info("Returning billType.getId()" + dbBillType.getId());

		model.addAttribute("billType", dbBillType);
		logger.info("Returning billTypeSave.jsp page");
		return TileDefinitions.BILLTYPEEDIT.toString();
	}

	/**
	 * @param id
	 * @param billType
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/billType/{id}/save.do", }, method = RequestMethod.POST, params = { "update" })
	public String updateBillTypeAction(@PathVariable Integer id, @ModelAttribute @Validated BillType billType,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		billType.setActiveInd('Y');
		logger.info("billType id is" + id);
		if (bindingResult.hasErrors()) {
			logger.info("Returning billTypeEdit.jsp page");
			billType.setActiveInd('Y');
			return TileDefinitions.BILLTYPEEDIT.toString();
		}

		if (!billTypeService.isDescriptionUnique(billType.getId(), billType.getDescription())) {
			FieldError insError = new FieldError("description", "description", billType.getDescription(), false, null,
					null, billType.getDescription() + " already exist");
			bindingResult.addError(insError);
			return TileDefinitions.BILLTYPEEDIT.toString();
		}

		if (null != billType.getId()) {
			logger.info("Returning BillTypeSuccess.jsp page after update");
			billType.setUpdatedBy(username);
			billTypeService.update(billType);
			model.addAttribute("Message", "BillType Details Updated Successfully");
		}

		return TileDefinitions.BILLTYPELIST.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/billType/{id}/save.do" }, method = RequestMethod.POST, params = { "delete" })
	public String deleteBillTypeAction(@PathVariable Integer id, Model model,
			@ModelAttribute("username") String username) {

		BillType dbBillType = billTypeService.findById(id);
		dbBillType.setActiveInd(new Character('N'));
		dbBillType.setUpdatedBy(username);
		billTypeService.update(dbBillType);
		model.addAttribute("billType", dbBillType);
		model.addAttribute("Message", "BillType details deleted successfully");
		logger.info("Returning BillTypeDeleteSuccess.jsp page after delete");
		return TileDefinitions.BILLTYPELIST.toString();
	}

}
