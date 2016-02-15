package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.page.Pagination;

import java.util.Collections;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pfchoice.JUnitSpringTestBase;
import com.pfchoice.core.dao.RoleDao;
import com.pfchoice.core.dao.UserDao;
import com.pfchoice.core.entity.Role;
import com.pfchoice.core.entity.User;

/**
 *
 * @author Sarath
 */
public class UserDaoImplTest extends JUnitSpringTestBase
{

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private RoleDao roleDao;
    
    public UserDaoImplTest()
    {
    }

    @Test
    public void testGetPage()
    {
        System.out.println("getPage");
        int pageNo = 0;
        int pageSize = 0;
        Pagination result = userDao.getPage(pageNo, pageSize);
    }

    @Test
    public void testFindById()
    {
        System.out.println("findById");
        Integer id = 3;
        User result = userDao.findById(id);
    }

   @Test
    public void testSave()
    {
        System.out.println("testSave start");
        User bean = new User();
        bean.setId(1);
        bean.setLogin("sara");
        Role role = roleDao.findById(3);
        bean.setRole(Collections.singleton(role));
        bean.setPassword("sara");
        bean.setCreatedBy("sarath");
        bean.setUpdatedBy("sarath");
        bean.setActiveInd('Y');
        User result = userDao.save(bean);
        System.out.println("testSave end");
    }

    @Test
    public void testDeleteById()
    {
        System.out.println("deleteById");
        Integer id = 1;
        User result = userDao.deleteById(id);
    }

}
