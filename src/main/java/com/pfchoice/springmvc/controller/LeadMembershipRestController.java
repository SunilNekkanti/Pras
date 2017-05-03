package com.pfchoice.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.pfchoice.core.entity.Gender;
import com.pfchoice.core.entity.LeadMembership;
import com.pfchoice.core.entity.MembershipStatus;
import com.pfchoice.core.service.GenderService;
import com.pfchoice.core.service.LeadMembershipService;
import com.pfchoice.core.service.MembershipStatusService;

import ml.rugal.sshcommon.page.Pagination;
  
@RestController
public class LeadMembershipRestController {
  
    @Autowired
    LeadMembershipService leadService;  //Service which will do all data retrieval/manipulation work
  
    @Autowired
     MembershipStatusService leadStatusService;  //Service which will do all data retrieval/manipulation work

    @Autowired
     GenderService leadGenderService;  //Service which will do all data retrieval/manipulation work
    //-------------------Retrieve All Leads--------------------------------------------------------
      
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/lead/", method = RequestMethod.GET)
    public ResponseEntity<List<LeadMembership>> listAllLeads(Integer pageNo, Integer pageSize) {
      Pagination pagination = leadService.getPage(pageNo, pageSize);
      List<LeadMembership> leads = (List<LeadMembership>) pagination.getList();
        if(leads.isEmpty()){
            return new ResponseEntity<List<LeadMembership>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<LeadMembership>>(leads, HttpStatus.OK);
    }
  
  
     
    //-------------------Retrieve Single LeadMembership--------------------------------------------------------
      
    @RequestMapping(value = "/lead/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LeadMembership> getLead(@PathVariable("id") Integer id) {
        System.out.println("Fetching LeadMembership with id " + id);
        LeadMembership lead = leadService.findById(id);
        if (lead == null) {
            System.out.println("LeadMembership with id " + id + " not found");
            return new ResponseEntity<LeadMembership>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<LeadMembership>(lead, HttpStatus.OK);
    }
  
      
      
    //-------------------Create a LeadMembership--------------------------------------------------------
      
    @RequestMapping(value = "/lead/", method = RequestMethod.POST)
    public ResponseEntity<Void> createLead(@RequestBody LeadMembership lead,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating LeadMembership " + lead.getMedicareNo());
  
        if (leadService.isLeadExist(lead)) {
            System.out.println("A LeadMembership with name " + lead.getFirstName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
  
        leadService.save(lead);
  
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/lead/{id}").buildAndExpand(lead.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
  
     
      
    //------------------- Update a LeadMembership --------------------------------------------------------
      
    @RequestMapping(value = "/lead/{id}", method = RequestMethod.PUT)
    public ResponseEntity<LeadMembership> updateLead(@PathVariable("id") int id, @RequestBody LeadMembership lead) {
        System.out.println("Updating LeadMembership " + id);
          
        LeadMembership currentLead = leadService.findById(id);
          
        if (currentLead==null) {
            System.out.println("LeadMembership with id " + id + " not found");
            return new ResponseEntity<LeadMembership>(HttpStatus.NOT_FOUND);
        }
  
        currentLead.setFirstName(lead.getFirstName());
        currentLead.setLastName(lead.getLastName());
        currentLead.setGenderId(lead.getGenderId());
        currentLead.setStatus(lead.getStatus());
        currentLead.setCountyCode(lead.getCountyCode());
        currentLead.setMedicareNo(lead.getMedicareNo());
        currentLead.setMedicaidNo(lead.getMedicaidNo());
          
        leadService.update(currentLead);
        return new ResponseEntity<LeadMembership>(currentLead, HttpStatus.OK);
    }
  
     
     
    //------------------- Delete a LeadMembership --------------------------------------------------------
      
    @RequestMapping(value = "/lead/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<LeadMembership> deleteLead(@PathVariable("id") int id) {
        System.out.println("Fetching & Deleting LeadMembership with id " + id);
  
        LeadMembership lead = leadService.findById(id);
        if (lead == null) {
            System.out.println("Unable to delete. LeadMembership with id " + id + " not found");
            return new ResponseEntity<LeadMembership>(HttpStatus.NOT_FOUND);
        }
  
        leadService.deleteById(id);
        return new ResponseEntity<LeadMembership>(HttpStatus.NO_CONTENT);
    }
  
    @SuppressWarnings("unchecked")
  	@RequestMapping(value = "/leadStatuses", method = RequestMethod.GET)
      public ResponseEntity<List<MembershipStatus>> listAllLeadStatuses(Integer pageNo, Integer pageSize) {
        Pagination pagination = leadStatusService.getPage(pageNo, pageSize);
        List<MembershipStatus> leads = (List<MembershipStatus>) pagination.getList();
          if(leads.isEmpty()){
              return new ResponseEntity<List<MembershipStatus>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
          }
          return new ResponseEntity<List<MembershipStatus>>(leads, HttpStatus.OK);
      }
    
    @SuppressWarnings("unchecked")
  	@RequestMapping(value = "/leadGenders", method = RequestMethod.GET)
      public ResponseEntity<List<Gender>> listAllLeadGenders(Integer pageNo, Integer pageSize) {
        Pagination pagination = leadGenderService.getPage(pageNo, pageSize);
        List<Gender> leads = (List<Gender>) pagination.getList();
          if(leads.isEmpty()){
              return new ResponseEntity<List<Gender>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
          }
          return new ResponseEntity<List<Gender>>(leads, HttpStatus.OK);
      }
    
    
}