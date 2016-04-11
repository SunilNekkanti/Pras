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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.pfchoice.common.CommonMessageContent;
import com.pfchoice.common.util.JsonConverter;
import com.pfchoice.core.service.InsuranceService;

import ml.rugal.sshcommon.page.Pagination;
import ml.rugal.sshcommon.springmvc.util.Message;



/**
 *
 * A Provider controller class for GET/DELETE/POST/PUT.
 *
 * @author sarath
 */
@Controller
@SessionAttributes({"username","userpath"})
public class InsuranceListController
{

    private static final Logger LOG = LoggerFactory.getLogger(InsuranceListController.class.getName());
    
    @Autowired
    private InsuranceService insuranceService;

   @RequestMapping(value = {"/admin/insuranceList","/user/insuranceList"})
    public String handleRequest( Model model) throws Exception {
 
		return "insuranceList";
	}
    
   @ResponseBody
   @RequestMapping(value = {"/admin/insurance/list","/user/insurance/list"}, method = RequestMethod.GET)
	public Message viewInsuranceListJsonTest(Model model,@RequestParam(required = false) Integer pageNo,
					@RequestParam(required = false) Integer pageSize,
					@RequestParam(required = false) String sSearch,
					@RequestParam(required = false) String sort,
					@RequestParam(required = false) String sortdir) throws Exception{
		
		
		Pagination pagination = insuranceService.getPage(pageNo, pageSize, sSearch, sort, sortdir);
		
     return Message.successMessage(CommonMessageContent.INSURANCE_LIST, JsonConverter.getJsonObject(pagination));
 }
}
