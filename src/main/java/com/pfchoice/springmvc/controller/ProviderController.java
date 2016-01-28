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
import com.pfchoice.core.entity.Provider;
import com.pfchoice.core.entity.ProviderContract;
import com.pfchoice.core.entity.ReferenceContract;
import com.pfchoice.core.service.ContractService;
import com.pfchoice.core.service.ProviderContractService;
import com.pfchoice.core.service.ProviderService;

@Controller
public class ProviderController{
	
    @Autowired
    ProviderService providerService;
    
    @Autowired
    ProviderContractService providerContractService;
  
    @Autowired
    ContractService contractService;
    
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
    public Provider createProviderModel() {
        // ModelAttribute value should be same as used in the empSave.jsp
        return new Provider();
    }
	
	@ModelAttribute("refContract")
    public ReferenceContract createRefContractModel() {
        // ModelAttribute value should be same as used in the empSave.jsp
        return new ReferenceContract();
    }
	
	@RequestMapping(value = "/provider/new")
    public String addProviderPage(Model model) {
		
		Provider provider = createProviderModel();
		provider.setCreatedBy("sarath");
		model.addAttribute("provider", provider);
        return "providerEdit";
    }
	
 
	
	@RequestMapping(value = "/provider/{id}", method = RequestMethod.GET)
    public String updateProviderPage(@PathVariable Integer id,Model model) {
		
		Provider dbProvider = providerService.findById(id);
		 logger.info("Returning provider.getId()"+dbProvider.getId());
		 
			       
		model.addAttribute("provider", dbProvider);
		ProviderContract providerContract = new ProviderContract();
		model.addAttribute("providerContract", providerContract);
		
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
        else
        {
	        
	        if (null != provider.getId())
	        {
	        	logger.info("Returning ProviderSuccess.jsp page after update");
	        	providerService.update(provider);
	        }
	        else
	        {
	        	logger.info("Returning ProviderSuccess.jsp page after create");
	        	model.addAttribute("provider", provider);
	        	provider.setCreatedBy("Mohanasundharam");
	 	      	providerService.save(provider);
	 	       return "providerEditSuccess";
	 	      	
	        }
	       
	        return "providerEditSuccess";
        }    
    }
	
	
	@RequestMapping(value = "/provider/save.do", method = RequestMethod.POST, params ={"delete"})
    public String deleteInsuranceAction( @Validated Provider provider,
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
	        	provider.setActiveInd('N');
	        	providerService.update(provider);
	        return "providerEditSuccess";
        }    
    }
	
	@ModelAttribute("activeIndMap")
	public Map<String,String> populateActiveIndList() {
		//Data referencing for ActiveMap box
		return PrasUtil.getActiveIndMap();
	}
	
}
