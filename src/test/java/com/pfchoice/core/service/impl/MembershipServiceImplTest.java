
package com.pfchoice.core.service.impl;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pfchoice.JUnitSpringTestBase;
import com.pfchoice.core.entity.Membership;
import com.pfchoice.core.service.MembershipService;
import com.pfchoice.core.service.impl.MembershipServiceImpl;

/**
 *
 * @author sarath
 */

public class MembershipServiceImplTest extends JUnitSpringTestBase
{

    @Autowired
    private MembershipService membershipService;

    public MembershipServiceImplTest()
    {
    }

  //  @Test
    public void testDeleteById()
    {
        System.out.println("deleteById");
        Integer id = null;
        MembershipServiceImpl instance = new MembershipServiceImpl();
        Membership expResult = null;
        Membership result = instance.deleteById(id);
    }

    @Test
    @Ignore
    public void testFindById()
    {
        System.out.println("findById");
        Integer id = 1;
        membershipService.findById(id);
    }

//    @Test
//    @Ignore
    public void testSave()
    {
        System.out.println("save");
        Membership bean = new Membership();
        bean.setId(1);
        bean.setLastName("LastName");
        bean.setFirstName("FirstName");
        membershipService.save(bean);
    }

}
