package com.pfchoice.springmvc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
import com.pfchoice.common.SystemDefaultProperties;
import com.pfchoice.core.entity.Gender;
import com.pfchoice.core.entity.HedisMeasure;
import com.pfchoice.core.entity.HedisMeasureGroup;
import com.pfchoice.core.service.GenderService;
import com.pfchoice.core.service.HedisMeasureGroupService;
import com.pfchoice.core.service.HedisMeasureService;

import ml.rugal.sshcommon.page.Pagination;
import ml.rugal.sshcommon.springmvc.util.Message;

@Controller
@SessionAttributes({ "username", "userpath" })
public class HedisMeasureController {

	private static final Logger logger = LoggerFactory.getLogger(HedisMeasureController.class);

	@Autowired
	private HedisMeasureService hedisMeasureService;

	@Autowired
	private HedisMeasureGroupService hedisMeasureGroupService;

	@Autowired
	private GenderService genderService;

	@Autowired
	@Qualifier("hedisMeasureValidator")
	private Validator validator;

	/**
	 * @param binder
	 */
	@InitBinder("hedisMeasure")
	private void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.setValidator(validator);
	}

	/**
	 * @return
	 */
	@ModelAttribute("hedisMeasure")
	public HedisMeasure createHedisMeasureModel() {
		return new HedisMeasure();
	}

	/**
	 * @return
	 */
	@ModelAttribute("hedisMeasureGroup")
	public HedisMeasureGroup createHedisMeasureGroupModel() {
		return new HedisMeasureGroup();
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ModelAttribute("genderList")
	public List<Gender> populateGenderList() {

		Pagination page = genderService.getPage(SystemDefaultProperties.DEFAULT_PAGE_NO,
												 SystemDefaultProperties.SMALL_LIST_SIZE);
		return (List<Gender>) page.getList();
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ModelAttribute("hedisMeasureGroupList")
	public List<HedisMeasureGroup> populateHedisMeasureGroupList() {

		Pagination page = hedisMeasureGroupService.getPage(SystemDefaultProperties.DEFAULT_PAGE_NO,
															SystemDefaultProperties.MEDIUM_LIST_SIZE);
		return (List<HedisMeasureGroup>) page.getList();
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/hedis/new" })
	public String addHedisMeasurePage(Model model) {

		HedisMeasure hedisMeasure = createHedisMeasureModel();
		model.addAttribute("hedisMeasure", hedisMeasure);
		return "hedisMeasureNew";
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/hedis/{id}", "/user/hedis/{id}" }, method = RequestMethod.GET)
	public String updateHedisMeasurePage(@PathVariable Integer id, Model model) {

		HedisMeasure dbHedisMeasure = hedisMeasureService.findById(id);
		logger.info("Returning hedisMeasure.getId()" + dbHedisMeasure.getId());
		model.addAttribute("hedisMeasure", dbHedisMeasure);
		logger.info("Returning hedisMeasureEdit.jsp page");
		return "hedisMeasureEdit";
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/hedis/{id}/display" }, method = RequestMethod.GET)
	public String displayHedisMeasurePage(@PathVariable Integer id, Model model) {

		HedisMeasure dbHedisMeasure = hedisMeasureService.findById(id);
		logger.info("Returning hedisMeasure.getId()" + dbHedisMeasure.getId());

		model.addAttribute("hedisMeasure", dbHedisMeasure);
		logger.info("Returning hedisMeasureDisplay.jsp page");
		return "hedisMeasureDisplay";
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/hedis/hedisMeasureList",
			"/user/hedis/hedisMeasureList" }, method = RequestMethod.GET)
	public String viewHedisMeasureAction(Model model) {

		logger.info("Returning view.jsp page after create");
		return "hedisMeasureList";
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
	@RequestMapping(value = { "/admin/hedis/hedisMeasureLists",
			"/user/hedis/hedisMeasureLists" }, method = RequestMethod.GET)
	public Message viewHedisMeasureList(@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sSearch,
			@RequestParam(required = false) String sort, @RequestParam(required = false) String sortdir) {

		Pagination pagination = hedisMeasureService.getPage(pageNo, pageSize, sSearch, sort, sortdir);

		return Message.successMessage(CommonMessageContent.HEDIS_LIST, pagination);
	}

	/**
	 * @param hedisMeasure
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/admin/hedis/save.do", method = RequestMethod.POST, params = { "add" })
	public String addHedisMeasureAction(@Validated HedisMeasure hedisMeasure, BindingResult bindingResult, Model model,
			@ModelAttribute("username") String username) {
		if (bindingResult.hasErrors()) {
			logger.info("Returning hedisMeasureEdit.jsp page");
			return "hedisMeasureNew";
		}

		model.addAttribute("hedisMeasure", hedisMeasure);
		hedisMeasure.setCreatedBy(username);
		hedisMeasure.setUpdatedBy(username);
		model.addAttribute("Message", "Hedis Measure added successfully");
		logger.info("Returning contactEditSuccess.jsp page after create");
		try {
			hedisMeasureService.save(hedisMeasure);
		} catch (HibernateException e) {
			model.addAttribute("Message", e.getCause().getMessage());
			return "hedisMeasureNew";
		}

		return "hedisMeasureList";
	}

	/**
	 * @param id
	 * @param hedisMeasure
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/admin/hedis/{id}/save.do", method = RequestMethod.POST, params = { "update" })
	public String saveHedisMeasureAction(@PathVariable Integer id, @Validated HedisMeasure hedisMeasure,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		hedisMeasure.setActiveInd('Y');
		if (bindingResult.hasErrors()) {
			logger.info("Returning  hedisMeasureEdit.jsp page");
			hedisMeasure.setUpdatedBy(username);
			return "hedisMeasureEdit";
		}

		if (null != hedisMeasure.getId()) {
			logger.info("Returning hedisMeasureEditSuccess.jsp page after update");
			hedisMeasureService.update(hedisMeasure);
			model.addAttribute("Message", "Hedis Measure updated successfully");
			return "hedisMeasureList";
		}

		return "hedisMeasureEdit";
	}

	/**
	 * @param id
	 * @param hedisMeasure
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/admin/hedis/{id}/save.do", method = RequestMethod.POST, params = { "delete" })
	public String deleteHedisMeasureAction(@PathVariable Integer id, @Validated HedisMeasure hedisMeasure,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		hedisMeasure.setActiveInd('Y');
		if (bindingResult.hasErrors()) {
			logger.info("Returning  hedisMeasureEdit.jsp page");
			return "hedisMeasureEdit";
		}

		if (null != hedisMeasure.getId()) {
			logger.info("Returning ContactEditSuccess.jsp page after update");
			hedisMeasure.setActiveInd('N');
			hedisMeasure.setUpdatedBy(username);
			hedisMeasureService.update(hedisMeasure);
			model.addAttribute("Message", "Hedis Measure deleted successfully");
			return "hedisMeasureList";
		}
		return "hedisMeasureEdit";
	}

}
