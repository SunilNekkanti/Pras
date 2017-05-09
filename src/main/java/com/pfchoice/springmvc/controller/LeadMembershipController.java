package com.pfchoice.springmvc.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.pfchoice.common.CommonMessageContent;
import com.pfchoice.common.SystemDefaultProperties;
import com.pfchoice.common.util.JsonConverter;
import com.pfchoice.common.util.PrasUtil;
import com.pfchoice.common.util.TileDefinitions;
import com.pfchoice.core.entity.BillType;
import com.pfchoice.core.entity.ClaimType;
import com.pfchoice.core.entity.County;
import com.pfchoice.core.entity.Ethinicity;
import com.pfchoice.core.entity.FacilityType;
import com.pfchoice.core.entity.FileType;
import com.pfchoice.core.entity.FrequencyType;
import com.pfchoice.core.entity.Gender;
import com.pfchoice.core.entity.Hospital;
import com.pfchoice.core.entity.Insurance;
import com.pfchoice.core.entity.LeadMembership;
import com.pfchoice.core.entity.LeadMembershipClaim;
import com.pfchoice.core.entity.LeadMembershipClaimDetails;
import com.pfchoice.core.entity.LeadMembershipProblem;
import com.pfchoice.core.entity.LeadMembershipHedisMeasure;
import com.pfchoice.core.entity.LeadMembershipHospitalization;
import com.pfchoice.core.entity.LeadMembershipInsurance;
import com.pfchoice.core.entity.LeadMembershipProvider;
import com.pfchoice.core.entity.MembershipStatus;
import com.pfchoice.core.entity.Problem;
import com.pfchoice.core.entity.Provider;
import com.pfchoice.core.entity.RiskRecon;
import com.pfchoice.core.service.BillTypeService;
import com.pfchoice.core.service.ClaimTypeService;
import com.pfchoice.core.service.CountyService;
import com.pfchoice.core.service.EthinicityService;
import com.pfchoice.core.service.FacilityTypeService;
import com.pfchoice.core.service.FileTypeService;
import com.pfchoice.core.service.FrequencyTypeService;
import com.pfchoice.core.service.GenderService;
import com.pfchoice.core.service.HospitalService;
import com.pfchoice.core.service.InsuranceService;
import com.pfchoice.core.service.LeadMembershipClaimService;
import com.pfchoice.core.service.LeadMembershipHospitalizationService;
import com.pfchoice.core.service.LeadMembershipInsuranceService;
import com.pfchoice.core.service.LeadMembershipProblemService;
import com.pfchoice.core.service.LeadMembershipProviderService;
import com.pfchoice.core.service.LeadMembershipService;
import com.pfchoice.core.service.MembershipStatusService;
import com.pfchoice.core.service.ProblemService;
import com.pfchoice.core.service.ProviderService;
import com.pfchoice.core.service.RiskReconService;

import ml.rugal.sshcommon.page.Pagination;
import ml.rugal.sshcommon.springmvc.util.Message;

@Controller
@SessionAttributes({ "username", "userpath" })
public class LeadMembershipController {

	private static final Logger logger = LoggerFactory.getLogger(LeadMembershipController.class);

	@Autowired
	private CountyService countyService;

	@Autowired
	private GenderService genderService;

	@Autowired
	private MembershipStatusService membershipStatusService;

	@Autowired
	private ProviderService providerService;

	@Autowired
	private LeadMembershipService leadMembershipService;

	@Autowired
	private LeadMembershipInsuranceService leadMembershipInsuranceService;

	@Autowired
	private LeadMembershipProviderService leadMembershipProviderService;

	@Autowired
	private LeadMembershipClaimService leadMembershipClaimService;

	@Autowired
	private FileTypeService fileTypeService;

	@Autowired
	private InsuranceService insuranceService;

	@Autowired
	private EthinicityService ethinicityService;

	@Autowired
	private FacilityTypeService facilityTypeService;

	@Autowired
	private FrequencyTypeService frequencyTypeService;

	@Autowired
	private BillTypeService billTypeService;

	@Autowired
	private ClaimTypeService claimTypeService;

	@Autowired
	private ProblemService problemService;

	@Autowired
	private HospitalService hospitalService;

	@Autowired
	private RiskReconService riskReconService;

	@Autowired
	private LeadMembershipProblemService leadMembershipProblemService;

	@Autowired
	private LeadMembershipHospitalizationService leadMembershipHospitalizationService;

	@Autowired
	@Qualifier("leadMembershipValidator")
	private Validator validator;

	/**
	 * @return
	 */
	@ModelAttribute("leadMembership")
	public LeadMembership createLeadMembershipModel() {
		return new LeadMembership();
	}

