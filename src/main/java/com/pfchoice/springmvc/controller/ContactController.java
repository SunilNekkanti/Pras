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
import com.pfchoice.common.SystemDefaultProperties;
import com.pfchoice.common.util.JsonConverter;
import com.pfchoice.common.util.TileDefinitions;
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

import ml.rugal.sshcommon.page.Pagination;
import ml.rugal.sshcommon.springmvc.util.Message;

@Controller
@SessionAttributes({ "username", "userpath" })
public class ContactController {

	private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

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

	/**
	 * @param binder
	 */
	@InitBinder("contact")
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	/**
	 * @return
	 */
	@ModelAttribute("contact")
	public Contact createContactModel() {
		return new Contact();
	}

	/**
	 * @return
	 */
	@ModelAttribute("refContact")
	public ReferenceContact createRefContactModel() {
		return new ReferenceContact();
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ModelAttribute("stateList")
	public List<State> populateStateList() {

		Pagination page = stateService.getPage(SystemDefaultProperties.DEFAULT_PAGE_NO , SystemDefaultProperties.MEDIUM_LIST_SIZE);
		return (List<State>) page.getList();
	}

	/**
	 * @return
	 */
	@ModelAttribute("zipCodeList")
	public List<ZipCode> populateZipCodeList() {

		return zipCodeService.findByStateCode(1);
	}

	/**
	 * @param id
	 * @param username
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/membership/{id}/contact/new", "/user/membership/{id}/contact/new" })
	public String addContactPage(@PathVariable Integer id, Model model) {
		logger.info("membership id is"+id);
		Contact contact = createContactModel();
		model.addAttribute("contact", contact);
		return TileDefinitions.MEMBERSHIPCONTACTEDIT.toString(); 
	}

	/**
	 * @param id
	 * @param cntId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/membership/{id}/contact/{cntId}",
			"/user/membership/{id}/contact/{cntId}" }, method = RequestMethod.GET)
	public String updateMembershipContact1Page(@PathVariable Integer id, @PathVariable Integer cntId, Model model) {
		logger.info("membership id is"+id);
		Contact dbContact = contactService.findById(cntId);
		if (dbContact == null) {
			dbContact = createContactModel();
		}
		final Integer stateId = dbContact.getStateCode().getCode();
		List<ZipCode> zipCodeList = zipCodeService.findByStateCode(stateId);
		model.addAttribute("zipCodeList", zipCodeList);
		model.addAttribute("contact", dbContact);
		logger.info("Returning contactEdit.jsp page");
		return TileDefinitions.MEMBERSHIPCONTACTEDIT.toString();
	}

	/**
	 * @param id
	 * @param cntId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/membership/{id}/contact/{cntId}/display",
			"/user/membership/{id}/contact/{cntId}/display" }, method = RequestMethod.GET)
	public String displayMembershipContactPage(@PathVariable Integer id, @PathVariable Integer cntId, Model model) {
		Contact dbContact = contactService.findById(cntId);
		logger.info("Returning contact.getId()" + dbContact.getId());
		logger.info("membership id is"+id);
		final Integer stateId = dbContact.getStateCode().getCode();
		List<ZipCode> zipCodeList = zipCodeService.findByStateCode(stateId);
		model.addAttribute("zipCodeList", zipCodeList);
		model.addAttribute("contact", dbContact);
		logger.info("Returning contactDisplay.jsp page");
		return TileDefinitions.MEMBERSHIPCONTACTDISPLAY.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/membership/{id}/contactList", "/user/membership/{id}/contactList" })
	public String handleRequest(@PathVariable Integer id, Model model) {

		List<Contact> listBean = contactService.findAllContactsByRefId("membership", id);
		model.addAttribute("contactList", listBean);

		return TileDefinitions.MEMBERSHIPCONTACTLIST.toString();
	}

	/**
	 * @param id
	 * @param contact
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/membership/{id}/contact/save.do",
			"/user/membership/{id}/contact/save.do" }, method = RequestMethod.POST, params = { "add" })
	public String addMembershipContactAction(@PathVariable Integer id, @Validated Contact contact,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		logger.info("membership id is"+id);
		if (bindingResult.hasErrors()) {
			logger.info("Returning contactEdit.jsp page");
			return TileDefinitions.MEMBERSHIPCONTACTEDIT.toString();
		}

		Membership dbMembership = membershipService.findById(id);
		logger.info("Returning membership.getId()" + dbMembership.getId());

		model.addAttribute("contact", contact);
		contact.setCreatedBy(username);
		contact.setUpdatedBy(username);
		contact.setFileId(1);
		ReferenceContact refCnt = createRefContactModel();
		refCnt.setCreatedBy(username);
		refCnt.setUpdatedBy(username);
		refCnt.setMbr(dbMembership);
		contact.setRefContact(refCnt);
		logger.info("Returning contactEditSuccess.jsp page after create");
		contactService.save(contact);
		model.addAttribute("Message", "Member Contact Added Successfully");
		List<Contact> listBean = contactService.findAllContactsByRefId("membership", id);
		model.addAttribute("contactList", listBean);

		return TileDefinitions.MEMBERSHIPCONTACTLIST.toString();
	}

	/**
	 * @param id
	 * @param contact
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/membership/{id}/contact/save.do",
			"/user/membership/{id}/contact/save.do" }, method = RequestMethod.POST, params = { "update" })
	public String saveMembershipContactAction(@PathVariable Integer id, @Validated Contact contact,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		logger.info("membership id is"+id);
		contact.setActiveInd('Y');
		if (bindingResult.hasErrors()) {
			contact.setActiveInd('Y');
			logger.info("Returning contactEdit.jsp page");
			return TileDefinitions.MEMBERSHIPCONTACTEDIT.toString();
		}
		if (null != contact.getId()) {
			logger.info("Returning ContactEditSuccess.jsp page after update");
			contact.setUpdatedBy(username);
			contact.setCreatedBy(username);
			contact.getRefContact().setUpdatedBy(username);
			contact.getRefContact().setCreatedBy(username);
			contact.getRefContact().setActiveInd('Y');
			contactService.update(contact);
			model.addAttribute("Message", "Member Contact Updated Successfully");
			List<Contact> listBean = contactService.findAllContactsByRefId("membership", id);
			model.addAttribute("contactList", listBean);

			return TileDefinitions.MEMBERSHIPCONTACTLIST.toString();
		}

		return TileDefinitions.MEMBERSHIPCONTACTEDIT.toString();
	}

	/**
	 * @param id
	 * @param contact
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/membership/{id}/contact/save.do",
			"/user/membership/{id}/contact/save.do" }, method = RequestMethod.POST, params = { "delete" })
	public String deleteMembershipContactAction(@PathVariable Integer id, @Validated Contact contact,
			 Model model, @ModelAttribute("username") String username) {

		if (null != contact.getId()) {
			logger.info("Returning ContactEditSuccess.jsp page after update");
			contact.setActiveInd('N');
			contact.setUpdatedBy(username);
			contact.setCreatedBy(username);
			contact.getRefContact().setUpdatedBy(username);
			contact.getRefContact().setCreatedBy(username);
			contact.getRefContact().setActiveInd('N');
			contactService.update(contact);
			model.addAttribute("Message", "Member Contact Deleted Successfully");
			List<Contact> listBean = contactService.findAllContactsByRefId("membership", id);
			model.addAttribute("contactList", listBean);
			return TileDefinitions.MEMBERSHIPCONTACTLIST.toString();
		}
		return TileDefinitions.MEMBERSHIPCONTACTEDIT.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/provider/{id}/contact/new", "/user/provider/{id}/contact/new" })
	public String addProviderContactPage(@PathVariable Integer id, Model model) {
		logger.info("provider id is"+id);
		Contact contact = createContactModel();
		model.addAttribute("contact", contact);
		return TileDefinitions.PROVIDERCONTACTEDIT.toString();
	}

	/**
	 * @param id
	 * @param cntId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/provider/{id}/contact/{cntId}",
			"/user/provider/{id}/contact/{cntId}" }, method = RequestMethod.GET)
	public String updateProviderContactPage(@PathVariable Integer id, @PathVariable Integer cntId, Model model) {
		logger.info("provider id is"+id);
		Contact dbContact = contactService.findById(cntId);
		if (dbContact == null) {
			dbContact = createContactModel();
		}
		final Integer stateId = dbContact.getStateCode().getCode();
		List<ZipCode> zipCodeList = zipCodeService.findByStateCode(stateId);
		model.addAttribute("zipCodeList", zipCodeList);
		model.addAttribute("contact", dbContact);
		logger.info("Returning contactEdit.jsp page");
		return TileDefinitions.PROVIDERCONTACTEDIT.toString();
	}

	/**
	 * @param id
	 * @param cntId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/provider/{id}/contact/{cntId}/display",
			"/user/provider/{id}/contact/{cntId}/display" }, method = RequestMethod.GET)
	public String displayProviderContactPage(@PathVariable Integer id, @PathVariable Integer cntId, Model model) {
		logger.info("provider id is"+id);
		Contact dbContact = contactService.findById(cntId);
		logger.info("Returning contact.getId()" + dbContact.getId());

		final Integer stateId = dbContact.getStateCode().getCode();
		List<ZipCode> zipCodeList = zipCodeService.findByStateCode(stateId);
		model.addAttribute("zipCodeList", zipCodeList);
		model.addAttribute("contact", dbContact);
		logger.info("Returning contactDisplay.jsp page");
		return TileDefinitions.PROVIDERCONTACTDISPLAY.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/provider/{id}/contactList", "/user/provider/{id}/contactList" })
	public String providerContactList(@PathVariable Integer id, Model model) {

		List<Contact> listBean = contactService.findAllContactsByRefId("provider", id);
		model.addAttribute("contactList", listBean);

		return TileDefinitions.PROVIDERCONTACTLIST.toString();
	}

	/**
	 * @param id
	 * @param contact
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/provider/{id}/contact/save.do",
			"/user/provider/{id}/contact/save.do" }, method = RequestMethod.POST, params = { "add" })
	public String addProviderContactAction(@PathVariable Integer id, @Validated Contact contact,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		logger.info("provider id is"+id);
		if (bindingResult.hasErrors()) {

			logger.info("Returning contactEdit.jsp page");
			return TileDefinitions.PROVIDERCONTACTEDIT.toString();
		}

		Provider dbProvider = providerService.findById(id);
		logger.info("Returning provider.getId()" + dbProvider.getId());

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
		model.addAttribute("Message", "Provider Contact Added Successfully");
		List<Contact> listBean = contactService.findAllContactsByRefId("provider", id);
		model.addAttribute("contactList", listBean);
		return TileDefinitions.PROVIDERCONTACTLIST.toString();
	}

	/**
	 * @param id
	 * @param contact
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/provider/{id}/contact/save.do",
			"/user/provider/{id}/contact/save.do" }, method = RequestMethod.POST, params = { "update" })
	public String updateProviderContactAction(@PathVariable Integer id, @Validated Contact contact,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		logger.info("provider id is"+id);
		contact.setActiveInd('Y');
		if (bindingResult.hasErrors()) {
			contact.setActiveInd('Y');
			logger.info("Returning contactEdit.jsp page");
			return TileDefinitions.PROVIDERCONTACTEDIT.toString();
		}

		if (null != contact.getId()) {
			logger.info("Returning ContactEditSuccess.jsp page after update");
			contact.setCreatedBy(username);
			contact.setUpdatedBy(username);
			contact.getRefContact().setUpdatedBy(username);
			contact.getRefContact().setCreatedBy(username);
			contact.getRefContact().setActiveInd('Y');
			contact.setActiveInd('Y');
			contactService.update(contact);
			model.addAttribute("Message", "Provider Contact Updated Successfully");
			List<Contact> listBean = contactService.findAllContactsByRefId("provider", id);
			model.addAttribute("contactList", listBean);
			return TileDefinitions.PROVIDERCONTACTLIST.toString();
		}

		return TileDefinitions.PROVIDERCONTACTEDIT.toString();
	}

	/**
	 * @param id
	 * @param contact
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/provider/{id}/contact/save.do",
			"/user/provider/{id}/contact/save.do" }, method = RequestMethod.POST, params = { "delete" })
	public String saveProviderContactAction(@PathVariable Integer id, @Validated Contact contact,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		logger.info("provider id is"+id);
		if (bindingResult.hasErrors()) {
			logger.info("Returning contactEdit.jsp page");
			contact.setActiveInd('Y');
			return TileDefinitions.PROVIDERCONTACTEDIT.toString();
		}

		if (null != contact.getId()) {
			logger.info("Returning ContactEditSuccess.jsp page after update");
			contact.setActiveInd('N');
			contact.setUpdatedBy(username);
			contact.setCreatedBy(username);
			contact.getRefContact().setUpdatedBy(username);
			contact.getRefContact().setCreatedBy(username);
			contact.getRefContact().setActiveInd('N');
			contactService.update(contact);
			model.addAttribute("Message", "Provider Contact Deleted Successfully");
			List<Contact> listBean = contactService.findAllContactsByRefId("provider", id);
			model.addAttribute("contactList", listBean);
			return TileDefinitions.PROVIDERCONTACTLIST.toString();
		}

		return TileDefinitions.PROVIDERCONTACTEDIT.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/insurance/{id}/contact/new", "/user/insurance/{id}/contact/new" })
	public String addInsuranceContactPage(@PathVariable Integer id, Model model) {
		logger.info("insurance id is"+id);
		Contact contact = createContactModel();
		model.addAttribute("contact", contact);
		return TileDefinitions.INSURANCECONTACTEDIT.toString();
	}

	/**
	 * @param id
	 * @param cntId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/insurance/{id}/contact/{cntId}",
			"/user/insurance/{id}/contact/{cntId}" }, method = RequestMethod.GET)
	public String updateInsuranceContactPage(@PathVariable Integer id, @PathVariable Integer cntId, Model model) {
		logger.info("insurance id is"+id);
		Contact dbContact = contactService.findById(cntId);
		if (dbContact == null) {
			dbContact = createContactModel();
		}
		final Integer stateId = dbContact.getStateCode().getCode();
		List<ZipCode> zipCodeList = zipCodeService.findByStateCode(stateId);
		model.addAttribute("zipCodeList", zipCodeList);
		model.addAttribute("contact", dbContact);
		logger.info("Returning contactEdit.jsp page");
		return TileDefinitions.INSURANCECONTACTEDIT.toString();
	}

	/**
	 * @param id
	 * @param cntId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/insurance/{id}/contact/{cntId}/display",
			"/user/insurance/{id}/contact/{cntId}/display" }, method = RequestMethod.GET)
	public String displayInsuranceContactPage(@PathVariable Integer id, @PathVariable Integer cntId, Model model) {
		logger.info("insurance id is"+id);
		Contact dbContact = contactService.findById(cntId);
		logger.info("Returning contact.getId()" + dbContact.getId());
		final Integer stateId = dbContact.getStateCode().getCode();
		List<ZipCode> zipCodeList = zipCodeService.findByStateCode(stateId);
		model.addAttribute("zipCodeList", zipCodeList);
		model.addAttribute("contact", dbContact);
		logger.info("Returning contactDisplay.jsp page");
		return TileDefinitions.INSURANCECONTACTDISPLAY.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/insurance/{id}/contactList", "/user/insurance/{id}/contactList" })
	public String insuranceContactList(@PathVariable Integer id, Model model) {

		List<Contact> listBean = contactService.findAllContactsByRefId("insurance", id);
		model.addAttribute("contactList", listBean);

		return TileDefinitions.INSURANCECONTACTLIST.toString();
	}

	/**
	 * @param id
	 * @param contact
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/insurance/{id}/contact/save.do",
			"/user/insurance/{id}/contact/save.do" }, method = RequestMethod.POST, params = { "add" })
	public String addInsuranceContactAction(@PathVariable Integer id, @Validated Contact contact,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		logger.info("insurance id is"+id);
		if (bindingResult.hasErrors()) {
			logger.info("Returning contactEdit.jsp page");
			return TileDefinitions.INSURANCECONTACTEDIT.toString();
		}

		Insurance dbInsurance = insuranceService.findById(id);
		logger.info("Returning insurance.getId()" + dbInsurance.getId());

		model.addAttribute("contact", contact);
		contact.setCreatedBy(username);
		contact.setUpdatedBy(username);
		contact.setFileId(1);
		ReferenceContact refCnt = createRefContactModel();
		refCnt.setCreatedBy(username);
		refCnt.setUpdatedBy(username);
		refCnt.setIns(dbInsurance);
		contact.setRefContact(refCnt);
		logger.info("Returning contactEditSuccess.jsp page after create");
		contactService.save(contact);
		model.addAttribute("Message", "Insurance Contact Added Successfully");
		List<Contact> listBean = contactService.findAllContactsByRefId("insurance", id);
		model.addAttribute("contactList", listBean);

		return TileDefinitions.INSURANCECONTACTLIST.toString();

	}

	/**
	 * @param id
	 * @param contact
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/insurance/{id}/contact/save.do",
			"/user/insurance/{id}/contact/save.do" }, method = RequestMethod.POST, params = { "update" })
	public String updateInsuranceContactAction(@PathVariable Integer id, @Validated Contact contact,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		contact.setActiveInd('Y');
		logger.info("insurance id is"+id);
		if (bindingResult.hasErrors()) {
			logger.info("Returning contactEdit.jsp page");
			return TileDefinitions.INSURANCECONTACTEDIT.toString();
		}

		if (null != contact.getId()) {
			logger.info("Returning ContactEditSuccess.jsp page after update");
			contact.setUpdatedBy(username);
			contact.setCreatedBy(username);
			contact.getRefContact().setUpdatedBy(username);
			contact.getRefContact().setCreatedBy(username);
			contact.getRefContact().setActiveInd('Y');
			;
			contactService.update(contact);
			model.addAttribute("Message", "Insurance Contact Updated Successfully");
			List<Contact> listBean = contactService.findAllContactsByRefId("insurance", id);
			model.addAttribute("contactList", listBean);

			return TileDefinitions.INSURANCECONTACTLIST.toString();
		}

		return TileDefinitions.INSURANCECONTACTEDIT.toString();
	}

	/**
	 * @param id
	 * @param contact
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/insurance/{id}/contact/save.do",
			"/user/insurance/{id}/contact/save.do" }, method = RequestMethod.POST, params = { "delete" })
	public String saveInsuranceContactAction(@PathVariable Integer id, @Validated Contact contact,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		logger.info("insurance id is"+id);
		if (bindingResult.hasErrors()) {
			logger.info("Returning contactEdit.jsp page");
			contact.setActiveInd('Y');
			return TileDefinitions.INSURANCECONTACTEDIT.toString();
		}

		if (null != contact.getId()) {
			logger.info("Returning ContactEditSuccess.jsp page after update");
			contact.setActiveInd('N');
			contact.setUpdatedBy(username);
			contact.setCreatedBy(username);
			contact.getRefContact().setUpdatedBy(username);
			contact.getRefContact().setCreatedBy(username);
			contact.getRefContact().setActiveInd('N');
			contactService.update(contact);
			model.addAttribute("Message", "Insurance Contact Deleted Successfully");
			List<Contact> listBean = contactService.findAllContactsByRefId("insurance", id);
			model.addAttribute("contactList", listBean);

			return TileDefinitions.INSURANCECONTACTLIST.toString();
		}

		return TileDefinitions.INSURANCECONTACTEDIT.toString();

	}

	/**
	 * @param stateCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/admin/contact/state/{stateCode}", "/user/contact/state/{stateCode}" })
	public Message getStateZip(@PathVariable Integer stateCode) {
		List<ZipCode> zipCodeList = zipCodeService.findByStateCode(stateCode);
		return Message.successMessage(CommonMessageContent.MEMBERSHIP_LIST, JsonConverter.getJsonObject(zipCodeList));

	}
}
