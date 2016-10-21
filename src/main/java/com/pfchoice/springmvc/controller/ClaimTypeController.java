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
import com.pfchoice.core.entity.ClaimType;
import com.pfchoice.core.service.ClaimTypeService;

import ml.rugal.sshcommon.page.Pagination;
import ml.rugal.sshcommon.springmvc.util.Message;

@Controller
@SessionAttributes({ "username", "userpath" })
public class ClaimTypeController {

	private static final Logger logger = LoggerFactory.getLogger(ClaimTypeController.class);

	@Autowired
	private ClaimTypeService claimTypeService;

	/*
	 * @Autowired
	 * 
	 * @Qualifier("ClaimTypeValidator") private Validator validator;
	 * 
	 * /**
	 * 
	 * @param binder
	 *
	 * @InitBinder("claimType") public void initBinder(WebDataBinder binder)
	 * { binder.setValidator(validator); }
	 * 
	 */

	/**
	 * @return
	 */
	@ModelAttribute("claimType")
	public ClaimType createClaimTypeModel() {
		return new ClaimType();
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
	@RequestMapping(value = { "/admin/claimType/list", "/user/claimType/list" }, method = RequestMethod.GET)
	public Message viewInsuranceList(@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize) {

		Pagination pagination = claimTypeService.getPage(pageNo, pageSize);
		logger.info("returning claimTypeList");
		return Message.successMessage(CommonMessageContent.CLAIMTYPE_LIST, JsonConverter.getJsonObject(pagination));
	}

}
