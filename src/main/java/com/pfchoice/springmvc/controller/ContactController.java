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

import com.pfchoice.core.entity.Contact;
import com.pfchoice.core.entity.Insurance;
import com.pfchoice.core.entity.Membership;
import com.pfchoice.core.entity.Provider;
import com.pfchoice.core.entity.ReferenceContact;
import com.pfchoice.core.entity.State;
import com.pfchoice.core.entity.ZipCode;
import com.pfchoice.core.service.ContactService;
import com.pfchoice.core.service.InsuranceService;
import com.pfchoice.core.service.MembershipService;
import com.pfchoice.core.service.ProviderService;
import com.pfchoice.core.service.StateService;
import com.pfchoice.core.service.ZipCodeService;

@Controller
public class ContactController{
	
    @Autowired
    ContactService contactService;
    
    @Autowired
    StateService stateService;
    
    @Autowired
    ZipCodeService zipCodeService;
    
    
    @Autowired
    MembershipService membershipService;
    
    @Autowired
    ProviderService providerService;
    
    @Autowired
    InsuranceService insuranceService;
    
    @Autowired
    @Qualifier("contactValidator")
    private Validator validator;
 
    @InitBinder("contact")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }
  
    private static final Logger logger = LoggerFactory
            .getLogger(ContactController.class);
 
	public ContactController() {
    }
	
	
	@ModelAttribute("contact")
    public Contact createContactModel() {
        // ModelAttribute value should be same as used in the empSave.jsp
        return new Contact();
    }
	
	@ModelAttribute("refContact")
    public ReferenceContact createRefContactModel() {
        // ModelAttribute value should be same as used in the empSave.jsp
        return new ReferenceContact();
    }
	
	@ModelAttribute("stateList")
	public List<State> populateStateList() {
		
		//Data referencing for county list box
		List<State> stateList = stateService.findAll();
		return stateList;
	}

	@ModelAttribute("zipCodeList")
	public List<ZipCode> populateZipCodeList() {
		
		//Data referencing for gender list box
		List<ZipCode> zipCodeList = zipCodeService.findAll();
		return zipCodeList;
	}
	
	@RequestMapping(value = "/membership/{id}/contact/new")
    public String addContactPage(Model model) {
		
		Contact contact = createContactModel();
		contact.setCreatedBy("sarath");
		contact.setFileId(3);
		model.addAttribute("contact", contact);
        return "membershipContactEdit";
    }
	
	@RequestMapping(value = "/membership/{id}/contact/{cntId}", method = RequestMethod.GET)
    public String updateMembershipContact1Page(@PathVariable Integer id,@PathVariable Integer cntId,Model model) {
		Contact dbContact = contactService.findById(cntId);
		if(dbContact == null){
	    	   dbContact = createContactModel();
	       }
		model.addAttribute("contact", dbContact);
        logger.info("Returning contactEdit.jsp page");
        return "membershipContactEdit";
    }
		
	@RequestMapping(value = "/membership/{id}/contact/{cntId}/display", method = RequestMethod.GET)
    public String displayMembershipContactPage(@PathVariable Integer id,@PathVariable Integer cntId,Model model) {
		Contact dbContact = contactService.findById(cntId);
		 logger.info("Returning contact.getId()"+dbContact.getId());
	       
		model.addAttribute("contact", dbContact);
        logger.info("Returning contactDisplay.jsp page");
        return "membershipContactDisplay";
    }
	
	@RequestMapping(value = "/membership/{id}/contactList")
    public ModelAndView handleRequest(@PathVariable Integer id) throws Exception {
 
    	List<Contact> listBean = contactService.findAllContactsByRefId("membership",id);
		ModelAndView modelAndView = new ModelAndView("membershipContactList");
		modelAndView.addObject("contactList", listBean);
 
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/membership/{id}/contact/save.do", method = RequestMethod.POST, params ={"add"})
	public String addMembershipContactAction(@PathVariable Integer id, @Validated Contact contact,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            logger.info("Returning contactEdit.jsp page");
            return "membershipContactEdit";
        }
        
    	Membership dbMembership = membershipService.findById(id);
	 	logger.info("Returning membership.getId()"+dbMembership.getId());

	 	model.addAttribute("contact", contact);
    	contact.setCreatedBy("sarath");
    	contact.setActiveInd('Y');
    	contact.setFileId(3);
    	ReferenceContact refCnt = createRefContactModel();
    	refCnt.setCreatedBy("sarath");
    	refCnt.setMbr(dbMembership);
    	contact.setRefContact(refCnt);
    	
    	logger.info("Returning contactEditSuccess.jsp page after create");
      	contactService.save(contact);
   
    return "membershipContactEditSuccess";
    }
	
	
	@RequestMapping(value = "/membership/{id}/contact/save.do", method = RequestMethod.POST, params ={"update"})
	public String saveMembershipContactAction(@PathVariable Integer id, @Validated Contact contact,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            logger.info("Returning contactEdit.jsp page");
            return "membershipContactEdit";
        }
	        
        if (null != contact.getId())
        {
        	logger.info("Returning ContactEditSuccess.jsp page after update");
        	contactService.update(contact);
        	return "membershipContactEditSuccess";
        }
       
        return "membershipContactEdit";
    }
	

	@RequestMapping(value = "/membership/{id}/contact/save.do", method = RequestMethod.POST, params ={"delete"})
	public String deleteMembershipContactAction(@PathVariable Integer id, @Validated Contact contact,
            BindingResult bindingResult, Model model) {
            
	        if (null != contact.getId())
	        {
	        	logger.info("Returning ContactEditSuccess.jsp page after update");
	        	contact.setActiveInd('N');
	        	contactService.update(contact);
	        }
	        return "contactList";
    }
	
       
	
	@RequestMapping(value = "/provider/{id}/contact/new")
    public String addProviderContactPage(@PathVariable Integer id,Model model) {
		
		Contact contact = createContactModel();
		contact.setCreatedBy("sarath");
		contact.setFileId(3);
		model.addAttribute("contact", contact);
        return "providerContactEdit";
    }
	
	
	@RequestMapping(value = "/provider/{id}/contact/{cntId}", method = RequestMethod.GET)
    public String updateProviderContactPage(@PathVariable Integer id,@PathVariable Integer cntId,Model model) {
		Contact dbContact = contactService.findById(cntId);
		// logger.info("Returning contact.getId()"+dbContact.getId());
	       if(dbContact == null){
	    	   dbContact = createContactModel();
	       }
		model.addAttribute("contact", dbContact);
        logger.info("Returning contactEdit.jsp page");
        return "providerContactEdit";
    }
		
	@RequestMapping(value = "/provider/{id}/contact/{cntId}/display", method = RequestMethod.GET)
    public String displayProviderContactPage(@PathVariable Integer id,@PathVariable Integer cntId,Model model) {
		Contact dbContact = contactService.findById(cntId);
		 logger.info("Returning contact.getId()"+dbContact.getId());
	       
		model.addAttribute("contact", dbContact);
        logger.info("Returning contactDisplay.jsp page");
        return "providerContactDisplay";
    }
	
	@RequestMapping(value = "/provider/{id}/contactList")
    public ModelAndView providerContactList(@PathVariable Integer id) throws Exception {
 
    	List<Contact> listBean = contactService.findAllContactsByRefId("provider",id);
		ModelAndView modelAndView = new ModelAndView("providerContactList");
		modelAndView.addObject("contactList", listBean);
 
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/provider/{id}/contact/save.do", method = RequestMethod.POST, params= {"add"})
	public String addProviderContactAction(@PathVariable Integer id, @Validated Contact contact,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
        	
            logger.info("Returning contactEdit.jsp page");
            return "providerContactEdit";
        }
           
    	Provider dbProvider = providerService.findById(id);
	 	logger.info("Returning provider.getId()"+dbProvider.getId());

	 	model.addAttribute("contact", contact);
    	contact.setCreatedBy("sarath");
    	contact.setActiveInd('Y');
    	contact.setFileId(3);
    	ReferenceContact refCnt = createRefContactModel();
    	refCnt.setCreatedBy("sarath");
    	refCnt.setPrvdr(dbProvider);
    	contact.setRefContact(refCnt);
    	
    	logger.info("Returning contactEditSuccess.jsp page after create");
      	contactService.save(contact);
	       
	        return "providerContactEditSuccess";
    }

	@RequestMapping(value = "/provider/{id}/contact/save.do", method = RequestMethod.POST, params= {"update"})
	public String updateProviderContactAction(@PathVariable Integer id, @Validated Contact contact,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            logger.info("Returning contactEdit.jsp page");
            return "providerContactEdit";
        }
	        
        if (null != contact.getId())
        {
        	logger.info("Returning ContactEditSuccess.jsp page after update");
        	System.out.println("contact"+contact.getRefContact().getPrvdr());
        	contactService.update(contact);
        	return "providerContactEditSuccess";
        }
       
        return "providerContactEdit";
    }
	
	@RequestMapping(value = "/provider/{id}/contact/save.do", method = RequestMethod.POST, params= {"delete"})
	public String saveProviderContactAction(@PathVariable Integer id, @Validated Contact contact,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            logger.info("Returning contactEdit.jsp page");
            return "providerContactEdit";
        }
	        
	        if (null != contact.getId())
	        {
	        	logger.info("Returning ContactEditSuccess.jsp page after update");
	        	System.out.println("contact"+contact.getRefContact().getPrvdr());
	        	contact.setActiveInd('N');
	        	contactService.update(contact);
	        	return "providerContactEditSuccess";
	        }
	       
	        return "providerContactEdit";
    }
	
	@RequestMapping(value = "/insurance/{id}/contact/new")
    public String addInsuranceContactPage(@PathVariable Integer id,Model model) {
		
		Contact contact = createContactModel();
		contact.setCreatedBy("sarath");
		contact.setFileId(3);
		model.addAttribute("contact", contact);
        return "insuranceContactEdit";
    }
	
	@RequestMapping(value = "/insurance/{id}/contact/{cntId}", method = RequestMethod.GET)
    public String updateInsuranceContactPage(@PathVariable Integer id,@PathVariable Integer cntId,Model model) {
		Contact dbContact = contactService.findById(cntId);
		// logger.info("Returning contact.getId()"+dbContact.getId());
	       if(dbContact == null){
	    	   dbContact = createContactModel();
	       }
		model.addAttribute("contact", dbContact);
        logger.info("Returning contactEdit.jsp page");
        return "insuranceContactEdit";
    }
		
	@RequestMapping(value = "/insurance/{id}/contact/{cntId}/display", method = RequestMethod.GET)
    public String displayInsuranceContactPage(@PathVariable Integer id,@PathVariable Integer cntId,Model model) {
		Contact dbContact = contactService.findById(cntId);
		 logger.info("Returning contact.getId()"+dbContact.getId());
	       
		model.addAttribute("contact", dbContact);
        logger.info("Returning contactDisplay.jsp page");
        return "insuranceContactDisplay";
    }
	
	@RequestMapping(value = "/insurance/{id}/contactList")
    public ModelAndView insuranceContactList(@PathVariable Integer id) throws Exception {
 
    	List<Contact> listBean = contactService.findAllContactsByRefId("insurance",id);
		ModelAndView modelAndView = new ModelAndView("insuranceContactList");
		modelAndView.addObject("contactList", listBean);
 
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/insurance/{id}/contact/save.do", method = RequestMethod.POST, params={"add"})
	public String addInsuranceContactAction(@PathVariable Integer id, @Validated Contact contact,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
        	for( ObjectError oe :bindingResult.getAllErrors()){
        		System.out.println("oe "+oe.getObjectName() +""+oe.getCode());
        	}
            logger.info("Returning contactEdit.jsp page");
            return "insuranceContactEdit";
        }
	        	
    	Insurance dbInsurance = insuranceService.findById(id);
	 	logger.info("Returning insurance.getId()"+dbInsurance.getId());

	 	model.addAttribute("contact", contact);
    	contact.setCreatedBy("sarath");
    	contact.setActiveInd('Y');
    	contact.setFileId(3);
    	ReferenceContact refCnt = createRefContactModel();
    	refCnt.setCreatedBy("sarath");
    	refCnt.setIns(dbInsurance);
    	contact.setRefContact(refCnt);
     	logger.info("Returning contactEditSuccess.jsp page after create");
      	contactService.save(contact);
       	return "insuranceContactEditSuccess";
    }

	@RequestMapping(value = "/insurance/{id}/contact/save.do", method = RequestMethod.POST, params={"update"})
	public String updateInsuranceContactAction(@PathVariable Integer id, @Validated Contact contact,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
        	for( ObjectError oe :bindingResult.getAllErrors()){
        		System.out.println("oe "+oe.getObjectName() +""+oe.getCode());
        	}
            logger.info("Returning contactEdit.jsp page");
            return "insuranceContactEdit";
        }
	        
        if (null != contact.getId())
        {
        	logger.info("Returning ContactEditSuccess.jsp page after update");
        	contactService.update(contact);
        	return "insuranceContactEditSuccess";
        }
       
        return "insuranceContactEdit";
    }

	@RequestMapping(value = "/insurance/{id}/contact/save.do", method = RequestMethod.POST, params={"delete"})
	public String saveInsuranceContactAction(@PathVariable Integer id, @Validated Contact contact,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            logger.info("Returning contactEdit.jsp page");
            return "insuranceContactEdit";
        }
	        
        if (null != contact.getId())
        {
        	logger.info("Returning ContactEditSuccess.jsp page after update");
        	contact.setActiveInd('N');
        	contactService.update(contact);
        	return "insuranceContactEditSuccess";
        }
       
        return "insuranceContactEdit";
    }

}
