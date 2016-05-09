package com.pfchoice.springmvc.controller;

import static com.pfchoice.common.SystemDefaultProperties.FOLLOWUP_TYPE_HEDIS;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.pfchoice.common.CommonMessageContent;
import com.pfchoice.common.util.JsonConverter;
import com.pfchoice.common.util.TileDefinitions;
import com.pfchoice.core.entity.File;
import com.pfchoice.core.entity.FollowupType;
import com.pfchoice.core.entity.MembershipFollowup;
import com.pfchoice.core.entity.MembershipHedisMeasure;
import com.pfchoice.core.service.AttPhysicianService;
import com.pfchoice.core.service.FileService;
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
		mbrHedisFollowup.setDateOfContact(new Date());
		mbrHedisFollowup.setCreatedBy(username);
		mbrHedisFollowup.setUpdatedBy(username);
		mbrHedisFollowupService.save(mbrHedisFollowup);

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
	 * @return
	 */
	@RequestMapping(value = { "/admin/reports/hospitalization", "/user/reports/hospitalization" })
	public String handleHospitalizationRequest() {

		return "membershipHospitalizationList";
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
			"/user/reports/membershipHospitalizatio/list" }, method = RequestMethod.GET)
	public Message viewHospitalizationMembershipList(@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sSearch,
			@RequestParam(required = true) Integer sSearchIns, @RequestParam(required = true) Integer sSearchPrvdr,
			@RequestParam(required = false) String sort, @RequestParam(required = false) String sortdir) {

		Pagination pagination = membershipService.getMbrHospitalizationPage(pageNo, pageSize, sSearch, sSearchIns,
				sSearchPrvdr, sort, sortdir);

		return Message.successMessage(CommonMessageContent.MEMBERSHIP_LIST, JsonConverter.getJsonObject(pagination));
	}

	/**
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/admin/membershipHospitalization/list",
			"/user/membershipHospitalization/list" }, method = RequestMethod.GET)
	public Message viewmembershipHospitalizationList(@ModelAttribute("username") String username) {
		Boolean dataExists = mbrHospitalizationService.isDataExists();

		if (dataExists) {
			LOG.warn("Previous file processing is incomplete");
			return Message.failMessage("Previous file processing is incomplete");
		} else {

			Integer fileId = 0;

			try {
				File fileRecord = new File();
				fileRecord.setFileName("Physicians First Choice Census 04-29-2016.csv");
				fileRecord.setFileTypeCode(2);
				fileRecord.setCreatedBy(username);
				fileRecord.setUpdatedBy(username);
				File newFile = fileService.save(fileRecord);
				fileId = newFile.getId();
			} catch (Exception e) {
				LOG.info("similar file already processed in past");
				return Message.failMessage("similar file already processed in past");
			}

			LOG.info("Loading  membershipHospitalization data");
			Integer loadedData = mbrHospitalizationService.loadDataCSV2Table();
			LOG.info("Loaded  membershipHospitalization data");

			if (loadedData < 1) {
				LOG.info("ZERO records to process");
				return Message.failMessage("ZERO records to process");
			}

			LOG.info("processing  membershipHospitalization data");
			Integer hosLoadedData = hospitalService.loadData(fileId);
			Integer attPhysicianLoadedData = attPhysicianService.loadData(fileId);
			Integer mbrHospitalizationLoadedData = mbrHospitalizationService.loadData(fileId);
			Integer mbrHospitalizationDetailsLoadedData = mbrHospitalizationDetailsService.loadData(fileId);
			LOG.info("hosLoadedData " + hosLoadedData);
			LOG.info("attPhysicianLoadedData " + attPhysicianLoadedData);
			LOG.info("mbrHospitalizationLoadedData " + mbrHospitalizationLoadedData);
			LOG.info("mbrHospitalizationDetailsLoadedData " + mbrHospitalizationDetailsLoadedData);
			Integer mbrHospitalizationUnloadedData = mbrHospitalizationService.unloadCSV2Table();
			LOG.info("mbrHospitalizationUnloadedData " + mbrHospitalizationUnloadedData);
			LOG.info("processed  membershipHospitalization data");

			LOG.info("returning membershipList");
			return Message.successMessage(CommonMessageContent.MEMBERSHIP_LIST, loadedData);
		}
	}
	
	@ResponseBody
	@RequestMapping(value = { "/admin/reports/membershipHospitalizationDetails/{mbrHosId}/list",
			"/user/reports/membershipHospitalizationDetails/{mbrHosId}/list" })
	public Message viewHospitalizationMembershipDetailsList(@PathVariable Integer mbrHosId) {
		System.out.println("mbrHosId2 "+ mbrHosId);
		Pagination pagination = mbrHospitalizationDetailsService.getMbrHospitalizationDetailsPage(mbrHosId);
		System.out.println("mbrHosId 3"+ mbrHosId);
		return Message.successMessage(CommonMessageContent.MEMBERSHIP_LIST, JsonConverter.getJsonObject(pagination));
	}
	
	
	
	@RequestMapping(value = { "/admin/reports/hospitalization/{mbrHosId}", "/user/reports/hospitalization/{mbrHosId}" })
	public String handleHospitalizationRequest(@PathVariable Integer mbrHosId, Model model) {
		System.out.println("mbrHosId "+ mbrHosId);
		model.addAttribute("mbrHosId", mbrHosId);
		return "membershipHospitalizationDetails";
	}
}
