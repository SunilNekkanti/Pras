package com.pfchoice.springmvc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.pfchoice.common.CommonMessageContent;
import com.pfchoice.common.util.JsonConverter;
import com.pfchoice.core.entity.HedisMeasureRule;
import com.pfchoice.core.service.HedisMeasureRuleService;

import ml.rugal.sshcommon.page.Pagination;
import ml.rugal.sshcommon.springmvc.util.Message;

@Controller
@SessionAttributes({ "username", "userpath" })
public class HedisMeasureRuleListController {

	private static final Logger logger = LoggerFactory.getLogger(HedisMeasureRuleListController.class);

	@Autowired
	private HedisMeasureRuleService hedisMeasureRuleService;

	@RequestMapping(value = { "/admin/hedisMeasureRule/hedisMeasureRuleList",
			"/user/hedisMeasureRule/hedisMeasureRuleList" })
	public String viewHedisMeasureRuleAction(Model model) throws Exception {

		logger.info("Returning view.jsp page after create");
		return "hedisMeasureRuleList";
	}

	@ResponseBody
	@RequestMapping(value = { "/admin/hedisMeasureRule/hedisMeasureRuleLists",
			"/user/hedisMeasureRule/hedisMeasureRuleLists" }, method = RequestMethod.GET)
	public Message viewHedisMeasureRuleActionJsonTest(Model model, @RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sSearch,
			@RequestParam(required = false) String sort, @RequestParam(required = false) String sortdir,
			@RequestParam(required = false) Integer insId, @RequestParam(required = false) Integer effYear)
					throws Exception {

		Pagination pagination = hedisMeasureRuleService.getPage(pageNo, pageSize, sSearch, sort, sortdir, insId,
				effYear);

		return Message.successMessage(CommonMessageContent.HEDIS_RULE_LIST, JsonConverter.getJsonObject(pagination));
	}

	@ResponseBody
	@RequestMapping(value = { "/admin/hedisMeasureRule/list",
			"/user/hedisMeasureRule/list" }, method = RequestMethod.GET)
	public Message viewHedisMeasureRuleList(Model model, @RequestParam(required = false) Integer insId)
			throws Exception {

		List<HedisMeasureRule> hedisMeasureRuleList = hedisMeasureRuleService.findAllByInsId(insId);

		return Message.successMessage(CommonMessageContent.HEDIS_RULE_LIST,
				JsonConverter.getJsonObject(hedisMeasureRuleList));
	}

}
