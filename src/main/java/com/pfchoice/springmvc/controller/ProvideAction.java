package com.pfchoice.springmvc.controller;

import ml.rugal.sshcommon.springmvc.util.Message;

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
import com.pfchoice.core.entity.Provider;
import com.pfchoice.core.service.ProviderService;





/**
 *
 * A Membership controller class for GET/DELETE/POST/PUT.
 *
 * @author Mohanasundharam
 */
@Controller
@RequestMapping(value = "/provider")
public class ProvideAction
{

    private static final Logger LOG = LoggerFactory.getLogger(ProvideAction.class.getName());

    @Autowired
    private ProviderService providerService;

    
    
    /**
     * Persist a Membership bean into database.
     *
     * @param bean Membership bean resembled from request body.
     *
     * @return The persisted Membership bean.
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public Message registerMembership(@RequestBody Provider bean)
    {
    	providerService.save(bean);
        /*
         * Now we need to push message notify them
         */
        return Message.successMessage(CommonMessageContent.PROVIDER_SAVED, bean);
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
    public Message updateMembershipProfile(@PathVariable("id") Integer id, @RequestBody Provider bean)
    {
    	Provider dbProvider = providerService.findById(id);
        if (null != dbProvider)
        {
        	providerService.update(bean);
        }
        /*
         * Here we need to push message to client
         */
        return Message.successMessage(CommonMessageContent.PROVIDER_UPDATED, bean);
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
    	Provider bean = providerService.findById(id);
        if (null != bean)
        {
        	providerService.deleteById(id);
        }
        return Message.successMessage(CommonMessageContent.PROVIDER_DELETED, bean);
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
    	Provider bean = providerService.findById(id);
        
              
        return Message.successMessage(CommonMessageContent.GET_PROVIDER, bean);
    }
}
