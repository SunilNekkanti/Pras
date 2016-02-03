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

import com.pfchoice.core.entity.County;
import com.pfchoice.core.entity.Ethinicity;
import com.pfchoice.core.entity.Gender;
import com.pfchoice.core.entity.Insurance;
import com.pfchoice.core.entity.Membership;
import com.pfchoice.core.entity.MembershipInsurance;
import com.pfchoice.core.entity.MembershipProvider;
import com.pfchoice.core.entity.MembershipStatus;
import com.pfchoice.core.service.CountyService;
import com.pfchoice.core.service.EthinicityService;
import com.pfchoice.core.service.GenderService;
import com.pfchoice.core.service.InsuranceService;
import com.pfchoice.core.service.MembershipInsuranceService;
import com.pfchoice.core.service.MembershipProviderService;
import com.pfchoice.core.service.MembershipService;
import com.pfchoice.core.service.MembershipStatusService;

@Controller
public class MembershipController{
	
	private static final Logger logger = LoggerFactory
            .getLogger(MembershipController.class);
    
    @Autowired
	private CountyService  countyService;
    
    @Autowired
    private GenderService  genderService;
    
    @Autowired
    private MembershipStatusService  membershipStatusService;
    
    @Autowired
    private MembershipService membershipService;
    
    @Autowired
    private MembershipInsuranceService membershipInsuranceService;
    
    @Autowired
    private InsuranceService insuranceService;
    
    @Autowired
    private EthinicityService ethinicityService;
    
    @Autowired
    private MembershipProviderService membershipProviderService;
    
    @Autowired
    @Qualifier("membershipValidator")
    private Validator validator;
    