	@InitBinder("leadMembership")
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.setValidator(validator);
	}

	/**
	 * @return
	 */
	@RequestMapping(value = { "/admin/leadMembershipList", "/user/leadMembershipList" })
	public String handleRequest() {
		logger.info("returning leadMembershipList.jsp");
		return TileDefinitions.LEADMEMBERSHIPLIST.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/leadMembership/{id}/detailsList", "/user/leadMembership/{id}/detailsList" })
	public String leadMembershipInsurance(@PathVariable Integer id, Model model) {

		List<LeadMembershipInsurance> listBean = leadMembershipInsuranceService.findAllByMbrId(id);
		model.addAttribute("membershipDetailsList", listBean);
		logger.info("Returning membershipDetailsList.jsp page");
		return TileDefinitions.MEMBERSHIPDETAILSLIST.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/leadmembership/{id}/details/new", "/user/leadmembership/{id}/details/new" })
	public String newMembershipInsDetailsPage(@PathVariable Integer id, Model model) {

		LeadMembership dbLeadMembership = leadMembershipService.findById(id);

		LeadMembershipInsurance dbLeadMembershipInsurance = new LeadMembershipInsurance();
		dbLeadMembershipInsurance.setLeadMbr(dbLeadMembership);
		model.addAttribute("membershipInsurance", dbLeadMembershipInsurance);

		logger.info("Returning membershipDetailsDisplay.jsp page");
		return TileDefinitions.MEMBERSHIPDETAILSDISPLAY.toString();
	}

	/**
	 * @param id
	 * @param membershipInsurance
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/leadMembership/{id}/details/save.do",
			"/user/leadMembership/{id}/details/save.do" }, method = RequestMethod.POST, params = { "add" })
	public String newLeadMembershipInDetailsPage(@PathVariable Integer id,
			@ModelAttribute("membershipInsurance") @Validated LeadMembershipInsurance leadMembershipInsurance,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		logger.info("leadMembership id is" + id);
		if (bindingResult.hasErrors()) {
			logger.info("Returning membershipDetailsDisplay");
			System.out.println(" leadMembershipInsurance " + leadMembershipInsurance.getId());
			leadMembershipInsurance.setActiveInd('Y');
			return TileDefinitions.MEMBERSHIPDETAILSDISPLAY.toString();
		} else {
			LeadMembership dbLeadMembership = leadMembershipService.findById(id);
			leadMembershipInsurance.setLeadMbr(dbLeadMembership);
			leadMembershipInsurance.setCreatedBy(username);
			leadMembershipInsurance.setUpdatedBy(username);
			leadMembershipInsurance.setCreatedDate(new Date());
			leadMembershipInsurance.setUpdatedDate(new Date());
			leadMembershipInsurance.setFileId(1);
			LeadMembershipInsurance dbMembershipInsurance = leadMembershipInsuranceService
					.save(leadMembershipInsurance);
			model.addAttribute("membershipInsurance", dbMembershipInsurance);
			model.addAttribute("Message", "leadMembership Insurance Added Successfully");
			List<LeadMembershipInsurance> listBean = leadMembershipInsuranceService.findAllByMbrId(id);
			model.addAttribute("membershipDetailsList", listBean);
			return TileDefinitions.MEMBERSHIPDETAILSLIST.toString();
		}
	}

	/**
	 * @param id
	 * @param mbrInsId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/leadMembership/{id}/details/{mbrInsId}/display",
			"/user/leadMembership/{id}/details/{mbrInsId}/display" }, method = RequestMethod.GET)
	public String displayLeadMembershipDetailsPage(@PathVariable Integer id, @PathVariable Integer mbrInsId,
			Model model) {
		LeadMembershipInsurance dbLeadMembershipInsurance = leadMembershipInsuranceService.findById(mbrInsId);
		logger.info("Returning dbMembershipInsurance.getId()" + dbLeadMembershipInsurance.getId());
		logger.info("leadMembership id is" + id);
		model.addAttribute("membershipInsurance", dbLeadMembershipInsurance);
		logger.info("Returning membershipDetailsDisplay.jsp page");
		return TileDefinitions.MEMBERSHIPDETAILSDISPLAY.toString();
	}

	/**
	 * @param id
	 * @param leadMembership
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/leadMembership/{id}/details/{mbrInsId}/save.do",
			"/user/leadMembership/{id}/details/{mbrInsId}/save.do" }, method = RequestMethod.POST, params = {
					"update" })
	public String saveLeadMembershipDetailsPage(@PathVariable Integer id, @PathVariable Integer mbrInsId,
			@ModelAttribute("membershipInsurance") @Validated LeadMembershipInsurance leadMembershipInsurance,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		leadMembershipInsurance.setActiveInd('Y');
		logger.info("leadMembership id is" + id);
		if (bindingResult.hasErrors()) {
			logger.info("Returning membershipDetailsDisplay");
			return TileDefinitions.MEMBERSHIPDETAILSDISPLAY.toString();
		} else {
			LeadMembership dbLeadMembership = leadMembershipService.findById(id);
			leadMembershipInsurance.setLeadMbr(dbLeadMembership);
			leadMembershipInsurance.setUpdatedBy(username);
			leadMembershipInsurance.setUpdatedDate(new Date());
			leadMembershipInsurance.setUpdatedDate(new Date());
			leadMembershipInsurance.setFileId(1);
			LeadMembershipInsurance dbLeadMembershipInsurance = leadMembershipInsuranceService
					.update(leadMembershipInsurance);
			model.addAttribute("membershipInsurance", dbLeadMembershipInsurance);
			model.addAttribute("Message", "leadMembership Insurance Updated Successfully");
			List<LeadMembershipInsurance> listBean = leadMembershipInsuranceService.findAllByMbrId(id);
			model.addAttribute("membershipDetailsList", listBean);
			return TileDefinitions.MEMBERSHIPDETAILSLIST.toString();
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
	@RequestMapping(value = { "/admin/leadMembership/{id}/details/{mbrInsId}/save.do",
			"/user/leadMembership/{id}/details/{mbrInsId}/save.do" }, method = RequestMethod.POST, params = {
					"delete" })
	public String deleteLeadMembershipInsDetailsPage(@PathVariable Integer id, @PathVariable Integer mbrInsId,
			@ModelAttribute("membershipInsurance") @Validated LeadMembershipInsurance leadMembershipInsurance,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		logger.info("leadMembership id is" + id);
		leadMembershipInsurance.setActiveInd('Y');
		if (bindingResult.hasErrors()) {
			logger.info("Returning membershipDetailsDisplay");

			return TileDefinitions.MEMBERSHIPDETAILSDISPLAY.toString();
		} else {
			LeadMembershipInsurance dbLeadMembershipInsurance = leadMembershipInsuranceService.findById(mbrInsId);
			leadMembershipInsurance.setActiveInd('N');
			leadMembershipInsurance.setUpdatedBy(username);
			leadMembershipInsurance.setUpdatedDate(new Date());
			leadMembershipInsurance.setLeadMbr(dbLeadMembershipInsurance.getLeadMbr());
			leadMembershipInsurance.setFileId(dbLeadMembershipInsurance.getFileId().intValue());
			leadMembershipInsurance = leadMembershipInsuranceService.update(leadMembershipInsurance);
			model.addAttribute("membershipInsurance", leadMembershipInsurance);
			model.addAttribute("Message", "leadMembership Insurance Deleted Successfully");
			List<LeadMembershipInsurance> listBean = leadMembershipInsuranceService.findAllByMbrId(id);
			model.addAttribute("membershipDetailsList", listBean);
			return TileDefinitions.MEMBERSHIPDETAILSLIST.toString();

		}
	}

	@RequestMapping(value = { "/admin/leadMembership/{id}/providerDetailsList",
			"/user/leadMembership/{id}/providerDetailsList" }, method = RequestMethod.GET)
	public String displayLeadMembershipProviderDetailsListPage(@PathVariable Integer id, Model model) {

		List<LeadMembershipProvider> leadMbrPrvdrList = leadMembershipProviderService.findAllByMbrId(id);
		model.addAttribute("leadMembershipProviderList", leadMbrPrvdrList);
		logger.info("Returning leadMembershipProviderList.jsp page");
		return TileDefinitions.LEADMEMBERSHIPPROVIDERLIST.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/leadMembership/{id}/providerDetails",
			"/user/leadMembership/{id}/providerDetails" }, method = RequestMethod.GET)
	public String displayLeadMembershipProviderDetailsPage(@PathVariable Integer id, Model model) {

		LeadMembershipProvider dbLeadMembershipProvider = leadMembershipProviderService.findByMbrId(id);
		dbLeadMembershipProvider = (dbLeadMembershipProvider != null) ? dbLeadMembershipProvider
				: new LeadMembershipProvider();
		model.addAttribute("leadMembershipProvider", dbLeadMembershipProvider);
		logger.info("Returning membershipProviderEdit.jsp page");
		return TileDefinitions.LEADMEMBERSHIPPROVIDEREDIT.toString();
	}

	/**
	 * @param id
	 * @param leadMembershipInsurance
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/leadMembership/{id}/provider/save.do",
			"/user/leadMembership/{id}/provider/save.do" }, method = RequestMethod.POST, params = { "add" })
	public String newLeadMembershipPrvdrDetailsPage(@PathVariable Integer id,
			@ModelAttribute("leadMembershipProvider") @Validated LeadMembershipProvider leadMembershipProvider,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		logger.info("lead leadMembership id is" + id);
		if (bindingResult.hasErrors()) {
			logger.info("Returning membershipDetailsDisplay");
			System.out.println(" leadMembershipInsurance " + leadMembershipProvider.getId());
			leadMembershipProvider.setActiveInd('Y');
			return TileDefinitions.LEADMEMBERSHIPPROVIDEREDIT.toString();
		} else {
			LeadMembership dbLeadMembership = leadMembershipService.findById(id);
			leadMembershipProvider.setLeadMbr(dbLeadMembership);
			leadMembershipProvider.setCreatedBy(username);
			leadMembershipProvider.setUpdatedBy(username);
			leadMembershipProvider.setCreatedDate(new Date());
			leadMembershipProvider.setUpdatedDate(new Date());
			leadMembershipProvider.setFileId(1);
			leadMembershipProviderService.save(leadMembershipProvider);
			List<LeadMembershipProvider> leadMbrPrvdrList = leadMembershipProviderService.findAllByMbrId(id);
			model.addAttribute("Message", "leadMembership Insurance Added Successfully");
			model.addAttribute("leadMembershipProviderList", leadMbrPrvdrList);
			logger.info("Returning leadMembershipProviderList.jsp page");
			return TileDefinitions.LEADMEMBERSHIPPROVIDERLIST.toString();
		}
	}

	/**
	 * @param leadMembership
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/leadMembership/{id}/provider/save.do",
			"/user/leadMembership/{id}/provider/save.do" }, method = RequestMethod.POST, params = { "update" })
	public String saveLeadMemershipAction(@PathVariable Integer id,
			@Validated LeadMembershipProvider leadMembershipProvider, BindingResult bindingResult, Model model,
			@ModelAttribute("username") String username) {
		leadMembershipProvider.setActiveInd('Y');
		if (bindingResult.hasErrors()) {
			logger.info("Returning leadMembership Edit.jsp page");
			return TileDefinitions.LEADMEMBERSHIPEDIT.toString();
		}

		LeadMembership dbLeadMembership = leadMembershipService.findById(id);
		leadMembershipProvider.setLeadMbr(dbLeadMembership);
		logger.info("Returning ProviderSuccess.jsp page after create");
		leadMembershipProvider.setUpdatedBy(username);
		leadMembershipProvider.setFileId(1);
		leadMembershipProvider.setUpdatedDate(new Date());
		leadMembershipProvider = leadMembershipProviderService.update(leadMembershipProvider);
		List<LeadMembershipProvider> leadMbrPrvdrList = leadMembershipProviderService.findAllByMbrId(id);
		model.addAttribute("leadMembershipProviderList", leadMbrPrvdrList);
		model.addAttribute("Message", "Lead provider details updated Successfully");
		logger.info("Returning leadMembershipProviderList.jsp page");
		return TileDefinitions.LEADMEMBERSHIPPROVIDERLIST.toString();
	}

	/**
	 * @param leadMembership
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/leadMembership/{id}/provider/save.do",
			"/user/leadMembership/{id}/provider/save.do" }, method = RequestMethod.POST, params = { "delete" })
	public String deleteLeadMemershipAction(@PathVariable Integer id,
			@Validated LeadMembershipProvider leadMembershipProvider, BindingResult bindingResult, Model model,
			@ModelAttribute("username") String username) {
		leadMembershipProvider.setActiveInd('Y');
		if (bindingResult.hasErrors()) {
			logger.info("Returning leadMembership Edit.jsp page");
			return TileDefinitions.LEADMEMBERSHIPEDIT.toString();
		}
		leadMembershipProvider.setActiveInd('N');
		LeadMembership dbLeadMembership = leadMembershipService.findById(id);
		leadMembershipProvider.setLeadMbr(dbLeadMembership);
		logger.info("Returning ProviderSuccess.jsp page after create");
		leadMembershipProvider.setUpdatedBy(username);
		leadMembershipProvider.setFileId(1);
		leadMembershipProvider.setUpdatedDate(new Date());
		leadMembershipProviderService.update(leadMembershipProvider);
		List<LeadMembershipProvider> leadMbrPrvdrList = leadMembershipProviderService.findAllByMbrId(id);
		model.addAttribute("Message", "Lead provider details deleted Successfully");
		model.addAttribute("leadMembershipProviderList", leadMbrPrvdrList);
		logger.info("Returning leadMembershipProviderList.jsp page");
		return TileDefinitions.LEADMEMBERSHIPPROVIDERLIST.toString();
	}

	/**
	 * @param pageNo
	 * @param pageSize
	 * @param sSearch
	 * @param sSearchIns
	 * @param sSearchPrvdr
	 * @param sort
	 * @param sortdir
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/admin/leadMembership/list", "/user/leadMembership/list" }, method = RequestMethod.GET)
	public Message viewMembershipList(@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sSearch,
			@RequestParam(required = false) Integer sSearchIns, @RequestParam(required = false) Integer sSearchPrvdr,
			@RequestParam(required = false) String sort, @RequestParam(required = false) String sortdir) {

		// provided 0 to ignore hedisMeasureRule join
		Integer i = 0;
		List<Integer> ruleIds = Collections.singletonList(i);
		Pagination pagination = leadMembershipService.getPage(pageNo, pageSize, sSearch, sSearchIns, sSearchPrvdr, 0,
				ruleIds, sort, sortdir);
		logger.info("returning membershipList");
		return Message.successMessage(CommonMessageContent.LEAD_MEMBERSHIP_LIST,
				JsonConverter.getJsonObject(pagination));
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/leadMembership/new" })
	public String addLeadMembershipPage(final Model model) {
		LeadMembership leadMembership = createLeadMembershipModel();
		model.addAttribute("leadMembership", leadMembership);
		return TileDefinitions.LEADMEMBERSHIPNEW.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/leadMembership/{id}/complete",
			"/user/leadMembership/{id}/complete" }, method = RequestMethod.GET)
	public String completeLeadMembershipProviderDetailsPage(@PathVariable Integer id, Model model) {

		LeadMembership dbMembership = leadMembershipService.findById(id);
		model.addAttribute("leadMembership", dbMembership);

		return TileDefinitions.LEADMEMBERSHIPCOMPLETEDETAILS.toString();
	}

	/**
	 * @param provider
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */

	@RequestMapping(value = { "/admin/leadMembership/save.do" }, method = RequestMethod.POST, params = { "add" })
	public String newLeadMemershipAction(@Validated LeadMembership leadMembership, BindingResult bindingResult,
			Model model, @ModelAttribute("username") String username) {
		if (bindingResult.hasErrors()) {
			logger.info("Returning leadMembership Edit.jsp page");
			return TileDefinitions.LEADMEMBERSHIPEDIT.toString();
		}

		logger.info("Returning ProviderSuccess.jsp page after create");
		model.addAttribute("leadMembership", leadMembership);
		leadMembership.setCreatedBy(username);
		leadMembership.setUpdatedBy(username);
		leadMembership.setFileId(1);
		leadMembership.setCreatedDate(new Date());
		leadMembership.setUpdatedDate(new Date());
		leadMembershipService.save(leadMembership);
		model.addAttribute("Message", "Lead provider details added successfully");
		return TileDefinitions.LEADMEMBERSHIPEDIT.toString();
	}

	/**
	 * @param id
	 * @param leadMembership
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/leadMembership/{id}/save.do",
			"/user/leadMembership/{id}/save.do" }, method = RequestMethod.POST, params = { "update" })
	public String saveMembershipAction(@PathVariable Integer id,
			@ModelAttribute("leadMembership") @Validated LeadMembership leadMembership, BindingResult bindingResult,
			Model model, @ModelAttribute("username") String username) {
		logger.info("leadMembership id is" + id);
		if (bindingResult.hasErrors()) {
			leadMembership.setActiveInd('Y');
			logger.info("Returning membershipEdit.jsp page");
			return TileDefinitions.MEMBERSHIPEDIT.toString();
		}
		if (null != leadMembership.getId()) {
			leadMembership.setActiveInd('Y');
			leadMembership.setFileId(1);
			leadMembership.setUpdatedDate(new Date());
			leadMembership.setUpdatedBy(username);
			leadMembershipService.update(leadMembership);
		}
		logger.info("Returning MembershipSuccess.jsp page");

		LeadMembership dbLeadMembership = leadMembershipService.findById(leadMembership.getId());
		model.addAttribute("Message", "leadMembership details updated successfully");
		model.addAttribute("leadMembership", dbLeadMembership);
		return TileDefinitions.MEMBERSHIPEDIT.toString();
	}

	/* Lead leadMembership Hedis Measure */

	@RequestMapping(value = { "/admin/leadMembership/{id}/hedisMeasureList",
			"/user/leadMembership/{id}/hedisMeasureList" }, method = RequestMethod.GET)
	public String displayLeadMbrHedisMeasureListPage(@PathVariable Integer id,
			@Validated LeadMembershipProvider leadMembershipProvider, BindingResult bindingResult, Model model) {

		LeadMembership dbMembership = leadMembershipService.findById(id);
		List<LeadMembershipHedisMeasure> leadMbrHedisMeasureList = dbMembership.getLeadMbrHedisMeasureList();
		model.addAttribute("leadMbrHedisMeasureList", leadMbrHedisMeasureList);
		logger.info("Returning leadMembershipHedisMeasureList.jsp page");
		return TileDefinitions.LEADMEMBERSHIPHEDISMEASURELIST.toString();
	}

	/* Lead leadMembership Hedis Measure end */

	/* Lead leadMembership Claim Start */

	/**
	 * @return
	 */
	@RequestMapping(value = { "/admin/leadMembership/{id}/claimList", "/user/leadMembership/{id}/claimList" })
	public String leadMembershipClaimList(@PathVariable Integer id, Model model) {
		logger.info("returning leadMembershipClaimList.jsp");
		model.addAttribute("id", id);
		return TileDefinitions.LEADMEMBERSHIPCLAIMLIST.toString();
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
	@RequestMapping(value = { "/admin/leadMembership/{id}/claim/list",
			"/user/leadMembership/{id}/claim/list" }, method = RequestMethod.GET)
	public Message viewProviderList(@PathVariable Integer id, @RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize) {
		System.out.println("claim list");
		Pagination pagination = leadMembershipClaimService.getPage(pageNo, pageSize, id);
		return Message.successMessage(CommonMessageContent.USER_LIST, JsonConverter.getJsonObject(pagination));
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/leadMembership/{id}/claim/new", "/user/leadMembership/{id}/claim/new" })
	public String newLeadMembershipClaim(@PathVariable Integer id, Model model) {

		LeadMembership dbLeadMembership = leadMembershipService.findById(id);

		LeadMembershipClaim dbLeadMembershipClaim = new LeadMembershipClaim();
		dbLeadMembershipClaim.setLeadMbr(dbLeadMembership);
		model.addAttribute("leadMembershipClaim", dbLeadMembershipClaim);

		logger.info("Returning leadMembershipClaimEdit.jsp page");
		return TileDefinitions.LEADMEMBERSHIPCLAIMEDIT.toString();
	}

	/* Lead leadMembership Claim End */

	/**
	 * @param id
	 * @param mbrInsId
	 * @param membershipInsurance
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/leadMembership/{id}/claim/save.do",
			"/user/leadMembership/{id}/claim/save.do" }, method = RequestMethod.POST, params = { "add" })
	public String addLeadMembershipClaimDetailsPage(@PathVariable Integer id,
			@RequestBody @Valid LeadMembershipClaim leadMembershipClaim, BindingResult bindingResult, Model model,
			@ModelAttribute("username") String username, @ModelAttribute("userpath") String userpath) {
		logger.info("leadMembership id is" + id);
		if (bindingResult.hasErrors()) {
			logger.info("Returning membershipClaimDetailsDisplay");

			return TileDefinitions.LEADMEMBERSHIPCLAIMEDIT.toString();
		} else {
			Calendar cal = Calendar.getInstance();
			Date date = cal.getTime();
			long dateTime = date.getTime();
			LeadMembership leadMbr = leadMembershipService.findById(id);
			leadMembershipClaim.setCreatedBy(username);
			leadMembershipClaim.setUpdatedBy(username);
			leadMembershipClaim.setClaimNumber(username + id + dateTime);
			leadMembershipClaim.setLeadMbr(leadMbr);
			Insurance ins = insuranceService.findById(3);
			leadMembershipClaim.setIns(ins);

			String reportMonth = PrasUtil.getDateWithFormatFromString(date, "YYYYMM");
			leadMembershipClaim.setReportMonth(Integer.parseInt(reportMonth));

			List<LeadMembershipClaimDetails> leadMbrClaimDetailsList = leadMembershipClaim.getLeadMbrClaimDetailsList();

			System.out.println("leadMbrClaimDetailsList.size" + leadMbrClaimDetailsList.size());
			for (LeadMembershipClaimDetails leadMembershipClaimDetails : leadMbrClaimDetailsList) {
				leadMembershipClaimDetails.setLeadMbrClaim(leadMembershipClaim);
				leadMembershipClaimDetails.setCreatedBy(username);
				leadMembershipClaimDetails.setUpdatedBy(username);
			}
			leadMembershipClaimService.save(leadMembershipClaim);

			model.addAttribute("Message", "LeadMembership Claim added Successfully");
			return "forward:/" + userpath + "/" + TileDefinitions.LEADMEMBERSHIPCLAIMLIST.toString();
		}
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/leadMembership/{id}/claimDetails", "/user/leadMembership/{id}/claimDetails" })
	public String leadMembershipClaimDetails(@PathVariable Integer id, Model model) {

		LeadMembershipClaim dbLeadMembershipClaim = leadMembershipClaimService.findById(id);
		System.out.println("  id " + id);
		System.out.println(
				"  " + dbLeadMembershipClaim.getId() + "  " + dbLeadMembershipClaim.getClaimNumber() + "  id " + id);
		System.out.println("  " + dbLeadMembershipClaim.getLeadMbrClaimDetailsList().get(0).getClaimStartDate());
		model.addAttribute("leadMembershipClaim", dbLeadMembershipClaim);

		logger.info("Returning leadMembershipClaimEdit.jsp page");
		return TileDefinitions.LEADMEMBERSHIPCLAIMEDIT.toString();
	}

	@RequestMapping(value = { "/admin/leadMembership/{id}/claim/save.do",
			"/user/leadMembership/{id}/claim/save.do" }, method = RequestMethod.POST, params = { "update" })
	public String updateLeadMembershipClaimDetailsPage(@PathVariable Integer id,
			@RequestBody @Valid LeadMembershipClaim leadMembershipClaim, BindingResult bindingResult, Model model,
			@ModelAttribute("username") String username, @ModelAttribute("userpath") String userpath) {
		logger.info("leadMembership id is" + id);
		if (bindingResult.hasErrors()) {
			logger.info("Returning membershipClaimDetailsDisplay");

			return TileDefinitions.LEADMEMBERSHIPCLAIMEDIT.toString();
		} else {
			
			LeadMembershipClaim leadMbrClaim = leadMembershipClaimService.findById(id);
			leadMbrClaim.getLeadMbrClaimDetailsList().clear();
			
			leadMembershipClaim.setUpdatedBy(username);
			leadMembershipClaim.setClaimNumber(leadMbrClaim.getClaimNumber());
			leadMembershipClaim.setIns(leadMbrClaim.getIns());
			leadMembershipClaim.setReportMonth(leadMbrClaim.getReportMonth());
			List<LeadMembershipClaimDetails> leadMbrClaimDetailsList = new ArrayList<>();
			for (LeadMembershipClaimDetails leadMembershipClaimDetails : leadMembershipClaim.getLeadMbrClaimDetailsList()) {
				leadMembershipClaimDetails.setLeadMbrClaim(leadMembershipClaim);
				leadMembershipClaimDetails.setCreatedBy(username);
				leadMembershipClaimDetails.setUpdatedBy(username);
				leadMbrClaimDetailsList.add(leadMembershipClaimDetails);
			} 
			leadMembershipClaimService.merge(leadMembershipClaim);

			model.addAttribute("Message", "LeadMembership Claim updated Successfully");
			logger.info("Before returning leadmembershipClaimList");
			return "forward:/" + userpath + "/" +TileDefinitions.LEADMEMBERSHIPCLAIMLIST.toString();

		}

	}

	/* Lead leadMembership Problem Start */

	/**
	 * @return
	 */
	@RequestMapping(value = { "/admin/leadMembership/{id}/problemList", "/user/leadMembership/{id}/problemList" })
	public String leadMembershipProblemList(@PathVariable Integer id, Model model) {
		logger.info("returning leadMembershipProblelmList.jsp");
		model.addAttribute("id", id);
		return TileDefinitions.LEADMEMBERSHIPPROBLEMLIST.toString();
	}

	/**
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/admin/leadMembership/{id}/problem/list",
			"/user/leadMembership/{id}/problem/list" }, method = RequestMethod.GET)
	public Message viewProblemList(@PathVariable Integer id, @RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize) {
		Pagination pagination = leadMembershipProblemService.getPage(pageNo, pageSize);
		return Message.successMessage(CommonMessageContent.LEADMEMBERSHIPPROBLEM_LIST,
				JsonConverter.getJsonObject(pagination));
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/leadMembership/{id}/problem/new", "/user/leadMembership/{id}/problem/new" })
	public String newLeadMembershipProblem(@PathVariable Integer id, Model model) {

		LeadMembership dbLeadMembership = leadMembershipService.findById(id);

		LeadMembershipProblem dbLeadMembershipProblem = new LeadMembershipProblem();
		dbLeadMembershipProblem.setLeadMbr(dbLeadMembership);
		model.addAttribute("leadMembershipProblem", dbLeadMembershipProblem);

		logger.info("Returning leadMembershipProblemEdit.jsp page");
		return TileDefinitions.LEADMEMBERSHIPPROBLEMEDIT.toString();
	}

	/* Lead leadMembership Problem End */

	/* Lead leadMembership Hospitalization Start */

	/**
	 * @return
	 */
	@RequestMapping(value = { "/admin/leadMembership/{id}/hospitalizationList",
			"/user/leadMembership/{id}/hospitalizationList" })
	public String leadMembershipHospitalizationList(@PathVariable Integer id, Model model) {
		logger.info("returning leadMembershipHospitalizationList.jsp");
		model.addAttribute("id", id);
		return TileDefinitions.LEADMEMBERSHIPHOSPITALIZATIONLIST.toString();
	}

	/**
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/admin/leadMembership/{id}/hospitalization/list",
			"/user/leadMembership/{id}/hospitalization/list" }, method = RequestMethod.GET)
	public Message viewLeadMembershipHospitalizationList(@PathVariable Integer id,
			@RequestParam(required = false) Integer pageNo, @RequestParam(required = false) Integer pageSize) {
		Pagination pagination = leadMembershipHospitalizationService.getPage(pageNo, pageSize);
		return Message.successMessage(CommonMessageContent.LEADMEMBERSHIPHOSPITALIZATION_LIST,
				JsonConverter.getJsonObject(pagination));
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/leadMembership/{id}/hospitalization/new",
			"/user/leadMembership/{id}/hospitalization/new" })
	public String newLeadMembershipHospitalization(@PathVariable Integer id, Model model) {

		LeadMembership dbLeadMembership = leadMembershipService.findById(id);

		LeadMembershipHospitalization dbLeadMembershipHospitalization = new LeadMembershipHospitalization();
		dbLeadMembershipHospitalization.setLeadMbr(dbLeadMembership);
		model.addAttribute("leadMembershipHospitalization", dbLeadMembershipHospitalization);

		logger.info("Returning leadMembershipHospitalizationEdit.jsp page");
		return TileDefinitions.LEADMEMBERSHIPHOSPITALIZATIONEDIT.toString();
	}

	/* Lead leadMembership Hospitalization End */

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
	@ModelAttribute("insList")
	public List<Insurance> populateInsuranceList() {
		Pagination page = insuranceService.getPage(SystemDefaultProperties.DEFAULT_PAGE_NO,
				SystemDefaultProperties.MEDIUM_LIST_SIZE);
		return (List<Insurance>) page.getList();
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ModelAttribute("fileTypeList")
	public List<FileType> populateFileTypeList() {
		Pagination page = fileTypeService.getPage(SystemDefaultProperties.DEFAULT_PAGE_NO,
				SystemDefaultProperties.MEDIUM_LIST_SIZE);
		return (List<FileType>) page.getList();
	}

	@SuppressWarnings("unchecked")
	@ModelAttribute("prvdrList")
	public List<Provider> populatePrvdrList() {
		Pagination page = providerService.getPage(SystemDefaultProperties.DEFAULT_PAGE_NO,
				SystemDefaultProperties.MEDIUM_LIST_SIZE, null, "name", null);
		return (List<Provider>) page.getList();
	}

	@SuppressWarnings("unchecked")
	@ModelAttribute("facilityTypeList")
	public List<FacilityType> facilityTypeList() {
		Pagination page = facilityTypeService.getPage(SystemDefaultProperties.DEFAULT_PAGE_NO,
				SystemDefaultProperties.MEDIUM_LIST_SIZE, null, "description", null);
		return (List<FacilityType>) page.getList();
	}

	@SuppressWarnings("unchecked")
	@ModelAttribute("billTypeList")
	public List<BillType> billTypeList() {
		Pagination page = billTypeService.getPage(SystemDefaultProperties.DEFAULT_PAGE_NO,
				SystemDefaultProperties.MEDIUM_LIST_SIZE, null, "description", null);
		return (List<BillType>) page.getList();
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ModelAttribute("frequencyTypeList")
	public List<FrequencyType> frequencyTypeList() {
		Pagination page = frequencyTypeService.getPage(SystemDefaultProperties.DEFAULT_PAGE_NO,
				SystemDefaultProperties.MEDIUM_LIST_SIZE, null, "description", null);
		return (List<FrequencyType>) page.getList();
	}

	@SuppressWarnings("unchecked")
	@ModelAttribute("claimTypeList")
	public List<ClaimType> claimTypeList() {
		Pagination page = claimTypeService.getPage(SystemDefaultProperties.DEFAULT_PAGE_NO,
				SystemDefaultProperties.MEDIUM_LIST_SIZE);
		return (List<ClaimType>) page.getList();
	}

	@SuppressWarnings("unchecked")
	@ModelAttribute("problemList")
	public List<Problem> problemList() {
		Pagination page = problemService.getPage(SystemDefaultProperties.DEFAULT_PAGE_NO,
				SystemDefaultProperties.MEDIUM_LIST_SIZE);
		return (List<Problem>) page.getList();
	}

	@SuppressWarnings("unchecked")
	@ModelAttribute("hospitalList")
	public List<Hospital> hospitalList() {
		Pagination page = hospitalService.getPage(SystemDefaultProperties.DEFAULT_PAGE_NO,
				SystemDefaultProperties.MEDIUM_LIST_SIZE);
		return (List<Hospital>) page.getList();
	}

	@SuppressWarnings("unchecked")
	@ModelAttribute("riskReconList")
	public List<RiskRecon> riskReconList() {
		Pagination page = riskReconService.getPage(SystemDefaultProperties.DEFAULT_PAGE_NO,
				SystemDefaultProperties.MEDIUM_LIST_SIZE);
		return (List<RiskRecon>) page.getList();
	}

}
