package com.pfchoice.springmvc.controller;

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
import com.pfchoice.core.entity.RiskRecon;
import com.pfchoice.core.service.RiskReconService;

import ml.rugal.sshcommon.page.Pagination;
import ml.rugal.sshcommon.springmvc.util.Message;

@Controller
@SessionAttributes({ "username", "userpath" })
public class RiskReconController {

	private static final Logger logger = LoggerFactory.getLogger(RiskReconController.class);

	@Autowired
	private RiskReconService riskReconService;

	/*
	 * @Autowired
	 * 
	 * @Qualifier("RiskReconValidator") private Validator validator;
	 * 
	 * /**
	 * 
	 * @param binder
	 *
	 * @InitBinder("riskRecon") public void initBinder(WebDataBinder binder)
	 * { binder.setValidator(validator); }
	 * 
	 */

	/**
	 * @return
	 */
	@ModelAttribute("riskRecon")
	public RiskRecon createRiskReconModel() {
		return new RiskRecon();
	}

	/**
	 * @return
	 */
	@RequestMapping(value = { "/admin/claimReport", "/user/claimReport" })
	public String handleRequest() {
		logger.info("returning riskReconList.jsp");
		return TileDefinitions.RISKRECONLIST.toString();
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
	@RequestMapping(value = { "/admin/riskRecon/list", "/user/riskRecon/list" }, method = RequestMethod.GET)
	public Message viewInsuranceList(@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sSearch,
			@RequestParam(required = false) String sort, @RequestParam(required = false) String sortdir) {

		Pagination pagination = riskReconService.getPage(pageNo, pageSize, sSearch, sort, sortdir);
		logger.info("returning riskReconList");
		return Message.successMessage(CommonMessageContent.RISKRECON_LIST, JsonConverter.getJsonObject(pagination));
	}

}
