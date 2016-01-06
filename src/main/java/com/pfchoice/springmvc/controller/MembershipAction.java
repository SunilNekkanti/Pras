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
import com.pfchoice.core.entity.County;
import com.pfchoice.core.entity.Gender;
import com.pfchoice.core.entity.Membership;
import com.pfchoice.core.entity.MembershipStatus;
import com.pfchoice.core.service.CountyService;
import com.pfchoice.core.service.GenderService;
import com.pfchoice.core.service.MembershipService;
import com.pfchoice.core.service.MembershipStatusService;




/**
 *
 * A Membership controller class for GET/DELETE/POST/PUT.
 *
 * @author sarath
 */
@Controller
@RequestMapping(value = "/membership")
public class MembershipAction
{

    private static final Logger LOG = LoggerFactory.getLogger(MembershipAction.class.getName());

    @Autowired
    private MembershipService membershipService;

    @Autowired
    private GenderService genderService;
    
    @Autowired
    private MembershipStatusService membershipStatusService;

    @Autowired
    private CountyService countyService;
    /**
     * Persist a Membership bean into database.
     *
     * @param bean Membership bean resembled from request body.
     *
     * @return The persisted Membership bean.
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public Message registerMembership(@RequestBody Membership bean)
    {
        membershipService.save(bean);
        /*
         * Now we need to push message notify them
         */
        return Message.successMessage(CommonMessageContent.MEMBERSHIP_SAVED, bean);
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
    public Message updateMembershipProfile(@PathVariable("id") Integer id, @RequestBody Membership bean)
    {
        Membership dbMembership = membershipService.findById(id);
        if (null != dbMembership)
        {
            membershipService.update(bean);
        }
        /*
         * Here we need to push message to client
         */
        return Message.successMessage(CommonMessageContent.MEMBERSHIP_UPDATED, bean);
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
        Membership bean = membershipService.findById(id);
        if (null != bean)
        {
            membershipService.deleteById(id);
        }
        return Message.successMessage(CommonMessageContent.MEMBERSHIP_DELETED, bean);
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
        Membership bean = membershipService.findById(id);
        Gender 	genderBean = genderService.findById(bean.getGenderId());
        MembershipStatus statusBean = membershipStatusService.findById(bean.getStatus());
        County countyBean = countyService.findById(bean.getCountyCode());
        bean.setGenderDescription(genderBean.getDescription());
        bean.setStatusDescription(statusBean.getDescription());
        bean.setCountyDescription(countyBean.getDescription());
        return Message.successMessage(CommonMessageContent.GET_MEMBERSHIP, bean);
    }
}
