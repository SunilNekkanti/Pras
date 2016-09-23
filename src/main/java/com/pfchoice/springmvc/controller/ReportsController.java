package com.pfchoice.springmvc.controller;

import static com.pfchoice.common.SystemDefaultProperties.FILE_TYPE_AMG_MBR_HOSPITALIZATION;
import static com.pfchoice.common.SystemDefaultProperties.FILES_UPLOAD_DIRECTORY_PATH;
import static com.pfchoice.common.SystemDefaultProperties.FOLLOWUP_TYPE_HEDIS;
import static com.pfchoice.common.SystemDefaultProperties.FOLLOWUP_TYPE_HOSPITALIZATION;
import static com.pfchoice.common.SystemDefaultProperties.FOLLOWUP_TYPE_CLAIM;
import static com.pfchoice.common.SystemDefaultProperties.CLAIM;
import static com.pfchoice.common.SystemDefaultProperties.HOSPITALIZATION;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.pfchoice.common.CommonMessageContent;
import com.pfchoice.common.SystemDefaultProperties;
import com.pfchoice.common.util.JsonConverter;
import com.pfchoice.common.util.PrasUtil;
import com.pfchoice.common.util.TileDefinitions;
import com.pfchoice.common.util.XLSX2CSV;
import com.pfchoice.core.entity.File;
import com.pfchoice.core.entity.FileType;
import com.pfchoice.core.entity.FollowupType;
import com.pfchoice.core.entity.Insurance;
import com.pfchoice.core.entity.MembershipFollowup;
import com.pfchoice.core.entity.MembershipHedisMeasure;
import com.pfchoice.core.service.AttPhysicianService;
import com.pfchoice.core.service.BillTypeService;
import com.pfchoice.core.service.FacilityTypeService;
import com.pfchoice.core.service.FileService;
import com.pfchoice.core.service.FileTypeService;
import com.pfchoice.core.service.FollowupTypeService;
import com.pfchoice.core.service.HospitalService;
import com.pfchoice.core.service.InsuranceService;
import com.pfchoice.core.service.MedicalLossRatioService;
import com.pfchoice.core.service.MembershipClaimDetailsService;
import com.pfchoice.core.service.MembershipClaimService;
import com.pfchoice.core.service.MembershipFollowupService;
import com.pfchoice.core.service.MembershipHedisMeasureService;
import com.pfchoice.core.service.MembershipHospitalizationDetailsService;
import com.pfchoice.core.service.MembershipHospitalizationService;
import com.pfchoice.core.service.MembershipProblemService;
import com.pfchoice.core.service.MembershipService;
import com.pfchoice.core.service.PharmacyService;
import com.pfchoice.core.service.UnprocessedClaimService;
import com.pfchoice.springmvc.controller.service.DBConnection;

import ml.rugal.sshcommon.page.Pagination;
import ml.rugal.sshcommon.springmvc.util.Message;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * A Membership controller class for GET/DELETE/POST/PUT.
 *
 * @author sarath
 */
@Controller
@SessionAttributes({ "username", "userpath" })
public class ReportsController {

	private static final Logger LOG = LoggerFactory.getLogger(ReportsController.class.getName());

	@Autowired
	private AttPhysicianService attPhysicianService;

	@Autowired
	private FacilityTypeService facilityTypeService;

	@Autowired
	private BillTypeService billTypeService;

	@Autowired
	private FileService fileService;

	@Autowired
	private FileTypeService fileTypeService;
	
	@Autowired
	private FollowupTypeService followupTypeService;

	@Autowired
	private HospitalService hospitalService;

	@Autowired
	private InsuranceService insuranceService;

	@Autowired
	private MembershipProblemService mbrProblemService;

	@Autowired
	private MembershipService membershipService;

	@Autowired
	private MembershipClaimDetailsService mbrClaimDetailsService;

	@Autowired
	private MembershipClaimService mbrClaimService;

	@Autowired
	private MembershipFollowupService mbrHedisFollowupService;

	@Autowired
	private MembershipHedisMeasureService mbrHedisMeasureService;

	@Autowired
	private MembershipHospitalizationService mbrHospitalizationService;

	@Autowired
	private MembershipHospitalizationDetailsService mbrHospitalizationDetailsService;

	@Autowired
	private UnprocessedClaimService unprocessedClaimService;

	@Autowired
	private DBConnection dBConnection;
	
	@Autowired
	private PharmacyService pharmacyService;
	
	@Autowired
	private MedicalLossRatioService mlrService;

	/**
	 * @param binder
	 */
	@InitBinder("contract")
	public void initBinder(final WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}

	/**
	 * @return
	 */
	@RequestMapping(value = { "/admin/reports/hedis", "/user/reports/hedis" })
	public String handleRequest() {

		return TileDefinitions.HEDISMEMBERSHIPLIST.toString();
	}

