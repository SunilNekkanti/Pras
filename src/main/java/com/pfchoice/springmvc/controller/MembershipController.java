package com.pfchoice.springmvc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
import com.pfchoice.common.Message;
import com.pfchoice.common.SystemDefaultProperties;
import com.pfchoice.common.util.JsonConverter;
import com.pfchoice.core.entity.County;
import com.pfchoice.core.entity.Ethinicity;
import com.pfchoice.core.entity.Gender;
import com.pfchoice.core.entity.HedisMeasure;
import com.pfchoice.core.entity.Insurance;
import com.pfchoice.core.entity.Membership;
import com.pfchoice.core.entity.MembershipHedisMeasure;
import com.pfchoice.core.entity.MembershipInsurance;
import com.pfchoice.core.entity.MembershipProvider;
import com.pfchoice.core.entity.MembershipStatus;
import com.pfchoice.core.service.CountyService;
import com.pfchoice.core.service.EthinicityService;
import com.pfchoice.core.service.GenderService;
import com.pfchoice.core.service.HedisMeasureService;
import com.pfchoice.core.service.InsuranceService;
import com.pfchoice.core.service.MembershipInsuranceService;
import com.pfchoice.core.service.MembershipProviderService;
import com.pfchoice.core.service.MembershipService;
import com.pfchoice.core.service.MembershipStatusService;

import ml.rugal.sshcommon.page.Pagination;

@Controller
@SessionAttributes({ "username", "userpath" })
public class MembershipController {

	private static final Logger logger = LoggerFactory.getLogger(MembershipController.class);

	@Autowired
	private CountyService countyService;

	@Autowired
	private GenderService genderService;

	@Autowired
	private MembershipStatusService membershipStatusService;

	@Autowired
	private MembershipService membershipService;

	@Autowired
	private MembershipInsuranceService membershipInsuranceService;

	@Autowired
	private InsuranceService insuranceService;

	@Autowired
	private EthinicityService ethinicityService;

	@Autowired
	private MembershipProviderService membershipProviderService;

	@Autowired
	private HedisMeasureService hedisMeasureService;

	@Autowired
	@Qualifier("membershipValidator")
	private Validator validator;

	@Autowired
	@Qualifier("membershipInsuranceValidator")
	private Validator insValidator;

