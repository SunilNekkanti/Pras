package com.pfchoice.springmvc.controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.pfchoice.common.CommonMessageContent;
import com.pfchoice.common.util.JsonConverter;
import com.pfchoice.common.util.PrasUtil;
import com.pfchoice.core.entity.CPTMeasure;
import com.pfchoice.core.entity.Gender;
import com.pfchoice.core.entity.HedisMeasure;
import com.pfchoice.core.entity.HedisMeasureRule;
import com.pfchoice.core.entity.ICDMeasure;
import com.pfchoice.core.entity.Insurance;
import com.pfchoice.core.service.CPTMeasureService;
import com.pfchoice.core.service.GenderService;
import com.pfchoice.core.service.HedisMeasureRuleService;
import com.pfchoice.core.service.HedisMeasureService;
import com.pfchoice.core.service.ICDMeasureService;
import com.pfchoice.core.service.InsuranceService;

import ml.rugal.sshcommon.page.Pagination;
import ml.rugal.sshcommon.springmvc.util.Message;


@Controller
@SessionAttributes({"username","userpath"})
public class HedisMeasureRuleController{
	
	private static final Logger logger = LoggerFactory
            .getLogger(HedisMeasureRuleController.class);
 
    @Autowired
    private HedisMeasureService hedisMeasureService;
    
    @Autowired
    private HedisMeasureRuleService hedisMeasureRuleService;
    
    @Autowired
    private CPTMeasureService cptMeasureService;
    
    @Autowired
    private ICDMeasureService icdMeasureService;
   
    @Autowired
    private GenderService genderService;
    
    @Autowired
    private InsuranceService insuranceService;
    
    @Autowired
    @Qualifier("hedisMeasureRuleValidator")
    private Validator validator;
 
    @InitBinder("hedisMeasureRule")
    private void initBinder(WebDataBinder binder) {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    	dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.setValidator(validator);
    }
    
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
	
	@ModelAttribute("cptMeasureListAjax")
	public List<CPTMeasure> populateCPTMeasureListAjax() {
		
		//Data referencing for CPT Measure list box
		List<CPTMeasure> cptMeasureList = new ArrayList<CPTMeasure>();
		return cptMeasureList;
	}
	
	@ModelAttribute("genderList")
	public List<Gender> populateGenderList() {
		
		//Data referencing for gender list box
		List<Gender> genderList = genderService.findAll();
		return genderList;
	}

	@ModelAttribute("icdMeasureListAjax")
	public List<ICDMeasure> populateICDMeasureListAjax() {
		
		//Data referencing for ICD Measure list box
		List<ICDMeasure> icdMeasureList = new ArrayList<ICDMeasure>();
		return icdMeasureList;
	}
	
	@ModelAttribute("icdMeasureList")
	public List<ICDMeasure> populateICDMeasureList() {
		
		//Data referencing for ICD Measure list box
		List<ICDMeasure> icdMeasureList = icdMeasureService.findAll();
		return icdMeasureList;
	}
	
	@ModelAttribute("insuranceList")
	public List<Insurance> populateInsuranceList() {
		
		//Data referencing for Insurance Measure list box
		List<Insurance> insuranceList = insuranceService.findAll();
		return insuranceList;
	}
	
	@ModelAttribute("effYearList")
	public SortedSet<Integer> populateEffectiveYearList() {
		//Data referencing for ActiveMap box
		return PrasUtil.getHedisEffectiveYearList();
	}
	
	@RequestMapping(value = "/admin/hedisMeasureRule/new")
    public String addHedisMeasureRulePage(Model model) {
		
		HedisMeasureRule hedisMeasureRule = createHedisMeasureRuleModel();
		model.addAttribute("hedisMeasureRule", hedisMeasureRule);
        return "hedisMeasureRuleNew";
    }
	
	@RequestMapping(value = {"/admin/hedisMeasureRule/{id}","/user/hedisMeasureRule/{id}"}, method = RequestMethod.GET)
    public String updateHedisMeasureRulePage(@PathVariable Integer id,Model model) {
	
		HedisMeasureRule dbHedisMeasureRule = hedisMeasureRuleService.findById(id);
	    logger.info("Returning hedisMeasureRule.getId()"+dbHedisMeasureRule.getId());
	    
	    Set<CPTMeasure> cptMeasureList = dbHedisMeasureRule.getCptCodes();
	    Set<ICDMeasure> icdMeasureList = dbHedisMeasureRule.getIcdCodes();
	    
		model.addAttribute("cptMeasureListAjax", cptMeasureList);
		model.addAttribute("icdMeasureListAjax", icdMeasureList);
		model.addAttribute("hedisMeasureRule", dbHedisMeasureRule);
        logger.info("Returning hedisMeasureRuleEdit.jsp page");
        return "hedisMeasureRuleEdit";
    }
	
