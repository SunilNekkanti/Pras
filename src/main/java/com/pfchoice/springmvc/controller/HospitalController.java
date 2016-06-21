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
import com.pfchoice.core.entity.Hospital;
import com.pfchoice.core.service.HospitalService;


import ml.rugal.sshcommon.page.Pagination;
import ml.rugal.sshcommon.springmvc.util.Message;

@Controller
@SessionAttributes({ "username", "userpath" })
public class HospitalController {

	private static final Logger logger = LoggerFactory.getLogger(HospitalController.class);

	@Autowired
	private HospitalService hospitalService;

	
/*
	@Autowired
	@Qualifier("HospitalValidator")
	private Validator validator;

	/**
	 * @param binder
	 *
	@InitBinder("hospital")
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	*/

	/**
	 * @return
	 */
	@ModelAttribute("hospital")
	public Hospital createHospitalModel() {
		return new Hospital();
	}
	
	/**
	 * @return
	 */
	@RequestMapping(value = { "/admin/hospitalList", "/user/hospitalList" })
	public String handleRequest() {
		logger.info("returning hospitalList.jsp");
		return TileDefinitions.HOSPITALLIST.toString();
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
	@RequestMapping(value = { "/admin/hospital/list", "/user/hospital/list" }, method = RequestMethod.GET)
	public Message viewInsuranceList(@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sSearch,
			@RequestParam(required = false) String sort, @RequestParam(required = false) String sortdir) {

		Pagination pagination = hospitalService.getPage(pageNo, pageSize, sSearch, sort, sortdir);
		logger.info("returning hospitalList");
		return Message.successMessage(CommonMessageContent.HOSPITAL_LIST, JsonConverter.getJsonObject(pagination));
	}

	
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/hospital/new" })
	public String addHospitalPage(Model model) {

		Hospital hospital = createHospitalModel();
		model.addAttribute("hosiptal", hospital);
		return TileDefinitions.HOSPITALNEW.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/hospital/{id}", "/user/hospital/{id}" }, method = RequestMethod.GET)
	public String updateHospitalPage(@PathVariable Integer id, Model model) {

		Hospital dbHospital = hospitalService.findById(id);
		logger.info("hospital.getId()" + dbHospital.getId());

		model.addAttribute("hospital", dbHospital);
		logger.info("Returning hospitalSave.jsp page");
		return TileDefinitions.HOSPITALEDIT.toString();
	}

	/**
	 * @param hospital
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/hospital/save.do" }, method = RequestMethod.POST, params = { "add" })
	public String newHospitalAction(@Validated Hospital hospital, BindingResult bindingResult, Model model,
			@ModelAttribute("username") String username) {

		if (bindingResult.hasErrors()) {
			logger.info("Returning hospitalEdit.jsp page");
			return TileDefinitions.HOSPITALNEW.toString();
		}

		if (hospitalService.findByName(hospital.getName()) != null) {
			FieldError insError = new FieldError("name", "name", hospital.getName(), false, null, null,
					hospital.getName() + " already exist");
			bindingResult.addError(insError);
			return TileDefinitions.HOSPITALNEW.toString();
		}

		logger.info("Returning HospitalSuccess.jsp page after create");
		model.addAttribute("hospital", hospital);
		hospital.setFileId(1);
		hospital.setCreatedBy(username);
		hospital.setUpdatedBy(username);
		hospitalService.save(hospital);
		model.addAttribute("Message", "Hospital details added successfully");
		return TileDefinitions.HOSPITALLIST.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/hospital/{id}/details",
			"/user/hospital/{id}/details" }, method = RequestMethod.GET)
	public String viewProviderPage(@PathVariable Integer id, Model model) {

		Hospital dbHospital = hospitalService.findById(id);
		logger.info("Returning hospital.getId()" + dbHospital.getId());

		model.addAttribute("hospital", dbHospital);
		logger.info("Returning hospitalSave.jsp page");
		return TileDefinitions.HOSPITALEDIT.toString();
	}

	/**
	 * @param id
	 * @param hospital
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/hospital/{id}/save.do", }, method = RequestMethod.POST, params = { "update" })
	public String updateHospitalAction(@PathVariable Integer id, @ModelAttribute @Validated Hospital hospital,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		hospital.setActiveInd('Y');
		logger.info("hospital id is" + id);
		if (bindingResult.hasErrors()) {
			logger.info("Returning hospitalEdit.jsp page");
			hospital.setActiveInd('Y');
			return TileDefinitions.HOSPITALEDIT.toString();
		}

		if (!hospitalService.isNameUnique(hospital.getId(), hospital.getName())) {
			FieldError insError = new FieldError("name", "name", hospital.getName(), false, null, null,
					hospital.getName() + " already exist");
			bindingResult.addError(insError);
			return TileDefinitions.HOSPITALEDIT.toString();
		}

		if (null != hospital.getId()) {
			logger.info("Returning HospitalSuccess.jsp page after update");
			hospital.setUpdatedBy(username);
			hospitalService.update(hospital);
			model.addAttribute("Message", "Hospital Details Updated Successfully");
		}

		return TileDefinitions.HOSPITALLIST.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/hospital/{id}/save.do" }, method = RequestMethod.POST, params = { "delete" })
	public String deleteHospitalAction(@PathVariable Integer id, Model model,
			@ModelAttribute("username") String username) {

		Hospital dbHospital = hospitalService.findById(id);
		dbHospital.setActiveInd(new Character('N'));
		dbHospital.setUpdatedBy(username);
		hospitalService.update(dbHospital);
		model.addAttribute("hospital", dbHospital);
		model.addAttribute("Message", "Hospital details deleted successfully");
		logger.info("Returning HospitalDeleteSuccess.jsp page after delete");
		return TileDefinitions.HOSPITALLIST.toString();
	}

}
