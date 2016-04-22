package com.pfchoice.springmvc.controller;

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
import com.pfchoice.core.entity.MembershipHedisFollowup;
import com.pfchoice.core.entity.MembershipHedisMeasure;
import com.pfchoice.core.service.MembershipHedisFollowupService;
import com.pfchoice.core.service.MembershipHedisMeasureService;
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
	private MembershipService membershipService;

	@Autowired
	private MembershipHedisFollowupService mbrHedisFollowupService;

	@Autowired
	private MembershipHedisMeasureService mbrHedisMeasureService;

	@RequestMapping(value = { "/admin/reports/hedis", "/user/reports/hedis" })
	public String handleRequest() throws Exception {

		return "hedisMembershipList";
	}

	@ResponseBody
	@RequestMapping(value = { "/admin/reports/hedisMembership/list",
			"/user/reports/hedisMembership/list" }, method = RequestMethod.GET)
	public Message viewMembershipListJsonTest(Model model, @RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sSearch,
			@RequestParam(required = true) Integer sSearchIns, @RequestParam(required = true) Integer sSearchPrvdr,
			@RequestParam(required = true) Integer sSearchHedisRule,
			@RequestParam(required = true) List<Integer> ruleIds, @RequestParam(required = false) String sort,
			@RequestParam(required = false) String sortdir) throws Exception {

		Pagination pagination = membershipService.getPage(pageNo, pageSize, sSearch, sSearchIns, sSearchPrvdr,
				sSearchHedisRule, ruleIds, sort, sortdir);

		return Message.successMessage(CommonMessageContent.MEMBERSHIP_LIST, JsonConverter.getJsonObject(pagination));
	}

	@ResponseBody
	@RequestMapping(value = { "/admin/reports/membershipHedis/followup",
			"/user/reports/membershipHedis/followup" }, method = RequestMethod.POST)
	public String addMembershipHedisFollowup(Model model, @RequestBody final MembershipHedisFollowup mbrHedisFollowup,
			@ModelAttribute("username") String username) {

		List<Map<Integer, String>> mbrHedisMeasureList = mbrHedisFollowup.getMbrHedisMeasureIds();
		if (mbrHedisMeasureList != null) {
			mbrHedisMeasureList.forEach(ruleMap -> {
				for (Map.Entry<Integer, String> entry : ruleMap.entrySet()) {
					System.out.println("key :" + entry.getKey() + " value:" + entry.getValue());
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
		mbrHedisFollowup.setDateOfContact(new Date());
		mbrHedisFollowup.setCreatedBy(username);
		mbrHedisFollowup.setUpdatedBy(username);
		mbrHedisFollowupService.save(mbrHedisFollowup);

		return "membershipContactEditSuccess";
	}

	@ResponseBody
	@RequestMapping(value = { "/admin/reports/membershipHedis/{mbrId}/followupDetails",
			"/user/reports/membershipHedis/{mbrId}/followupDetails" })
	public Message membershipFollowupDetails(@PathVariable Integer mbrId, @ModelAttribute("username") String username,
			Model model) {
		List<MembershipHedisFollowup> dbMbrHedisFollowup = mbrHedisFollowupService.findAllByMbrId(mbrId);
		return Message.successMessage(CommonMessageContent.HEDIS_FOLLOWUP_LIST,
				JsonConverter.getJsonObject(dbMbrHedisFollowup));
	}

}