	/**
	 * @param binder
	 */
	@InitBinder("membership")
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.setValidator(validator);
	}

	/**
	 * @param binder
	 */
	@InitBinder("membershipInsurance")
	public void initInsBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.setValidator(insValidator);
	}


	/**
	 * @return
	 */
	@ModelAttribute("membership")
	public Membership createMembershipModel() {
		return new Membership();
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/membership/{id}", "/user/membership/{id}" }, method = RequestMethod.GET)
	public String updateMembershipPage(@PathVariable Integer id, Model model) {
		Membership dbMembership = membershipService.findById(id);
		logger.info("Returning membership.getId()" + dbMembership.getId());

		model.addAttribute("membership", dbMembership);
		logger.info("Returning membershipEdit.jsp page");
		return "membershipEdit";
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/membership/{id}/display",
			"/user/membership/{id}/display" }, method = RequestMethod.GET)
	public String displayMembershipPage(@PathVariable Integer id, Model model) {
		Membership dbMembership = membershipService.findById(id);
		logger.info("Returning membership.getId()" + dbMembership.getId());

		model.addAttribute("membership", dbMembership);
		logger.info("Returning membershipDisplay.jsp page");
		return "membershipDisplay";
	}

	/**
	 * @param id
	 * @param membership
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/membership/{id}/save.do",
			"/user/membership/{id}/save.do" }, method = RequestMethod.POST, params = { "update" })
	public String saveMembershipAction(@PathVariable Integer id,
			@ModelAttribute("membership") @Validated Membership membership, BindingResult bindingResult, Model model,
			@ModelAttribute("username") String username) {
		logger.info("membership id is"+id);
		if (bindingResult.hasErrors()) {
			membership.setActiveInd('Y');
			logger.info("Returning membershipEdit.jsp page");
			return "membershipEdit";
		}
		logger.info("Returning MembershipSuccess.jsp page");
		if (null != membership.getId()) {
			membership.setUpdatedBy(username);
			membershipService.update(membership);
		}
		Membership dbMembership = membershipService.findById(membership.getId());
		model.addAttribute("Message", "Membership details updated successfully");
		model.addAttribute("membership", dbMembership);
		return "membershipEdit";
	}

	/**
	 * @param id
	 * @param membership
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/membership/{id}/save.do",
			"/user/membership/{id}/save.do" }, method = RequestMethod.POST, params = { "delete" })
	public String deleteMembershipAction(@PathVariable Integer id, @Validated Membership membership,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		logger.info("membership id is"+id);
		if (bindingResult.hasErrors()) {
			membership.setActiveInd('Y');
			logger.info("Returning membershipEdit.jsp page");
			return "membershipEdit";
		}
		logger.info("Returning MembershipSuccess.jsp page");
		if (null != membership.getId()) {
			membership.setActiveInd('N');
			membership.setUpdatedBy(username);
			membershipService.update(membership);
		}

		model.addAttribute("Message", "Membership details deleted successfully");
		model.addAttribute("membership", membership);
		return "membershipEdit";
	}

	/**
	 * @param id
	 * @param mbrInsId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/membership/{id}/details/{mbrInsId}/display",
			"/user/membership/{id}/details/{mbrInsId}/display" }, method = RequestMethod.GET)
	public String displayMembershipDetailsPage(@PathVariable Integer id, @PathVariable Integer mbrInsId, Model model) {
		MembershipInsurance dbMembershipInsurance = membershipInsuranceService.findById(mbrInsId);
		logger.info("Returning dbMembershipInsurance.getId()" + dbMembershipInsurance.getId());
		logger.info("membership id is"+id);
		model.addAttribute("membershipInsurance", dbMembershipInsurance);
		logger.info("Returning membershipDetailsDisplay.jsp page");
		return "membershipDetailsDisplay";
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/membership/{id}/details/new", "/user/membership/{id}/details/new" })
	public String newMembershipInsDetailsPage(@PathVariable Integer id, Model model) {

		Membership dbMembership = membershipService.findById(id);

		MembershipInsurance dbMembershipInsurance = new MembershipInsurance();
		dbMembershipInsurance.setMbr(dbMembership);
		model.addAttribute("membershipInsurance", dbMembershipInsurance);

		logger.info("Returning membershipDetailsDisplay.jsp page");
		return "membershipDetailsDisplay";
	}

	/**
	 * @param id
	 * @param mbrInsId
	 * @param membershipInsurance
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/membership/{id}/details/{mbrInsId}/save.do",
			"/user/membership/{id}/details/{mbrInsId}/save.do" }, method = RequestMethod.POST, params = { "update" })
	public String saveMembershipDetailsPage(@PathVariable Integer id, @PathVariable Integer mbrInsId,
			@ModelAttribute("membershipInsurance") @Validated MembershipInsurance membershipInsurance,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		logger.info("membership id is"+id);
		if (bindingResult.hasErrors()) {
			logger.info("Returning membershipDetailsDisplay");
			membershipInsurance.setActiveInd('Y');
			return "membershipDetailsDisplay";
		} else {
			membershipInsurance.setUpdatedBy(username);
			MembershipInsurance dbMembershipInsurance = membershipInsuranceService.update(membershipInsurance);
			model.addAttribute("membershipInsurance", dbMembershipInsurance);
			model.addAttribute("Message", "Membership Insurance Updated Successfully");
			List<MembershipInsurance> listBean = membershipInsuranceService.findAllByMbrId(id);
			model.addAttribute("membershipDetailsList", listBean);
			return "membershipDetailsList";
		}
	}

	/**
	 * @param id
	 * @param membershipInsurance
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/membership/{id}/details/save.do",
			"/user/membership/{id}/details/save.do" }, method = RequestMethod.POST, params = { "add" })
	public String newMembershipInDetailsPage(@PathVariable Integer id,
			@ModelAttribute("membershipInsurance") @Validated MembershipInsurance membershipInsurance,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		logger.info("membership id is"+id);
		if (bindingResult.hasErrors()) {
			logger.info("Returning membershipDetailsDisplay");
			membershipInsurance.setActiveInd('Y');
			return "membershipDetailsDisplay";
		} else {
			Membership dbMembership = membershipService.findById(id);
			membershipInsurance.setMbr(dbMembership);
			membershipInsurance.setCreatedBy(username);
			membershipInsurance.setUpdatedBy(username);
			MembershipInsurance dbMembershipInsurance = membershipInsuranceService.save(membershipInsurance);
			model.addAttribute("membershipInsurance", dbMembershipInsurance);
			model.addAttribute("Message", "Membership Insurance Added Successfully");
			List<MembershipInsurance> listBean = membershipInsuranceService.findAllByMbrId(id);
			model.addAttribute("membershipDetailsList", listBean);
			return "membershipDetailsList";
		}
	}

	/**
	 * @param id
	 * @param mbrInsId
	 * @param membershipInsurance
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/membership/{id}/details/{mbrInsId}/save.do",
			"/user/membership/{id}/details/{mbrInsId}/save.do" }, method = RequestMethod.POST, params = { "delete" })
	public String deleteMembershipInsDetailsPage(@PathVariable Integer id, @PathVariable Integer mbrInsId,
			@ModelAttribute("membershipInsurance") @Validated MembershipInsurance membershipInsurance,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		logger.info("membership id is"+id);
		if (bindingResult.hasErrors()) {
			logger.info("Returning membershipDetailsDisplay");
			membershipInsurance.setActiveInd('Y');
			return "membershipDetailsDisplay";
		} else {
			membershipInsurance.setActiveInd('N');
			membershipInsurance.setUpdatedBy(username);
			MembershipInsurance dbMembershipInsurance = membershipInsuranceService.update(membershipInsurance);
			model.addAttribute("membershipInsurance", dbMembershipInsurance);
			model.addAttribute("Message", "Membership Insurance Deleted Successfully");
			List<MembershipInsurance> listBean = membershipInsuranceService.findAllByMbrId(id);
			model.addAttribute("membershipDetailsList", listBean);
			return "membershipDetailsList";

		}
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/membership/{id}/detailsList", "/user/membership/{id}/detailsList" })
	public String handleRequest(@PathVariable Integer id, Model model) {

		List<MembershipInsurance> listBean = membershipInsuranceService.findAllByMbrId(id);
		model.addAttribute("membershipDetailsList", listBean);
		logger.info("Returning membershipDetailsList.jsp page");
		return "membershipDetailsList";
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/membership/{id}/providerDetails",
			"/user/membership/{id}/providerDetails" }, method = RequestMethod.GET)
	public String displayMembershipProviderDetailsPage(@PathVariable Integer id, Model model) {

		MembershipProvider dbMembershipProvider = membershipProviderService.findByMbrId(id);
		dbMembershipProvider = (dbMembershipProvider != null) ? dbMembershipProvider : new MembershipProvider();
		model.addAttribute("membershipProvider", dbMembershipProvider);
		logger.info("Returning membershipProviderEdit.jsp page");
		return "membershipProviderEdit";
	}

	/**
	 * @param id
	 * @param mbrPrvdrId
	 * @param membershipProvider
	 * @param bindingResult
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/membership/{id}/memberProvider/{mbrPrvdrId}",
			"/user/membership/{id}/memberProvider/{mbrPrvdrId}" }, method = RequestMethod.GET)
	public String displayInactiveMembershipProviderDetailsPage(@PathVariable Integer id,
			@PathVariable Integer mbrPrvdrId, @ModelAttribute @Validated MembershipProvider membershipProvider,
			BindingResult bindingResult, Model model)  {
		logger.info("membership id is"+id);
		MembershipProvider dbMembershipProvider = membershipProviderService.findById(mbrPrvdrId);
		model.addAttribute("membershipProvider", dbMembershipProvider);
		logger.info("Returning membershipProviderEdit.jsp page");
		return "membershipProviderEdit";
	}

	/**
	 * @param id
	 * @param membershipProvider
	 * @param bindingResult
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/membership/{id}/providerDetailsList",
			"/user/membership/{id}/providerDetailsList" }, method = RequestMethod.GET)
	public String displayMembershipProviderDetailsListPage(@PathVariable Integer id,
			@Validated MembershipProvider membershipProvider, BindingResult bindingResult, Model model) {

		List<MembershipProvider> mbrPrvdrList = membershipProviderService.findAllByMbrId(id);
		model.addAttribute("membershipProviderList", mbrPrvdrList);
		logger.info("Returning membershipProviderList.jsp page");
		return "membershipProviderList";
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/membership/{id}/complete",
			"/user/membership/{id}/complete" }, method = RequestMethod.GET)
	public String completeMembershipProviderDetailsPage(@PathVariable Integer id, Model model)  {
		Membership dbMembership = membershipService.findById(id);
		model.addAttribute("membership", dbMembership);
		List<MembershipInsurance> dbMembershipInsuranceList = membershipInsuranceService.findAllByMbrId(id);
		List<MembershipProvider> dbMembershipProviderList = membershipProviderService.findAllByMbrId(id);

		for (MembershipProvider s : dbMembershipProviderList) {
			if (s.getActiveInd() == 'Y') {
				MembershipProvider dbMembershipProvider = membershipProviderService.findByMbrId(id);
				model.addAttribute("membershipProvider", dbMembershipProvider);
			}
		}
		for (MembershipInsurance s : dbMembershipInsuranceList) {
			if (s.getActiveInd() == 'Y') {
				MembershipInsurance dbMembershipInsurance = membershipInsuranceService.findByMbrId(id);
				model.addAttribute("membershipInsurance", dbMembershipInsurance);
			}
		}

		List<MembershipHedisMeasure> mbrHedisMeasureList = dbMembership.getMbrHedisMeasureList();
		model.addAttribute("mbrHedisMeasureList", mbrHedisMeasureList);

		List<MembershipInsurance> listBean = membershipInsuranceService.findAllByMbrId(id);
		model.addAttribute("membershipDetailsList", listBean);
		logger.info("Returning membershipDetailsList.jsp page");

		List<MembershipProvider> mbrPrvdrList = membershipProviderService.findAllByMbrId(id);
		model.addAttribute("membershipProviderList", mbrPrvdrList);
		logger.info("Returning membershipProviderList.jsp page");

		return "membershipCompleteDetails";

	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/membership/{id}/hedisMeasure",
			"/user/membership/{id}/hedisMeasure" }, method = RequestMethod.GET)
	public String displayMembershipHedisMeasurePage(@PathVariable Integer id, Model model)  {

		Membership dbMembership = membershipService.findById(id);
		List<MembershipHedisMeasure> mbrHedisMeasureList = dbMembership.getMbrHedisMeasureList();
		model.addAttribute("mbrHedisMeasureList", mbrHedisMeasureList);
		logger.info("Returning membershipHedisMeasure.jsp page");
		return "membershipHedisMeasure";
	}

	/**
	 * @param id
	 * @param model
	 * @param hedisRuleId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/admin/membership/{id}/hedisMeasureList",
			"/user/membership/{id}/hedisMeasureList" }, method = RequestMethod.GET)
	public Message displayMembershipHedisMeasureListPage(@PathVariable Integer id, Model model,
			@RequestParam(required = false) Integer hedisRuleId)  {

		Membership dbMembership = membershipService.findById(id);
		List<MembershipHedisMeasure> mbrHedisMeasureList = dbMembership.getMbrHedisMeasureList();
		if (hedisRuleId != null && hedisRuleId != 9999) {
			mbrHedisMeasureList = mbrHedisMeasureList.stream()
					.filter(mbrHedisMeasure -> mbrHedisMeasure.getHedisMeasureRule().getId() == hedisRuleId
							&& mbrHedisMeasure.getDos() == null)
					.collect(Collectors.toList());
		} else {
			mbrHedisMeasureList = mbrHedisMeasureList.stream()
					.filter(mbrHedisMeasure -> mbrHedisMeasure.getDos() == null).collect(Collectors.toList());
		}
		return Message.successMessage(CommonMessageContent.HEDIS_RULE_LIST,
				JsonConverter.getJsonObject(mbrHedisMeasureList));
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ModelAttribute("countyList")
	public List<County> populateCountyList() {
		Pagination page = countyService.getPage(SystemDefaultProperties.DEFAULT_PAGE_NO,
					SystemDefaultProperties.LARGE_LIST_SIZE);
		return (List<County>) page.getList();
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
	@ModelAttribute("statusList")
	public List<MembershipStatus> populateStatusList() {
		
		Pagination page = membershipStatusService.getPage(SystemDefaultProperties.DEFAULT_PAGE_NO,
				SystemDefaultProperties.SMALL_LIST_SIZE);
		return (List<MembershipStatus>) page.getList();
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ModelAttribute("ethinicityList")
	public List<Ethinicity> populateEthinicityList() {

		Pagination page = ethinicityService.getPage(SystemDefaultProperties.DEFAULT_PAGE_NO,
				SystemDefaultProperties.SMALL_LIST_SIZE);
		return (List<Ethinicity>) page.getList();
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ModelAttribute("hedisMeasureList")
	public List<HedisMeasure> populateHedisMeasureList() {
		Pagination page = hedisMeasureService.getPage(SystemDefaultProperties.DEFAULT_PAGE_NO,
				SystemDefaultProperties.MEDIUM_LIST_SIZE);
		return (List<HedisMeasure>) page.getList();
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ModelAttribute("insList")
	public List<Insurance> populateInsuranceList() {
		Pagination page = insuranceService.getPage(SystemDefaultProperties.DEFAULT_PAGE_NO,
				SystemDefaultProperties.MEDIUM_LIST_SIZE);
		return (List<Insurance>) page.getList();
	}

}
