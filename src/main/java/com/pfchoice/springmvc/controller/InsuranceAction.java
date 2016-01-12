package com.pfchoice.springmvc.controller;

import ml.rugal.sshcommon.springmvc.util.Message;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.pfchoice.common.CommonMessageContent;
import com.pfchoice.core.entity.Insurance;
import com.pfchoice.core.entity.Membership;
import com.pfchoice.core.service.InsuranceService;





/**
 *
 * A Membership controller class for GET/DELETE/POST/PUT.
 *
 * @author Mohanasundharam
 */
@Controller
@RequestMapping(value = "/insurance")
public class InsuranceAction
{

    private static final Logger LOG = LoggerFactory.getLogger(InsuranceAction.class.getName());

    @Autowired
    private InsuranceService insuranceService;

    
    
    /**
     * Persist a Membership bean into database.
     *
     * @param bean Membership bean resembled from request body.
     *
     * @return The persisted Membership bean.
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public Message registerMembership(@RequestBody Insurance bean)
    {
    	insuranceService.save(bean);
        /*
         * Now we need to push message notify them
         */
        return Message.successMessage(CommonMessageContent.INSURANCE_SAVED, bean);
    }

    /**
     * Update a Membership bean.
     *
     * @param id   primary key of target Membership.
     * @param bean the newer Membership bean
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Message updateMembershipProfile(@PathVariable("id") Integer id, @RequestBody Insurance bean)
    {
        Insurance dbInsurance = insuranceService.findById(id);
        if (null != dbInsurance)
        {
        	insuranceService.update(bean);
        }
        /*
         * Here we need to push message to client
         */
        return Message.successMessage(CommonMessageContent.INSURANCE_UPDATED, bean);
    }

    /**
     * DELETE a Membership record from database.
     *
     * @param id the target Membership id.
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Message deregister(@PathVariable("id") Integer id)
    {
        Insurance bean = insuranceService.findById(id);
        if (null != bean)
        {
        	insuranceService.deleteById(id);
        }
        return Message.successMessage(CommonMessageContent.INSURANCE_DELETED, bean);
    }

    /**
     * GET a Membership record from database.
     *
     * @param id primary key of target Membership.
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Message retrieve(@PathVariable("id") Integer id)
    {
        Insurance bean = insuranceService.findById(id);
        
              
        return Message.successMessage(CommonMessageContent.GET_INSURANCE, bean);
    }
    
    /**
     * GET all Insurance records from database.
     *
     *
     * @return
     */
    @ResponseBody
    @RequestMapping( method = RequestMethod.GET)
    public Message retrieveAll()
    {
        List<Insurance> listBean = insuranceService.findAll();
          for( Insurance i : listBean){
        	  System.out.println(i.getName());
          }
        return Message.successMessage(CommonMessageContent.GET_INSURANCE, listBean);
    }
}