	@RequestMapping(value = "admin/hedisMeasureRuleAjax/{id}", method = RequestMethod.GET)
    public String updateHedisMeasureRuleAjaxPage(@PathVariable Integer id,Model model) {
	
		HedisMeasureRule dbHedisMeasureRule = hedisMeasureRuleService.findById(id);
	    logger.info("Returning hedisMeasureRule.getId()"+dbHedisMeasureRule.getId());
	    
	    Set<CPTMeasure> cptMeasureList = dbHedisMeasureRule.getCptCodes();
	    Set<ICDMeasure> icdMeasureList = dbHedisMeasureRule.getIcdCodes();
	    
		model.addAttribute("hedisMeasureRule", dbHedisMeasureRule);
		model.addAttribute("cptMeasureListAjax", cptMeasureList);
		model.addAttribute("icdMeasureListAjax", icdMeasureList);
        logger.info("Returning hedisMeasureRuleEditAjax.jsp page");
        return "hedisMeasureRuleEditAjax";
    }
	
		
	@RequestMapping(value = "/admin/hedisMeasureRule/{id}/display", method = RequestMethod.GET)
    public String displayHedisMeasureRulePage(@PathVariable Integer id,Model model) {
	
		HedisMeasureRule dbHedisMeasureRule = hedisMeasureRuleService.findById(id);
		 logger.info("Returning hedisMeasureRule.getId()"+dbHedisMeasureRule.getId());
	       
		model.addAttribute("hedisMeasureRule", dbHedisMeasureRule);
        logger.info("Returning hedisMeasureRuleDisplay.jsp page");
        return "hedisMeasureRuleDisplay";
    }
	
	@RequestMapping(value = "/admin/hedisMeasureRule/save.do", method = RequestMethod.POST, params ={"add"})
	public String addHedisMeasureAction(@ModelAttribute("hedisMeasureRule")  @Validated HedisMeasureRule hedisMeasureRule,
            BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
        if (bindingResult.hasErrors()) {
            logger.info("Returning hedisMeasureRuleEdit.jsp page");
            return "hedisMeasureRuleNew";
        }
        
	 	model.addAttribute("hedisMeasureRule", hedisMeasureRule);
	 	hedisMeasureRule.setCreatedBy(username);
	 	hedisMeasureRule.setUpdatedBy(username);
	 	
    	logger.info("Returning hedisMeasureRuleEditSuccess.jsp page after create");
      	hedisMeasureRuleService.save(hedisMeasureRule);
   
    return "hedisMeasureRuleEditSuccess";
    }
	
	
	@RequestMapping(value = "/admin/hedisMeasureRule/{id}/save.do", method = RequestMethod.POST, params ={"update"})
	public String saveHedisMeasureRuleAction(@PathVariable Integer id,
			@ModelAttribute("hedisMeasureRule") @Validated HedisMeasureRule hedisMeasureRule,
            BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
        if (bindingResult.hasErrors()) {
        	hedisMeasureRule.setActiveInd('Y');
            logger.info("Returning  hedisMeasureRuleEdit.jsp page");
            return "hedisMeasureRuleEdit";
        }
	        
        if (null != hedisMeasureRule.getId())
        {
        	logger.info("Returning hedisMeasureRuleEditSuccess.jsp page after update");
        	hedisMeasureRuleService.update(hedisMeasureRule);
        	hedisMeasureRule.setUpdatedBy(username);
          	return "hedisMeasureRuleEditSuccess";
        }
       
        return "hedisMeasureRuleEdit";
    }
	

	@RequestMapping(value = "/admin/hedisMeasureRule/{id}/save.do", method = RequestMethod.POST, params ={"delete"})
	public String deleteHedisMeasureAction(@PathVariable Integer id, 
			@ModelAttribute("hedisMeasureRule") @Validated HedisMeasureRule hedisMeasureRule,
            BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
			
			if (bindingResult.hasErrors()) {
	        	hedisMeasureRule.setActiveInd('Y');
	        	logger.info("Returning  hedisMeasureRuleEdit.jsp page");
	            return "hedisMeasureRuleEdit";
	        }
            
	        if (null != hedisMeasureRule.getId())
	        {
	        	logger.info("Returning hedisMeasureRuleEditSuccess.jsp page after update");
	        	hedisMeasureRule.setActiveInd('N');
	        	hedisMeasureRule.setUpdatedBy(username);
	        	hedisMeasureRuleService.update(hedisMeasureRule);
	        	return "redirect:hedisMeasureRuleList";
	        }
	        return "hedisMeasureRuleEdit";
    }
	