	/**
	 * @param pageNo
	 * @param pageSize
	 * @param sSearch
	 * @param sSearchIns
	 * @param sSearchPrvdr
	 * @param sSearchHedisRule
	 * @param ruleIds
	 * @param sort
	 * @param sortdir
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/admin/reports/hedisMembership/list",
			"/user/reports/hedisMembership/list" }, method = RequestMethod.GET)
	public Message viewMembershipList(@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sSearch,
			@RequestParam(required = true) Integer sSearchIns, @RequestParam(required = true) Integer sSearchPrvdr,
			@RequestParam(required = true) Integer sSearchHedisRule,
			@RequestParam(required = true) List<Integer> ruleIds, @RequestParam(required = false) String sort,
			@RequestParam(required = false) String sortdir) {
   
		assert  pageNo != null  ;
		Pagination pagination = membershipService.getPage(pageNo, pageSize, sSearch, sSearchIns, sSearchPrvdr,
				sSearchHedisRule, ruleIds, sort, sortdir);

		return Message.successMessage(CommonMessageContent.MEMBERSHIP_LIST, JsonConverter.getJsonObject(pagination));
	}

	/**
	 * @param mbrHedisFollowup
	 * @param username
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/admin/reports/membershipHedis/followup",
			"/user/reports/membershipHedis/followup" }, method = RequestMethod.POST)
	public String addMembershipHedisFollowup(@RequestBody final MembershipFollowup mbrHedisFollowup,
			@ModelAttribute("username") String username) {

		List<Map<Integer, String>> mbrHedisMeasureList = mbrHedisFollowup.getMbrHedisMeasureIds();
		if (mbrHedisMeasureList != null) {
			mbrHedisMeasureList.forEach(ruleMap -> {
				for (Map.Entry<Integer, String> entry : ruleMap.entrySet()) {
					MembershipHedisMeasure dbMembershipHedisMeasure = mbrHedisMeasureService.findById(entry.getKey());
					SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
					String dateOfService = entry.getValue();
					try {
						dbMembershipHedisMeasure.setDos(dateFormat.parse(dateOfService));
						dbMembershipHedisMeasure.setActiveInd(new Character('N'));
					} catch (Exception e) {
						LOG.warn(e.getCause().getMessage());
					}
					mbrHedisMeasureService.update(dbMembershipHedisMeasure);
				}
			});
		}

		FollowupType followupType = followupTypeService.findByCode(FOLLOWUP_TYPE_HEDIS);
		mbrHedisFollowup.setFollowupType(followupType);
		mbrHedisFollowup.setCreatedBy(username);
		mbrHedisFollowup.setUpdatedBy(username);
		mbrHedisFollowupService.save(mbrHedisFollowup);

		return TileDefinitions.MEMBERSHIPCONTACTEDITSUCCESS.toString();
	}

	/**
	 * @param mbrHospitalizationFollowup
	 * @param username
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/admin/reports/membershipHospitalization/followup",
			"/user/reports/membershipHospitalization/followup" }, method = RequestMethod.POST)
	public String addMembershipHospitalizationFollowup(@RequestBody final MembershipFollowup mbrHospitalizationFollowup,
			@ModelAttribute("username") String username) {

		FollowupType followupType = followupTypeService.findByCode("HOSPITALIZATION_FOLLOWUP");
		mbrHospitalizationFollowup.setFollowupType(followupType);
		// mbrHospitalizationFollowup.setDateOfContact(new Date());
		mbrHospitalizationFollowup.setCreatedBy(username);
		mbrHospitalizationFollowup.setUpdatedBy(username);
		mbrHedisFollowupService.save(mbrHospitalizationFollowup);

		return TileDefinitions.MEMBERSHIPCONTACTEDITSUCCESS.toString();
	}

	/**
	 * @param mbrId
	 * @param username
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/admin/reports/membershipHedis/{mbrId}/followupDetails",
			"/user/reports/membershipHedis/{mbrId}/followupDetails" })
	public Message membershipFollowupDetails(@PathVariable Integer mbrId, Model model) {
		List<MembershipFollowup> dbMbrHedisFollowup = mbrHedisFollowupService.findAllByMbrId(mbrId,
				FOLLOWUP_TYPE_HEDIS);
		return Message.successMessage(CommonMessageContent.HEDIS_FOLLOWUP_LIST,
				JsonConverter.getJsonObject(dbMbrHedisFollowup));
	}

	/**
	 * @param mbrId
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/admin/reports/membershipHospitalization/{mbrId}/followupDetails",
			"/user/reports/membershipmembershipHospitalization/{mbrId}/followupDetails" })
	public Message membershipHospitalizationFollowupDetails(@PathVariable Integer mbrId, Model model) {
		List<MembershipFollowup> dbMbrHospitalizationFollowup = mbrHedisFollowupService.findAllByMbrId(mbrId,
				FOLLOWUP_TYPE_HOSPITALIZATION);
		return Message.successMessage(CommonMessageContent.HOSPITALIZATION_FOLLOWUP_LIST,
				JsonConverter.getJsonObject(dbMbrHospitalizationFollowup));
	}

	/**
	 * @return
	 */
	@RequestMapping(value = { "/admin/reports/hospitalization", "/user/reports/hospitalization" })
	public String handleHospitalizationRequest() {

		return "membershipHospitalizationList";
	}

