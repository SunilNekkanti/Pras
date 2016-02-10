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

import com.pfchoice.core.entity.CPTMeasure;
import com.pfchoice.core.service.CPTMeasureService;

@Controller
public class CPTMeasureController{
	
    @Autowired
    CPTMeasureService cptMeasureService;
    
    
    @Autowired
    @Qualifier("cPTMeasureValidator")
    private Validator validator;
 
    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }
    
    private static final Logger logger = LoggerFactory
            .getLogger(CPTMeasureController.class);
 
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
	
	@RequestMapping(value = "/cpt/cptMeasureList")
    public ModelAndView cptMeasureList() throws Exception {
 
    	List<CPTMeasure> listBean = cptMeasureService.findAll();
		ModelAndView modelAndView = new ModelAndView("cptMeasureList");
		modelAndView.addObject("cptMeasureList", listBean);
 
		return modelAndView;
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
	 	cptMeasure.setActiveInd('Y');
    	
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
        	cptMeasure.setActiveInd('Y');
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
