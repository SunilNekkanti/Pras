package com.pfchoice.springmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.pfchoice.common.CommonMessageContent;
import com.pfchoice.core.entity.ICDMeasure;
import com.pfchoice.core.service.ICDMeasureService;

import ml.rugal.sshcommon.page.Pagination;
import ml.rugal.sshcommon.springmvc.util.Message;

@Controller
@SessionAttributes({ "username", "userpath" })
public class ICDMeasureController {

	@Autowired
	private ICDMeasureService icdMeasureService;

	@Autowired
	@Qualifier("iCDMeasureValidator")
	private Validator validator;

	@InitBinder("icdMeasure")
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	private static final Logger logger = LoggerFactory.getLogger(ICDMeasureController.class);

	public ICDMeasureController() {
	}

	/**
	 * @return
	 */
	@ModelAttribute("icdMeasure")
	public ICDMeasure createICDMeasureModel() {
		return new ICDMeasure();
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/admin/icd/new")
	public String addICDMeasurePage(Model model) {

		ICDMeasure icdMeasure = createICDMeasureModel();
		model.addAttribute("icdMeasure", icdMeasure);
		return "icdMeasureNew";
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/icd/{id}", "/user/icd/{id}" }, method = RequestMethod.GET)
	public String updateICDMeasurePage(@PathVariable Integer id, Model model) {
		ICDMeasure dbICDMeasure = icdMeasureService.findById(id);
		logger.info("Returning icdMeasure.getId()" + dbICDMeasure.getId());
		model.addAttribute("icdMeasure", dbICDMeasure);
		logger.info("Returning icdMeasureEdit.jsp page");
		return "icdMeasureEdit";
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/icd/{id}/display" }, method = RequestMethod.GET)
	public String displayICDMeasurePage(@PathVariable Integer id, Model model) {
		ICDMeasure dbICDMeasure = icdMeasureService.findById(id);
		logger.info("Returning icdMeasure.getId()" + dbICDMeasure.getId());

		model.addAttribute("icdMeasure", dbICDMeasure);
		logger.info("Returning icdMeasureDisplay.jsp page");
		return "icdMeasureDisplay";
	}

	/**
	 * @return
	 */
	@RequestMapping(value = { "/admin/icd/icdMeasureList", "/user/icd/icdMeasureList" }, method = RequestMethod.GET)
	public String viewICDMeasureAction()  {
		logger.info("Returning view.jsp page after create");
		return "icdMeasureList";
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
	@RequestMapping(value = { "/admin/icd/icdMeasureLists", "/user/icd/icdMeasureLists" }, method = RequestMethod.GET)
	public Message getICDMeasureList(@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize, 
			@RequestParam(required = false) String sSearch,
			@RequestParam(required = false) String sort, 
			@RequestParam(required = false) String sortdir) {

		Pagination pagination = icdMeasureService.getPage(pageNo, pageSize, sSearch, sort, sortdir);

		return Message.successMessage(CommonMessageContent.ICD_LIST, pagination);
	}

	/**
	 * @param icdMeasure
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/admin/icd/save.do", method = RequestMethod.POST, params = { "add" })
	public String addICDMeasureAction(@ModelAttribute("icdMeasure") @Validated ICDMeasure icdMeasure,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		if (bindingResult.hasErrors()) {
			logger.info("Returning icdMeasureEdit.jsp page");
			return "icdMeasureNew";
		}

		model.addAttribute("icdMeasure", icdMeasure);
		icdMeasure.setCreatedBy(username);
		icdMeasure.setUpdatedBy(username);

		logger.info("Returning icdEditSuccess.jsp page after create");
		icdMeasureService.save(icdMeasure);
		model.addAttribute("Message", "ICD Measure added successfully");
		return "icdMeasureList";
	}

	@RequestMapping(value = "/admin/icd/{id}/save.do", method = RequestMethod.POST, params = { "update" })
	public String saveICDMeasureAction(@PathVariable Integer id,
			@ModelAttribute("icdMeasure") @Validated ICDMeasure icdMeasure, BindingResult bindingResult, Model model,
			@ModelAttribute("username") String username) {
		icdMeasure.setActiveInd('Y');
		if (bindingResult.hasErrors()) {
			logger.info("Returning  icdMeasureEdit.jsp page");
			return "icdMeasureEdit";
		}

		if (null != icdMeasure.getId()) {
			logger.info("Returning icdMeasureEditSuccess.jsp page after update");
			icdMeasure.setUpdatedBy(username);
			icdMeasureService.update(icdMeasure);
			model.addAttribute("icdMeasure", icdMeasure);
			model.addAttribute("Message", "ICD Measure updated successfully");
			return "icdMeasureList";
		}

		return "icdMeasureEdit";
	}

	@RequestMapping(value = "/admin/icd/{id}/save.do", method = RequestMethod.POST, params = { "delete" })
	public String deleteICDMeasureAction(@PathVariable Integer id,
			@ModelAttribute("icdMeasure") @Validated ICDMeasure icdMeasure, BindingResult bindingResult, Model model,
			@ModelAttribute("username") String username) {

		if (bindingResult.hasErrors()) {
			icdMeasure.setActiveInd('Y');
			logger.info("Returning  icdMeasureEdit.jsp page");
			return "icdMeasureEdit";
		}
		if (null != icdMeasure.getId()) {
			logger.info("Returning icdMeasureEditSuccess.jsp page after update");
			icdMeasure.setActiveInd('N');
			icdMeasure.setUpdatedBy(username);
			icdMeasureService.update(icdMeasure);
			model.addAttribute("Message", "ICD Measure deleted successfully");
			return "icdMeasureList";
		}
		return "icdMeasureEdit";
	}
}
