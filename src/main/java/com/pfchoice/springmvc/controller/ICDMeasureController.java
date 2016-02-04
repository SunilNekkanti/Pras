package com.pfchoice.springmvc.controller;


import java.util.List;

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
import org.springframework.web.servlet.ModelAndView;

import com.pfchoice.core.entity.ICDMeasure;
import com.pfchoice.core.service.ICDMeasureService;

@Controller
public class ICDMeasureController{
	
    @Autowired
    ICDMeasureService icdMeasureService;
    
    
    @Autowired
    @Qualifier("iCDMeasureValidator")
    private Validator validator;
 
    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }
    
    private static final Logger logger = LoggerFactory
            .getLogger(ICDMeasureController.class);
 
	public ICDMeasureController() {
    }
	
	@ModelAttribute("icdMeasure")
    public ICDMeasure createICDMeasureModel() {
        // ModelAttribute value should be same as used in the ICDMeasureEdit.jsp
        return new ICDMeasure();
    }
	
	@RequestMapping(value = "/icd/new")
    public String addICDMeasurePage(Model model) {
		
		ICDMeasure icdMeasure = createICDMeasureModel();
		icdMeasure.setCreatedBy("sarath");
		model.addAttribute("icdMeasure", icdMeasure);
        return "icdMeasureEdit";
    }
	
	@RequestMapping(value = "/icd/{id}", method = RequestMethod.GET)
    public String updateICDMeasurePage(@PathVariable Integer id,Model model) {
		ICDMeasure dbICDMeasure = icdMeasureService.findById(id);
	    logger.info("Returning icdMeasure.getId()"+dbICDMeasure.getId());
		model.addAttribute("icdMeasure", dbICDMeasure);
        logger.info("Returning icdMeasureEdit.jsp page");
        return "icdMeasureEdit";
    }
		
	@RequestMapping(value = "/icd/{id}/display", method = RequestMethod.GET)
    public String displayICDMeasurePage(@PathVariable Integer id,Model model) {
		ICDMeasure dbICDMeasure = icdMeasureService.findById(id);
		 logger.info("Returning icdMeasure.getId()"+dbICDMeasure.getId());
	       
		model.addAttribute("icdMeasure", dbICDMeasure);
        logger.info("Returning icdMeasureDisplay.jsp page");
        return "icdMeasureDisplay";
    }
	
	@RequestMapping(value = "/icd/icdMeasureList")
    public ModelAndView icdMeasureList() throws Exception {
 
    	List<ICDMeasure> listBean = icdMeasureService.findAll();
		ModelAndView modelAndView = new ModelAndView("icdMeasureList");
		modelAndView.addObject("icdMeasureList", listBean);
 
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/icd/save.do", method = RequestMethod.POST, params ={"add"})
	public String addICDMeasureAction(@Validated ICDMeasure icdMeasure,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            logger.info("Returning icdMeasureEdit.jsp page");
            return "icdMeasureEdit";
        }
        
	 	model.addAttribute("icdMeasure", icdMeasure);
	 	icdMeasure.setCreatedBy("sarath");
	 	icdMeasure.setUpdatedBy("sarath");
	 	icdMeasure.setActiveInd('Y');
    	
    	logger.info("Returning icdEditSuccess.jsp page after create");
      	icdMeasureService.save(icdMeasure);
   
    return "icdMeasureEditSuccess";
    }
	
	
	@RequestMapping(value = "/icd/save.do", method = RequestMethod.POST, params ={"update"})
	public String saveICDMeasureAction(@Validated ICDMeasure icdMeasure,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            logger.info("Returning  icdMeasureEdit.jsp page");
            return "icdMeasureEdit";
        }
	        
        if (null != icdMeasure.getId())
        {
        	logger.info("Returning icdMeasureEditSuccess.jsp page after update");
        	icdMeasure.setActiveInd('Y');
        	icdMeasureService.update(icdMeasure);
        	model.addAttribute("icdMeasure", icdMeasure);
        	return "icdMeasureEditSuccess";
        }
       
        return "icdMeasureEdit";
    }
	

	@RequestMapping(value = "/icd/save.do", method = RequestMethod.POST, params ={"delete"})
	public String deleteICDMeasureAction(@Validated ICDMeasure icdMeasure,
            BindingResult bindingResult, Model model) {
       
	        if (null != icdMeasure.getId())
	        {
	        	logger.info("Returning icdMeasureEditSuccess.jsp page after update");
	        	icdMeasure.setActiveInd('N');
	        	icdMeasureService.update(icdMeasure);
	        	return "redirect:icdMeasureList";
	        }
	        return "icdMeasureEdit";
    }
}
