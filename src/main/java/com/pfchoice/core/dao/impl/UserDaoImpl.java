package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.UserDao;
import com.pfchoice.core.entity.User;

/**
 *
 * @author Sarath
 */
@Repository
public class UserDaoImpl extends HibernateBaseDao<User, Integer> implements UserDao
{

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(UserDaoImpl.class
        .getName());

    @Override
    public Pagination getPage(final int pageNo, final int pageSize)
    {
        Criteria crit = createCriteria();
        Pagination page = findByCriteria(crit, pageNo, pageSize);
        return page;
    }

    @Override
    public User findById(final Integer id)
    {
    	User entity = get(id);
        return entity;
    }

    @Override
    public User save(final User bean)
    {
        getSession().save(bean);
        return bean;
    }

    @Override
    public User deleteById(final Integer id)
    {
//        throw new UnsupportedOperationException();
    	User entity = super.get(id);
        if (entity != null)
        {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    protected Class<User> getEntityClass()
    {
        return User.class;
    }
    
    @Override
    public User findByLogin(final String login)
    {
    	 Criteria crit = createCriteria();
    	 crit.add(Restrictions.eq("login", login));
    	 
    	User entity = (User) crit.uniqueResult();

        return entity;
    }
    
    @Override
    public boolean isValidUser(final String login, final String password)
    {
    
    	Criteria crit = createCriteria();
    	crit.add(Restrictions.eq("login", login));
    	crit.add(Restrictions.eq("password", password));
    	
    	User entity = (User) crit.uniqueResult();
    	
    	return (entity != null)?true:false;
    		
    }
}
