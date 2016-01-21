package com.pfchoice.springmvc.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.pfchoice.core.entity.County;
import com.pfchoice.core.entity.Gender;
import com.pfchoice.core.entity.Membership;
import com.pfchoice.core.entity.MembershipInsurance;
import com.pfchoice.core.entity.MembershipStatus;
import com.pfchoice.core.service.CountyService;
import com.pfchoice.core.service.GenderService;
import com.pfchoice.core.service.MembershipInsuranceService;
import com.pfchoice.core.service.MembershipService;
import com.pfchoice.core.service.MembershipStatusService;
import com.pfchoice.form.MembershipForm;

@Controller
public class MembershipController{
	
    @Autowired
	CountyService  countyService;
    
    @Autowired
	GenderService  genderService;
    
    @Autowired
   	MembershipStatusService  membershipStatusService;
    
    @Autowired
    MembershipService membershipService;
    
    @Autowired
    MembershipInsuranceService membershipInsuranceService;
    
 /*   @Autowired
    @Qualifier("membershipValidator")
    private Validator validator;
 
    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }
 */   
    private static final Logger logger = LoggerFactory
            .getLogger(MembershipController.class);
 
	public MembershipController() {
    }
	
	
	@ModelAttribute("membership")
    public Membership createMembershipModel() {
        // ModelAttribute value should be same as used in the empSave.jsp
        return new Membership();
    }
 
	
	@RequestMapping(value = "/membership/{id}", method = RequestMethod.GET)
    public String updateMembershipPage(@PathVariable Integer id,Model model) {
		Membership dbMembership = membershipService.findById(id);
		 logger.info("Returning membership.getId()"+dbMembership.getId());
	       
		model.addAttribute("membership", dbMembership);
        logger.info("Returning membershipEdit.jsp page");
        return "membershipEdit";
    }
	
	@RequestMapping(value = "/membership/display/{id}", method = RequestMethod.GET)
    public String displayMembershipPage(@PathVariable Integer id,Model model) {
		Membership dbMembership = membershipService.findById(id);
		 logger.info("Returning membership.getId()"+dbMembership.getId());
	       
		model.addAttribute("membership", dbMembership);
        logger.info("Returning membershipDisplay.jsp page");
        return "membershipDisplay";
    }
	
	@RequestMapping(value = "/membership/save.do", method = RequestMethod.POST)
    public String saveMembershipAction( @Validated Membership membership,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
        	for( ObjectError oe :bindingResult.getAllErrors()){
        		System.out.println("oe "+oe.getObjectName() +""+oe.getCode());
        	}
            logger.info("Returning membershipEdit.jsp page");
            return "membershipEdit";
        }
        logger.info("Returning MembershipSuccess.jsp page");
        if (null != membership.getId())
        {
        	
            membershipService.update(membership);
        }
        
        model.addAttribute("membership", membership);
        return "membershipEditSuccess";
    }
	
	@RequestMapping(value = "/membership/details/display/{id}", method = RequestMethod.GET)
    public String displayMembershipDetailsPage(@PathVariable Integer id,Model model) {
		MembershipInsurance dbMembershipInsurance = membershipInsuranceService.findById(id);
		 logger.info("Returning dbMembershipInsurance.getId()"+dbMembershipInsurance.getId());
	       
		model.addAttribute("dbMembershipInsurance", dbMembershipInsurance);
        logger.info("Returning membershipDetailsDisplay.jsp page");
        return "membershipDetailsDisplay";
    }
	
	
	@RequestMapping(value = "/membership/details/save.do", method = RequestMethod.POST)
    public String saveMembershipInsuranceAction( @Validated MembershipInsurance membershipInsurance,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
        	for( ObjectError oe :bindingResult.getAllErrors()){
        		System.out.println("oe "+oe.getObjectName() +""+oe.getCode());
        	}
            logger.info("Returning membershipDetailsEdit.jsp page");
            return "membershipDetailsEdit";
        }
        logger.info("Returning MembershipDetailsEditSuccess.jsp page");
        if (null != membershipInsurance.getId())
        {
        	
            membershipInsuranceService.update(membershipInsurance);
        }
        
        model.addAttribute("membershipInsurance", membershipInsurance);
        return "membershipDetailsEditSuccess";
    }
	
	@RequestMapping(value = "/membership/detailsList/{id}")
    public ModelAndView handleRequest(@PathVariable Integer id) throws Exception {
 
    	List<MembershipInsurance> listBean = membershipInsuranceService.findAllByMbrId(id);
    	System.out.println("MembershipInsurance list bean sze "+listBean.size());
		ModelAndView modelAndView = new ModelAndView("membershipDetailsList");
		modelAndView.addObject("membershipDetailsList", listBean);
 
		return modelAndView;
	}
    
	
	@ModelAttribute("countyMap")
	public Map<Integer,String> populateCountyList() {
		
		//Data referencing for county list box
		List<County> countyList = countyService.findAll();
		Map<Integer,String> countyMap = new HashMap<Integer,String>();
		for(County c:countyList){
			countyMap.put(c.getCode(),c.getDescription());
		}
		return countyMap;
	}

	@ModelAttribute("genderMap")
	public Map<Byte,String> populateGenderList() {
		
		//Data referencing for gender list box
		List<Gender> genderList = genderService.findAll();
		Map<Byte,String> genderMap = new HashMap<Byte,String>();
		for(Gender g:genderList){
			genderMap.put(g.getId(),g.getDescription());
		}
		return genderMap;
	}
	
	@ModelAttribute("statusMap")
	public Map<Byte,String> populateStatusList() {
		
		//Data referencing for Membership Status list box
		List<MembershipStatus> mbrStatusList = membershipStatusService.findAll();
		Map<Byte,String> mbrStatusMap = new HashMap<Byte,String>();
		for(MembershipStatus ms:mbrStatusList){
			mbrStatusMap.put(ms.getId(),ms.getDescription());
		}
		return mbrStatusMap;
	}
}
