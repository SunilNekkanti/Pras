package com.pfchoice.springmvc.controller;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.MediaType;

import com.pfchoice.ControllerClientSideTestBase;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author sarath
 */
public class MembershipActionClientSideTest extends ControllerClientSideTestBase
{

    @Test
    @Ignore
    public void test() throws Exception
    {
    	System.out.println("test");
        this.mockMvc.perform(get("/membership/1").contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))).andExpect(status()
                .isOk());
        System.out.println("sarath");
    }

    @Test
    @Ignore
    public void testPost() throws Exception
    {
    		System.out.println("testPost");
        String json = "{\"id\":2,\"firstName\":\"firstName\",\"lastName\":\"lastName\",\"createdBy\":\"sarath\"}";
        this.mockMvc.perform(post(json).contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))).andExpect(status()
                .isOk());
        System.out.println("sarath");
    }
}