	@ResponseBody
	@RequestMapping(value = {"/admin/hedisMeasureRule/cpt","/user/hedisMeasureRule/cpt"})
	 public Message  getICDMeasure(@ModelAttribute("username") String username, Model model) 
	{
		List<CPTMeasure> cptMeasureList = cptMeasureService.findAll();
		  return  Message.successMessage(CommonMessageContent.CPT_LIST, cptMeasureList);
	        
	 }
	
	@ResponseBody
	@RequestMapping(value = {"/admin/hedisMeasureRule/icd","/user/hedisMeasureRule/icd"})
	 public Message  getCPTMeasure(@ModelAttribute("username") String username, Model model) 
	{
		List<ICDMeasure> icdMeasureList = icdMeasureService.findAll();
		  return  Message.successMessage(CommonMessageContent.ICD_LIST, JsonConverter.getJsonObject(icdMeasureList));
	        
	 }
	
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = {"/admin/hedisMeasureRule/{id}/cpt/cptMeasureLists","/user/hedisMeasureRule/{id}/cpt/cptMeasureLists"}, method = RequestMethod.GET)
	public Message viewCPTMeasureList(@PathVariable Integer id,Model model,@RequestParam(required = false) Integer pageNo,
					@RequestParam(required = false) Integer pageSize,
					@RequestParam(required = false) String sSearch,
					@RequestParam(required = false) String sort,
					@RequestParam(required = false) String sortdir) throws Exception{
		
		HedisMeasureRule dbHedisMeasureRule = hedisMeasureRuleService.findById(id);
		Set<CPTMeasure> hedisRuleCPTMeasureList = dbHedisMeasureRule.getCptCodes();
		
		Pagination pagination = cptMeasureService.getPage(pageNo, pageSize,	sSearch, sort,sortdir);
		List<CPTMeasure> cptMeasureList = (List<CPTMeasure>) pagination.getList();
		
		List<CPTMeasure> retainCPTList = new ArrayList<CPTMeasure>(hedisRuleCPTMeasureList);
		retainCPTList.retainAll(cptMeasureList);
		
		cptMeasureList.removeAll(hedisRuleCPTMeasureList);
		
		if(sSearch!= null && "".equals(sSearch))
		{
			final int count = pagination.getTotalCount() - hedisRuleCPTMeasureList.size();
			pagination.setTotalCount(count);    
		}
		else 
		{
			final int count = pagination.getTotalCount() - retainCPTList.size();
			pagination.setTotalCount(count); 
		}
		
		pagination.setList(cptMeasureList);
		
        return Message.successMessage(CommonMessageContent.CPT_LIST, JsonConverter.getJsonObject(pagination));
    }
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = {"/admin/hedisMeasureRule/{id}/icd/icdMeasureLists","/user/hedisMeasureRule/{id}/icd/icdMeasureLists"}, method = RequestMethod.GET)
	public Message viewICDMeasureList(@PathVariable Integer id,Model model,@RequestParam(required = false) Integer pageNo,
					@RequestParam(required = false) Integer pageSize,
					@RequestParam(required = false) String sSearch,
					@RequestParam(required = false) String sort,
					@RequestParam(required = false) String sortdir) throws Exception{
		
		HedisMeasureRule dbHedisMeasureRule = hedisMeasureRuleService.findById(id);
		Set<ICDMeasure> hedisRuleICDMeasureList = dbHedisMeasureRule.getIcdCodes();
		
		Pagination pagination = icdMeasureService.getPage(pageNo, pageSize,	sSearch, sort,sortdir);
		List<ICDMeasure> icdMeasureList = (List<ICDMeasure>) pagination.getList();
		
		List<ICDMeasure> retainICDList = new ArrayList<ICDMeasure>(hedisRuleICDMeasureList);
		retainICDList.retainAll(icdMeasureList);
		
		icdMeasureList.removeAll(hedisRuleICDMeasureList);
		
		if(sSearch!= null && "".equals(sSearch))
		{
			final int count = pagination.getTotalCount() - hedisRuleICDMeasureList.size();
			pagination.setTotalCount(count);    
		}
		else 
		{
			final int count = pagination.getTotalCount() - retainICDList.size();
			pagination.setTotalCount(count); 
		}
		
		pagination.setList(icdMeasureList);
		
        return Message.successMessage(CommonMessageContent.ICD_LIST, JsonConverter.getJsonObject(pagination));
    }
	
}
