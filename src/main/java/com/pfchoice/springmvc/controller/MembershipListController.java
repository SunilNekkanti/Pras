package com.pfchoice.springmvc.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.pfchoice.core.entity.Membership;
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

public class MembershipListController
{

    private static final Logger LOG = LoggerFactory.getLogger(MembershipListController.class.getName());
    
    @Autowired
    private MembershipService membershipService;

   @RequestMapping(value = "/membershipTmpList")
    public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
 
		ModelAndView modelAndView = new ModelAndView("membershipList");
 
		return modelAndView;
	}
    
   @ResponseBody
	@RequestMapping(value = "/membership/list", method = RequestMethod.GET)
	public Message viewMembershipListJsonTest(Model model,@RequestParam(required = false) Integer pageNo,
					@RequestParam(required = false) Integer pageSize,
					@RequestParam(required = false) String sSearch,
					@RequestParam(required = false) String sort,
					@RequestParam(required = false) String sortdir,
					HttpServletRequest request) throws Exception{
		
		
		Pagination pagination = membershipService.getPage(pageNo, pageSize, sSearch, sort, sortdir);
		
       return Message.successMessage(CommonMessageContent.MEMBERSHIP_LIST, JsonConverter.getJsonObject(pagination));
   }
}
