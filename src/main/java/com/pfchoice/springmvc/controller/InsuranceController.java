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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.pfchoice.common.util.PrasUtil;
import com.pfchoice.core.entity.Insurance;
import com.pfchoice.core.entity.InsuranceProvider;
import com.pfchoice.core.entity.PlanType;
import com.pfchoice.core.entity.Provider;
import com.pfchoice.core.service.InsuranceService;
import com.pfchoice.core.service.PlanTypeService;

@Controller
@SessionAttributes("username")
public class InsuranceController{
	
	private static final Logger logger = LoggerFactory
	            .getLogger(InsuranceController.class);
	   
    @Autowired
    private InsuranceService insuranceService;
    
    @Autowired
    private PlanTypeService planTypeService;
   
    @Autowired
    @Qualifier("insuranceValidator")
    private Validator validator;
 
    @InitBinder("insurance")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }
 
	@ModelAttribute("insurance")
    public Insurance createInsuranceModel() {
        // ModelAttribute value should be same as used in the empSave.jsp
        return new Insurance();
    }
	
	@ModelAttribute("planTypeList")
	public List<PlanType> populatePlanTypeList() {
		
		//Data referencing for county list box
		List<PlanType> planTypeList = planTypeService.findAll();
		return planTypeList;
	}
	
	@RequestMapping(value = "/insurance/new")
    public String addInsurancePage(Model model) {
		
		Insurance insurance = createInsuranceModel();
		model.addAttribute("insurance", insurance);
        return "insuranceNew";
    }
	
	@RequestMapping(value = "/insurance/{id}", method = RequestMethod.GET)
    public String updateInsurancePage(@PathVariable Integer id,Model model, @ModelAttribute("username") String username) {
		
		Insurance dbInsurance = insuranceService.findById(id);
		 logger.info("Returning insurance.getId()"+dbInsurance.getId());
			       
		model.addAttribute("insurance", dbInsurance);
		 logger.info("Returning insuranceSave.jsp page");
        return "insuranceDetails";
    }
	
	@RequestMapping(value = "/insurance/save.do", method = RequestMethod.POST, params ={"add"})
    public String newInsuranceAction(@Validated Insurance insurance,
            BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
       
		if (bindingResult.hasErrors()) {
        	logger.info("Returning insuranceEdit.jsp page");
            return "insuranceNew";
        }
        
    	logger.info("Returning InsuranceSuccess.jsp page after create");
    	model.addAttribute("insurance", insurance);
    	insurance.setCreatedBy(username);
    	insurance.setUpdatedBy(username);
      	insuranceService.save(insurance);
       return "insuranceNewSuccess";
    }
	
	@RequestMapping(value = "/insurance/{id}/details", method = RequestMethod.GET)
    public String viewProviderPage(@PathVariable Integer id,Model model) {
		
		Insurance dbInsurance = insuranceService.findById(id);
		 logger.info("Returning insurance.getId()"+dbInsurance.getId());
			       
		model.addAttribute("insurance", dbInsurance);
		 logger.info("Returning insuranceSave.jsp page");
       return "insuranceEdit";
    }
	
	@RequestMapping(value = "/insurance/{id}/save.do", method = RequestMethod.POST, params ={"update"})
    public String updateInsuranceAction( @PathVariable Integer id,@ModelAttribute @Validated Insurance insurance,
            BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		
        if (bindingResult.hasErrors()) {
        	logger.info("Returning insuranceEdit.jsp page");
        	insurance.setActiveInd('Y');
            return "insuranceEdit";
        }
	        
        if (null != insurance.getId())
        {
        	logger.info("Returning InsuranceSuccess.jsp page after update");
        	insurance.setUpdatedBy(username);
        	insuranceService.update(insurance);
        }
           
        return "insuranceEditSuccess";
    }
	
	@RequestMapping(value = "/insurance/{id}/save.do", method = RequestMethod.POST, params ={"delete"})
    public String deleteInsuranceAction(@PathVariable Integer id, @ModelAttribute("username") String username) {
        
		Insurance dbInsurance = insuranceService.findById(id);
		dbInsurance.setActiveInd(new Character('N'));
		dbInsurance.setUpdatedBy(username);
	    insuranceService.update(dbInsurance);
        logger.info("Returning InsuranceDeleteSuccess.jsp page after delete");
        return "insuranceDeleteSuccess";
    }
	
	@ModelAttribute("activeIndMap")
	public Map<String,String> populateActiveIndList() {
		//Data referencing for ActiveMap box
		return PrasUtil.getActiveIndMap();
	}
	
}
