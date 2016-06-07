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
import com.pfchoice.core.entity.AttPhysician;
import com.pfchoice.core.service.AttPhysicianService;

import ml.rugal.sshcommon.page.Pagination;
import ml.rugal.sshcommon.springmvc.util.Message;

@Controller
@SessionAttributes({ "username", "userpath" })
public class AttPhysicianController {

	private static final Logger logger = LoggerFactory.getLogger(AttPhysicianController.class);

	@Autowired
	private AttPhysicianService attPhysicianService;

	/*
	 * @Autowired
	 * 
	 * @Qualifier("AttPhysicianValidator") private Validator validator;
	 * 
	 * /**
	 * 
	 * @param binder
	 *
	 * @InitBinder("attPhysician") public void initBinder(WebDataBinder binder)
	 * { binder.setValidator(validator); }
	 * 
	 */

	/**
	 * @return
	 */
	@ModelAttribute("attPhysician")
	public AttPhysician createAttPhysicianModel() {
		return new AttPhysician();
	}

	/**
	 * @return
	 */
	@RequestMapping(value = { "/admin/attPhysicianList", "/user/attPhysicianList" })
	public String handleRequest() {
		logger.info("returning attPhysicianList.jsp");
		return TileDefinitions.ATTPHYSICIANLIST.toString();
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
	@RequestMapping(value = { "/admin/attPhysician/list", "/user/attPhysician/list" }, method = RequestMethod.GET)
	public Message viewInsuranceList(@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sSearch,
			@RequestParam(required = false) String sort, @RequestParam(required = false) String sortdir) {

		Pagination pagination = attPhysicianService.getPage(pageNo, pageSize, sSearch, sort, sortdir);
		logger.info("returning attPhysicianList");
		return Message.successMessage(CommonMessageContent.ATTPHYSICIAN_LIST, JsonConverter.getJsonObject(pagination));
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/attPhysician/new" })
	public String addAttPhysicianPage(Model model) {

		AttPhysician attPhysician = createAttPhysicianModel();
		model.addAttribute("attPhysician", attPhysician);
		return TileDefinitions.ATTPHYSICIANNEW.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/attPhysician/{id}", "/user/attPhysician/{id}" }, method = RequestMethod.GET)
	public String updateAttPhysicianPage(@PathVariable Integer id, Model model) {

		AttPhysician dbAttPhysician = attPhysicianService.findById(id);
		logger.info("attPhysician.getId()" + dbAttPhysician.getId());

		model.addAttribute("attPhysician", dbAttPhysician);
		logger.info("Returning attPhysicianSave.jsp page");
		return TileDefinitions.ATTPHYSICIANEDIT.toString();
	}

	/**
	 * @param attPhysician
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/attPhysician/save.do" }, method = RequestMethod.POST, params = { "add" })
	public String newAttPhysicianAction(@Validated AttPhysician attPhysician, BindingResult bindingResult, Model model,
			@ModelAttribute("username") String username) {

		if (bindingResult.hasErrors()) {
			logger.info("Returning attPhysicianEdit.jsp page");
			return TileDefinitions.ATTPHYSICIANNEW.toString();
		}

		if (attPhysicianService.findByCode(attPhysician.getCode()) != null) {
			FieldError insError = new FieldError("code", "code", attPhysician.getCode(), false, null, null,
					attPhysician.getCode() + " already exist");
			bindingResult.addError(insError);
			return TileDefinitions.ATTPHYSICIANNEW.toString();
		}

		logger.info("Returning AttPhysicianSuccess.jsp page after create");
		model.addAttribute("attPhysician", attPhysician);
		attPhysician.setCreatedBy(username);
		attPhysician.setUpdatedBy(username);
		attPhysicianService.save(attPhysician);
		model.addAttribute("Message", "AttPhysician details added successfully");
		return TileDefinitions.ATTPHYSICIANLIST.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/attPhysician/{id}/details",
			"/user/attPhysician/{id}/details" }, method = RequestMethod.GET)
	public String viewProviderPage(@PathVariable Integer id, Model model) {

		AttPhysician dbAttPhysician = attPhysicianService.findById(id);
		logger.info("Returning attPhysician.getId()" + dbAttPhysician.getId());

		model.addAttribute("attPhysician", dbAttPhysician);
		logger.info("Returning attPhysicianSave.jsp page");
		return TileDefinitions.ATTPHYSICIANEDIT.toString();
	}

	/**
	 * @param id
	 * @param attPhysician
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/attPhysician/{id}/save.do", }, method = RequestMethod.POST, params = { "update" })
	public String updateAttPhysicianAction(@PathVariable Integer id,
			@ModelAttribute @Validated AttPhysician attPhysician, BindingResult bindingResult, Model model,
			@ModelAttribute("username") String username) {
		attPhysician.setActiveInd('Y');
		logger.info("attPhysician id is" + id);
		if (bindingResult.hasErrors()) {
			logger.info("Returning attPhysicianEdit.jsp page");
			attPhysician.setActiveInd('Y');
			return TileDefinitions.ATTPHYSICIANEDIT.toString();
		}

		if (!attPhysicianService.isCodeUnique(attPhysician.getId(), attPhysician.getCode())) {
			FieldError insError = new FieldError("code", "code", attPhysician.getCode(), false, null, null,
					attPhysician.getCode() + " already exist");
			bindingResult.addError(insError);
			return TileDefinitions.ATTPHYSICIANEDIT.toString();
		}

		if (null != attPhysician.getId()) {
			logger.info("Returning Att PhysicianSuccess.jsp page after update");
			attPhysician.setUpdatedBy(username);
			attPhysicianService.update(attPhysician);
			model.addAttribute("Message", "AttPhysician Details Updated Successfully");
		}

		return TileDefinitions.ATTPHYSICIANLIST.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/attPhysician/{id}/save.do" }, method = RequestMethod.POST, params = { "delete" })
	public String deleteAttPhysicianAction(@PathVariable Integer id, Model model,
			@ModelAttribute("username") String username) {

		AttPhysician dbAttPhysician = attPhysicianService.findById(id);
		dbAttPhysician.setActiveInd(new Character('N'));
		dbAttPhysician.setUpdatedBy(username);
		attPhysicianService.update(dbAttPhysician);
		model.addAttribute("attPhysician", dbAttPhysician);
		model.addAttribute("Message", "AttPhysician details deleted successfully");
		logger.info("Returning AttPhysicianDeleteSuccess.jsp page after delete");
		return TileDefinitions.ATTPHYSICIANLIST.toString();
	}

}
