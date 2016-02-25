package com.pfchoice.springmvc.controller;

import java.util.HashMap;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pfchoice.ControllerServerSideTestBase;
import com.pfchoice.core.entity.Membership;

/**
 *
 * @author Sarath
 */
public class MembershipActionServerSideTest extends ControllerServerSideTestBase
{

/*    @Autowired
    private MembershipAction membershipAction;

    @Test
    @Ignore
    public void registerMembership()
    {
    	System.out.println("registerMembership================");
        request.setRequestURI("/membership");
        request.setMethod(HttpMethod.POST.name());
        request.setContentType("application/json");

        String json = "{\"id\":\"2\",\"firstName\":\"firstname2\",\"lastName\":\"lastname2\",\"createdBy\":\"sarath\"}";
        request.setContent(json.getBytes());
        Class<?>[] parameterTypes = new Class<?>[]
        {
            Membership.class
        };
        ModelAndView mv = null;
        try
        {
            mv = handlerAdapter
                .handle(request, response, new HandlerMethod(membershipAction, "registerMembership", parameterTypes));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    @Ignore
    public void getMembership()
    {
    	System.out.println("getMembership================");
        request.setMethod(HttpMethod.GET.name());
        request.setRequestURI("/membership/{id}");
        HashMap<String, String> pathVariablesMap = new HashMap<>(1);
        pathVariablesMap.put("id", "3");
        request.setAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, pathVariablesMap);
        Class<?>[] parameterTypes = new Class<?>[]
        {
            Integer.class
        };
        ModelAndView mv = null;
        try
        {
            mv = handlerAdapter
                .handle(request, response, new HandlerMethod(membershipAction, "retrieve", parameterTypes));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
*/
}
