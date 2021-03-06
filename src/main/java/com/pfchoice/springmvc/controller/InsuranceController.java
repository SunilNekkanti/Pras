package com.pfchoice.springmvc.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.pfchoice.common.CommonMessageContent;
import com.pfchoice.common.Message;
import com.pfchoice.common.SystemDefaultProperties;
import com.pfchoice.common.util.JsonConverter;
import com.pfchoice.common.util.PrasUtil;
import com.pfchoice.common.util.TileDefinitions;
import com.pfchoice.core.entity.Insurance;
import com.pfchoice.core.entity.PlanType;
import com.pfchoice.core.service.FileTypeService;
import com.pfchoice.core.service.InsuranceService;
import com.pfchoice.core.service.PlanTypeService;

import ml.rugal.sshcommon.page.Pagination;

@Controller
@SessionAttributes({ "username", "userpath" })
public class InsuranceController {

	private static final Logger logger = LoggerFactory.getLogger(InsuranceController.class);

	@Autowired
	private FileTypeService fileTypeService;

	@Autowired
	private InsuranceService insuranceService;

	@Autowired
	private PlanTypeService planTypeService;

	@Autowired
	@Qualifier("insuranceValidator")
	private Validator validator;

	/**
	 * @param binder
	 */
	@InitBinder("insurance")
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	/**
	 * @return
	 */
	@ModelAttribute("insurance")
	public Insurance createInsuranceModel() {
		return new Insurance();
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ModelAttribute("planTypeList")
	public List<PlanType> populatePlanTypeList() {

		Pagination page = planTypeService.getPage(SystemDefaultProperties.DEFAULT_PAGE_NO,
				SystemDefaultProperties.SMALL_LIST_SIZE);

		return (List<PlanType>) page.getList();
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/insurance/new" })
	public String addInsurancePage(Model model) {

		Insurance insurance = createInsuranceModel();
		model.addAttribute("insurance", insurance);
		return TileDefinitions.INSURANCENEW.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/insurance/{id}", "/user/insurance/{id}" }, method = RequestMethod.GET)
	public String updateInsurancePage(@PathVariable Integer id, Model model) {

		Insurance dbInsurance = insuranceService.findById(id);
		logger.info("insurance.getId()" + dbInsurance.getId());

		model.addAttribute("insurance", dbInsurance);
		logger.info("Returning insuranceSave.jsp page");
		return TileDefinitions.INSURANCEDETAILS.toString();
	}

	/**
	 * @param insurance
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/insurance/save.do" }, method = RequestMethod.POST, params = { "add" })
	public String newInsuranceAction(@Validated Insurance insurance, BindingResult bindingResult, Model model,
			@ModelAttribute("username") String username) {

		if (bindingResult.hasErrors()) {
			logger.info("Returning insuranceEdit.jsp page");
			return TileDefinitions.INSURANCENEW.toString();
		}

		if (insuranceService.findByInsName(insurance.getName()) != null) {
			FieldError insError = new FieldError("name", "name", insurance.getName(), false, null, null,
					insurance.getName() + " already exist");
			bindingResult.addError(insError);
			return TileDefinitions.INSURANCENEW.toString();
		}

		logger.info("Returning InsuranceSuccess.jsp page after create");
		model.addAttribute("insurance", insurance);
		insurance.setCreatedBy(username);
		insurance.setUpdatedBy(username);
		insuranceService.save(insurance);
		model.addAttribute("Message", "Insurance details added successfully");
		return TileDefinitions.INSURANCELIST.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/insurance/{id}/details",
			"/user/insurance/{id}/details" }, method = RequestMethod.GET)
	public String viewProviderPage(@PathVariable Integer id, Model model) {

		Insurance dbInsurance = insuranceService.findById(id);
		logger.info("Returning insurance.getId()" + dbInsurance.getId());

		model.addAttribute("insurance", dbInsurance);
		logger.info("Returning insuranceSave.jsp page");
		return TileDefinitions.INSURANCEEDIT.toString();
	}

	/**
	 * @param id
	 * @param insurance
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/insurance/{id}/save.do", }, method = RequestMethod.POST, params = { "update" })
	public String updateInsuranceAction(@PathVariable Integer id, @ModelAttribute @Validated Insurance insurance,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		insurance.setActiveInd('Y');
		logger.info("insurance id is" + id);
		if (bindingResult.hasErrors()) {
			logger.info("Returning insuranceEdit.jsp page");
			insurance.setActiveInd('Y');
			return TileDefinitions.INSURANCEEDIT.toString();
		}

		if (!insuranceService.isInsUnique(insurance.getId(), insurance.getName())) {
			FieldError insError = new FieldError("name", "name", insurance.getName(), false, null, null,
					insurance.getName() + " already exist");
			bindingResult.addError(insError);
			return TileDefinitions.INSURANCEEDIT.toString();
		}

		if (null != insurance.getId()) {
			logger.info("Returning InsuranceSuccess.jsp page after update");
			insurance.setUpdatedBy(username);
			insuranceService.update(insurance);
			model.addAttribute("Message", "Insurance Details Updated Successfully");
		}

		return TileDefinitions.INSURANCEEDIT.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/insurance/{id}/save.do" }, method = RequestMethod.POST, params = { "delete" })
	public String deleteInsuranceAction(@PathVariable Integer id, Model model,
			@ModelAttribute("username") String username) {

		Insurance dbInsurance = insuranceService.findById(id);
		dbInsurance.setActiveInd(new Character('N'));
		dbInsurance.setUpdatedBy(username);
		insuranceService.update(dbInsurance);
		model.addAttribute("insurance", dbInsurance);
		model.addAttribute("Message", "Insurance details deleted successfully");
		logger.info("Returning InsuranceDeleteSuccess.jsp page after delete");
		return TileDefinitions.INSURANCEEDIT.toString();
	}

	/**
	 * @return
	 */
	@ModelAttribute("activeIndMap")
	public Map<String, String> populateActiveIndList() {
		return PrasUtil.getActiveIndMap();
	}


	/**
	  * @param insId
	  * @return
	  */
	 @ResponseBody
	 @RequestMapping(value = { "/admin/insurance/fileTypeList",
	   "/user/insurance/fileTypeList" }, method = RequestMethod.GET)
	 public Message viewFileTypeList(@RequestParam(required = true) Integer insId) {

	  Pagination pagination = fileTypeService.findByInsId(insId,SystemDefaultProperties.DEFAULT_PAGE_NO,
				SystemDefaultProperties.SMALL_LIST_SIZE);
	  logger.info("returning fileTypeList for insId" + insId);
	  return Message.successMessage(CommonMessageContent.FILE_TYPE_LIST, JsonConverter.getJsonObject(pagination));
	 }	
}
