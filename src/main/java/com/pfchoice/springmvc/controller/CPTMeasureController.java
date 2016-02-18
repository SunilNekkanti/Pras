package com.pfchoice.springmvc.controller;

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
import com.pfchoice.core.entity.CPTMeasure;
import com.pfchoice.core.service.CPTMeasureService;

import ml.rugal.sshcommon.page.Pagination;
import ml.rugal.sshcommon.springmvc.util.Message;

@Controller
public class CPTMeasureController{
	
    @Autowired
    private CPTMeasureService cptMeasureService;
    
    
    @Autowired
    @Qualifier("cPTMeasureValidator")
    private Validator validator;
 
    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }
    
    private static final Logger logger = LoggerFactory
            .getLogger(CPTMeasureController.class);
 
	public CPTMeasureController() {
    }
	
	@ModelAttribute("cptMeasure")
    public CPTMeasure createCPTMeasureModel() {
        // ModelAttribute value should be same as used in the CPTMeasureEdit.jsp
        return new CPTMeasure();
    }
	
	@RequestMapping(value = "/cpt/new")
    public String addCPTMeasurePage(Model model) {
		
		CPTMeasure cptMeasure = createCPTMeasureModel();
		cptMeasure.setCreatedBy("sarath");
		model.addAttribute("cptMeasure", cptMeasure);
        return "cptMeasureEdit";
    }
	
	@RequestMapping(value = "/cpt/{id}", method = RequestMethod.GET)
    public String updateCPTMeasurePage(@PathVariable Integer id,Model model) {
		CPTMeasure dbCPTMeasure = cptMeasureService.findById(id);
	    logger.info("Returning cptMeasure.getId()"+dbCPTMeasure.getId());
		model.addAttribute("cptMeasure", dbCPTMeasure);
        logger.info("Returning cptMeasureEdit.jsp page");
        return "cptMeasureEdit";
    }
		
	@RequestMapping(value = "/cpt/{id}/display", method = RequestMethod.GET)
    public String displayCPTMeasurePage(@PathVariable Integer id,Model model) {
		CPTMeasure dbCPTMeasure = cptMeasureService.findById(id);
		 logger.info("Returning cptMeasure.getId()"+dbCPTMeasure.getId());
	       
		model.addAttribute("cptMeasure", dbCPTMeasure);
        logger.info("Returning cptMeasureDisplay.jsp page");
        return "cptMeasureDisplay";
    }
	
	@RequestMapping(value = "/cpt/cptMeasureList", method = RequestMethod.GET)
	public String viewCPTMeasureAction(Model model,
					HttpServletRequest request) throws Exception{
		logger.info("Returning view.jsp page after create");
        return "cptMeasureList";
    }
	
	@ResponseBody
	@RequestMapping(value = "/cpt/cptMeasureLists", method = RequestMethod.GET)
	public Message viewCPTMeasureActionJsonTest(Model model,@RequestParam(required = false) Integer pageNo,
					@RequestParam(required = false) Integer pageSize,
					@RequestParam(required = false) String sSearch,
					@RequestParam(required = false) String sort,
					@RequestParam(required = false) String sortdir,
					HttpServletRequest request) throws Exception{
		
		Pagination pagination = cptMeasureService.getPage(pageNo, pageSize,	sSearch, sort,sortdir);

        return Message.successMessage(CommonMessageContent.CPT_LIST, pagination);
    }
	
	
	@RequestMapping(value = "/cpt/save.do", method = RequestMethod.POST, params ={"add"})
	public String addCPTMeasureAction(@ModelAttribute("cptMeasure") @Validated CPTMeasure cptMeasure,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            logger.info("Returning cptMeasureEdit.jsp page");
            return "cptMeasureEdit";
        }
        
	 	model.addAttribute("cptMeasure", cptMeasure);
	 	cptMeasure.setCreatedBy("sarath");
	 	cptMeasure.setUpdatedBy("sarath");
	 	logger.info("Returning cptEditSuccess.jsp page after create");
      	cptMeasureService.save(cptMeasure);
   
    return "cptMeasureEditSuccess";
    }
	
	
	@RequestMapping(value = "/cpt/save.do", method = RequestMethod.POST, params ={"update"})
	public String saveCPTMeasureAction(@ModelAttribute("cptMeasure") @Validated CPTMeasure cptMeasure,
            BindingResult bindingResult, Model model) {
        
		if (bindingResult.hasErrors()) {
			cptMeasure.setActiveInd('Y');
            logger.info("Returning  cptMeasureEdit.jsp page");
            return "cptMeasureEdit";
        }
	        
        if (null != cptMeasure.getId())
        {
        	logger.info("Returning cptMeasureEditSuccess.jsp page after update");
        	cptMeasureService.update(cptMeasure);
        	model.addAttribute("cptMeasure", cptMeasure);
        	return "cptMeasureEditSuccess";
        }
       
        return "cptMeasureEdit";
    }
	

	@RequestMapping(value = "/cpt/save.do", method = RequestMethod.POST, params ={"delete"})
	public String deleteCPTMeasureAction(@ModelAttribute("cptMeasure") @Validated CPTMeasure cptMeasure,
            BindingResult bindingResult, Model model) {
       
			if (bindingResult.hasErrors()) {
				cptMeasure.setActiveInd('Y');
	            logger.info("Returning  cptMeasureEdit.jsp page");
	            return "cptMeasureEdit";
	        }
	        if (null != cptMeasure.getId())
	        {
	        	logger.info("Returning cptMeasureEditSuccess.jsp page after update");
	        	cptMeasure.setActiveInd('N');
	        	cptMeasureService.update(cptMeasure);
	        	return "redirect:cptMeasureList";
	        }
	        return "cptMeasureEdit";
    }
}
