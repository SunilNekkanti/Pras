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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.pfchoice.common.CommonMessageContent;
import com.pfchoice.common.util.JsonConverter;
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

import ml.rugal.sshcommon.springmvc.util.Message;

@Controller
@SessionAttributes("username")
public class ContactController{
	
    @Autowired
    private ContactService contactService;
    
    @Autowired
    private StateService stateService;
    
    @Autowired
    private ZipCodeService zipCodeService;
    
    
    @Autowired
    private MembershipService membershipService;
    
    @Autowired
    private ProviderService providerService;
    
    @Autowired
    private InsuranceService insuranceService;
    
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
		List<ZipCode> zipCodeList = zipCodeService.findByStateCode(1);
		return zipCodeList;
	}
	
	@RequestMapping(value = "/membership/{id}/contact/new")
    public String addContactPage(@PathVariable Integer id,@ModelAttribute("username") String username, Model model) {
		
		Contact contact = createContactModel();
		model.addAttribute("contact", contact);
        return "membershipContactEdit";
    }
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/membership/{id}/contact/{cntId}", method = RequestMethod.GET)
    public String updateMembershipContact1Page(@PathVariable Integer id,@PathVariable Integer cntId,Model model) {
		Contact dbContact = contactService.findById(cntId);
		if(dbContact == null){
	    	   dbContact = createContactModel();
	       }
		final Integer stateId = dbContact.getStateCode().getCode();
		List<ZipCode> zipCodeList = zipCodeService.findByStateCode(stateId);
		model.addAttribute("zipCodeList",zipCodeList);
		model.addAttribute("contact", dbContact);
        logger.info("Returning contactEdit.jsp page");
        return "membershipContactEdit";
    }
		
	@RequestMapping(value = "/membership/{id}/contact/{cntId}/display", method = RequestMethod.GET)
    public String displayMembershipContactPage(@PathVariable Integer id,@PathVariable Integer cntId,Model model) {
		Contact dbContact = contactService.findById(cntId);
		 logger.info("Returning contact.getId()"+dbContact.getId());
	    
		final Integer stateId = dbContact.getStateCode().getCode();
		List<ZipCode> zipCodeList = zipCodeService.findByStateCode(stateId);
		model.addAttribute("zipCodeList",zipCodeList); 
		model.addAttribute("contact", dbContact);
        logger.info("Returning contactDisplay.jsp page");
        return "membershipContactDisplay";
    }
	
	@RequestMapping(value = "/membership/{id}/contactList")
    public String handleRequest(@PathVariable Integer id, Model model) throws Exception {
 
    	List<Contact> listBean = contactService.findAllContactsByRefId("membership",id);
		model.addAttribute("contactList", listBean);
 
		return "membershipContactList";
	}
	
	
	@RequestMapping(value = "/membership/{id}/contact/save.do", method = RequestMethod.POST, params ={"add"})
	public String addMembershipContactAction(@PathVariable Integer id, @Validated Contact contact,
            BindingResult bindingResult, Model model,
            @ModelAttribute("username") String username) {
        if (bindingResult.hasErrors()) {
            logger.info("Returning contactEdit.jsp page");
            return "membershipContactEdit";
        }
        
    	Membership dbMembership = membershipService.findById(id);
	 	logger.info("Returning membership.getId()"+dbMembership.getId());

	 	model.addAttribute("contact", contact);
    	contact.setCreatedBy(username);
    	contact.setUpdatedBy(username);
    	contact.setFileId(3);
    	ReferenceContact refCnt = createRefContactModel();
    	refCnt.setCreatedBy(username);
    	refCnt.setUpdatedBy(username);
    	refCnt.setMbr(dbMembership);
    	contact.setRefContact(refCnt);
    	
    	logger.info("Returning contactEditSuccess.jsp page after create");
      	contactService.save(contact);
   
    return "membershipContactEditSuccess";
    }
	
	
	@RequestMapping(value = "/membership/{id}/contact/save.do", method = RequestMethod.POST, params ={"update"})
	public String saveMembershipContactAction(@PathVariable Integer id, @Validated Contact contact,
            BindingResult bindingResult, Model model,
            @ModelAttribute("username") String username) {
        if (bindingResult.hasErrors()) {
        	contact.setActiveInd('Y');
            logger.info("Returning contactEdit.jsp page");
            return "membershipContactEdit";
        }
	        
        if (null != contact.getId())
        {
        	logger.info("Returning ContactEditSuccess.jsp page after update");
        	contact.setUpdatedBy(username);
        	contactService.update(contact);
        	contact.getRefContact().setUpdatedBy(username);
        	return "membershipContactEditSuccess";
        }
       
        return "membershipContactEdit";
    }
	

	@RequestMapping(value = "/membership/{id}/contact/save.do", method = RequestMethod.POST, params ={"delete"})
	public String deleteMembershipContactAction(@PathVariable Integer id, @Validated Contact contact,
            BindingResult bindingResult, Model model,
            @ModelAttribute("username") String username) {
            
	        if (null != contact.getId())
	        {
	        	logger.info("Returning ContactEditSuccess.jsp page after update");
	        	contact.setActiveInd('N');
	        	contact.setUpdatedBy(username);
	        	contact.getRefContact().setUpdatedBy(username);
	        	contactService.update(contact);
	        }
	        return "contactList";
    }
	
       
	
	@RequestMapping(value = "/provider/{id}/contact/new")
    public String addProviderContactPage(@PathVariable Integer id,Model model) {
		
		Contact contact = createContactModel();
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
        final Integer stateId = dbContact.getStateCode().getCode();
		List<ZipCode> zipCodeList = zipCodeService.findByStateCode(stateId);
		model.addAttribute("zipCodeList",zipCodeList);   
		model.addAttribute("contact", dbContact);
        logger.info("Returning contactEdit.jsp page");
        return "providerContactEdit";
    }
		
	@RequestMapping(value = "/provider/{id}/contact/{cntId}/display", method = RequestMethod.GET)
    public String displayProviderContactPage(@PathVariable Integer id,@PathVariable Integer cntId,Model model) {
		Contact dbContact = contactService.findById(cntId);
		 logger.info("Returning contact.getId()"+dbContact.getId());
		
		final Integer stateId = dbContact.getStateCode().getCode();
		List<ZipCode> zipCodeList = zipCodeService.findByStateCode(stateId);
		model.addAttribute("zipCodeList",zipCodeList);   
		model.addAttribute("contact", dbContact);
        logger.info("Returning contactDisplay.jsp page");
        return "providerContactDisplay";
    }
	
	@RequestMapping(value = "/provider/{id}/contactList")
    public String providerContactList(@PathVariable Integer id, Model model) throws Exception {
 
    	List<Contact> listBean = contactService.findAllContactsByRefId("provider",id);
		model.addAttribute("contactList", listBean);
 
		return "providerContactList";
	}
	
	
	@RequestMapping(value = "/provider/{id}/contact/save.do", method = RequestMethod.POST, params= {"add"})
	public String addProviderContactAction(@PathVariable Integer id, @Validated Contact contact,
            BindingResult bindingResult, Model model,
            @ModelAttribute("username") String username) {
        if (bindingResult.hasErrors()) {
        	
            logger.info("Returning contactEdit.jsp page");
            return "providerContactEdit";
        }
           
    	Provider dbProvider = providerService.findById(id);
	 	logger.info("Returning provider.getId()"+dbProvider.getId());

	 	model.addAttribute("contact", contact);
    	contact.setCreatedBy(username);
    	contact.setUpdatedBy(username);
    	contact.setFileId(1);
    	ReferenceContact refCnt = createRefContactModel();
    	refCnt.setCreatedBy(username);
    	refCnt.setUpdatedBy(username);
    	refCnt.setPrvdr(dbProvider);
    	contact.setRefContact(refCnt);
    	
    	logger.info("Returning contactEditSuccess.jsp page after create");
      	contactService.save(contact);
	       
	        return "providerContactEditSuccess";
    }

	@RequestMapping(value = "/provider/{id}/contact/save.do", method = RequestMethod.POST, params= {"update"})
	public String updateProviderContactAction(@PathVariable Integer id, @Validated Contact contact,
            BindingResult bindingResult, Model model,
            @ModelAttribute("username") String username) {
        if (bindingResult.hasErrors()) {
        	contact.setActiveInd('Y');
            logger.info("Returning contactEdit.jsp page");
            return "providerContactEdit";
        }
	        
        if (null != contact.getId())
        {
        	logger.info("Returning ContactEditSuccess.jsp page after update");
        	contact.setUpdatedBy(username);
        	contact.getRefContact().setUpdatedBy(username);
        	contact.getRefContact().setUpdatedBy(username);
        	contactService.update(contact);
        	return "providerContactEditSuccess";
        }
       
        return "providerContactEdit";
    }
	
	@RequestMapping(value = "/provider/{id}/contact/save.do", method = RequestMethod.POST, params= {"delete"})
	public String saveProviderContactAction(@PathVariable Integer id, @Validated Contact contact,
            BindingResult bindingResult, Model model,
            @ModelAttribute("username") String username) {
        if (bindingResult.hasErrors()) {
            logger.info("Returning contactEdit.jsp page");
            contact.setActiveInd('Y');
            return "providerContactEdit";
        }
	        
	        if (null != contact.getId())
	        {
	        	logger.info("Returning ContactEditSuccess.jsp page after update");
	        	contact.setActiveInd('N');
	        	contact.setUpdatedBy(username);
	        	contact.getRefContact().setUpdatedBy(username);
	        	contactService.update(contact);
	        	return "providerContactEditSuccess";
	        }
	       
	        return "providerContactEdit";
    }
	
	@RequestMapping(value = "/insurance/{id}/contact/new")
    public String addInsuranceContactPage(@PathVariable Integer id,Model model) {
		
		Contact contact = createContactModel();
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
        final Integer stateId = dbContact.getStateCode().getCode();
		List<ZipCode> zipCodeList = zipCodeService.findByStateCode(stateId);
		model.addAttribute("zipCodeList",zipCodeList);
		model.addAttribute("contact", dbContact);
        logger.info("Returning contactEdit.jsp page");
        return "insuranceContactEdit";
    }
		
	@RequestMapping(value = "/insurance/{id}/contact/{cntId}/display", method = RequestMethod.GET)
    public String displayInsuranceContactPage(@PathVariable Integer id,@PathVariable Integer cntId,Model model) {
		Contact dbContact = contactService.findById(cntId);
		 logger.info("Returning contact.getId()"+dbContact.getId());
		final Integer stateId = dbContact.getStateCode().getCode();
		List<ZipCode> zipCodeList = zipCodeService.findByStateCode(stateId);
		model.addAttribute("zipCodeList",zipCodeList);     
		model.addAttribute("contact", dbContact);
        logger.info("Returning contactDisplay.jsp page");
        return "insuranceContactDisplay";
    }
	
	@RequestMapping(value = "/insurance/{id}/contactList")
    public String insuranceContactList(@PathVariable Integer id,Model model) throws Exception {
 
    	List<Contact> listBean = contactService.findAllContactsByRefId("insurance",id);
		model.addAttribute("contactList", listBean);
 
		return "insuranceContactList";
	}
	
	
	@RequestMapping(value = "/insurance/{id}/contact/save.do", method = RequestMethod.POST, params={"add"})
	public String addInsuranceContactAction(@PathVariable Integer id, @Validated Contact contact,
            BindingResult bindingResult, Model model,
            @ModelAttribute("username") String username) {
        if (bindingResult.hasErrors()) {
            logger.info("Returning contactEdit.jsp page");
            return "insuranceContactEdit";
        }
	        	
    	Insurance dbInsurance = insuranceService.findById(id);
	 	logger.info("Returning insurance.getId()"+dbInsurance.getId());

	 	model.addAttribute("contact", contact);
    	contact.setCreatedBy(username);
    	contact.setUpdatedBy(username);
    	contact.setFileId(3);
    	ReferenceContact refCnt = createRefContactModel();
    	refCnt.setCreatedBy(username);
    	refCnt.setUpdatedBy(username);
    	refCnt.setIns(dbInsurance);
    	contact.setRefContact(refCnt);
     	logger.info("Returning contactEditSuccess.jsp page after create");
      	contactService.save(contact);
       	return "insuranceContactEditSuccess";
    }

	@RequestMapping(value = "/insurance/{id}/contact/save.do", method = RequestMethod.POST, params={"update"})
	public String updateInsuranceContactAction(@PathVariable Integer id, @Validated Contact contact,
            BindingResult bindingResult, Model model,
            @ModelAttribute("username") String username) {
        if (bindingResult.hasErrors()) {
            logger.info("Returning contactEdit.jsp page");
            contact.setActiveInd('Y');
            return "insuranceContactEdit";
        }
	        
        if (null != contact.getId())
        {
        	logger.info("Returning ContactEditSuccess.jsp page after update");
        	contact.setUpdatedBy(username);
        	contact.getRefContact().setUpdatedBy(username);
        	contactService.update(contact);
        	return "insuranceContactEditSuccess";
        }
       
        return "insuranceContactEdit";
    }

	@RequestMapping(value = "/insurance/{id}/contact/save.do", method = RequestMethod.POST, params={"delete"})
	public String saveInsuranceContactAction(@PathVariable Integer id, @Validated Contact contact,
            BindingResult bindingResult, Model model,
            @ModelAttribute("username") String username) {
        if (bindingResult.hasErrors()) {
            logger.info("Returning contactEdit.jsp page");
            contact.setActiveInd('Y');
            return "insuranceContactEdit";
        }
	        
        if (null != contact.getId())
        {
        	logger.info("Returning ContactEditSuccess.jsp page after update");
        	contact.setActiveInd('N');
        	contact.setUpdatedBy(username);
        	contact.getRefContact().setUpdatedBy(username);
        	contactService.update(contact);
        	return "insuranceContactEditSuccess";
        }
       
        return "insuranceContactEdit";
    }

	@ResponseBody
	@RequestMapping(value = "/contact/state/{stateCode}")
	 public Message  getStateZip(@PathVariable Integer stateCode,@ModelAttribute("username") String username, Model model) 
	{
		  List<ZipCode> zipCodeList = zipCodeService.findByStateCode(stateCode);
		  return  Message.successMessage(CommonMessageContent.MEMBERSHIP_LIST, JsonConverter.getJsonObject(zipCodeList));
	        
	 }
}
