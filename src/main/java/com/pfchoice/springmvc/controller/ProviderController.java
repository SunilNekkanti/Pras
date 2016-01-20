package com.pfchoice.springmvc.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pfchoice.core.entity.Provider;
import com.pfchoice.core.service.ProviderService;

@Controller
public class ProviderController{
	
    @Autowired
    ProviderService providerService;
    
 /*   @Autowired
    @Qualifier("providerValidator")
    private Validator validator;
 
    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }
 */   
    private Map<Integer, Provider> prvdrs = null;
    private static final Logger logger = LoggerFactory
            .getLogger(ProviderController.class);
 
	public ProviderController() {
	       prvdrs = new HashMap<Integer, Provider>();
	    }
	
	
	@ModelAttribute("provider")
    public Provider createMembershipModel() {
        // ModelAttribute value should be same as used in the empSave.jsp
        return new Provider();
    }
 
	
	@RequestMapping(value = "/provider/{id}", method = RequestMethod.GET)
    public String updateProviderPage(@PathVariable Integer id,Model model) {
		Provider dbProvider = providerService.findById(id);
		 logger.info("Returning provider.getId()"+dbProvider.getId());
	       
		model.addAttribute("provider", dbProvider);
        logger.info("Returning providerSave.jsp page");
        return "providerEdit";
    }
	
	@RequestMapping(value = "/provider/save.do", method = RequestMethod.POST)
    public String saveProviderAction( @Validated Provider provider,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
        	for( ObjectError oe :bindingResult.getAllErrors()){
        		System.out.println("oe "+oe.getObjectName() +""+oe.getCode());
        	}
            logger.info("Returning providerEdit.jsp page");
            return "providerEdit";
        }
        logger.info("Returning ProviderSuccess.jsp page");
        if (null != provider.getId())
        {
        	
        	providerService.update(provider);
        }
        
        model.addAttribute("provider", provider);
        prvdrs.put(provider.getId(), provider);
        return "providerEditSuccess";
    }
	
}