    @InitBinder("membership")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }
   
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
	
	
	@RequestMapping(value = "/membership/{id}/display", method = RequestMethod.GET)
    public String displayMembershipPage(@PathVariable Integer id,Model model) {
		Membership dbMembership = membershipService.findById(id);
		 logger.info("Returning membership.getId()"+dbMembership.getId());
	       
		model.addAttribute("membership", dbMembership);
        logger.info("Returning membershipDisplay.jsp page");
        return "membershipDisplay";
    }
	
	
	
	@RequestMapping(value = "/membership/save.do", method = RequestMethod.POST)
    public String saveMembershipAction( @ModelAttribute("membership") @Validated  Membership membership,
            BindingResult bindingResult, Model model) {
		
		
        if (bindingResult.hasErrors()) {
            logger.info("Returning membershipEdit.jsp page");
            return "membershipEdit";
        }
        
        logger.info("Returning MembershipSuccess.jsp page");
        if (null != membership.getId())
        {
        	membership.setActiveInd('Y');
        	membership.setUpdatedBy("Mohanasundharam");
            membershipService.update(membership);
        }
        Membership dbMembership  = membershipService.findById(membership.getId()); 
        model.addAttribute("membership", dbMembership);

        return "membershipEditSuccess";
    }
	
	@RequestMapping(value = "/membership/save.do", method = RequestMethod.POST,params={"delete"})
    public String deleteMembershipAction( @Validated Membership membership,
            BindingResult bindingResult, Model model) {
		
		
        if (bindingResult.hasErrors()) {
            logger.info("Returning membershipEdit.jsp page");
            return "membershipEdit";
        }
        logger.info("Returning MembershipSuccess.jsp page");
        if (null != membership.getId())
        {
        	membership.setActiveInd('N');
        	membership.setUpdatedBy("Mohanasundharam");
            membershipService.update(membership);
        }
        
        model.addAttribute("membership", membership);
        return "membershipEditSuccess";
    }
	
	
	@RequestMapping(value = "/membership/{id}/details/{mbrInsId}/display", method = RequestMethod.GET)
    public String displayMembershipDetailsPage(@PathVariable Integer id,@PathVariable Integer mbrInsId,Model model) {
		MembershipInsurance dbMembershipInsurance = membershipInsuranceService.findById(mbrInsId);
		 logger.info("Returning dbMembershipInsurance.getId()"+dbMembershipInsurance.getId());
	       
		model.addAttribute("membershipInsurance", dbMembershipInsurance);
        logger.info("Returning membershipDetailsDisplay.jsp page");
        return "membershipDetailsDisplay";
    }
	
	
	@RequestMapping(value = "/membership/{id}/details/new")
    public String newMembershipInsDetailsPage(@PathVariable Integer id,Model model) {
		
		Membership dbMembership = membershipService.findById(id);
		
		MembershipInsurance  dbMembershipInsurance = new MembershipInsurance();
		dbMembershipInsurance.setCreatedBy("Mohanasundharam");
		dbMembershipInsurance.setMbr(dbMembership);
		model.addAttribute("membershipInsurance", dbMembershipInsurance);
		
        logger.info("Returning membershipDetailsDisplay.jsp page");
        return "membershipDetailsDisplay";
    }
	
	
	@RequestMapping(value = "/membership/{id}/details/{mbrInsId}/save.do", method = RequestMethod.POST,params={"update"})
    public String saveMembershipDetailsPage(@PathVariable Integer id,@PathVariable Integer mbrInsId,
    		@ModelAttribute("membershipInsurance") @Validated MembershipInsurance membershipInsurance,Model model,BindingResult bindingResult) {
        
		 if (bindingResult.hasErrors()) {
	   		 	logger.info("Returning membershipDetailsDisplay");
	   			return "membershipDetailsDisplay";
	      }
	      else
	      {
	        	membershipInsurance.setActiveInd('Y');
	        	membershipInsurance.setUpdatedBy("Mohanasundharam");
	        	MembershipInsurance dbMembershipInsurance = membershipInsuranceService.update(membershipInsurance);
	            model.addAttribute("membershipInsurance", dbMembershipInsurance);
	             
		        return "membershipDetailsEditSuccess";
	       }    
    }
	
	
	@RequestMapping(value = "/membership/{id}/details/save.do", method = RequestMethod.POST,params={"add"})
    public String newMembershipInDetailsPage(@PathVariable Integer id,
    		@ModelAttribute("membershipInsurance") @Validated MembershipInsurance membershipInsurance,Model model,BindingResult bindingResult) {
		
		 if (bindingResult.hasErrors()) {
			 logger.info("Returning membershipDetailsDisplay");	
	           return "membershipDetailsDisplay";
	        }
	        else
	        {
	        	membershipInsurance.setActiveInd('Y');
	        	membershipInsurance.setCreatedBy("Mohanasundharam");
	        	membershipInsurance.setUpdatedBy("Mohanasundharam");
	        	MembershipInsurance dbMembershipInsurance = membershipInsuranceService.save(membershipInsurance);
	            model.addAttribute("membershipInsurance",dbMembershipInsurance);
		        return "membershipDetailsEditSuccess";
	        }    
    }
	
	
	@RequestMapping(value = "/membership/{id}/details/{mbrInsId}/save.do", method = RequestMethod.POST,params={"delete"})
    public String deleteMembershipInsDetailsPage(@PathVariable Integer id,@PathVariable Integer mbrInsId,
    		@ModelAttribute("membershipInsurance") @Validated MembershipInsurance membershipInsurance,Model model,BindingResult bindingResult) {
	 	
		 if (bindingResult.hasErrors()) {
	       	 logger.info("Returning membershipDetailsDisplay");
	           return "membershipDetailsDisplay";
	        }
	        else
	        {
	        	membershipInsurance.setActiveInd('N');
	        	membershipInsurance.setUpdatedBy("Mohanasundharam");
	            membershipInsuranceService.update(membershipInsurance);
	            return "membershipEditSuccess";
	        }    
    }
	
	
	@RequestMapping(value = "/membership/details/save.do", method = RequestMethod.POST)
    public String saveMembershipInsuranceAction( @Validated MembershipInsurance membershipInsurance,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            logger.info("Returning membershipDetailsEdit.jsp page");
            return "membershipDetailsEdit";
        }
        logger.info("Returning MembershipDetailsEditSuccess.jsp page");
        if (null != membershipInsurance.getId())
        {
        	membershipInsurance =  membershipInsuranceService.update(membershipInsurance);
        }
        
        model.addAttribute("membershipInsurance", membershipInsurance);
        return "membershipDetailsEditSuccess";
    }
	
	
	
	@RequestMapping(value = "/membership/{id}/detailsList")
    public ModelAndView handleRequest(@PathVariable Integer id) throws Exception {
 
    	List<MembershipInsurance> listBean = membershipInsuranceService.findAllByMbrId(id);
		ModelAndView modelAndView = new ModelAndView("membershipDetailsList");
		modelAndView.addObject("membershipDetailsList", listBean);
		return modelAndView;
	}
    
	
	@RequestMapping(value = "/membership/{id}/providerDetails", method = RequestMethod.GET)
    public String displayMembershipProviderDetailsPage(@PathVariable Integer id,  @Validated MembershipProvider membershipProvider,
    				BindingResult bindingResult, Model model)throws Exception {
		
		MembershipProvider dbMembershipProvider = membershipProviderService.findByMbrId(id);
		model.addAttribute("membershipProvider", dbMembershipProvider);
        logger.info("Returning membershipProviderEdit.jsp page");
        return "membershipProviderEdit";
    }
	
	@RequestMapping(value = "/membership/{id}/memberProvider/{mbrPrvdrId}", method = RequestMethod.GET)
    public String displayInactiveMembershipProviderDetailsPage(@PathVariable Integer id, @PathVariable Integer mbrPrvdrId, 
    				@ModelAttribute @Validated  MembershipProvider membershipProvider,
    				BindingResult bindingResult, Model model)throws Exception {
		
		MembershipProvider dbMembershipProvider = membershipProviderService.findById(mbrPrvdrId);
		model.addAttribute("membershipProvider", dbMembershipProvider);
        logger.info("Returning membershipProviderEdit.jsp page");
        return "membershipProviderEdit";
    }
	
	@RequestMapping(value = "/membership/{id}/providerDetailsList", method = RequestMethod.GET)
    public String displayMembershipProviderDetailsListPage(@PathVariable Integer id,  @Validated MembershipProvider membershipProvider,
    				BindingResult bindingResult, Model model)throws Exception {
		
		List<MembershipProvider> mbrPrvdrList = membershipProviderService.findAllByMbrId(id);
		model.addAttribute("membershipProviderList", mbrPrvdrList);
        logger.info("Returning membershipProviderList.jsp page");
        return "membershipProviderList";
    }
	
	@RequestMapping(value = "/membership/{id}/complete", method = RequestMethod.GET)
    public String completeMembershipProviderDetailsPage(@PathVariable Integer id,Model model)throws Exception {
		
		Membership membership = membershipService.findById(id);
		model.addAttribute("membership", membership);
		
		List<MembershipInsurance> dbMembershipInsurance = membershipInsuranceService.findAllByMbrId(membership.getId());
		List<MembershipProvider>  dbMembershipProvider  = membershipProviderService.findAllByMbrId(membership.getId());
		
		model.addAttribute("membershipInsurance", dbMembershipInsurance);
		model.addAttribute("membershipProvider", dbMembershipProvider);
		model.addAttribute("membership", membership);
        return "membershipCompleteDetails";
           
    }
	
	
	@ModelAttribute("countyList")
	public List<County> populateCountyList() {
		
		//Data referencing for county list box
		List<County> countyList = countyService.findAll();
		return countyList;
	}

	
	@ModelAttribute("genderList")
	public List<Gender> populateGenderList() {
		
		//Data referencing for gender list box
		List<Gender> genderList = genderService.findAll();
		return genderList;
	}
	
	
	@ModelAttribute("insList")
	public List<Insurance> populateInsuranceList() {
		
		//Data referencing for Insurance list box
		List<Insurance> insList = insuranceService.findAll();
		return insList;
	}
	
	
	@ModelAttribute("statusList")
	public List<MembershipStatus> populateStatusList1() {
		
		//Data referencing for Membership Status list box
		List<MembershipStatus> mbrStatusList = membershipStatusService.findAll();
		return mbrStatusList;
	}
	
	@ModelAttribute("ethinicityList")
	public List<Ethinicity> populateethinicityList1() {
		
		//Data referencing for Membership Status list box
		List<Ethinicity> mbrethinicityList = ethinicityService.findAll();
		return mbrethinicityList;
	}
}
