package com.pfchoice.springmvc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.pfchoice.core.entity.Contract;
import com.pfchoice.core.entity.Insurance;
import com.pfchoice.core.entity.Provider;
import com.pfchoice.core.entity.ReferenceContract;
import com.pfchoice.core.service.ContractService;
import com.pfchoice.core.service.InsuranceService;
import com.pfchoice.core.service.ProviderService;


@Controller
@SessionAttributes("username")
public class ContractController{
	
    @Autowired
    private ContractService contractService;
    
    @Autowired
    private ProviderService providerService;
    
    @Autowired
    private InsuranceService insuranceService;
    
    @Autowired
    @Qualifier("contractValidator")
    private Validator validator;
 
    @InitBinder("contract")
    private void initBinder(final WebDataBinder binder) {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    	dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.setValidator(validator);
    }
    
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
    public String addContractPage(@PathVariable Integer id,Model model) {
		
		Contract contract = createContractModel();
	
		model.addAttribute("contract", contract);
        return "providerContractEdit";
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
        return "providerContractEdit";
    }
	
	@RequestMapping(value = "/provider/{id}/contractList")
    public String handleRequest(@PathVariable Integer id, Model model) throws Exception {
 
    	List<Contract> listBean = contractService.findAllContractsByRefId("provider",id);
		model.addAttribute("contractList", listBean);
 
		return "providerContractList";
	}
	
	@RequestMapping(value = "/provider/{id}/contractDisplay", method = RequestMethod.GET)
    public String displayProviderContractPage(@PathVariable Integer id,Model model) {
		Contract dbContract = contractService.findActiveContractByRefId("provider", id);
		 logger.info("Returning contract.getId()"+dbContract.getId());
	       
		model.addAttribute("contract", dbContract);
        logger.info("Returning contractDisplay.jsp page");
        return "contractDisplay";
    }
	
	@RequestMapping(value = "/provider/{id}/contract/save.do", method = RequestMethod.POST, params ={"add"})
	public String newproviderContractAction(@PathVariable Integer id, @Validated Contract contract,
            BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
        if (bindingResult.hasErrors()) {
        	
            logger.info("Returning contractEdit.jsp page");
            return "providerContractEdit";
        }
        else
        {
	        	Provider dbProvider = providerService.findById(id);
	   		 	logger.info("Returning provider.getId()"+dbProvider.getId());
	   		 	model.addAttribute("contract", contract);
	   		   	contract.setCreatedBy(username);
	   		   	contract.setUpdatedBy(username);
	        	ReferenceContract refContract = createRefContractModel();
	        	refContract.setCreatedBy(username);
	        	refContract.setUpdatedBy(username);
	        	refContract.setPrvdr(dbProvider);
	        	contract.setReferenceContract(refContract);
	        	
	        	logger.info("Returning contractEditSuccess.jsp page after create");
	 	      	contractService.save(contract);
	       
	       
	        return "providerContractEditSuccess";
        }    
    }
	
	@RequestMapping(value = "/provider/{id}/contract/save.do", method = RequestMethod.POST,params ={"update"})
	public String saveproviderContractAction(@PathVariable Integer id, @Validated Contract contract,
            BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
        if (bindingResult.hasErrors()) {
            logger.info("Returning contractEdit.jsp page");
            contract.setActiveInd('Y');
            return "providerContractEdit";
        }
        else
        {
	        if (null != contract.getId())
	        {
	        	logger.info("Returning ContractEditSuccess.jsp page after update");
	        	contract.setUpdatedBy(username);
	        	contract.getReferenceContract().setUpdatedBy(username);
	        	contractService.update(contract);
	        }
	       
	        return "providerContractEditSuccess";
        }    
    }
	
	@RequestMapping(value = "/provider/{id}/contract/{cntId}", method = RequestMethod.GET)
    public String updateProviderContractPage(@PathVariable Integer id,@PathVariable Integer cntId,Model model) {
		Contract dbContract = contractService.findById(cntId);
		// logger.info("Returning contract.getId()"+dbContract.getId());
	       if(dbContract == null){
	    	   dbContract = createContractModel();
	       }
		model.addAttribute("contract", dbContract);
        logger.info("Returning contractEdit.jsp page");
        return "providerContractEdit";
    }
	
	
	@RequestMapping(value = "/provider/{id}/contract/save.do", method = RequestMethod.POST, params ={"delete"})
	public String deleteMembershipContractAction(@PathVariable Integer id, @Validated Contract contract,
            BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
       
			if (bindingResult.hasErrors()) {
				contract.setActiveInd('Y');
	            logger.info("Returning insuranceContractEdit.jsp page");
	             return "providerContractEdit";
	        }
	        if (null != contract.getId())
	        {
	        	logger.info("Returning ContractEditSuccess.jsp page after update");
	        	contract.setUpdatedBy(username);
	        	contract.getReferenceContract().setUpdatedBy(username);
	        	contract.setActiveInd('N');
	        	contractService.update(contract);
	        }
	        return "providerContractEditSuccess";
    }
	
	
	@RequestMapping(value = "/insurance/{id}/contractList")
    public String handleInsuranceRequest(@PathVariable Integer id,Model model) throws Exception {
 
    	List<Contract> listBean = contractService.findAllContractsByRefId("insurance",id);
		model.addAttribute("contractList", listBean);
 
		return "insuranceContractList";
	}
	
