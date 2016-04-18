package com.pfchoice.springmvc.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import com.pfchoice.core.entity.Gender;
import com.pfchoice.core.entity.HedisMeasure;
import com.pfchoice.core.entity.HedisMeasureGroup;
import com.pfchoice.core.service.GenderService;
import com.pfchoice.core.service.HedisMeasureGroupService;
import com.pfchoice.core.service.HedisMeasureService;

import ml.rugal.sshcommon.page.Pagination;
import ml.rugal.sshcommon.springmvc.util.Message;

@Controller
@SessionAttributes({"username","userpath"})
public class HedisMeasureController{
	
    
    @Autowired
    private HedisMeasureService hedisMeasureService;
    
    @Autowired
    private HedisMeasureGroupService hedisMeasureGroupService;
    
    @Autowired
    private GenderService genderService;
   
    @Autowired
    @Qualifier("hedisMeasureValidator")
    private Validator validator;
 
    @InitBinder("hedisMeasure")
    private void initBinder(WebDataBinder binder) {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    	dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.setValidator(validator);
    }
    
    private static final Logger logger = LoggerFactory
            .getLogger(HedisMeasureController.class);
	
	@ModelAttribute("hedisMeasure")
    public HedisMeasure createHedisMeasureModel() {
        // ModelAttribute value should be same as used in the HedisMeasureEdit.jsp
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
	
	@RequestMapping(value = {"/admin/hedis/new"})
    public String addHedisMeasurePage(Model model) {
		
		HedisMeasure hedisMeasure = createHedisMeasureModel();
		model.addAttribute("hedisMeasure", hedisMeasure);
        return "hedisMeasureNew";
    }
	
	@RequestMapping(value = {"/admin/hedis/{id}","/user/hedis/{id}"}, method = RequestMethod.GET)
    public String updateHedisMeasurePage(@PathVariable Integer id,Model model) {
		
		HedisMeasure dbHedisMeasure = hedisMeasureService.findById(id);
	    logger.info("Returning hedisMeasure.getId()"+dbHedisMeasure.getId());
		model.addAttribute("hedisMeasure", dbHedisMeasure);
        logger.info("Returning hedisMeasureEdit.jsp page");
        return "hedisMeasureEdit";
    }
		
	@RequestMapping(value = {"/admin/hedis/{id}/display"}, method = RequestMethod.GET)
    public String displayHedisMeasurePage(@PathVariable Integer id,Model model) {
		
		HedisMeasure dbHedisMeasure = hedisMeasureService.findById(id);
		 logger.info("Returning hedisMeasure.getId()"+dbHedisMeasure.getId());
	       
		model.addAttribute("hedisMeasure", dbHedisMeasure);
        logger.info("Returning hedisMeasureDisplay.jsp page");
        return "hedisMeasureDisplay";
    }
	
	@RequestMapping(value = {"/admin/hedis/hedisMeasureList","/user/hedis/hedisMeasureList"}, method = RequestMethod.GET)
	public String viewHedisMeasureAction(Model model) throws Exception{
		
		logger.info("Returning view.jsp page after create");
        return "hedisMeasureList";
    }
	
	@ResponseBody
	@RequestMapping(value = {"/admin/hedis/hedisMeasureLists","/user/hedis/hedisMeasureLists"}, method = RequestMethod.GET)
	public Message viewHedisMeasureActionJsonTest(Model model,@RequestParam(required = false) Integer pageNo,
					@RequestParam(required = false) Integer pageSize,
					@RequestParam(required = false) String sSearch,
					@RequestParam(required = false) String sort,
					@RequestParam(required = false) String sortdir) throws Exception{
		
		Pagination pagination = hedisMeasureService.getPage(pageNo, pageSize,	sSearch, sort,sortdir);

        return Message.successMessage(CommonMessageContent.HEDIS_LIST, pagination);
    }
	
	
	@RequestMapping(value = "/admin/hedis/save.do", method = RequestMethod.POST, params ={"add"})
	public String addHedisMeasureAction(@Validated HedisMeasure hedisMeasure,
            BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
        if (bindingResult.hasErrors()) {
            logger.info("Returning hedisMeasureEdit.jsp page");
            return "hedisMeasureNew";
        }
        
	 	model.addAttribute("hedisMeasure", hedisMeasure);
	 	hedisMeasure.setCreatedBy(username);
	 	hedisMeasure.setUpdatedBy(username);
	 	model.addAttribute("Message", "Hedis Measure added successfully");
    	logger.info("Returning contactEditSuccess.jsp page after create");
      	hedisMeasureService.save(hedisMeasure);
   
    return "hedisMeasureList";
    }
	
	
	@RequestMapping(value = "/admin/hedis/{id}/save.do", method = RequestMethod.POST, params ={"update"})
	public String saveHedisMeasureAction(@PathVariable Integer id,@Validated HedisMeasure hedisMeasure,
            BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		hedisMeasure.setActiveInd('Y');
		if (bindingResult.hasErrors()) {
            logger.info("Returning  hedisMeasureEdit.jsp page");
            hedisMeasure.setActiveInd('Y');
            hedisMeasure.setUpdatedBy(username);
            return "hedisMeasureEdit";
        }
	        
        if (null != hedisMeasure.getId())
        {
        	logger.info("Returning hedisMeasureEditSuccess.jsp page after update");
        	hedisMeasureService.update(hedisMeasure);
        	model.addAttribute("Message", "Hedis Measure updated successfully");
        	return "hedisMeasureEdit";
        }
       
        return "hedisMeasureEdit";
    }
	

	@RequestMapping(value = "/admin/hedis/{id}/save.do", method = RequestMethod.POST, params ={"delete"})
	public String deleteHedisMeasureAction(@PathVariable Integer id,@Validated HedisMeasure hedisMeasure,
            BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		 
		if (bindingResult.hasErrors()) {
	            logger.info("Returning  hedisMeasureEdit.jsp page");
	            hedisMeasure.setActiveInd('Y');
	            return "hedisMeasureEdit";
	        }
            
	        if (null != hedisMeasure.getId())
	        {
	        	logger.info("Returning ContactEditSuccess.jsp page after update");
	        	hedisMeasure.setActiveInd('N');
	        	hedisMeasure.setUpdatedBy(username);
	        	hedisMeasureService.update(hedisMeasure);
	        	model.addAttribute("Message", "Hedis Measure deleted successfully");
	        	return "hedisMeasureEdit";
	        }
	        return "hedisMeasureEdit";
    }
	
}
