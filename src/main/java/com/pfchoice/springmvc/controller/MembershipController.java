package com.pfchoice.springmvc.controller;

import static com.pfchoice.common.SystemDefaultProperties.FILES_UPLOAD_DIRECTORY_PATH;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.pfchoice.common.CommonMessageContent;
import com.pfchoice.common.Message;
import com.pfchoice.common.SystemDefaultProperties;
import com.pfchoice.common.util.JsonConverter;
import com.pfchoice.common.util.TileDefinitions;
import com.pfchoice.common.util.XLSX2CSV;
import com.pfchoice.core.entity.County;
import com.pfchoice.core.entity.Ethinicity;
import com.pfchoice.core.entity.FileType;
import com.pfchoice.core.entity.Gender;
import com.pfchoice.core.entity.HedisMeasure;
import com.pfchoice.core.entity.Insurance;
import com.pfchoice.core.entity.Membership;
import com.pfchoice.core.entity.MembershipHedisMeasure;
import com.pfchoice.core.entity.MembershipHospitalization;
import com.pfchoice.core.entity.MembershipInsurance;
import com.pfchoice.core.entity.MembershipProblem;
import com.pfchoice.core.entity.MembershipProvider;
import com.pfchoice.core.entity.MembershipStatus;
import com.pfchoice.core.entity.Problem;
import com.pfchoice.core.entity.User;
import com.pfchoice.core.service.CountyService;
import com.pfchoice.core.service.EthinicityService;
import com.pfchoice.core.service.FileTypeService;
import com.pfchoice.core.service.GenderService;
import com.pfchoice.core.service.HedisMeasureService;
import com.pfchoice.core.service.InsuranceService;
import com.pfchoice.core.service.MembershipInsuranceService;
import com.pfchoice.core.service.MembershipProblemService;
import com.pfchoice.core.service.MembershipProviderService;
import com.pfchoice.core.service.MembershipService;
import com.pfchoice.core.service.MembershipStatusService;
import com.pfchoice.core.service.ProblemService;
import com.pfchoice.core.service.UserService;

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
	private FileTypeService fileTypeService;
	
	@Autowired
	private MembershipInsuranceService membershipInsuranceService;

	@Autowired
	private ProblemService problemService;

	@Autowired
	private MembershipProblemService membershipProblemService;

	@Autowired
	private InsuranceService insuranceService;

	@Autowired
	private EthinicityService ethinicityService;

	@Autowired
	private MembershipProviderService membershipProviderService;

	@Autowired
	private HedisMeasureService hedisMeasureService;

	@Autowired
	private UserService userService;

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
		return TileDefinitions.MEMBERSHIPEDIT.toString();
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
		return TileDefinitions.MEMBERSHIPDISPLAY.toString();
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
		logger.info("membership id is" + id);
		if (bindingResult.hasErrors()) {
			membership.setActiveInd('Y');
			logger.info("Returning membershipEdit.jsp page");
			return TileDefinitions.MEMBERSHIPEDIT.toString();
		}
		logger.info("Returning MembershipSuccess.jsp page");
		if (null != membership.getId()) {
			membership.setUpdatedBy(username);
			membershipService.update(membership);
		}
		Membership dbMembership = membershipService.findById(membership.getId());
		model.addAttribute("Message", "Membership details updated successfully");
		model.addAttribute("membership", dbMembership);
		return TileDefinitions.MEMBERSHIPEDIT.toString();
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
		logger.info("membership id is" + id);
		if (bindingResult.hasErrors()) {
			membership.setActiveInd('Y');
			logger.info("Returning membershipEdit.jsp page");
			return TileDefinitions.MEMBERSHIPEDIT.toString();
		}
		logger.info("Returning MembershipSuccess.jsp page");
		if (null != membership.getId()) {
			membership.setActiveInd('N');
			membership.setUpdatedBy(username);
			membershipService.update(membership);
		}

		model.addAttribute("Message", "Membership details deleted successfully");
		model.addAttribute("membership", membership);
		return TileDefinitions.MEMBERSHIPEDIT.toString();
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
		logger.info("membership id is" + id);
		model.addAttribute("membershipInsurance", dbMembershipInsurance);
		logger.info("Returning membershipDetailsDisplay.jsp page");
		return TileDefinitions.MEMBERSHIPDETAILSDISPLAY.toString();
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
		return TileDefinitions.MEMBERSHIPDETAILSDISPLAY.toString();
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
		logger.info("membership id is" + id);
		if (bindingResult.hasErrors()) {
			logger.info("Returning membershipDetailsDisplay");
			membershipInsurance.setActiveInd('Y');
			return TileDefinitions.MEMBERSHIPDETAILSDISPLAY.toString();
		} else {
			membershipInsurance.setUpdatedBy(username);
			MembershipInsurance dbMembershipInsurance = membershipInsuranceService.update(membershipInsurance);
			model.addAttribute("membershipInsurance", dbMembershipInsurance);
			model.addAttribute("Message", "Membership Insurance Updated Successfully");
			List<MembershipInsurance> listBean = membershipInsuranceService.findAllByMbrId(id);
			model.addAttribute("membershipDetailsList", listBean);
			return TileDefinitions.MEMBERSHIPDETAILSLIST.toString();
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
		logger.info("membership id is" + id);
		if (bindingResult.hasErrors()) {
			logger.info("Returning membershipDetailsDisplay");
			membershipInsurance.setActiveInd('Y');
			return TileDefinitions.MEMBERSHIPDETAILSDISPLAY.toString();
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
	@RequestMapping(value = { "/admin/membership/{id}/details/{mbrInsId}/save.do",
			"/user/membership/{id}/details/{mbrInsId}/save.do" }, method = RequestMethod.POST, params = { "delete" })
	public String deleteMembershipInsDetailsPage(@PathVariable Integer id, @PathVariable Integer mbrInsId,
			@ModelAttribute("membershipInsurance") @Validated MembershipInsurance membershipInsurance,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		logger.info("membership id is" + id);
		if (bindingResult.hasErrors()) {
			logger.info("Returning membershipDetailsDisplay");
			membershipInsurance.setActiveInd('Y');
			return TileDefinitions.MEMBERSHIPDETAILSDISPLAY.toString();
		} else {
			membershipInsurance.setActiveInd('N');
			membershipInsurance.setUpdatedBy(username);
			MembershipInsurance dbMembershipInsurance = membershipInsuranceService.update(membershipInsurance);
			model.addAttribute("membershipInsurance", dbMembershipInsurance);
			model.addAttribute("Message", "Membership Insurance Deleted Successfully");
			List<MembershipInsurance> listBean = membershipInsuranceService.findAllByMbrId(id);
			model.addAttribute("membershipDetailsList", listBean);
			return TileDefinitions.MEMBERSHIPDETAILSLIST.toString();

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
		return TileDefinitions.MEMBERSHIPDETAILSLIST.toString();
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
		return TileDefinitions.MEMBERSHIPPROVIDEREDIT.toString();
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
			BindingResult bindingResult, Model model) {
		logger.info("membership id is" + id);
		MembershipProvider dbMembershipProvider = membershipProviderService.findById(mbrPrvdrId);
		model.addAttribute("membershipProvider", dbMembershipProvider);
		logger.info("Returning membershipProviderEdit.jsp page");
		return TileDefinitions.MEMBERSHIPPROVIDEREDIT.toString();
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
		return TileDefinitions.MEMBERSHIPPROVIDERLIST.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/membership/{id}/complete",
			"/user/membership/{id}/complete" }, method = RequestMethod.GET)
	public String completeMembershipProviderDetailsPage(@PathVariable Integer id, Model model) {
		
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

		List<MembershipHospitalization> mbrHospitalizationList = dbMembership.getMbrHospitalizationList();
		model.addAttribute("mbrHospitalizationList", mbrHospitalizationList);

		List<MembershipInsurance> listBean = membershipInsuranceService.findAllByMbrId(id);
		model.addAttribute("membershipDetailsList", listBean);
		logger.info("Returning membershipDetailsList.jsp page");

		List<MembershipProvider> mbrPrvdrList = membershipProviderService.findAllByMbrId(id);
		model.addAttribute("membershipProviderList", mbrPrvdrList);
		logger.info("Returning membershipProviderList.jsp page");

		return TileDefinitions.MEMBERSHIPCOMPLETEDETAILS.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/membership/{id}/hedisMeasure",
			"/user/membership/{id}/hedisMeasure" }, method = RequestMethod.GET)
	public String displayMembershipHedisMeasurePage(@PathVariable Integer id, Model model) {

		Membership dbMembership = membershipService.findById(id);
		List<MembershipHedisMeasure> mbrHedisMeasureList = dbMembership.getMbrHedisMeasureList();
		model.addAttribute("mbrHedisMeasureList", mbrHedisMeasureList);
		logger.info("Returning membershipHedisMeasure.jsp page");
		return TileDefinitions.MEMBERSHIPHEDISMEASURE.toString();
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
			@RequestParam(required = true) List<Integer> hedisRuleIds) {

		Membership dbMembership = membershipService.findById(id);
		List<MembershipHedisMeasure> mbrHedisMeasureList = dbMembership.getMbrHedisMeasureList();
		List<MembershipHedisMeasure> mbrHedisMeasureListAll = new ArrayList<>();

		for (Integer hedisRuleId : hedisRuleIds) {
			List<MembershipHedisMeasure> mbrHedisMeasureList1 = mbrHedisMeasureList.stream()
					.filter(mbrHedisMeasure -> mbrHedisMeasure.getHedisMeasureRule().getId() == hedisRuleId
							&& mbrHedisMeasure.getDos() == null)
					.collect(Collectors.toList());
			mbrHedisMeasureListAll.addAll(mbrHedisMeasureList1);
		}

		return Message.successMessage(CommonMessageContent.HEDIS_RULE_LIST,
				JsonConverter.getJsonObject(mbrHedisMeasureListAll));
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Problem> populateProblemList(final Integer insId, final Integer effYear) {
		Pagination page = problemService.getPage(SystemDefaultProperties.DEFAULT_PAGE_NO,
				SystemDefaultProperties.LARGE_LIST_SIZE, null, null,
				null, insId , effYear )  ;
		return (List<Problem>) page.getList();
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

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ModelAttribute("fileTypeList")
	public List<FileType> populateFileTypeList() {
		Pagination page = fileTypeService.getPage( SystemDefaultProperties.DEFAULT_PAGE_NO,
				SystemDefaultProperties.MEDIUM_LIST_SIZE);
		return (List<FileType>) page.getList();
	}

	/**
	 * @return
	 */
	@RequestMapping(value = { "/admin/membership/membershipRoster", "/user/membership/membershipRoster" })
	public String membershipRoster() {
		return TileDefinitions.MEMBERSHIPROSTER.toString();
	}

	/**
	 * @return
	 */
	@RequestMapping(value = { "/admin/membership/membershipRoster/fileProcessing.do",
			"/user/membership/membershipRoster/fileProcessing.do" })
	public String mbrRosterFileProcessing(Model model, @ModelAttribute("username") String username,
			@RequestParam(required = true, value = "insId") Integer insId,
			@RequestParam(required = true, value = "fileType") Integer fileTypeId,
			@RequestParam(required = false, value = "activityMonth") Integer activityMonth,
			@RequestParam(required = false, value = "fileUpload") CommonsMultipartFile fileUpload,
			HttpServletRequest request) throws InvalidFormatException {

		logger.info("started file processsing for" + activityMonth);
		java.io.File sourceFile = null;
		java.io.File newSourceFile = null;
		String mbrRoster = null;

		if (fileUpload != null && !"".equals(fileUpload.getOriginalFilename())) {
			String fileName = fileUpload.getOriginalFilename();
			String newfileName = fileName.substring(0, fileName.indexOf('.'));

			try {
				FileUtils.writeByteArrayToFile(new java.io.File(FILES_UPLOAD_DIRECTORY_PATH + fileName),
						fileUpload.getBytes());

				sourceFile = new java.io.File(FILES_UPLOAD_DIRECTORY_PATH + fileName);
				newSourceFile = new java.io.File(FILES_UPLOAD_DIRECTORY_PATH + newfileName + ".csv");
				sourceFile.createNewFile();
				newSourceFile.createNewFile();
				XLSX2CSV.xls(sourceFile, newSourceFile);
				if (sourceFile.exists()) {
					sourceFile.delete();
				}

			} catch (IOException e) {
				logger.info(e.getCause().getMessage());
			}
		}
		logger.info("before file processing");

		if (newSourceFile != null)
			mbrRoster = "forward:/admin/membership/membershipRoster/list?fileName=" + newSourceFile.getName()
					+ "&insId=" + insId + "&fileTypeId=" + fileTypeId + "&activityMonth=" + activityMonth;
		return mbrRoster;
	}

	/**
	 * @return
	 */
	@RequestMapping(value = { "/admin/membership/membershipCapReport", "/user/membership/membershipCapReport" })
	public String membershipCapReport() {
		return TileDefinitions.MEMBERSHIPCAPREPORT.toString();
	}

	/**
	 * @return
	 */
	@RequestMapping(value = { "/admin/membership/membershipCapReport/fileProcessing.do",
			"/user/membership/membershipCapReport/fileProcessing.do" })
	public String mbrCapReportFileProcessing(Model model, @ModelAttribute("username") String username,
			@RequestParam(required = true, value = "insId") Integer insId,
			@RequestParam(required = true, value = "fileType") Integer fileTypeId,
			@RequestParam(required = false, value = "activityMonth") Integer activityMonth,
			@RequestParam(required = false, value = "fileUpload") CommonsMultipartFile fileUpload,
			HttpServletRequest request) throws InvalidFormatException {

		logger.info("started file processsing for" + activityMonth);
		java.io.File sourceFile = null;
		java.io.File newSourceFile = null;
		String mbrRoster = null;

		if (fileUpload != null && !"".equals(fileUpload.getOriginalFilename())) {
			String fileName = fileUpload.getOriginalFilename();
			String newfileName = fileName.substring(0, fileName.indexOf('.'));

			try {
				FileUtils.writeByteArrayToFile(new java.io.File(FILES_UPLOAD_DIRECTORY_PATH + fileName),
						fileUpload.getBytes());

				sourceFile = new java.io.File(FILES_UPLOAD_DIRECTORY_PATH + fileName);
				newSourceFile = new java.io.File(FILES_UPLOAD_DIRECTORY_PATH + newfileName + ".csv");
				sourceFile.createNewFile();
				newSourceFile.createNewFile();
				XLSX2CSV.xls(sourceFile, newSourceFile);
				if (sourceFile.exists()) {
					sourceFile.delete();
				}

			} catch (IOException e) {
				logger.info(e.getCause().getMessage());
			}
		}
		logger.info("before file processing");

		if (newSourceFile != null)
			mbrRoster = "forward:/admin/membership/membershipCapReport/list?fileName=" + newSourceFile.getName()
					+ "&insId=" + insId + "&fileTypeId=" + fileTypeId + "&activityMonth=" + activityMonth;
		return mbrRoster;
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
	@RequestMapping(value = { "/admin/membership/membershipActivityMonth/list",
			"/user/membership/membershipActivityMonth/list" }, method = RequestMethod.GET)
	public Message viewMembershipActivityMonthList(@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sSearch,
			@RequestParam(required = true) Integer sSearchIns, @RequestParam(required = true) Integer sSearchPrvdr,
			@RequestParam(required = true) Integer sSearchActivityYear,
			@RequestParam(required = true) List<Integer> ruleIds, @RequestParam(required = false) String sort,
			@RequestParam(required = false) String sortdir) {

		Pagination pagination = membershipService.getMembershipActivityMonthPage(pageNo, pageSize, sSearch, sSearchIns,
				sSearchPrvdr, sSearchActivityYear, ruleIds, sort, sortdir);
		;
		return Message.successMessage(CommonMessageContent.MEMBERSHIP_ACTIVITY_LIST,
				JsonConverter.getJsonObject(pagination));
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/membershipProblemList/{mbrId}", "/user/membershipProblemList/{mbrId}" })
	public String membershipProblemList(@PathVariable Integer mbrId, Model model) {
		
		Membership dbMembership = membershipService.findById(mbrId);
		List<MembershipProblem> mbrProblemList = dbMembership.getMbrProblemList();
		
		model.addAttribute("mbrProblemList", mbrProblemList);
		logger.info("Returning membershipProblemList.jsp page");
		return TileDefinitions.MEMBRESHIPPROBLEMLIST.toString();
	}

	@RequestMapping(value = { "/admin/membershipProblem/{mbrId}/new",
			"/user/membershipProblem/{mbrId}/new" }, method = RequestMethod.GET)
	public String addMembershipProblemPage(@PathVariable Integer mbrId, Model model,
			@ModelAttribute("username") String username) {

		MembershipInsurance mbrInsurance = membershipInsuranceService.findByMbrId(mbrId);
		final Integer insId = mbrInsurance.getInsId().getId();
		 
		User user =  userService.findByUserName(username);
		final Integer effYear = user.getEffectiveYear();
		List<Problem> pbmList = populateProblemList(insId,effYear );
		MembershipProblem membershipProblem = createMembershipProblelmModel();
		model.addAttribute("membershipProblem", membershipProblem);
		model.addAttribute("problemList", pbmList);
		return TileDefinitions.MEMBRESHIPPROBLEMNEW.toString();
	}

	@RequestMapping(value = { "/admin/membershipProblem/{mbrPbmId}",
			"/user/membershipProblem/{mbrPbmId}" }, method = RequestMethod.GET)
	public String viewMembershipProblemPage(@PathVariable Integer mbrPbmId, Model model,
			@ModelAttribute("username") String username) {

		MembershipProblem membershipProblem = membershipProblemService.findById(mbrPbmId);
		model.addAttribute("membershipProblem", membershipProblem);
		
		final Integer mbrId = membershipProblem.getMbr().getId();
		MembershipInsurance mbrInsurance = membershipInsuranceService.findByMbrId(mbrId);
		final Integer insId = mbrInsurance.getInsId().getId();
		 
		User user =  userService.findByUserName(username);
		final Integer effYear = user.getEffectiveYear();
		List<Problem> pbmList = populateProblemList(insId,effYear );
		
		
		model.addAttribute("problemList", pbmList);
		return TileDefinitions.MEMBRESHIPPROBLEMEDIT.toString();
	}

	@RequestMapping(value = "/admin/membershipProblem/{mbrId}/save.do", method = RequestMethod.POST, params = { "add" })
	public String newMembershipProblemAction(@PathVariable Integer mbrId,
			@Validated MembershipProblem membershipProblem, BindingResult bindingResult, Model model,
			@ModelAttribute("username") String username) {
		
		 final Integer mbrProblemIdCount = membershipProblemService.findByMbrIdAndPbmId(membershipProblem.getMbr(), membershipProblem.getPbm(), 0);
		 if(mbrProblemIdCount > 0){
			FieldError pbmError = new FieldError("pbm", "pbm", "Similar active problem already exists");
			bindingResult.addError(pbmError);
		}
		 
		if (bindingResult.hasErrors()) {
			MembershipInsurance mbrInsurance = membershipInsuranceService.findByMbrId(mbrId);
			final Integer insId = mbrInsurance.getInsId().getId();
			 
			User user =  userService.findByUserName(username);
			final Integer effYear = user.getEffectiveYear();
			List<Problem> pbmList = populateProblemList(insId,effYear );
			model.addAttribute("problemList", pbmList);
			logger.info("Returning membership problem new page");
			return TileDefinitions.MEMBRESHIPPROBLEMNEW.toString();
		}
		membershipProblem.setCreatedBy(username);
		membershipProblem.setUpdatedBy(username);
		membershipProblemService.save(membershipProblem);
		logger.info("Returning membership problem add page");
		return TileDefinitions.MEMBRESHIPPROBLEMLIST.toString();
			
	}

	@RequestMapping(value = "/admin/membershipProblem/{mbrId}/save.do", method = RequestMethod.POST, params = {
			"update" })
	public String modifyMembershipProblemAction(@PathVariable Integer mbrId,
			@Validated MembershipProblem membershipProblem, BindingResult bindingResult, Model model,
			@ModelAttribute("username") String username) {
		
		 final Integer mbrProblemIdCount = membershipProblemService.findByMbrIdAndPbmId(membershipProblem.getMbr(), membershipProblem.getPbm(), membershipProblem.getId());
		 
		 if(mbrProblemIdCount > 0){
			FieldError pbmError = new FieldError("pbm", "pbm", "Similar active problem already exists");
			bindingResult.addError(pbmError);
		 }
		 
		if (bindingResult.hasErrors()) {
			MembershipInsurance mbrInsurance = membershipInsuranceService.findByMbrId(mbrId);
			final Integer insId = mbrInsurance.getInsId().getId();
			 
			User user =  userService.findByUserName(username);
			final Integer effYear = user.getEffectiveYear();
			List<Problem> pbmList = populateProblemList(insId,effYear );
			model.addAttribute("problemList", pbmList);
			membershipProblem.setActiveInd('Y');
			logger.info("Returning membership problem edit page");
			return TileDefinitions.MEMBRESHIPPROBLEMEDIT.toString();
		}
		else
		{
			membershipProblem.setCreatedBy(username);
			membershipProblem.setUpdatedBy(username);
			membershipProblem.setActiveInd('Y');
			membershipProblemService.update(membershipProblem);
			logger.info("Returning membership problem update page");
			return TileDefinitions.MEMBRESHIPPROBLEMLIST.toString();
		}	
	}

	@RequestMapping(value = "/admin/membershipProblem/{mbrId}/save.do", method = RequestMethod.POST, params = {
			"delete" })
	public String deleteMembershipProblemAction(@PathVariable Integer mbrId,
			@Validated MembershipProblem membershipProblem, BindingResult bindingResult, Model model,
			@ModelAttribute("username") String username) {
		
		 final Integer mbrProblemIdCount = membershipProblemService.findByMbrIdAndPbmId(membershipProblem.getMbr(), membershipProblem.getPbm(), membershipProblem.getId());
		 
		 if(mbrProblemIdCount > 0){
			FieldError pbmError = new FieldError("pbm", "pbm", "Similar active problem already exists");
			bindingResult.addError(pbmError);
		}
		 
		if (bindingResult.hasErrors()) {
			MembershipInsurance mbrInsurance = membershipInsuranceService.findByMbrId(mbrId);
			final Integer insId = mbrInsurance.getInsId().getId();
			 
			User user =  userService.findByUserName(username);
			final Integer effYear = user.getEffectiveYear();
			List<Problem> pbmList = populateProblemList(insId,effYear );
			membershipProblem.setActiveInd('Y');
			model.addAttribute("problemList", pbmList);
			logger.info("Returning membership problem edit page");
			return TileDefinitions.MEMBRESHIPPROBLEMEDIT.toString();
		}
		else
		{
			membershipProblem.setCreatedBy(username);
			membershipProblem.setUpdatedBy(username);
			membershipProblem.setActiveInd('N');
			membershipProblemService.update(membershipProblem);
			logger.info("Returning membership problem delete page");
			return TileDefinitions.MEMBRESHIPPROBLEMLIST.toString();
		}	
	}

	@ModelAttribute("membershipProblem")
	public MembershipProblem createMembershipProblelmModel() {
		return new MembershipProblem();
	}
}
