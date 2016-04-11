package com.pfchoice.springmvc.controller;


import java.util.List;
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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.pfchoice.common.util.PrasUtil;
import com.pfchoice.core.entity.Insurance;
import com.pfchoice.core.entity.Provider;
import com.pfchoice.core.service.InsuranceService;
import com.pfchoice.core.service.ProviderService;


@Controller
@SessionAttributes({"username","userpath"})
public class ProviderController{
	
	@Autowired
	private InsuranceService insuranceService;
	 
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
	
	@ModelAttribute("insuranceList")
	public List<Insurance> populateInsuranceList() {
		
		//Data referencing for Insurance Measure list box
		List<Insurance> insuranceList = insuranceService.findAll();
		return insuranceList;
	}
	
	
	@RequestMapping(value = {"/admin/provider/new"})
    public String addProviderPage(final Model model) {
		
		Provider provider = createProviderModel();
		model.addAttribute("provider", provider);
        return "providerNew";
    }
 
	
	@RequestMapping(value = {"/admin/provider/{id}","/user/provider/{id}"}, method = RequestMethod.GET)
    public String updateProviderPage(@PathVariable Integer id,Model model) {
		
		Provider dbProvider = providerService.findById(id);
		logger.info("Returning provider.getId()"+dbProvider.getId());
			       
		model.addAttribute("provider", dbProvider);
		logger.info("Returning providerView.jsp page");
        return "providerDetails";
    }
	
	@RequestMapping(value = {"/admin/provider/{id}/details","/user/provider/{id}/details"}, method = RequestMethod.GET)
    public String viewProviderPage(@PathVariable Integer id,Model model) {
		
		Provider dbProvider = providerService.findById(id);
		logger.info("Returning provider.getId()"+dbProvider.getId());
			       
		model.addAttribute("provider", dbProvider);
		logger.info("Returning providerView.jsp page");
        return "providerEdit";
    }
	
	@RequestMapping(value = {"/admin/provider/save.do"}, method = RequestMethod.POST, params ={"add"})
    public String newProviderAction( @Validated Provider provider,
            BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
       
		if (bindingResult.hasErrors()) {
            logger.info("Returning providerEdit.jsp page");
            return "providerNew";
        }
		
    	logger.info("Returning ProviderSuccess.jsp page after create");
    	model.addAttribute("provider", provider);
    	provider.setCreatedBy(username);
    	provider.setUpdatedBy(username);
      	providerService.save(provider);
       return "providerNewSuccess";
    }
	
	@RequestMapping(value = {"/admin/provider/{id}/save.do"}, method = RequestMethod.POST, params ={"update"})
    public String updateProviderAction( @PathVariable Integer id,@Validated Provider provider,
            BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
        if (bindingResult.hasErrors()) {
        	provider.setActiveInd('Y');
            logger.info("Returning providerEdit.jsp page");
            return "providerEdit";
        }
        
        if ( provider.getId() != null)
        {
        	logger.info("Returning ProviderEditSuccess.jsp page after update");
        	provider.setUpdatedBy(username);
        	provider.setCreatedBy(username);
        	providerService.update(provider);
        	model.addAttribute("Message", "Provider Details Updated Successfully");
        }
        return "providerEditSuccess";
    }
	
	
	@RequestMapping(value = {"/admin/provider/{id}/save.do"}, method = RequestMethod.POST, params ={"delete"})
    public String deleteInsuranceAction(@PathVariable Integer id,  @ModelAttribute("username") String username) {
           
		Provider dbProvider = providerService.findById(id);
        dbProvider.setActiveInd(new Character('N'));
        dbProvider.setUpdatedBy(username);
        providerService.update(dbProvider);
        logger.info("Returning providerSuccess.jsp page after delete");
        return "providerEditSuccess";
    }
	
}
