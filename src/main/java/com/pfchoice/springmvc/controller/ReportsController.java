package com.pfchoice.springmvc.controller;

import static com.pfchoice.common.SystemDefaultProperties.FILE_TYPE_AMG_MBR_HOSPITALIZATION;
import static com.pfchoice.common.SystemDefaultProperties.FILES_UPLOAD_DIRECTORY_PATH;
import static com.pfchoice.common.SystemDefaultProperties.FOLLOWUP_TYPE_HEDIS;
import static com.pfchoice.common.SystemDefaultProperties.FOLLOWUP_TYPE_HOSPITALIZATION;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
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
import com.pfchoice.common.util.JsonConverter;
import com.pfchoice.common.util.TileDefinitions;
import com.pfchoice.common.util.XlstoCSV;
import com.pfchoice.core.entity.File;
import com.pfchoice.core.entity.FileType;
import com.pfchoice.core.entity.FollowupType;
import com.pfchoice.core.entity.MembershipFollowup;
import com.pfchoice.core.entity.MembershipHedisMeasure;
import com.pfchoice.core.service.AttPhysicianService;
import com.pfchoice.core.service.FileService;
import com.pfchoice.core.service.FileTypeService;
import com.pfchoice.core.service.FollowupTypeService;
import com.pfchoice.core.service.HospitalService;
import com.pfchoice.core.service.MembershipFollowupService;
import com.pfchoice.core.service.MembershipHedisMeasureService;
import com.pfchoice.core.service.MembershipHospitalizationDetailsService;
import com.pfchoice.core.service.MembershipHospitalizationService;
import com.pfchoice.core.service.MembershipService;

import ml.rugal.sshcommon.page.Pagination;
import ml.rugal.sshcommon.springmvc.util.Message;

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
	private FileService fileService;

	@Autowired
	private FileTypeService fileTypeService;

	@Autowired
	private HospitalService hospitalService;

	@Autowired
	private MembershipService membershipService;

	@Autowired
	private MembershipFollowupService mbrHedisFollowupService;

	@Autowired
	private MembershipHedisMeasureService mbrHedisMeasureService;

	@Autowired
	private MembershipHospitalizationService mbrHospitalizationService;

	@Autowired
	private MembershipHospitalizationDetailsService mbrHospitalizationDetailsService;

	@Autowired
	private FollowupTypeService followupTypeService;

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
		// mbrHedisFollowup.setDateOfContact(new Date());
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
			"/user/reports/hospitalization/fileProcessing.do" })
	public String mbrHospitalizationFileProcessing(Model model, @ModelAttribute("username") String username,
			@RequestParam(required = false, value = "fileUpload") CommonsMultipartFile fileUpload,
			HttpServletRequest request) {
		java.io.File sourceFile, newSourceFile = null;
		if (fileUpload != null && !"".equals(fileUpload.getOriginalFilename())) {
			String fileName = fileUpload.getOriginalFilename();
			String newfileName = fileName.substring(0, fileName.indexOf("."));

			try {
				FileUtils.writeByteArrayToFile(new java.io.File(FILES_UPLOAD_DIRECTORY_PATH + fileName),
						fileUpload.getBytes());

				sourceFile = new java.io.File(FILES_UPLOAD_DIRECTORY_PATH + fileName);
				newSourceFile = new java.io.File(FILES_UPLOAD_DIRECTORY_PATH + newfileName + ".csv");
				sourceFile.createNewFile();
				newSourceFile.createNewFile();
				XlstoCSV.xls(sourceFile, newSourceFile);
				if (sourceFile.exists()) {
					sourceFile.delete();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		LOG.info("before file processing");
		return "forward:/admin/membershipHospitalization/list?fileName=" + newSourceFile.getName();
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

	@ResponseBody
	@RequestMapping(value = { "/admin/reports/membershipHospitalizationDetails/{mbrHosId}/list",
			"/user/reports/membershipHospitalizationDetails/{mbrHosId}/list" })
	public Message viewHospitalizationMembershipDetailsList(@PathVariable Integer mbrHosId) {
		Pagination pagination = mbrHospitalizationDetailsService.getMbrHospitalizationDetailsPage(mbrHosId);
		return Message.successMessage(CommonMessageContent.HOSPITALIZATION_FOLLOWUP_LIST,
				JsonConverter.getJsonObject(pagination));
	}

	@RequestMapping(value = { "/admin/reports/hospitalization/{mbrHosId}", "/user/reports/hospitalization/{mbrHosId}" })
	public String handleHospitalizationRequest(@PathVariable Integer mbrHosId, Model model) {
		model.addAttribute("mbrHosId", mbrHosId);
		return "membershipHospitalizationDetails";
	}
}
