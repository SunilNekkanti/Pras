package com.pfchoice.springmvc.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.pfchoice.core.entity.Contact;
import com.pfchoice.core.entity.County;
import com.pfchoice.core.entity.Gender;
import com.pfchoice.core.entity.Membership;
import com.pfchoice.core.entity.ReferenceContact;
import com.pfchoice.core.entity.State;
import com.pfchoice.core.entity.ZipCode;
import com.pfchoice.core.service.ContactService;
import com.pfchoice.core.service.MembershipService;
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
    
 /*   @Autowired
    @Qualifier("contactValidator")
    private Validator validator;
 
    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }
 */   
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
	
 
	@RequestMapping(value = "/membership/{id}/contact/new")
    public String addContactPage(Model model) {
		
		Contact contact = createContactModel();
		contact.setCreatedBy("sarath");
		contact.setFileId(3);
		model.addAttribute("contact", contact);
        return "contactEdit";
    }
	
	@RequestMapping(value = "/membership/{id}/contact", method = RequestMethod.GET)
    public String updateMembershipContactPage(@PathVariable Integer id,Model model) {
		Contact dbContact = contactService.findActiveContactByRefId("membership",id);
		// logger.info("Returning contact.getId()"+dbContact.getId());
	       if(dbContact == null){
	    	   dbContact = createContactModel();
	       }
		model.addAttribute("contact", dbContact);
        logger.info("Returning contactEdit.jsp page");
        return "contactEdit";
    }
	
	@RequestMapping(value = "/membership/{id}/contactDisplay", method = RequestMethod.GET)
    public String displayMembershipContactPage(@PathVariable Integer id,Model model) {
		Contact dbContact = contactService.findActiveContactByRefId("membership", id);
		 logger.info("Returning contact.getId()"+dbContact.getId());
	       
		model.addAttribute("contact", dbContact);
        logger.info("Returning contactDisplay.jsp page");
        return "contactDisplay";
    }
	
	@RequestMapping(value = "/membership/{id}/contactList")
    public ModelAndView handleRequest(@PathVariable Integer id) throws Exception {
 
    	List<Contact> listBean = contactService.findAllContactsByRefId("membership",id);
    	System.out.println("contact list bean sze "+listBean.size());
		ModelAndView modelAndView = new ModelAndView("contactList");
		modelAndView.addObject("contactList", listBean);
 
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/membership/{id}/contact/save.do", method = RequestMethod.POST)
	public String saveMembershipContactAction(@PathVariable Integer id, @Validated Contact contact,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
        	for( ObjectError oe :bindingResult.getAllErrors()){
        		System.out.println("oe "+oe.getObjectName() +""+oe.getCode());
        	}
            logger.info("Returning contactEdit.jsp page");
            return "contactEdit";
        }
        else
        {
	        
	        if (null != contact.getId())
	        {
	        	logger.info("Returning ContactEditSuccess.jsp page after update");
	        	contactService.update(contact);
	        }
	        else
	        {
	        	
	        	Membership dbMembership = membershipService.findById(id);
	   		 	logger.info("Returning membership.getId()"+dbMembership.getId());

	   		 	model.addAttribute("contact", contact);
	        	contact.setCreatedBy("sarath");
	        	ReferenceContact refCnt = createRefContactModel();
	        	refCnt.setCreatedBy("sarath");
	        	refCnt.setMbr(dbMembership);
	        	contact.setRefContact(refCnt);
	        	
	        	logger.info("Returning contactEditSuccess.jsp page after create");
	 	      	contactService.save(contact);
	        }
	       
	        return "contactEditSuccess";
        }    
    }
	
	@ModelAttribute("stateMap")
	public Map<Integer,String> populateStateList() {
		
		//Data referencing for county list box
		List<State> stateList = stateService.findAll();
		Map<Integer,String> stateMap = new HashMap<Integer,String>();
		for(State s:stateList){
			stateMap.put(s.getCode(),s.getShortName());
		}
		return stateMap;
	}

	@ModelAttribute("zipCodeMap")
	public Map<Integer,Integer> populateZipCodeList() {
		
		//Data referencing for gender list box
		List<ZipCode> zipCodeList = zipCodeService.findAll();
		Map<Integer,Integer> zipCodeMap = new HashMap<Integer,Integer>();
		for(ZipCode zc:zipCodeList){
			zipCodeMap.put(zc.getCode(),zc.getCode());
		}
		return zipCodeMap;
	}
	

}