	/**
	 * @return
	 */
	@RequestMapping(value = { "/admin/reports/hospitalization/fileProcessing.do",
			"/user/reports/hospitalization/fileProcessing.do", "/admin/reports/claim/fileProcessing.do",
			"/user/reports/claim/fileProcessing.do" })
	public String mbrHospitalizationFileProcessing(Model model, @ModelAttribute("username") String username,
			@RequestParam(required = false, value = "insId") Integer insId,
			@RequestParam(required = false, value = "fileUpload") CommonsMultipartFile fileUpload,
			@RequestParam(required = false, value = "claimOrHospital") Integer claimOrHospital,
			@RequestParam(required = false, value = "fileType") Integer fileTypeCode,
			@RequestParam(required = false, value = "activityMonth") Integer activityMonth,
			@RequestParam(required = false, value = "pharmacyClaim") Integer pharmacyClaim,
			HttpServletRequest request) throws InvalidFormatException{
		LOG.info("started file processsing");
		java.io.File sourceFile, newSourceFile = null;
		if (fileUpload != null && !"".equals(fileUpload.getOriginalFilename())) {
			String fileName = fileUpload.getOriginalFilename();
			String newfileName = fileName.substring(0, fileName.indexOf('.'));

			try {
				String ext = FilenameUtils.getExtension(fileName);
				
				FileUtils.writeByteArrayToFile(new java.io.File(FILES_UPLOAD_DIRECTORY_PATH + fileName),
						fileUpload.getBytes());

				sourceFile = new java.io.File(FILES_UPLOAD_DIRECTORY_PATH + fileName);
				sourceFile.createNewFile();
				if(!"csv".equals(ext)){
					newSourceFile = new java.io.File(FILES_UPLOAD_DIRECTORY_PATH + newfileName + ".csv");
					newSourceFile.createNewFile();
					XLSX2CSV.xls(sourceFile, newSourceFile);
					if (sourceFile.exists()) {
						sourceFile.delete();
					}
				}	
				else{
					newSourceFile = sourceFile;
				}

			} catch (IOException e) {
				LOG.warn(e.getCause().getMessage());
			}
		}
		if(newSourceFile == null)
			return null;
		LOG.info("before file processing");
		String forwardToClaimOrHospital = null;
		
		if(pharmacyClaim != null && pharmacyClaim == 1){
			forwardToClaimOrHospital = "forward:/admin/membership/membershipPharmacyClaim/list?fileName=" + newSourceFile.getName()
			+ "&insId=" + insId + "&fileTypeCode=" + fileTypeCode + "&activityMonth=" + activityMonth;
		}
		else if(activityMonth != null)
		{
			forwardToClaimOrHospital = "forward:/admin/membership/membershipRoster/list?fileName=" + newSourceFile.getName()
			+ "&insId=" + insId + "&fileTypeId=" + fileTypeCode + "&activityMonth=" + activityMonth;
		}
		else{	
			if (claimOrHospital == HOSPITALIZATION) {
				LOG.info("forwarding to hospitalization");
				forwardToClaimOrHospital = "forward:/admin/membershipHospitalization/list?fileName="
						+ newSourceFile.getName();
			} else if (claimOrHospital == CLAIM) {
				LOG.info("forwarding to claims");
	
				forwardToClaimOrHospital = "forward:/admin/membershipClaim/list?fileName=" + newSourceFile.getName()
						+ "&insId=" + insId+"&fileTypeCode="+fileTypeCode;
			}
		}
		return forwardToClaimOrHospital;
	}
	
