package com.pfchoice.springmvc.controller;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pfchoice.common.CommonMessageContent;
import com.pfchoice.common.util.JsonConverter;
import com.pfchoice.core.entity.CPTMeasure;
import com.pfchoice.core.entity.HedisMeasure;
import com.pfchoice.core.entity.HedisMeasureRule;
import com.pfchoice.core.entity.ICDMeasure;
import com.pfchoice.core.service.CPTMeasureService;
import com.pfchoice.core.service.HedisMeasureRuleService;
import com.pfchoice.core.service.HedisMeasureService;
import com.pfchoice.core.service.ICDMeasureService;

import ml.rugal.sshcommon.page.Pagination;
import ml.rugal.sshcommon.springmvc.util.Message;

@Controller
public class HedisMeasureRuleController{
	
    @Autowired
    private HedisMeasureService hedisMeasureService;
    
    @Autowired
    private HedisMeasureRuleService hedisMeasureRuleService;
    
    @Autowired
    private CPTMeasureService cptMeasureService;
    
    @Autowired
    private ICDMeasureService icdMeasureService;
    
    @Autowired
    @Qualifier("hedisMeasureRuleValidator")
    private Validator validator;
 
    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }
    
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
	public String viewHedisMeasureRuleAction(Model model,
			HttpServletRequest request) throws Exception{
				logger.info("Returning view.jsp page after create");
				return "hedisMeasureRuleList";
	}
	
	@ResponseBody
	@RequestMapping(value = "/hedisMeasureRule/hedisMeasureRuleLists", method = RequestMethod.GET)
	public Message viewHedisMeasureRuleActionJsonTest(Model model,@RequestParam(required = false) Integer pageNo,
					@RequestParam(required = false) Integer pageSize,
					@RequestParam(required = false) String sSearch,
					@RequestParam(required = false) String sort,
					@RequestParam(required = false) String sortdir,
					HttpServletRequest request) throws Exception{
		
		Pagination pagination = hedisMeasureRuleService.getPage(pageNo, pageSize,	sSearch, sort, sortdir);

        return Message.successMessage(CommonMessageContent.HEDIS_RULE_LIST, JsonConverter.getJsonObject(pagination));
    }
	
	
	@RequestMapping(value = "/hedisMeasureRule/save.do", method = RequestMethod.POST, params ={"add"})
	public String addHedisMeasureAction(@ModelAttribute("hedisMeasureRule")  @Validated HedisMeasureRule hedisMeasureRule,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            logger.info("Returning hedisMeasureRuleEdit.jsp page");
            return "hedisMeasureRuleEdit";
        }
        
	 	model.addAttribute("hedisMeasureRule", hedisMeasureRule);
	 	hedisMeasureRule.setCreatedBy("sarath");
	 	hedisMeasureRule.setUpdatedBy("sarath");
	 	Date d = new Date();
	 	hedisMeasureRule.setActiveInd('Y');
    	
    	logger.info("Returning hedisMeasureRuleEditSuccess.jsp page after create");
      	hedisMeasureRuleService.save(hedisMeasureRule);
   
    return "hedisMeasureRuleEditSuccess";
    }
	
	
	@RequestMapping(value = "/hedisMeasureRule/save.do", method = RequestMethod.POST, params ={"update"})
	public String saveHedisMeasureRuleAction(@ModelAttribute("hedisMeasureRule") @Validated HedisMeasureRule hedisMeasureRule,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
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
	public String deleteHedisMeasureAction(@ModelAttribute("hedisMeasureRule") @Validated HedisMeasureRule hedisMeasureRule,
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
