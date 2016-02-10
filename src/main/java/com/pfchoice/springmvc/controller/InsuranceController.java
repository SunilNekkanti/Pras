package com.pfchoice.springmvc.controller;


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pfchoice.common.util.PrasUtil;
import com.pfchoice.core.entity.Insurance;
import com.pfchoice.core.service.InsuranceService;

@Controller
public class InsuranceController{
	
	   private static final Logger logger = LoggerFactory
	            .getLogger(InsuranceController.class);
	   
    @Autowired
    InsuranceService insuranceService;
    
     
    @Autowired
    @Qualifier("insuranceValidator")
    private Validator validator;
 
    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }
 
	@ModelAttribute("insurance")
    public Insurance createInsuranceModel() {
        // ModelAttribute value should be same as used in the empSave.jsp
        return new Insurance();
    }
	
	@RequestMapping(value = "/insurance/new")
    public String addInsurancePage(Model model) {
		
		Insurance insurance = createInsuranceModel();
		insurance.setCreatedBy("sarath");
		model.addAttribute("insurance", insurance);
        return "insuranceNew";
    }
	
	@RequestMapping(value = "/insurance/{id}", method = RequestMethod.GET)
    public String updateInsurancePage(@PathVariable Integer id,Model model) {
		
		Insurance dbInsurance = insuranceService.findById(id);
		 logger.info("Returning insurance.getId()"+dbInsurance.getId());
		 
			       
		model.addAttribute("insurance", dbInsurance);
		 logger.info("Returning insuranceSave.jsp page");
        return "insuranceEdit";
    }
	
	@RequestMapping(value = "/insurance/save.do", method = RequestMethod.POST, params ={"add"})
    public String newInsuranceAction(@Validated Insurance insurance,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            logger.info("Returning insuranceEdit.jsp page");
            return "insuranceNew";
        }
        else
        {
	        	logger.info("Returning InsuranceSuccess.jsp page after create");
	        	model.addAttribute("insurance", insurance);
	        	insurance.setActiveInd('Y');
	        	insurance.setCreatedBy("Mohanasundharam");
	 	      	insuranceService.save(insurance);
	 	       return "insuranceNewSuccess";
        }    
    }
	
	@RequestMapping(value = "/insurance/save.do", method = RequestMethod.POST, params ={"update"})
    public String saveInsuranceAction(@Validated Insurance insurance,
            BindingResult bindingResult, Model model) {
		insurance.setActiveInd('Y');
        if (bindingResult.hasErrors()) {
        	logger.info("Returning insuranceEdit.jsp page");
        	insurance.setActiveInd('Y');
            return "insuranceEdit";
        }
        else
        {
	        
	        if (null != insurance.getId())
	        {
	        	logger.info("Returning InsuranceSuccess.jsp page after update");
	        	insurance.setActiveInd('Y');
	        	insurance.setUpdatedBy("Mohanasundharam");
	        	insuranceService.update(insurance);
	        }
	           
	        return "insuranceEditSuccess";
        }    
    }
	
	@RequestMapping(value = "/insurance/save.do", method = RequestMethod.POST, params ={"delete"})
    public String deleteInsuranceAction( @Validated Insurance insurance,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            logger.info("Returning insuranceEdit.jsp page");
            return "insuranceEdit";
        }
        else
        {
	        	logger.info("Returning InsuranceSuccess.jsp page after update");
	        	insurance.setActiveInd('N');
	        	insurance.setUpdatedBy("Mohanasundharam");
	        	insuranceService.update(insurance);
	        return "insuranceDeleteSuccess";
        }    
    }
	
	@ModelAttribute("activeIndMap")
	public Map<String,String> populateActiveIndList() {
		//Data referencing for ActiveMap box
		return PrasUtil.getActiveIndMap();
	}
	
}