	/**
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/admin/membership/membershipRoster/list", "/user/membership/membershipRoster/list" })
	public Message viewmembershipRosterList(@ModelAttribute("username") String username,
			@RequestParam(required = true, value = "insId") Integer insId,
			@RequestParam(required = true, value = "fileTypeId") Integer fileTypeId,
			@RequestParam(required = true, value = "activityMonth") Integer activityMonth,
			@RequestParam(value = "fileName", required = true) String fileName) {

		FileType fileType = fileTypeService.findById(fileTypeId);
		String mbrRoster = fileType.getDescription();
		Boolean dataExists = membershipService.isDataExists(mbrRoster);
		if (dataExists) {
			LOG.info("Previous file processing is incomplete ");
			return Message.failMessage("Previous file processing is incomplete");
		} else {
			Integer fileId = 0;
			try {
				File fileRecord = new File();
				fileRecord.setFileName(fileName);
				fileRecord.setFileTypeCode(fileType.getCode());
				fileRecord.setCreatedBy(username);
				fileRecord.setUpdatedBy(username);
				File newFile = fileService.save(fileRecord);

				if (newFile != null)
					fileId = newFile.getId();
				else
					LOG.info("fileId is empty");

			} catch (Exception e) {
				LOG.warn(e.getCause().getMessage());
				LOG.info("Similar file already processed in past");
				return Message.failMessage("similar file already processed in past");
			}

			LOG.info("Loading  membershipRoster data");
			Integer loadedData = membershipService.loadDataCSV2Table(fileName, mbrRoster);

			if (loadedData < 1) {
				return Message.failMessage("ZERO records to process");
			}

			LOG.info("processing  membershipRoster data" + new Date());

			Integer membershipLoadedData = membershipService.loadData(insId, fileId, activityMonth, mbrRoster);
			LOG.info("membershipLoadedData " + membershipLoadedData);
			Integer membershipUnloadedData = membershipService.unloadCSV2Table(mbrRoster);
			LOG.info("membershipUnloadedData " + membershipUnloadedData);

			LOG.info("processed  membership roster data" + new Date());

			return Message.successMessage(CommonMessageContent.MEMBERSHIP_LIST, membershipLoadedData);
		}
	}

	

	/**
	 * @param pageNo
	 * @param pageSize
	 * @param sSearch
	 * @param sSearchIns
	 * @param sSearchPrvdr
	 * @param sSearchHedisRule
	 * @param ruleIds
	 * @param sort
	 * @param sortdir
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/admin/reports/membershipHospitalization/list",
			"/user/reports/membershipHospitalization/list" }, method = RequestMethod.GET)
	public Message viewHospitalizationMembershipList(@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sSearch,
			@RequestParam(required = true) Integer sSearchIns, @RequestParam(required = true) Integer sSearchPrvdr,
			@RequestParam(required = false) String sort, @RequestParam(required = false) String sortdir,
			@RequestParam(required = true) Date processFrom, @RequestParam(required = true) Date processTo,
			@RequestParam(required = true) Integer processHospitalization) {

		Pagination pagination = membershipService.getPage(pageNo, pageSize, sSearch, sSearchIns, sSearchPrvdr, sort,
				sortdir, processFrom, processTo, processHospitalization);

		return Message.successMessage(CommonMessageContent.HOSPITALIZATION_FOLLOWUP_LIST,
				JsonConverter.getJsonObject(pagination));
	}
	
	/**
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/admin/membershipHospitalization/list", "/user/membershipHospitalization/list" })
	public Message viewmembershipHospitalizationList(@ModelAttribute("username") String username,
			@RequestParam(value = "fileName", required = true) String fileName) {

		LOG.info("1");

		Boolean dataExists = mbrHospitalizationService.isDataExists();
		if (dataExists) {
			return Message.failMessage("Previous file processing is incomplete");
		} else {

			LOG.info("2");

			Integer fileId = 0;

			try {
				LOG.info("3");

				FileType fileType = fileTypeService.findByCode(FILE_TYPE_AMG_MBR_HOSPITALIZATION);
				LOG.info("4");

				File fileRecord = new File();
				fileRecord.setFileName(fileName);
				fileRecord.setFileTypeCode(fileType.getCode());
				fileRecord.setCreatedBy(username);
				fileRecord.setUpdatedBy(username);
				File newFile = fileService.save(fileRecord);
				LOG.info("5");

				if (newFile != null)
					fileId = newFile.getId();
				else
					LOG.info("fileId is empty");

			} catch (Exception e) {
				LOG.info(e.getCause().getMessage());
				LOG.info("Similar file already processed in past");
				return Message.failMessage("similar file already processed in past");
			}

			LOG.info("Loading  membershipHospitalization data");
			Integer loadedData = mbrHospitalizationService.loadDataCSV2Table(fileName);

			if (loadedData < 1) {
				return Message.failMessage("ZERO records to process");
			}

			LOG.info("processing  membershipHospitalization data");
			Integer hosLoadedData = hospitalService.loadData(fileId);
			Integer attPhysicianLoadedData = attPhysicianService.loadData(fileId);
			Integer mbrHospitalizationUpdatedData = mbrHospitalizationService.updateData(fileId);
			Integer mbrHospitalizationLoadedData = mbrHospitalizationService.loadData(fileId);
			Integer mbrHospitalizationDetailsLoadedData = mbrHospitalizationDetailsService.loadData(fileId);
			LOG.info("hosLoadedData " + hosLoadedData);
			LOG.info("attPhysicianLoadedData " + attPhysicianLoadedData);
			LOG.info("mbrHospitalizationUpdatedData " + mbrHospitalizationUpdatedData);
			LOG.info("mbrHospitalizationLoadedData " + mbrHospitalizationLoadedData);
			LOG.info("mbrHospitalizationDetailsLoadedData " + mbrHospitalizationDetailsLoadedData);
			Integer mbrHospitalizationUnloadedData = mbrHospitalizationService.unloadCSV2Table();
			LOG.info("mbrHospitalizationUnloadedData " + mbrHospitalizationUnloadedData);
			LOG.info("processed  membershipHospitalization data");

			LOG.info("returning membershipList");
			return Message.successMessage(CommonMessageContent.HOSPITALIZATION_FOLLOWUP_LIST, loadedData);
		}
	}

	/**
	 * @return
	 */

