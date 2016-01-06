package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;
import org.hibernate.Criteria;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.ContactDao;
import com.pfchoice.core.entity.Contact;

/**
 *
 * @author Sarath
 */
@Repository
public class ContactDaoImpl extends HibernateBaseDao<Contact, Integer> implements ContactDao
{

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ContactDaoImpl.class
        .getName());

    @Override
    public Pagination getPage(int pageNo, int pageSize)
    {
        Criteria crit = createCriteria();
        Pagination page = findByCriteria(crit, pageNo, pageSize);
        return page;
    }

    @Override
    public Contact findById(Integer id)
    {
    	Contact entity = get(id);
        return entity;
    }

    @Override
    public Contact save(Contact bean)
    {
        getSession().save(bean);
        return bean;
    }

    @Override
    public Contact deleteById(Integer id)
    {
//        throw new UnsupportedOperationException();
    	Contact entity = super.get(id);
        if (entity != null)
        {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    protected Class<Contact> getEntityClass()
    {
        return Contact.class;
    }

}