	/** contract Display where activeInd =N  **/
	
	@RequestMapping(value = "/provider/{id}/contract/{cntId}/display", method = RequestMethod.GET)
    public String displayProviderContractPage(@PathVariable Integer id,@PathVariable Integer cntId,Model model) {
		Contract dbContract = contractService.findById(cntId);
		logger.info("Returning contract.getId()"+dbContract.getId());
		model.addAttribute("contract", dbContract);
        logger.info("Returning contractDisplay.jsp page");
        return "providerContractEdit";
    }
	/**  End of contract display **/
	
	
	/* -- Insurance Contract   */
	
		@RequestMapping(value = "/insurance/{id}/contract/new")
	    public String addProviderContractPage(Model model) {
			Contract contract = createContractModel();
			model.addAttribute("contract", contract);
	        return "insuranceContractEdit";
	    }
		
		@RequestMapping(value = "/insurance/{id}/contract/save.do", method = RequestMethod.POST, params ={"add"})
		public String addMembershipContractAction(@PathVariable Integer id, @Validated Contract contract,
	            BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
				
	        if (bindingResult.hasErrors()) {
	            logger.info("Returning insuranceContractEdit.jsp page");
	             return "insuranceContractEdit";
	        }
	        Insurance dbInsurance = insuranceService.findById(id);
   		 	logger.info("Returning insurance.getId()"+dbInsurance.getId());
	        model.addAttribute("contract", contract);
	    	contract.setCreatedBy(username);
	    	contract.setUpdatedBy(username);
	    	ReferenceContract refCnt = createRefContractModel();
	    	refCnt.setCreatedBy(username);
	    	refCnt.setUpdatedBy(username);
	    	refCnt.setIns(dbInsurance);
	    	contract.setReferenceContract(refCnt);
	    	
	    	logger.info("Returning insuranceContractEditSuccess.jsp page after create");
	      	contractService.save(contract);
	   
	      	return "insuranceContractEditSuccess";
	    }
		
		@RequestMapping(value = "/insurance/{id}/contract/save.do", method = RequestMethod.POST, params ={"update"})
		public String saveMembershipContractAction(@PathVariable Integer id, @Validated Contract contract,
	            BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
			
	        if (bindingResult.hasErrors()) {
	            logger.info("Returning insuranceContractEdit.jsp page");
	            contract.setActiveInd('Y');
	            return "insuranceContractEdit";
	        }
		        
	        if (null != contract.getId())
	        {
	        	logger.info("Returning ContractEditSuccess.jsp page after update");
	        	contract.setUpdatedBy(username);
	        	contract.getReferenceContract().setUpdatedBy(username);
	        	contractService.update(contract);
	        	return "insuranceContractEditSuccess";
	        }
	       
	        return "insuranceContractEdit";
	    }
		
		@RequestMapping(value = "/insurance/{id}/contract/save.do", method = RequestMethod.POST, params ={"delete"})
		public String deleteInsuranceContractAction(@PathVariable Integer id, @Validated Contract contract,
	            BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
	
			if (bindingResult.hasErrors())
			{
	            logger.info("Returning insuranceContractEdit.jsp page");
	            contract.setActiveInd('Y');
	            return "insuranceContractEdit";
	        }
	            
		        if (null != contract.getId())
		        {
		        	logger.info("Returning ContractEditSuccess.jsp page after update");
		        	contract.setUpdatedBy(username);
		        	contract.getReferenceContract().setUpdatedBy(username);
		        	contractService.update(contract);
		        	return "insuranceContractEditSuccess";
		        }
		        return "insuranceContractEdit";
		        
	    }
		
		@RequestMapping(value = "/insurance/{id}/contract/{cntId}", method = RequestMethod.GET)
	    public String updateInsuranceContractPage(@PathVariable Integer id,@PathVariable Integer cntId,Model model) {
			Contract dbContract = contractService.findById(cntId);
			// logger.info("Returning contract.getId()"+dbContract.getId());
		       if(dbContract == null){
		    	   dbContract = createContractModel();
		       }
			model.addAttribute("contract", dbContract);
	        logger.info("Returning insuranceContractEdit.jsp page");
	        return "insuranceContractEdit";
	    }
		
		@RequestMapping(value = "/insurance/{id}/contract/{cntId}/display", method = RequestMethod.GET)
	    public String displayInsuranceContractPage(@PathVariable Integer id,@PathVariable Integer cntId,Model model) {
			Contract dbContract = contractService.findActiveContractByRefId("insurance", id);
			 logger.info("Returning contract.getId()"+dbContract.getId());
		       
			model.addAttribute("contract", dbContract);
	        logger.info("Returning contractDisplay.jsp page");
	        return "insuranceContractEdit";
	    }
	
	/* -- End of Insurance Contract   */

}
