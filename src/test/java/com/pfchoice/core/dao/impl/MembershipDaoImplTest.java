package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.page.Pagination;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pfchoice.JUnitSpringTestBase;
import com.pfchoice.core.dao.CountyDao;
import com.pfchoice.core.dao.GenderDao;
import com.pfchoice.core.dao.MembershipDao;
import com.pfchoice.core.dao.MembershipStatusDao;
import com.pfchoice.core.entity.County;
import com.pfchoice.core.entity.Gender;
import com.pfchoice.core.entity.Membership;
import com.pfchoice.core.entity.MembershipStatus;

/**
 *
 * @author Sarath
 */
public class MembershipDaoImplTest extends JUnitSpringTestBase
{

    @Autowired
    private MembershipDao membershipDao;
    
    @Autowired
    private CountyDao countyDao;
    
    @Autowired
    private GenderDao genderDao;
    
    @Autowired
    private MembershipStatusDao membershipStatusDao;

    public MembershipDaoImplTest()
    {
    }

//    @Test
    public void testGetPage()
    {
        System.out.println("getPage");
        int pageNo = 0;
        int pageSize = 0;
        Pagination result = membershipDao.getPage(pageNo, pageSize);
    }

    @Test
    public void testFindById()
    {
        System.out.println("findById");
        Integer id = 1;
        Membership result = membershipDao.findById(id);
    }

   @Test
    public void testSave()
    {
        System.out.println("testSave start");
        Membership bean = new Membership();
        bean.setId(1);
        bean.setDob(Date.valueOf(LocalDate.of(1985,1,1)));
        bean.setFirstName("FirstName");
        bean.setLastName("lastName");
        bean.setCountyCode(countyDao.findById(1));
        bean.setActiveInd('Y');
        bean.setFileId(1);
        bean.setGenderId(genderDao.findById((byte)1));
        bean.setStatus(membershipStatusDao.findById((byte)1));
        bean.setCreatedBy("sarath");
        Membership result = membershipDao.save(bean);
        System.out.println("testSave end");
    }

//    @Test
    public void testDeleteById()
    {
        System.out.println("deleteById");
        Integer id = 1;
        Membership result = membershipDao.deleteById(id);
    }

}
