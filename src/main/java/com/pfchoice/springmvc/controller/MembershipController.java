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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.pfchoice.core.entity.County;
import com.pfchoice.core.entity.Ethinicity;
import com.pfchoice.core.entity.Gender;
import com.pfchoice.core.entity.HedisMeasure;
import com.pfchoice.core.entity.Insurance;
import com.pfchoice.core.entity.Membership;
import com.pfchoice.core.entity.MembershipHedisMeasure;
import com.pfchoice.core.entity.MembershipInsurance;
import com.pfchoice.core.entity.MembershipProvider;
import com.pfchoice.core.entity.MembershipStatus;
import com.pfchoice.core.service.CountyService;
import com.pfchoice.core.service.EthinicityService;
import com.pfchoice.core.service.GenderService;
import com.pfchoice.core.service.HedisMeasureService;
import com.pfchoice.core.service.InsuranceService;
import com.pfchoice.core.service.MembershipInsuranceService;
import com.pfchoice.core.service.MembershipProviderService;
import com.pfchoice.core.service.MembershipService;
import com.pfchoice.core.service.MembershipStatusService;

@Controller
@SessionAttributes({"username","userpath"})
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
    private HedisMeasureService hedisMeasureService;
    
   
    @Autowired
    @Qualifier("membershipValidator")
    private Validator validator;
    
    @Autowired
    @Qualifier("membershipInsuranceValidator")
    private Validator insValidator;
    
    @InitBinder("membership")
    private void initBinder(WebDataBinder binder) {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    	dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.setValidator(validator);
    }
    
    
    @InitBinder("membershipInsurance")
    private void initInsBinder(WebDataBinder binder) {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    	dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.setValidator(insValidator);
    }

    
	public MembershipController() {
    }
	
	@ModelAttribute("membership")
    public Membership createMembershipModel() {
        // ModelAttribute value should be same as used in the empSave.jsp
        return new Membership();
    }
	
	
	@RequestMapping(value = {"/admin/membership/{id}","/user/membership/{id}"}, method = RequestMethod.GET)
    public String updateMembershipPage(@PathVariable Integer id,Model model) {
		Membership dbMembership = membershipService.findById(id);
		 logger.info("Returning membership.getId()"+dbMembership.getId());
	       
		model.addAttribute("membership", dbMembership);
        logger.info("Returning membershipEdit.jsp page");
        return "membershipEdit";
    }
	
	
	@RequestMapping(value = {"/admin/membership/{id}/display","/user/membership/{id}/display"}, method = RequestMethod.GET)
    public String displayMembershipPage(@PathVariable Integer id,Model model) {
		Membership dbMembership = membershipService.findById(id);
		 logger.info("Returning membership.getId()"+dbMembership.getId());
	       
		model.addAttribute("membership", dbMembership);
        logger.info("Returning membershipDisplay.jsp page");
        return "membershipDisplay";
    }
	
	
	
	@RequestMapping(value = {"/admin/membership/{id}/save.do","/user/membership/{id}/save.do"}, method = RequestMethod.POST, params={"update"})
    public String saveMembershipAction(@PathVariable Integer id, @ModelAttribute("membership") @Validated  Membership membership,
            BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
			
		if (bindingResult.hasErrors()) {
        	membership.setActiveInd('Y');
            logger.info("Returning membershipEdit.jsp page");
            return "membershipEdit";
        }
        logger.info("Returning MembershipSuccess.jsp page");
        if (null != membership.getId())
        {
        	membership.setUpdatedBy(username);
            membershipService.update(membership);
        }
        Membership dbMembership  = membershipService.findById(membership.getId()); 
        model.addAttribute("Message", "Membership details updated successfully");
        model.addAttribute("membership", dbMembership);
        return "membershipEdit";
    }
	
	@RequestMapping(value = {"/admin/membership/{id}/save.do","/user/membership/{id}/save.do"}, method = RequestMethod.POST,params={"delete"})
    public String deleteMembershipAction(@PathVariable Integer id, @Validated Membership membership,
            BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
				
        if (bindingResult.hasErrors()) {
        	membership.setActiveInd('Y');
            logger.info("Returning membershipEdit.jsp page");
            return "membershipEdit";
        }
        logger.info("Returning MembershipSuccess.jsp page");
        if (null != membership.getId())
        {
        	membership.setActiveInd('N');
        	membership.setUpdatedBy(username);
            membershipService.update(membership);
        }
        
        model.addAttribute("Message", "Membership details deleted successfully");
        model.addAttribute("membership", membership);
        return "membershipEdit";
    }
	
	
	@RequestMapping(value = {"/admin/membership/{id}/details/{mbrInsId}/display","/user/membership/{id}/details/{mbrInsId}/display"}, method = RequestMethod.GET)
    public String displayMembershipDetailsPage(@PathVariable Integer id,@PathVariable Integer mbrInsId,Model model) {
		MembershipInsurance dbMembershipInsurance = membershipInsuranceService.findById(mbrInsId);
		 logger.info("Returning dbMembershipInsurance.getId()"+dbMembershipInsurance.getId());
	       
		model.addAttribute("membershipInsurance", dbMembershipInsurance);
        logger.info("Returning membershipDetailsDisplay.jsp page");
        return "membershipDetailsDisplay";
    }
	
	
	@RequestMapping(value = {"/admin/membership/{id}/details/new","/user/membership/{id}/details/new"})
    public String newMembershipInsDetailsPage(@PathVariable Integer id,Model model) {
		
		Membership dbMembership = membershipService.findById(id);
		
		MembershipInsurance  dbMembershipInsurance = new MembershipInsurance();
		dbMembershipInsurance.setMbr(dbMembership);
		model.addAttribute("membershipInsurance", dbMembershipInsurance);
		
        logger.info("Returning membershipDetailsDisplay.jsp page");
        return "membershipDetailsDisplay";
    }
	
	@RequestMapping(value = {"/admin/membership/{id}/details/{mbrInsId}/save.do","/user/membership/{id}/details/{mbrInsId}/save.do"}, method = RequestMethod.POST,params={"update"})
    public String saveMembershipDetailsPage(@PathVariable Integer id,@PathVariable Integer mbrInsId,
    		@ModelAttribute("membershipInsurance") @Validated MembershipInsurance membershipInsurance,BindingResult bindingResult
    		,Model model, @ModelAttribute("username") String username) {
        
		 if (bindingResult.hasErrors()) {
	   		 	logger.info("Returning membershipDetailsDisplay");
	   		    membershipInsurance.setActiveInd('Y');
	   			return "membershipDetailsDisplay";
	      }
	      else
	      {
	        	membershipInsurance.setUpdatedBy(username);
	        	MembershipInsurance dbMembershipInsurance = membershipInsuranceService.update(membershipInsurance);
	            model.addAttribute("membershipInsurance", dbMembershipInsurance);
	            model.addAttribute("Message", "Membership Insurance Updated Successfully");
	            List<MembershipInsurance> listBean = membershipInsuranceService.findAllByMbrId(id);
	        	model.addAttribute("membershipDetailsList", listBean);
	        	return "membershipDetailsList";
	       }    
    }
	
	
	@RequestMapping(value = {"/admin/membership/{id}/details/save.do","/user/membership/{id}/details/save.do"}, method = RequestMethod.POST,params={"add"})
    public String newMembershipInDetailsPage(@PathVariable Integer id,
    		@ModelAttribute("membershipInsurance") @Validated MembershipInsurance membershipInsurance,
    		BindingResult bindingResult,Model model, @ModelAttribute("username") String username) {
		
		 if (bindingResult.hasErrors()) {
			 logger.info("Returning membershipDetailsDisplay");	
			 membershipInsurance.setActiveInd('Y');
	           return "membershipDetailsDisplay";
	        }
	        else
	        {
	        	Membership dbMembership = membershipService.findById(id);
	        	membershipInsurance.setMbr(dbMembership);
	        	membershipInsurance.setCreatedBy(username);
	        	membershipInsurance.setUpdatedBy(username);
	        	MembershipInsurance dbMembershipInsurance = membershipInsuranceService.save(membershipInsurance);
	            model.addAttribute("membershipInsurance",dbMembershipInsurance);
	            model.addAttribute("Message", "Membership Insurance Added Successfully");
	            List<MembershipInsurance> listBean = membershipInsuranceService.findAllByMbrId(id);
	        	model.addAttribute("membershipDetailsList", listBean);
	        	return "membershipDetailsList";
	        }    
    }
	
	
	@RequestMapping(value = {"/admin/membership/{id}/details/{mbrInsId}/save.do","/user/membership/{id}/details/{mbrInsId}/save.do"}, method = RequestMethod.POST,params={"delete"})
    public String deleteMembershipInsDetailsPage(@PathVariable Integer id,@PathVariable Integer mbrInsId,
    		@ModelAttribute("membershipInsurance") @Validated MembershipInsurance membershipInsurance,
    		BindingResult bindingResult,Model model, @ModelAttribute("username") String username) {
	 	
		 if (bindingResult.hasErrors()) {
	       	 logger.info("Returning membershipDetailsDisplay");
	       	 membershipInsurance.setActiveInd('Y');
	           return "membershipDetailsDisplay";
	        }
	        else
	        {
	        	membershipInsurance.setActiveInd('N');
	        	membershipInsurance.setUpdatedBy(username);
	            MembershipInsurance dbMembershipInsurance = membershipInsuranceService.update(membershipInsurance);
	            model.addAttribute("membershipInsurance",dbMembershipInsurance);
	            model.addAttribute("Message", "Membership Insurance Deleted Successfully");
	            List<MembershipInsurance> listBean = membershipInsuranceService.findAllByMbrId(id);
	        	model.addAttribute("membershipDetailsList", listBean);
	        	return "membershipDetailsList";
	           
	        }    
    }
	
	
   @RequestMapping(value = {"/admin/membership/{id}/detailsList","/user/membership/{id}/detailsList"})
    public String handleRequest(@PathVariable Integer id,Model model) throws Exception {
 
    	List<MembershipInsurance> listBean = membershipInsuranceService.findAllByMbrId(id);
    	model.addAttribute("membershipDetailsList", listBean);
    	logger.info("Returning membershipDetailsList.jsp page");
		return "membershipDetailsList";
	}
    
	
	@RequestMapping(value = {"/admin/membership/{id}/providerDetails","/user/membership/{id}/providerDetails"}, method = RequestMethod.GET)
    public String displayMembershipProviderDetailsPage(@PathVariable Integer id, 
    				Model model)throws Exception {
		
		MembershipProvider dbMembershipProvider = membershipProviderService.findByMbrId(id);
		dbMembershipProvider = (dbMembershipProvider != null) ?dbMembershipProvider :new MembershipProvider();
		model.addAttribute("membershipProvider", dbMembershipProvider);
        logger.info("Returning membershipProviderEdit.jsp page");
        return "membershipProviderEdit";
    }
	
	@RequestMapping(value = {"/admin/membership/{id}/memberProvider/{mbrPrvdrId}","/user/membership/{id}/memberProvider/{mbrPrvdrId}"}, method = RequestMethod.GET)
    public String displayInactiveMembershipProviderDetailsPage(@PathVariable Integer id, @PathVariable Integer mbrPrvdrId, 
    				@ModelAttribute @Validated  MembershipProvider membershipProvider,
    				BindingResult bindingResult, Model model)throws Exception {
		
		MembershipProvider dbMembershipProvider = membershipProviderService.findById(mbrPrvdrId);
		model.addAttribute("membershipProvider", dbMembershipProvider);
        logger.info("Returning membershipProviderEdit.jsp page");
        return "membershipProviderEdit";
    }
	
	@RequestMapping(value = {"/admin/membership/{id}/providerDetailsList","/user/membership/{id}/providerDetailsList"}, method = RequestMethod.GET)
    public String displayMembershipProviderDetailsListPage(@PathVariable Integer id,  @Validated MembershipProvider membershipProvider,
    				BindingResult bindingResult, Model model)throws Exception {
		
		List<MembershipProvider> mbrPrvdrList = membershipProviderService.findAllByMbrId(id);
		model.addAttribute("membershipProviderList", mbrPrvdrList);
        logger.info("Returning membershipProviderList.jsp page");
        return "membershipProviderList";
    }
	
	@RequestMapping(value ={"/admin/membership/{id}/complete","/user/membership/{id}/complete"}, method = RequestMethod.GET)
    public String completeMembershipProviderDetailsPage(@PathVariable Integer id,Model model)throws Exception {
		Membership dbMembership = membershipService.findById(id);
		model.addAttribute("membership", dbMembership);
		List<MembershipInsurance> dbMembershipInsuranceList = membershipInsuranceService.findAllByMbrId(id);
		List<MembershipProvider>  dbMembershipProviderList  = membershipProviderService.findAllByMbrId(id);
		
		for(MembershipProvider s:dbMembershipProviderList){  
		     if(s.getActiveInd() == 'Y')
		     {
		    	 MembershipProvider dbMembershipProvider = membershipProviderService.findByMbrId(id);
		 		 model.addAttribute("membershipProvider", dbMembershipProvider);
		     }
		   }  
		for(MembershipInsurance s:dbMembershipInsuranceList){  
		     if(s.getActiveInd() == 'Y')
		     {
		    	 MembershipInsurance dbMembershipInsurance = membershipInsuranceService.findByMbrId(id);
		 		 model.addAttribute("membershipInsurance", dbMembershipInsurance);
		     }
		   }  
		
		List<MembershipHedisMeasure> mbrHedisMeasureList = dbMembership.getMbrHedisMeasureList();
		model.addAttribute("mbrHedisMeasureList", mbrHedisMeasureList);
		
		List<MembershipInsurance> listBean = membershipInsuranceService.findAllByMbrId(id);
    	model.addAttribute("membershipDetailsList", listBean);
    	logger.info("Returning membershipDetailsList.jsp page");
    	
    	List<MembershipProvider> mbrPrvdrList = membershipProviderService.findAllByMbrId(id);
		model.addAttribute("membershipProviderList", mbrPrvdrList);
        logger.info("Returning membershipProviderList.jsp page");
    	
    	
		return "membershipCompleteDetails";
           
    }
	
	@RequestMapping(value = {"/admin/membership/{id}/hedisMeasure","/user/membership/{id}/hedisMeasure"}, method = RequestMethod.GET)
    public String displayMembershipHedisMeasurePage(@PathVariable Integer id, Model model)throws Exception {
		
		Membership dbMembership = membershipService.findById(id);
		List<MembershipHedisMeasure> mbrHedisMeasureList = dbMembership.getMbrHedisMeasureList();
		model.addAttribute("mbrHedisMeasureList", mbrHedisMeasureList);
        logger.info("Returning membershipHedisMeasure.jsp page");
        return "membershipHedisMeasure";
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
	
	@ModelAttribute("hedisMeasureList")
	public List<HedisMeasure> populateHedisMeasureList() {
		
		//Data referencing for Hedis Measure Codes list box
		List<HedisMeasure> hedisMeasureList = hedisMeasureService.findAll();
		return hedisMeasureList;
	}
	
	@ModelAttribute("insList")
	public List<Insurance> populateInsuranceList() {
		
		//Data referencing for Insurance list box
		List<Insurance> insuranceList = insuranceService.findAll();
		return insuranceList;
	}
		
}