	@ResponseBody
	@RequestMapping(value = { "/admin/reports/membershipHospitalizationDetails/{mbrHosId}/list",
			"/user/reports/membershipHospitalizationDetails/{mbrHosId}/list" })
	public Message viewHospitalizationMembershipDetailsList(@PathVariable Integer mbrHosId) {
		Pagination pagination = mbrHospitalizationDetailsService.getMbrHospitalizationDetailsPage(mbrHosId);
		return Message.successMessage(CommonMessageContent.HOSPITALIZATION_FOLLOWUP_LIST,
				JsonConverter.getJsonObject(pagination));
	}

	/**
	 * @return
	 */

	@RequestMapping(value = { "/admin/reports/hospitalization/{mbrHosId}", "/user/reports/hospitalization/{mbrHosId}" })
	public String handleHospitalizationRequest(@PathVariable Integer mbrHosId, Model model) {
		model.addAttribute("mbrHosId", mbrHosId);
		return "membershipHospitalizationDetails";
	}

	/**
	 * @return
	 */
	@RequestMapping(value = { "/admin/reports/claim", "/user/reports/claim" })
	public String handleClaimRequest() {
		return TileDefinitions.MEMBERSHIPCLIAMLIST.toString();
	}

	/**
	 * @param pageNo
	 * @param pageSize
	 * @param sSearch
	 * @param sSearchIns
	 * @param sSearchPrvdr
	 * @param sSearchHedisRule
	 * @param ruleIds
	 * @param sort
	 * @param sortdir
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/admin/reports/membershipClaim/list",
			"/user/reports/membershipClaim/list" }, method = RequestMethod.GET)
	public Message viewMembershipClaimList(@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sSearch,
			@RequestParam(required = true) Integer sSearchIns, @RequestParam(required = true) Integer sSearchPrvdr,
			@RequestParam(required = false) String sort, @RequestParam(required = false) String sortdir,
			@RequestParam(required = true) Date processFrom, @RequestParam(required = true) Date processTo,
			@RequestParam(required = true) Integer processClaim) {

		Pagination pagination = mbrClaimService.getClaimPage(pageNo, pageSize, sSearch, sSearchIns, sSearchPrvdr, sort,
				sortdir, processFrom, processTo, processClaim);

		return Message.successMessage(CommonMessageContent.MEMBERSHIP_CLAIMS_LIST,
				JsonConverter.getJsonObject(pagination));
	}

	/**
	 * @return
	 */
	@RequestMapping(value = { "/admin/reports/mamClaim", "/user/reports/mamClaim" })
	public String handleMembershipClaimDetailsRequest() {
		return TileDefinitions.MEMBERSHIPMAMCLAIMDETAILLIST.toString();
	}

