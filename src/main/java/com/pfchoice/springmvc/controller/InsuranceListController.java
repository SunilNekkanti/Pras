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

import com.pfchoice.core.entity.Insurance;
import com.pfchoice.core.service.InsuranceService;



/**
 *
 * A Provider controller class for GET/DELETE/POST/PUT.
 *
 * @author sarath
 */
@Controller

public class InsuranceListController
{

    private static final Logger LOG = LoggerFactory.getLogger(InsuranceListController.class.getName());
    
    @Autowired
    private InsuranceService insuranceService;

   @RequestMapping(value = "/insuranceList")
    public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
 
    	List<Insurance> listBean = insuranceService.findAll();
    	System.out.println("Insurance list bean sze "+listBean.size());
		ModelAndView modelAndView = new ModelAndView("insuranceList");
		modelAndView.addObject("insuranceList", listBean);
 
		return modelAndView;
	}
    
}
