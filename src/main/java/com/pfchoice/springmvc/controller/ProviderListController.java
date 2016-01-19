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

import com.pfchoice.core.entity.Provider;
import com.pfchoice.core.service.ProviderService;


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
    public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
 
    	List<Provider> listBean = providerService.findAll();
    	System.out.println("Provider list bean sze "+listBean.size());
		ModelAndView modelAndView = new ModelAndView("providerList");
		modelAndView.addObject("providerList", listBean);
 
		return modelAndView;
	}
    
}
