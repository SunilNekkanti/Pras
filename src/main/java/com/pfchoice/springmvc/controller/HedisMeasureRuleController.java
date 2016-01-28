package com.pfchoice.springmvc.controller;


import java.util.Date;
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

import com.pfchoice.core.entity.CPTMeasure;
import com.pfchoice.core.entity.HedisMeasure;
import com.pfchoice.core.entity.HedisMeasureRule;
import com.pfchoice.core.entity.ICDMeasure;
import com.pfchoice.core.service.CPTMeasureService;
import com.pfchoice.core.service.HedisMeasureRuleService;
import com.pfchoice.core.service.HedisMeasureService;
import com.pfchoice.core.service.ICDMeasureService;

@Controller
public class HedisMeasureRuleController{
	
    @Autowired
    HedisMeasureService hedisMeasureService;
    
    @Autowired
    HedisMeasureRuleService hedisMeasureRuleService;
    
    @Autowired
    CPTMeasureService cptMeasureService;
    
    @Autowired
    ICDMeasureService icdMeasureService;
    
   /* @Autowired
    @Qualifier("hedisMeasureRuleValidator")
    private Validator validator;
 
    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }*/
    
    private static final Logger logger = LoggerFactory
            .getLogger(HedisMeasureRuleController.class);
 
	public HedisMeasureRuleController() {
    }
	
	
	@ModelAttribute("hedisMeasureRule")
    public HedisMeasureRule createHedisMeasureRuleModel() {
        // ModelAttribute value should be same as used in the hedisMeasureEdit.jsp
        return new HedisMeasureRule();
    }
	
	@ModelAttribute("hedisMeasureList")
	public List<HedisMeasure> populateHedisMeasureList() {
		
		//Data referencing for Hedis Measure list box
		List<HedisMeasure> hedisMeasureList = hedisMeasureService.findAll();
		return hedisMeasureList;
	}
	
	@ModelAttribute("cptMeasureList")
	public List<CPTMeasure> populateCPTMeasureList() {
		
		//Data referencing for CPT Measure list box
		List<CPTMeasure> cptMeasureList = cptMeasureService.findAll();
		return cptMeasureList;
	}

	@ModelAttribute("icdMeasureList")
	public List<ICDMeasure> populateICDMeasureList() {
		
		//Data referencing for ICD Measure list box
		List<ICDMeasure> icdMeasureList = icdMeasureService.findAll();
		return icdMeasureList;
	}
	
	@RequestMapping(value = "/hedisMeasureRule/new")
    public String addHedisMeasureRulePage(Model model) {
		
		HedisMeasureRule hedisMeasureRule = createHedisMeasureRuleModel();
		hedisMeasureRule.setCreatedBy("sarath");
		hedisMeasureRule.setUpdatedBy("sarath");
		model.addAttribute("hedisMeasureRule", hedisMeasureRule);
        return "hedisMeasureRuleEdit";
    }
	
	@RequestMapping(value = "/hedisMeasureRule/{id}", method = RequestMethod.GET)
    public String updateHedisMeasureRulePage(@PathVariable Integer id,Model model) {
		HedisMeasureRule dbHedisMeasureRule = hedisMeasureRuleService.findById(id);
	    logger.info("Returning hedisMeasureRule.getId()"+dbHedisMeasureRule.getId());
		model.addAttribute("hedisMeasureRule", dbHedisMeasureRule);
        logger.info("Returning hedisMeasureRuleEdit.jsp page");
        return "hedisMeasureRuleEdit";
    }
		
	@RequestMapping(value = "/hedisMeasureRule/{id}/display", method = RequestMethod.GET)
    public String displayHedisMeasureRulePage(@PathVariable Integer id,Model model) {
		HedisMeasureRule dbHedisMeasureRule = hedisMeasureRuleService.findById(id);
		 logger.info("Returning hedisMeasureRule.getId()"+dbHedisMeasureRule.getId());
	       
		model.addAttribute("hedisMeasureRule", dbHedisMeasureRule);
        logger.info("Returning hedisMeasureRuleDisplay.jsp page");
        return "hedisMeasureRuleDisplay";
    }
	
	@RequestMapping(value = "/hedisMeasureRule/hedisMeasureRuleList")
    public ModelAndView hedisMeasureRuleList() throws Exception {
 
    	List<HedisMeasureRule> listBean = hedisMeasureRuleService.findAll();
		ModelAndView modelAndView = new ModelAndView("hedisMeasureRuleList");
		modelAndView.addObject("hedisMeasureRuleList", listBean);
 
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/hedisMeasureRule/save.do", method = RequestMethod.POST, params ={"add"})
	public String addHedisMeasureAction(@Validated HedisMeasureRule hedisMeasureRule,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
        	for( ObjectError oe :bindingResult.getAllErrors()){
        		System.out.println("oe "+oe.getObjectName() +""+oe.getCode());
        	}
            logger.info("Returning hedisMeasureRuleEdit.jsp page");
            return "hedisMeasureRuleEdit";
        }
        
	 	model.addAttribute("hedisMeasureRule", hedisMeasureRule);
	 	hedisMeasureRule.setCreatedBy("sarath");
	 	hedisMeasureRule.setUpdatedBy("sarath");
	 	Date d = new Date();
	 	hedisMeasureRule.setDueDate( new java.sql.Date(d.getTime()));
	 	hedisMeasureRule.setActiveInd('Y');
    	
    	logger.info("Returning hedisMeasureRuleEditSuccess.jsp page after create");
      	hedisMeasureRuleService.save(hedisMeasureRule);
   
    return "hedisMeasureRuleEditSuccess";
    }
	
	
	@RequestMapping(value = "/hedisMeasureRule/save.do", method = RequestMethod.POST, params ={"update"})
	public String saveHedisMeasureRuleAction(@Validated HedisMeasureRule hedisMeasureRule,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
        	for( ObjectError oe :bindingResult.getAllErrors()){
        		System.out.println("oe "+oe.getObjectName() +""+oe.getCode());
        	}
            logger.info("Returning  hedisMeasureRuleEdit.jsp page");
            return "hedisMeasureRuleEdit";
        }
	        
        if (null != hedisMeasureRule.getId())
        {
        	logger.info("Returning hedisMeasureRuleEditSuccess.jsp page after update");
        	hedisMeasureRule.setActiveInd('Y');
        	hedisMeasureRuleService.update(hedisMeasureRule);
          	return "hedisMeasureRuleEditSuccess";
        }
       
        return "hedisMeasureRuleEdit";
    }
	

	@RequestMapping(value = "/hedisMeasureRule/save.do", method = RequestMethod.POST, params ={"delete"})
	public String deleteHedisMeasureAction(@Validated HedisMeasureRule hedisMeasureRule,
            BindingResult bindingResult, Model model) {
       
            
	        if (null != hedisMeasureRule.getId())
	        {
	        	logger.info("Returning hedisMeasureRuleEditSuccess.jsp page after update");
	        	hedisMeasureRule.setActiveInd('N');
	        	hedisMeasureRuleService.update(hedisMeasureRule);
	        	return "redirect:hedisMeasureRuleList";
	        }
	        return "hedisMeasureRuleEdit";
    }
}
