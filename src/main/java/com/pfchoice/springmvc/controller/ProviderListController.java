package com.pfchoice.springmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pfchoice.common.CommonMessageContent;
import com.pfchoice.common.util.JsonConverter;
import com.pfchoice.core.entity.Provider;
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

public class ProviderListController
{

    private static final Logger LOG = LoggerFactory.getLogger(ProviderListController.class.getName());
    
    @Autowired
    private ProviderService providerService;

   @RequestMapping(value = "/providerList")
    public String handleRequest() throws Exception {
 
		return "providerList";
	}
   
    @ResponseBody
    @RequestMapping(value = "/provider/list", method = RequestMethod.GET)
	public Message viewProviderListJsonTest(Model model,@RequestParam(required = false) Integer pageNo,
					@RequestParam(required = false) Integer pageSize,
					@RequestParam(required = false) String sSearch,
					@RequestParam(required = false) String sort,
					@RequestParam(required = false) String sortdir,
					@RequestParam(required = false) String insId) throws Exception{
		
		Pagination pagination = providerService.getPage(pageNo, pageSize, sSearch, sort, sortdir);
		
      return Message.successMessage(CommonMessageContent.PROVIDER_LIST, JsonConverter.getJsonObject(pagination));
  }
  
    
    @ResponseBody
    @RequestMapping(value = "/insurance/providerlist", method = RequestMethod.GET)
	public Message viewProviderListJsonTest(Model model,@RequestParam(required = false) Integer insId) throws Exception{
		
		Pagination pagination = providerService.findByInsId(insId);
		
      return Message.successMessage(CommonMessageContent.PROVIDER_LIST, JsonConverter.getJsonObject(pagination));
  }
    
}
