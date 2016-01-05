package com.pfchoice.springmvc.controller;

import ml.rugal.sshcommon.springmvc.util.Message;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import com.pfchoice.ControllerClientSideTestBase;
import com.pfchoice.core.entity.Membership;
import com.pfchoice.springmvc.controller.MembershipAction;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.annotation.Resource;


/**
 *
 * @author sarath
 */
public class MembershipActionTest extends ControllerClientSideTestBase
{

    @Autowired
    private MembershipAction membershipAction;

    public MembershipActionTest()
    {
    }

    @Test
    public void testRegisterMembership() throws Exception
    {
        System.out.println("registerMembership");
      
        this.mockMvc.perform(post("/membership")
            .content("{\"id\":\"3\",\"firstName\":\"firstname3\",\"lastName\":\"lastName3\",\"genderId\":\"1\","
            		+ "\"countyCode\":\"1\",\"dob\":\"January 1, 1985\",\"fileId\":\"1\",\"status\":\"1\",\"activeInd\":\"Y\","
            		+ "\"updatedBy\":\"Sarath\",\"createdBy\":\"sarath\",\"createdDate\":\"January 4, 2016 0:0:00 AM\",\"updatedDate\":\"January 4, 2016 0:0:00 AM\"}")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        System.out.println("sarath");
    }

 //   @Test
    public void testUpdateMembershipProfile()
    {
        System.out.println("updateMembershipProfile");
        Integer id = 3;
        Membership bean = null;
       
        MembershipAction instance = new MembershipAction();
        Message expResult = null;
        Message result = instance.updateMembershipProfile(id, bean);
    }

//    @Test
    public void testDeregister()
    {
        System.out.println("cancelOrder");
        Integer id = 3;
        MembershipAction instance = new MembershipAction();
        Message expResult = null;
        Message result = instance.deregister(id);
    }

//    @Test
    public void testRetrieve()
    {
        System.out.println("retrieve");
        Integer id = null;
        MembershipAction instance = new MembershipAction();
        Message expResult = null;
        Message result = instance.retrieve(id);
    }

}
