package com.pfchoice.springmvc.controller;


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import com.pfchoice.common.util.PrasUtil;
import com.pfchoice.core.entity.Provider;
import com.pfchoice.core.service.ProviderService;

@Controller
public class ProviderController{
	
    @Autowired
    private ProviderService providerService;
    
    @Autowired
    @Qualifier("providerValidator")
    private Validator validator;
 
    @InitBinder("provider")
    private void initBinder(final WebDataBinder binder) {
        binder.setValidator(validator);
    }
    
    private static final Logger logger = LoggerFactory
            .getLogger(ProviderController.class);
 
	
	@ModelAttribute("provider")
    public Provider createProviderModel() {
        // ModelAttribute value should be same as used in the empSave.jsp
        return new Provider();
    }
	
	@ModelAttribute("activeIndMap")
	public Map<String,String> populateActiveIndList() {
		//Data referencing for ActiveMap box
		return PrasUtil.getActiveIndMap();
	}
	
	@RequestMapping(value = "/provider/new")
    public String addProviderPage(final Model model) {
		
		Provider provider = createProviderModel();
		provider.setCreatedBy("sarath");
		model.addAttribute("provider", provider);
        return "providerNew";
    }
 
	
	@RequestMapping(value = "/provider/{id}", method = RequestMethod.GET)
    public String updateProviderPage(@PathVariable Integer id,Model model) {
		
		Provider dbProvider = providerService.findById(id);
		logger.info("Returning provider.getId()"+dbProvider.getId());
			       
		model.addAttribute("provider", dbProvider);
			
        logger.info("Returning providerSave.jsp page");
        return "providerEdit";
    }
	
	@RequestMapping(value = "/provider/save.do", method = RequestMethod.POST, params ={"add"})
    public String newProviderAction( @Validated Provider provider,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            logger.info("Returning providerEdit.jsp page");
            return "providerNew";
        }
        else
        {
	        	logger.info("Returning ProviderSuccess.jsp page after create");
	        	model.addAttribute("provider", provider);
	        	provider.setActiveInd('Y');
	        	provider.setCreatedBy("Mohanasundharam");
	 	      	providerService.save(provider);
	 	       return "providerNewSuccess";
        }    
    }
	
	@RequestMapping(value = "/provider/{id}/save.do", method = RequestMethod.POST, params ={"update"})
    public String updateProviderAction( @PathVariable Integer id,@Validated Provider provider,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
        	provider.setActiveInd('Y');
            logger.info("Returning providerEdit.jsp page");
            return "providerEdit";
        }
        else
        {
	        if (null != provider.getId())
	        {
	        	logger.info("Returning ProviderSuccess.jsp page after update");
	        	provider.setUpdatedBy("Mohanasundharam");
	        	provider.setActiveInd('Y');
	        	providerService.update(provider);
	        }
	        return "providerEditSuccess";
        }    
    }
	
	
	@RequestMapping(value = "/provider/{id}/save.do", method = RequestMethod.POST, params ={"delete"})
    public String deleteInsuranceAction(@PathVariable Integer id, @Validated Provider provider,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
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
	
}