	/**
	 * @param pageNo
	 * @param pageSize
	 * @param sSearch
	 * @param sSearchIns
	 * @param sSearchPrvdr
	 * @param sSearchHedisRule
	 * @param ruleIds
	 * @param sort
	 * @param sortdir
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/admin/reports/membershipMamClaim/list",
			"/user/reports/membershipMamClaim/list" }, method = RequestMethod.GET)
	public Message viewMembershipClaimDetailList(@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sSearch,
			@RequestParam(required = true) Integer sSearchIns, @RequestParam(required = true) Integer sSearchPrvdr,
			@RequestParam(required = false) String sort, @RequestParam(required = false) String sortdir,
			@RequestParam(required = true) List<Integer> monthPicker,
			@RequestParam(required = true) Integer processClaim) {

		Pagination pagination = mbrClaimDetailsService.getMbrClaimDetailsByActivityMonth(pageNo, pageSize, sSearch,
				sSearchIns, sSearchPrvdr, sort, sortdir, monthPicker, processClaim);

		return Message.successMessage(CommonMessageContent.MEMBERSHIP_MAM_CLAIM_DETAIL_LIST,
				JsonConverter.getJsonObject(pagination));
	}

	/**
	 * @param mbrId
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/admin/reports/membershipClaim/{mbrId}/followupDetails",
			"/user/reports/membershipmembershipHospitalization/{mbrId}/followupDetails" })
	public Message membershipClaimFollowupDetails(@PathVariable Integer mbrId, Model model) {
		List<MembershipFollowup> dbMbrHospitalizationFollowup = mbrHedisFollowupService.findAllByMbrId(mbrId,
				FOLLOWUP_TYPE_CLAIM);
		return Message.successMessage(CommonMessageContent.HOSPITALIZATION_FOLLOWUP_LIST,
				JsonConverter.getJsonObject(dbMbrHospitalizationFollowup));
	}

	/**
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/admin/membershipClaim/list", "/user/membershipClaim/list" })
	public Message viewMembershipClaimList(@ModelAttribute("username") String username,
			@RequestParam(required = true, value = "insId") Integer insId,
			@RequestParam(value = "fileName", required = true) String fileName,
			@RequestParam(value = "fileTypeCode", required = true) Integer fileTypeCode) {

		LOG.info("verifying fileTypecode");
		FileType fileType = fileTypeService.findById(fileTypeCode) ;
		
		if(fileType.getTablesName() == null || "".equals(fileType.getTablesName()) )
			return Message.failMessage("File Type configuration is inproper");
		
		String tableName = fileType.getTablesName();
		Boolean dataExists = mbrClaimService.isDataExists(tableName);
		LOG.info("verifying if processing table contains previous data or not ");
		if (dataExists) {
			return Message.failMessage("Previous file processing is incomplete");
		} else {
			Integer fileId = 0;
			try {
				File fileRecord = new File();
				fileRecord.setFileName(fileName);
				fileRecord.setFileTypeCode(fileType.getCode());
				fileRecord.setCreatedBy(username);
				fileRecord.setUpdatedBy(username);
				File newFile = fileService.save(fileRecord);
				LOG.info("5");

				if (newFile != null)
					fileId = newFile.getId();
				else
					LOG.info("fileId is empty");

			} catch (Exception e) {
				LOG.info(e.getCause().getMessage());
				LOG.info("Similar file already processed in past");
				return Message.failMessage("similar file already processed in past");
			}
			LOG.info("Loading  membershipClaim data" + new Date());
			String insuranceCode  = fileType.getInsuranceCode();
			Integer loadedData = mbrClaimService.loadDataCSV2Table(fileName, insuranceCode, tableName);

			if (loadedData < 1) {
				return Message.failMessage("ZERO records to process");
			}

			LOG.info("processing  membershipClaim data" + new Date());
			
			Integer facilityTypeLoadedData = facilityTypeService.loadData(fileId, insuranceCode);
			LOG.info("facilityTypeLoadedData " + facilityTypeLoadedData + new Date());
			Integer billTypeLoadedData = 0;
			if (insId == 1) {
				billTypeLoadedData = billTypeService.loadData(fileId, insuranceCode);
			}
			LOG.info("billTypeLoadedData " + billTypeLoadedData + new Date());
			Integer mbrClaimLoadedData = mbrClaimService.loadData(fileId, insId, insuranceCode);
			LOG.info("membershipClaimLoadedData " + mbrClaimLoadedData + new Date());
			Integer mbrClaimDetailsLoadedData = mbrClaimDetailsService.loadData(fileId, insId);
			LOG.info("membershipClaimDetailsLoadedData " + mbrClaimDetailsLoadedData + new Date());
			Integer mbrProblemLoadedData = mbrProblemService.loadData(fileId, insId, insuranceCode);
			LOG.info("mbrProblemLoadedData " + mbrProblemLoadedData + new Date());
		//	Integer mbrHedisUnLoadedData = mbrHedisMeasureService.unloadTable(insId, insuranceCode);
		//	LOG.info("mbrHedisUnLoadedData " + mbrHedisUnLoadedData + new Date());
			Integer mbrHedisLoadedData = mbrHedisMeasureService.loadData(fileId, insId, insuranceCode);
			LOG.info("mbrHedisLoadedData " + mbrHedisLoadedData + new Date());
			Integer unprocessedClaimLoadedData = unprocessedClaimService.loadDataCSV2Table( fileId,  insuranceCode,  tableName);
			LOG.info("unprocessedClaimLoadedData " + unprocessedClaimLoadedData + new Date());
			Integer mbrClaimUnloadedData = mbrClaimService.unloadCSV2Table(tableName);
			LOG.info("membershipClaimUnloadedData " + mbrClaimUnloadedData + new Date());

			LOG.info("processed  membershipClaim data");

			LOG.info("returning membershipClaimList");
			return Message.successMessage(CommonMessageContent.MEMBERSHIP_CLAIMS_LIST, loadedData);
		}
	}
	
	/**
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/admin/membership/membershipPharmacyClaim/list", "/user/membership/membershipPharmacyClaim/list" })
	public Message viewMembershipPharmacyClaimList(@ModelAttribute("username") String username,
			@RequestParam(required = true, value = "insId") Integer insId,
			@RequestParam(value = "fileName", required = true) String fileName,
			@RequestParam(value = "fileTypeCode", required = true) Integer fileTypeCode) {

		LOG.info("verifying fileTypecode");
		FileType fileType = fileTypeService.findById(fileTypeCode) ;
		
		if(fileType.getTablesName() == null || "".equals(fileType.getTablesName()) )
			return Message.failMessage("File Type configuration is inproper");
		
		String tableName = fileType.getTablesName();
		Boolean dataExists = pharmacyService.isDataExists(tableName);
		LOG.info("verifying if processing table contains previous data or not ");
		if (dataExists) {
			return Message.failMessage("Previous file processing is incomplete");
		} else {
			Integer fileId = 0;
			try {
				File fileRecord = new File();
				fileRecord.setFileName(fileName);
				fileRecord.setFileTypeCode(fileType.getCode());
				fileRecord.setCreatedBy(username);
				fileRecord.setUpdatedBy(username);
				File newFile = fileService.save(fileRecord);
				LOG.info("5");

				if (newFile != null)
					fileId = newFile.getId();
				else
					LOG.info("fileId is empty");

			} catch (Exception e) {
				LOG.info(e.getCause().getMessage());
				LOG.info("Similar file already processed in past");
				return Message.failMessage("similar file already processed in past");
			}
			LOG.info("Loading  membershipPharmacyClaim data" + new Date());
			String insuranceCode  = (fileType.getInsuranceCode() == null)?"":fileType.getInsuranceCode();
			Integer loadedData = pharmacyService.loadDataCSV2Table(fileName, insuranceCode, tableName);

			Integer mbrClaimLoadedData = pharmacyService.loadData(fileId, insId, insuranceCode);
			LOG.info("membershipClaimLoadedData " + mbrClaimLoadedData + new Date());
			
			if (loadedData < 1) {
				return Message.failMessage("ZERO records to process");
			}
			/*
			LOG.info("processing  membershipPharmacyClaim data" + new Date());
			
			Integer facilityTypeLoadedData = facilityTypeService.loadData(fileId, insuranceCode);
			LOG.info("facilityTypeLoadedData " + facilityTypeLoadedData + new Date());
			Integer billTypeLoadedData = 0;
			if (insId == 1) {
				billTypeLoadedData = billTypeService.loadData(fileId, insuranceCode);
			}
			LOG.info("billTypeLoadedData " + billTypeLoadedData + new Date());
			Integer mbrClaimLoadedData = mbrClaimService.loadData(fileId, insId, insuranceCode);
			LOG.info("membershipClaimLoadedData " + mbrClaimLoadedData + new Date());
			Integer mbrClaimDetailsLoadedData = mbrClaimDetailsService.loadData(fileId, insId);
			LOG.info("membershipClaimDetailsLoadedData " + mbrClaimDetailsLoadedData + new Date());
			Integer mbrProblemLoadedData = mbrProblemService.loadData(fileId, insId, insuranceCode);
			LOG.info("mbrProblemLoadedData " + mbrProblemLoadedData + new Date());
			Integer mbrHedisUnLoadedData = mbrHedisMeasureService.unloadTable(insId, insuranceCode);
			LOG.info("mbrHedisUnLoadedData " + mbrHedisUnLoadedData + new Date());
			Integer mbrHedisLoadedData = mbrHedisMeasureService.loadData(fileId, insId, insuranceCode);
			LOG.info("mbrHedisLoadedData " + mbrHedisLoadedData + new Date());
			Integer unprocessedClaimLoadedData = unprocessedClaimService.loadDataCSV2Table( fileId,  insuranceCode,  tableName);
			LOG.info("unprocessedClaimLoadedData " + unprocessedClaimLoadedData + new Date());
			Integer mbrClaimUnloadedData = mbrClaimService.unloadCSV2Table(tableName);
			LOG.info("membershipClaimUnloadedData " + mbrClaimUnloadedData + new Date());
*/
			LOG.info("Loading  membershipPharmacyClaim data");

