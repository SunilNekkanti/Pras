package com.pfchoice.springmvc.controller;



import java.util.List;


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
import org.springframework.web.servlet.ModelAndView;


import com.pfchoice.core.entity.Contract;
import com.pfchoice.core.entity.Provider;
import com.pfchoice.core.entity.ReferenceContract;
import com.pfchoice.core.service.ContractService;
import com.pfchoice.core.service.ProviderService;


@Controller
public class ContractController{
	
    @Autowired
    ContractService contractService;
    
     
    
    @Autowired
    ProviderService providerService;
    

    private static final Logger logger = LoggerFactory
            .getLogger(ContractController.class);
 
	public ContractController() {
    }
	
	
	@ModelAttribute("contract")
    public Contract createContractModel() {
        // ModelAttribute value should be same as used in the empSave.jsp
        return new Contract();
    }
	
	@ModelAttribute("refContract")
    public ReferenceContract createRefContractModel() {
        // ModelAttribute value should be same as used in the empSave.jsp
        return new ReferenceContract();
    }
	
 
	@RequestMapping(value = "/provider/{id}/contract/new")
    public String addContractPage(Model model) {
		
		Contract contract = createContractModel();
		contract.setCreatedBy("sarath");
	
		model.addAttribute("contract", contract);
        return "contractEdit";
    }
	
	@RequestMapping(value = "/provider/{id}/contract", method = RequestMethod.GET)
    public String updateProviderContractPage(@PathVariable Integer id,Model model) {
		Contract dbContract = contractService.findActiveContractByRefId("provider",id);
		// logger.info("Returning contract.getId()"+dbContract.getId());
	       if(dbContract == null){
	    	   dbContract = createContractModel();
	       }
		model.addAttribute("contract", dbContract);
        logger.info("Returning contractEdit.jsp page");
        return "contractEdit";
    }
	
	@RequestMapping(value = "/provider/{id}/contractDisplay", method = RequestMethod.GET)
    public String displayProviderContractPage(@PathVariable Integer id,Model model) {
		Contract dbContract = contractService.findActiveContractByRefId("provider", id);
		 logger.info("Returning contract.getId()"+dbContract.getId());
	       
		model.addAttribute("contract", dbContract);
        logger.info("Returning contractDisplay.jsp page");
        return "contractDisplay";
    }
	
	@RequestMapping(value = "/provider/{id}/contractList")
    public ModelAndView handleRequest(@PathVariable Integer id) throws Exception {
 
    	List<Contract> listBean = contractService.findAllContractsByRefId("provider",id);
    	System.out.println("contract list bean sze "+listBean.size());
		ModelAndView modelAndView = new ModelAndView("contractList");
		modelAndView.addObject("contractList", listBean);
 
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/provider/{id}/contract/save.do", method = RequestMethod.POST)
	public String saveproviderContractAction(@PathVariable Integer id, @Validated Contract contract,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
        	for( ObjectError oe :bindingResult.getAllErrors()){
        		System.out.println("oe "+oe.getObjectName() +""+oe.getCode());
        	}
            logger.info("Returning contractEdit.jsp page");
            return "contractEdit";
        }
        else
        {
	        
	        if (null != contract.getId())
	        {
	        	logger.info("Returning ContractEditSuccess.jsp page after update");
	        	contractService.update(contract);
	        }
	        else
	        {
	        	
	        	Provider dbProvider = providerService.findById(id);
	   		 	logger.info("Returning provider.getId()"+dbProvider.getId());

	   		 	model.addAttribute("contract", contract);
	        	contract.setCreatedBy("sarath");
	        	ReferenceContract refContract = createRefContractModel();
	        	refContract.setCreatedBy("sarath");
	        	refContract.setPrvdr(dbProvider);
	        	contract.setReferenceContract(refContract);
	        	
	        	logger.info("Returning contractEditSuccess.jsp page after create");
	 	      	contractService.save(contract);
	        }
	       
	        return "contractEditSuccess";
        }    
    }
	
	
	

}
