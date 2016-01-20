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

import com.pfchoice.core.entity.Insurance;
import com.pfchoice.core.service.InsuranceService;

@Controller
public class InsuranceController{
	
    @Autowired
    InsuranceService insuranceService;
    
 /*   @Autowired
    @Qualifier("insuranceValidator")
    private Validator validator;
 
    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }
 */   
    private Map<Integer, Insurance> prvdrs = null;
    private static final Logger logger = LoggerFactory
            .getLogger(InsuranceController.class);
 
	public InsuranceController() {
	       prvdrs = new HashMap<Integer, Insurance>();
	    }
	
	
	@ModelAttribute("insurance")
    public Insurance createMembershipModel() {
        // ModelAttribute value should be same as used in the empSave.jsp
        return new Insurance();
    }
 
	
	@RequestMapping(value = "/insurance/{id}", method = RequestMethod.GET)
    public String updateInsurancePage(@PathVariable Integer id,Model model) {
		Insurance dbInsurance = insuranceService.findById(id);
		 logger.info("Returning insurance.getId()"+dbInsurance.getId());
	       
		model.addAttribute("insurance", dbInsurance);
        logger.info("Returning insuranceSave.jsp page");
        return "insuranceEdit";
    }
	
	@RequestMapping(value = "/insurance/save.do", method = RequestMethod.POST)
    public String saveInsuranceAction( @Validated Insurance insurance,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
        	for( ObjectError oe :bindingResult.getAllErrors()){
        		System.out.println("oe "+oe.getObjectName() +""+oe.getCode());
        	}
            logger.info("Returning insuranceEdit.jsp page");
            return "insuranceEdit";
        }
        logger.info("Returning insuranceSuccess.jsp page");
        if (null != insurance.getId())
        {
        	
        	insuranceService.update(insurance);
        }
        
        model.addAttribute("insurance", insurance);
        prvdrs.put(insurance.getId(), insurance);
        return "insuranceEditSuccess";
    }
	
}