			//LOG.info("returning membershipClaimList");
			return Message.successMessage(CommonMessageContent.MEMBERSHIP_CLAIMS_LIST, loadedData);
		}
	}

	@ResponseBody
	@RequestMapping(value = { "/admin/reports/membershipClaimDetails/{mbrClaimId}/list",
			"/user/reports/membershipClaimDetails/{mbrClaimId}/list" })
	public Message viewClaimMembershipDetailsList(@PathVariable Integer mbrClaimId) {
		Pagination pagination = mbrClaimDetailsService.getMbrClaimDetailsPage(mbrClaimId);
		return Message.successMessage(CommonMessageContent.MEMBERSHIP_CLAIM_DETAILS_LIST,
				JsonConverter.getJsonObject(pagination));
	}

	/**
	 * @return
	 */
	@RequestMapping(value = { "/admin/reports/problem", "/user/reports/problem" })
	public String viewProblemMembershipList() {

		return TileDefinitions.PROBLEMMEMBERSHIPLIST.toString();
	}

	/**
	 * @param pageNo
	 * @param pageSize
	 * @param sSearch
	 * @param sSearchIns
	 * @param sSearchPrvdr
	 * @param sSearchHedisRule
	 * @param ruleIds
	 * @param sort
	 * @param sortdir
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/admin/reports/problemMembership/list",
			"/user/reports/problemMembership/list" }, method = RequestMethod.GET)
	public Message viewProblemMembershipList(@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sSearch,
			@RequestParam(required = true) Integer sSearchIns, @RequestParam(required = true) Integer sSearchPrvdr,
			@RequestParam(required = true) Integer sSearchProblemRule,
			@RequestParam(required = true) List<Integer> ruleIds, @RequestParam(required = false) String sort,
			@RequestParam(required = false) String sortdir) {

		Pagination pagination = membershipService.getMembershipProblemPage(pageNo, pageSize, sSearch, sSearchIns,
				sSearchPrvdr, sSearchProblemRule, ruleIds, sort, sortdir);

		return Message.successMessage(CommonMessageContent.MEMBERSHIP_LIST, JsonConverter.getJsonObject(pagination));
	}

	/**
	 * @return
	 * @throws JRException
	 */
	@RequestMapping(value = { "/admin/reports/generateReport", "/user/reports/generateReport" })
	public void handleRequest1(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) Integer insId,
			@RequestParam(required = true) String reportFormat)
			throws JRException, NamingException, SQLException, IOException {
		
		LOG.info("returning report Generation.jsp");

		HashMap<String, Object> rptParams =  new HashMap<>();
		rptParams.put("insId",insId);
		
		String fileName = "AMGMonthlyStatisticsDashboard";
		PrasUtil.generateReport(request,response,dBConnection,fileName, rptParams ,reportFormat);
		
	}

	
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/reports/membershipActivityMonthList",
			"/user/reports/membershipActivityMonthList" }, method = RequestMethod.GET)
	public String viewHedisMeasureAction() {

		LOG.info("Returning view.jsp page after create");
		return TileDefinitions.MEMBERSHIPACTIVITYMONTHLIST.toString();
	}
	
	
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/reports/medicalLossRatioList",
			"/user/reports/medicalLossRatioList" }, method = RequestMethod.GET)
	public String viewMedicalLossReportList() {

		LOG.info("Returning Medical Loss Report Listjsp page after create");
		return TileDefinitions.MEDICALLOSSRATIOLIST.toString();
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
	@RequestMapping(value = { "/admin/medicalLossRatio/list", "/user/medicalLossRatio/list" }, method = RequestMethod.GET)
	public Message viewInsuranceList(@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize,@RequestParam(required = true) Integer insId,
			@RequestParam(required = false) Integer prvdrId, @RequestParam(required = false) String sSearch,
			@RequestParam(required = false) String sort, @RequestParam(required = false) String sortdir,
			@RequestParam(required = false) String repGenDate, @RequestParam(required = false) String category) {

		// Pagination pagination = mlrService.getPage(pageNo, pageSize, insId, prvdrId, sSearch, sort, sortdir);
		
		List<Object[]> entities = mlrService.reportQuery("sarath20160921",insId, prvdrId, repGenDate,category);

		LOG.info("returning insuranceList");
		return Message.successMessage(CommonMessageContent.INSURANCE_LIST, JsonConverter.getJsonObject(entities));
	}
	
	
	/**
	 * @param pageNo
	 * @param pageSize
	 * @param sort
	 * @param sortdir
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/admin/mlrReportDate/list", "/user/mlrReportDate/list" }, method = RequestMethod.GET)
	public Message viewMLRReportDate(@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize,@RequestParam(required = true) Integer insId,
			@RequestParam(required = false) Integer prvdrId, @RequestParam(required = false) String sort, @RequestParam(required = false) String sortdir) {

		Pagination pagination = mlrService.getMlrReportDate(pageNo, pageSize, insId, prvdrId, sort, sortdir);
		LOG.info("returning insuranceList");
		return Message.successMessage(CommonMessageContent.INSURANCE_LIST, JsonConverter.getJsonObject(pagination));
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

}
