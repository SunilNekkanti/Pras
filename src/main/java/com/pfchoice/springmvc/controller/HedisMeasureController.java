package com.pfchoice.springmvc.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pfchoice.core.entity.Gender;
import com.pfchoice.core.entity.HedisMeasure;
import com.pfchoice.core.entity.HedisMeasureGroup;
import com.pfchoice.core.service.GenderService;
import com.pfchoice.core.service.HedisMeasureGroupService;
import com.pfchoice.core.service.HedisMeasureService;

@Controller
public class HedisMeasureController{
	
    @Autowired
    HedisMeasureService hedisMeasureService;
    
    @Autowired
    HedisMeasureGroupService hedisMeasureGroupService;
    
    @Autowired
    GenderService genderService;
 /*  
    @Autowired
    @Qualifier("hedisMeasure")
    private Validator validator;
 
    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }
    */
    private static final Logger logger = LoggerFactory
            .getLogger(HedisMeasureController.class);
 
	public HedisMeasureController() {
    }
	
	@ModelAttribute("hedisMeasure")
    public HedisMeasure createHedisMeasureModel() {
        // ModelAttribute value should be same as used in the hedisMeasureEdit.jsp
        return new HedisMeasure();
    }
	
	@ModelAttribute("hedisMeasureGroup")
    public HedisMeasureGroup createHedisMeasureGroupModel() {
        // ModelAttribute value should be same as used in the hedisMeasureEdit.jsp
        return new HedisMeasureGroup();
    }
	
	@ModelAttribute("genderList")
	public List<Gender> populateGenderList() {
		
		//Data referencing for gender list box
		List<Gender> genderList = genderService.findAll();
		return genderList;
	}
	
	@ModelAttribute("hedisMeasureGroupList")
	public List<HedisMeasureGroup> populateHedisMeasureGroupList() {
		
		//Data referencing for Hedis Measure Group list box
		List<HedisMeasureGroup> hedisMeasureGroupList = hedisMeasureGroupService.findAll();
		return hedisMeasureGroupList;
	}

	@RequestMapping(value = "/hedis/new")
    public String addHedisMeasurePage(Model model) {
		
		HedisMeasure hedisMeasure = createHedisMeasureModel();
		hedisMeasure.setCreatedBy("sarath");
		model.addAttribute("hedisMeasure", hedisMeasure);
        return "hedisMeasureEdit";
    }
	
	@RequestMapping(value = "/hedis/{id}", method = RequestMethod.GET)
    public String updateHedisMeasurePage(@PathVariable Integer id,Model model) {
		HedisMeasure dbHedisMeasure = hedisMeasureService.findById(id);
	    logger.info("Returning hedisMeasure.getId()"+dbHedisMeasure.getId());
		model.addAttribute("hedisMeasure", dbHedisMeasure);
        logger.info("Returning hedisMeasureEdit.jsp page");
        return "hedisMeasureEdit";
    }
		
	@RequestMapping(value = "/hedis/{id}/display", method = RequestMethod.GET)
    public String displayHedisMeasurePage(@PathVariable Integer id,Model model) {
		HedisMeasure dbHedisMeasure = hedisMeasureService.findById(id);
		 logger.info("Returning hedisMeasure.getId()"+dbHedisMeasure.getId());
	       
		model.addAttribute("hedisMeasure", dbHedisMeasure);
        logger.info("Returning hedisMeasureDisplay.jsp page");
        return "hedisMeasureDisplay";
    }
	
	@RequestMapping(value = "/hedis/hedisMeasureList")
    public ModelAndView hedisMeasureList() throws Exception {
 
    	List<HedisMeasure> listBean = hedisMeasureService.findAll();
		ModelAndView modelAndView = new ModelAndView("hedisMeasureList");
		modelAndView.addObject("hedisMeasureList", listBean);
 
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/hedis/save.do", method = RequestMethod.POST, params ={"add"})
	public String addHedisMeasureAction(@Validated HedisMeasure hedisMeasure,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            logger.info("Returning hedisMeasureEdit.jsp page");
            return "hedisMeasureEdit";
        }
        
	 	model.addAttribute("hedisMeasure", hedisMeasure);
	 	hedisMeasure.setCreatedBy("sarath");
	 	hedisMeasure.setActiveInd('Y');
    	
    	logger.info("Returning contactEditSuccess.jsp page after create");
      	hedisMeasureService.save(hedisMeasure);
   
    return "hedisMeasureEditSuccess";
    }
	
	
	@RequestMapping(value = "/hedis/save.do", method = RequestMethod.POST, params ={"update"})
	public String saveHedisMeasureAction(@Validated HedisMeasure hedisMeasure,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            logger.info("Returning  hedisMeasureEdit.jsp page");
            return "hedisMeasureEdit";
        }
	        
        if (null != hedisMeasure.getId())
        {
        	logger.info("Returning hedisMeasureEditSuccess.jsp page after update");
        	hedisMeasure.setActiveInd('Y');
        	hedisMeasureService.update(hedisMeasure);
        	return "hedisMeasureEditSuccess";
        }
       
        return "hedisMeasureEdit";
    }
	

	@RequestMapping(value = "/hedis/save.do", method = RequestMethod.POST, params ={"delete"})
	public String deleteHedisMeasureAction(@Validated HedisMeasure hedisMeasure,
            BindingResult bindingResult, Model model) {
            
	        if (null != hedisMeasure.getId())
	        {
	        	logger.info("Returning ContactEditSuccess.jsp page after update");
	        	hedisMeasure.setActiveInd('N');
	        	hedisMeasureService.update(hedisMeasure);
	        	return "redirect:hedisMeasureList";
	        }
	        return "hedisMeasureEdit";
    }
	
}
