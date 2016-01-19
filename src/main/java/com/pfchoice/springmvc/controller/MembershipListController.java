package com.pfchoice.springmvc.controller;

import ml.rugal.sshcommon.springmvc.util.Message;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pfchoice.common.CommonMessageContent;
import com.pfchoice.core.entity.County;
import com.pfchoice.core.entity.Gender;
import com.pfchoice.core.entity.Membership;
import com.pfchoice.core.entity.MembershipStatus;
import com.pfchoice.core.service.CountyService;
import com.pfchoice.core.service.GenderService;
import com.pfchoice.core.service.MembershipService;
import com.pfchoice.core.service.MembershipStatusService;
import com.pfchoice.core.service.impl.MembershipServiceImpl;




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
 
    	List<Membership> listBean = membershipService.findAll();
    	System.out.println("Membership list bean sze "+listBean.size());
		ModelAndView modelAndView = new ModelAndView("membershipList");
		modelAndView.addObject("membershipList", listBean);
 
		return modelAndView;
	}
    
}
