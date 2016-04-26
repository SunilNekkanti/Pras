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
import com.pfchoice.common.util.JsonConverter;
import com.pfchoice.core.entity.CPTMeasure;
import com.pfchoice.core.service.CPTMeasureService;

import ml.rugal.sshcommon.page.Pagination;
import ml.rugal.sshcommon.springmvc.util.Message;

@Controller
@SessionAttributes({ "username", "userpath" })
public class CPTMeasureController {

	private static final Logger logger = LoggerFactory.getLogger(CPTMeasureController.class);

	@Autowired
	private CPTMeasureService cptMeasureService;

	@Autowired
	@Qualifier("cPTMeasureValidator")
	private Validator validator;

	@InitBinder("cptMeasure")
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	public CPTMeasureController() {
	}

	@ModelAttribute("cptMeasure")
	public CPTMeasure createCPTMeasureModel() {
		return new CPTMeasure();
	}

	@RequestMapping(value = "/admin/cpt/new")
	public String addCPTMeasurePage(Model model) {

		CPTMeasure cptMeasure = createCPTMeasureModel();
		model.addAttribute("cptMeasure", cptMeasure);
		return "cptMeasureNew";
	}

	@RequestMapping(value = { "/admin/cpt/{id}", "/user/cpt/{id}" }, method = RequestMethod.GET)
	public String updateCPTMeasurePage(@PathVariable Integer id, Model model) {

		CPTMeasure dbCPTMeasure = cptMeasureService.findById(id);
		logger.info("Returning cptMeasure.getId()" + dbCPTMeasure.getId());
		model.addAttribute("cptMeasure", dbCPTMeasure);
		logger.info("Returning cptMeasureEdit.jsp page");
		return "cptMeasureEdit";
	}

	@RequestMapping(value = "/admin/cpt/{id}/display", method = RequestMethod.GET)
	public String displayCPTMeasurePage(@PathVariable Integer id, Model model) {

		CPTMeasure dbCPTMeasure = cptMeasureService.findById(id);
		logger.info("Returning cptMeasure.getId()" + dbCPTMeasure.getId());

		model.addAttribute("cptMeasure", dbCPTMeasure);
		logger.info("Returning cptMeasureDisplay.jsp page");
		return "cptMeasureDisplay";
	}

	@RequestMapping(value = { "/admin/cpt/cptMeasureList", "/user/cpt/cptMeasureList" }, method = RequestMethod.GET)
	public String viewCPTMeasureAction(Model model) {

		logger.info("Returning view.jsp page after create");
		return "cptMeasureList";
	}

	@ResponseBody
	@RequestMapping(value = { "/admin/cpt/cptMeasureLists", "/user/cpt/cptMeasureLists" }, method = RequestMethod.GET)
	public Message viewCPTMeasureAction(@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize, 
			@RequestParam(required = false) String sSearch,
			@RequestParam(required = false) String sort, 
			@RequestParam(required = false) String sortdir) {

		Pagination pagination = cptMeasureService.getPage(pageNo, pageSize, sSearch, sort, sortdir);

		return Message.successMessage(CommonMessageContent.CPT_LIST, JsonConverter.getJsonObject(pagination));
	}

	@RequestMapping(value = "/admin/cpt/save.do", method = RequestMethod.POST, params = { "add" })
	public String addCPTMeasureAction(@ModelAttribute("cptMeasure") @Validated CPTMeasure cptMeasure,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		if (bindingResult.hasErrors()) {
			logger.info("Returning cptMeasureEdit.jsp page");
			return "cptMeasureNew";
		}

		model.addAttribute("cptMeasure", cptMeasure);
		cptMeasure.setCreatedBy(username);
		cptMeasure.setUpdatedBy(username);
		logger.info("Returning cptEditSuccess.jsp page after create");
		cptMeasureService.save(cptMeasure);
		model.addAttribute("Message", "CPT Measure added successfully");
		return "cptMeasureList";
	}

	@RequestMapping(value = "/admin/cpt/{id}/save.do", method = RequestMethod.POST, params = { "update" })
	public String saveCPTMeasureAction(@PathVariable Integer id,
			@ModelAttribute("cptMeasure") @Validated CPTMeasure cptMeasure, BindingResult bindingResult, Model model,
			@ModelAttribute("username") String username) {
		cptMeasure.setActiveInd('Y');
		if (bindingResult.hasErrors()) {
			logger.info("Returning  cptMeasureEdit.jsp page");
			return "cptMeasureEdit";
		}

		if (null != cptMeasure.getId()) {
			logger.info("Returning cptMeasureEditSuccess.jsp page after update");
			cptMeasure.setUpdatedBy(username);
			cptMeasureService.update(cptMeasure);
			model.addAttribute("cptMeasure", cptMeasure);
			model.addAttribute("Message", "CPT Measure updated successfully");
			return "cptMeasureList";
		}

		return "cptMeasureEdit";
	}

	@RequestMapping(value = "/admin/cpt/{id}/save.do", method = RequestMethod.POST, params = { "delete" })
	public String deleteCPTMeasureAction(@PathVariable Integer id,
			@ModelAttribute("cptMeasure") @Validated CPTMeasure cptMeasure, BindingResult bindingResult, Model model,
			@ModelAttribute("username") String username) {
		cptMeasure.setActiveInd('Y');
		if (bindingResult.hasErrors()) {
			logger.info("Returning  cptMeasureEdit.jsp page");
			return "cptMeasureEdit";
		}
		if (null != cptMeasure.getId()) {
			logger.info("Returning cptMeasureEditSuccess.jsp page after update");
			cptMeasure.setActiveInd('N');
			cptMeasure.setUpdatedBy(username);
			cptMeasureService.update(cptMeasure);
			model.addAttribute("Message", "CPT Measure deleted successfully");
			return "cptMeasureList";
		}
		return "cptMeasureEdit";
	}
}
