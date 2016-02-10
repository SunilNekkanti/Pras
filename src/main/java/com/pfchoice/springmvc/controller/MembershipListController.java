package com.pfchoice.springmvc.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pfchoice.core.entity.Membership;
import com.pfchoice.core.service.MembershipService;





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
		ModelAndView modelAndView = new ModelAndView("membershipList");
		modelAndView.addObject("membershipList", listBean);
 
		return modelAndView;
	}
    
}
