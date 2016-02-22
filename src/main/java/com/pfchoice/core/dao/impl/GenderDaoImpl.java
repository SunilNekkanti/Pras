package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.GenderDao;
import com.pfchoice.core.entity.Gender;

/**
 *
 * @author Sarath
 */
@Repository
public class GenderDaoImpl extends HibernateBaseDao<Gender, Byte> implements GenderDao
{

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(GenderDaoImpl.class
        .getName());

    @Override
    public Pagination getPage(final int pageNo,final  int pageSize)
    {
        Criteria crit = createCriteria();
        Pagination page = findByCriteria(crit, pageNo, pageSize);
        return page;
    }

    @Override
    public Gender findById(final Byte id)
    {
    	Gender entity = get(id);
        return entity;
    }

    @Override
    public Gender save(final Gender bean)
    {
        getSession().save(bean);
        return bean;
    }

    @Override
    public Gender deleteById(final Byte id)
    {
//        throw new UnsupportedOperationException();
    	Gender entity = super.get(id);
        if (entity != null)
        {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    protected Class<Gender> getEntityClass()
    {
        return Gender.class;
    }

    @SuppressWarnings("unchecked")
	public List<Gender> findAll()
    {
    	Criteria cr = createCriteria();
    	cr.addOrder(Order.asc("description"));
    	List<Gender> list = cr.list();
    	return list;
    }
}
