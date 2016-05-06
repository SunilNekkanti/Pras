package com.pfchoice.springmvc.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.pfchoice.common.CommonMessageContent;
import com.pfchoice.common.util.JsonConverter;
import com.pfchoice.common.util.TileDefinitions;
import com.pfchoice.core.entity.File;
import com.pfchoice.core.service.AttPhysicianService;
import com.pfchoice.core.service.FileService;
import com.pfchoice.core.service.HospitalService;
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
public class MembershipListController {

	private static final Logger LOG = LoggerFactory.getLogger(MembershipListController.class.getName());

	@Autowired
	private AttPhysicianService attPhysicianService;

	@Autowired
	private FileService fileService;
	
	@Autowired
	private HospitalService hospitalService;

	@Autowired
	private MembershipService membershipService;

	@Autowired
	private MembershipHospitalizationService membershipHospitalizationService;

	@Autowired
	private MembershipHospitalizationDetailsService membershipHospitalizationDetailsService;
	
	/**
	 * @return
	 */
	@RequestMapping(value = { "/admin/membershipList", "/user/membershipList" })
	public String handleRequest()  {
		LOG.info("returning membershipList.jsp");
		return TileDefinitions.MEMBERSHIPLIST.toString();
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
	@RequestMapping(value = { "/admin/membership/list", "/user/membership/list" }, method = RequestMethod.GET)
	public Message viewMembershipList(@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize, 
			@RequestParam(required = false) String sSearch,
			@RequestParam(required = false) Integer sSearchIns, 
			@RequestParam(required = false) Integer sSearchPrvdr,
			@RequestParam(required = false) String sort, 
			@RequestParam(required = false) String sortdir) {

		// provided 0 to ignore hedisMeasureRule join
		Integer i = 0;
		List<Integer> ruleIds = Collections.singletonList(i);
		Pagination pagination = membershipService.getPage(pageNo, pageSize, sSearch, sSearchIns, sSearchPrvdr, 0,
				ruleIds, sort, sortdir);
		LOG.info("returning membershipList");
		return Message.successMessage(CommonMessageContent.MEMBERSHIP_LIST, JsonConverter.getJsonObject(pagination));
	}
	
	/**
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/admin/membershipHospitalization/list"}, method = RequestMethod.GET)
	public Message viewmembershipHospitalizationList(@ModelAttribute("username") String username) {
		Boolean dataExists = membershipHospitalizationService.isDataExists();
		
		if(dataExists){
			LOG.warn("Previous file processing is incomplete");
			return Message.failMessage("Previous file processing is incomplete");
		}else{
			
			Integer fileId = 0; 
			
			try{
				File fileRecord = new File();
				fileRecord.setFileName("Physicians First Choice Census 04-28-2016.csv");
				fileRecord.setFileTypeCode(2);
				fileRecord.setCreatedBy(username);
				fileRecord.setUpdatedBy(username);
				File newFile = fileService.save(fileRecord);
				fileId = newFile.getId();
			}
			catch(Exception e){
				LOG.info("similar file already processed in past");
				return Message.failMessage("similar file already processed in past");
			}
			
			LOG.info("Loading  membershipHospitalization data");
			Integer loadedData = membershipHospitalizationService.loadDataCSV2Table();
			LOG.info("Loaded  membershipHospitalization data");
			
			if( loadedData < 1){
				LOG.info("ZERO records to process");
				return Message.failMessage("ZERO records to process");
			}
			
			LOG.info("processing  membershipHospitalization data");
			Integer hosLoadedData = hospitalService.loadData(fileId);
			Integer attPhysicianLoadedData = attPhysicianService.loadData(fileId);
			Integer mbrHospitalizationLoadedData = membershipHospitalizationService.loadData(fileId);
			Integer mbrHospitalizationDetailsLoadedData = membershipHospitalizationDetailsService.loadData(fileId);
			LOG.info("hosLoadedData "+hosLoadedData);
			LOG.info("attPhysicianLoadedData "+attPhysicianLoadedData);
			LOG.info("mbrHospitalizationLoadedData "+mbrHospitalizationLoadedData);
			LOG.info("mbrHospitalizationDetailsLoadedData "+mbrHospitalizationDetailsLoadedData);
			Integer mbrHospitalizationUnloadedData = membershipHospitalizationService.unloadCSV2Table();
			LOG.info("mbrHospitalizationUnloadedData "+mbrHospitalizationUnloadedData);
			LOG.info("processed  membershipHospitalization data");
			
			LOG.info("returning membershipList");
	          return Message.successMessage(CommonMessageContent.MEMBERSHIP_LIST, loadedData);
		}
	}
}
