package com.pfchoice.springmvc.controller;


import java.util.HashMap;
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

import com.pfchoice.common.util.PrasUtil;
import com.pfchoice.core.entity.Contract;
import com.pfchoice.core.entity.Insurance;
import com.pfchoice.core.entity.InsuranceContract;
import com.pfchoice.core.entity.ReferenceContract;
import com.pfchoice.core.service.ContractService;
import com.pfchoice.core.service.InsuranceContractService;
import com.pfchoice.core.service.InsuranceService;

@Controller
public class InsuranceController{
	
    @Autowired
    InsuranceService insuranceService;
    
    @Autowired
    InsuranceContractService insuranceContractService;
  
    @Autowired
    ContractService contractService;
    
 /*   @Autowired
    @Qualifier("insuranceValidator")
    private Validator validator;
 
    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }
 */   
    private Map<Integer, Insurance> ins = null;
    private static final Logger logger = LoggerFactory
            .getLogger(InsuranceController.class);
 
	public InsuranceController() {
	       ins = new HashMap<Integer, Insurance>();
	    }
	
	
	@ModelAttribute("insurance")
    public Insurance createInsuranceModel() {
        // ModelAttribute value should be same as used in the empSave.jsp
        return new Insurance();
    }
	
	@ModelAttribute("refContract")
    public ReferenceContract createRefContractModel() {
        // ModelAttribute value should be same as used in the empSave.jsp
        return new ReferenceContract();
    }
	
	@RequestMapping(value = "/insurance/new")
    public String addInsurancePage(Model model) {
		
		Insurance insurance = createInsuranceModel();
		insurance.setCreatedBy("sarath");
		model.addAttribute("insurance", insurance);
        return "insuranceEdit";
    }
	
 
	
	@RequestMapping(value = "/insurance/{id}", method = RequestMethod.GET)
    public String updateInsurancePage(@PathVariable Integer id,Model model) {
		
		Insurance dbInsurance = insuranceService.findById(id);
		 logger.info("Returning insurance.getId()"+dbInsurance.getId());
		 
			       
		model.addAttribute("insurance", dbInsurance);
		InsuranceContract insuranceContract = new InsuranceContract();
		model.addAttribute("insuranceContract", insuranceContract);
		
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
        else
        {
	        
	        if (null != insurance.getId())
	        {
	        	logger.info("Returning InsuranceSuccess.jsp page after update");
	        	insurance.setActiveInd('Y');
	        	insuranceService.update(insurance);
	        }
	        else
	        {
	        	logger.info("Returning InsuranceSuccess.jsp page after create");
	        	model.addAttribute("insurance", insurance);
	        	insurance.setActiveInd('Y');
	        	insurance.setCreatedBy("Mohanasundharam");
	 	      	insuranceService.save(insurance);
	 	       return "insuranceEditSuccess";
	 	      	
	        }
	       
	        return "insuranceEditSuccess";
        }    
    }
	
	@RequestMapping(value = "/insurance/save.do", method = RequestMethod.POST, params ={"delete"})
    public String deleteInsuranceAction( @Validated Insurance insurance,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
        	for( ObjectError oe :bindingResult.getAllErrors()){
        		System.out.println("oe "+oe.getObjectName() +""+oe.getCode());
        	}
            logger.info("Returning insuranceEdit.jsp page");
            return "insuranceEdit";
        }
        else
        {
	        	logger.info("Returning InsuranceSuccess.jsp page after update");
	        	insurance.setActiveInd('N');
	        	insuranceService.update(insurance);
	        return "insuranceEditSuccess";
        }    
    }
	
	@ModelAttribute("activeIndMap")
	public Map<String,String> populateActiveIndList() {
		//Data referencing for ActiveMap box
		return PrasUtil.getActiveIndMap();
	}
	
}
