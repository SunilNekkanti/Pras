package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.UserDao;
import com.pfchoice.core.entity.User;
import com.pfchoice.core.service.UserService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class UserServiceImpl implements UserService
{

    @Autowired
    private UserDao userDao;

    @Override
    public User deleteById(final Integer id)
    {
        //Used for transaction test
        return userDao.deleteById(id);
//        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(final Integer id)
    {
        return userDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(final int pageNo, final int pageSize)
    {
        return userDao.getPage(pageNo, pageSize);
    }

    @Override
    public User save(final User bean)
    {
        //Used for transaction test
        return userDao.save(bean);
//        this.deleteById(1);
//        return null;
    }

    @Override
    public User update(final User bean)
    {
        Updater<User> updater = new Updater<>(bean);
        return userDao.updateByUpdater(updater);
    }
    
    @Override
    @Transactional(readOnly = true)
    public User findByLogin(final String login)
    {
        return userDao.findByLogin(login);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean isValidUser(final String login, final String password)
    {
        return userDao.isValidUser(login, password);
    }

}
