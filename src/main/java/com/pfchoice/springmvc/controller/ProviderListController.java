package com.pfchoice.springmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.pfchoice.common.CommonMessageContent;
import com.pfchoice.common.util.JsonConverter;
import com.pfchoice.common.util.TileDefinitions;
import com.pfchoice.core.service.ProviderService;

import ml.rugal.sshcommon.page.Pagination;
import ml.rugal.sshcommon.springmvc.util.Message;

/**
 *
 * A Provider controller class for GET/DELETE/POST/PUT.
 *
 * @author sarath
 */
@Controller
@SessionAttributes({ "username", "userpath" })
public class ProviderListController {

	private static final Logger LOG = LoggerFactory.getLogger(ProviderListController.class.getName());

	@Autowired
	private ProviderService providerService;

	/**
	 * @return
	 */
	@RequestMapping(value = { "/admin/providerList", "/user/providerList" })
	public String handleRequest() {
		LOG.info("returning providerList.jsp");
		return TileDefinitions.PROVIDERLIST.toString();
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
	@RequestMapping(value = { "/admin/provider/list", "/user/provider/list" }, method = RequestMethod.GET)
	public Message viewProviderList(@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize, 
			@RequestParam(required = false) String sSearch,
			@RequestParam(required = false) String sort, 
			@RequestParam(required = false) String sortdir) {

		Pagination pagination = providerService.getPage(pageNo, pageSize, sSearch, sort, sortdir);
		LOG.info("returning providerList");
		return Message.successMessage(CommonMessageContent.PROVIDER_LIST, JsonConverter.getJsonObject(pagination));
	}

	/**
	 * @param insId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/admin/insurance/providerlist",
			"/user/insurance/providerlist" }, method = RequestMethod.GET)
	public Message viewProviderListt(@RequestParam(required = false) Integer insId) {

		Pagination pagination = providerService.findByInsId(insId);
		LOG.info("returning providerList for insId"+insId);
		return Message.successMessage(CommonMessageContent.PROVIDER_LIST, JsonConverter.getJsonObject(pagination));
	}

}
