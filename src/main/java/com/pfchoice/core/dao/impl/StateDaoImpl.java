package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.StateDao;
import com.pfchoice.core.entity.State;

/**
 *
 * @author Sarath
 */
@Repository
public class StateDaoImpl extends HibernateBaseDao<State, Integer> implements StateDao
{

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(StateDaoImpl.class
        .getName());

    @Override
    public Pagination getPage(final int pageNo,final  int pageSize)
    {
        Criteria crit = createCriteria();
        Pagination page = findByCriteria(crit, pageNo, pageSize);
        return page;
    }

    @Override
    public State findById(final Integer id)
    {
    	State entity = get(id);
        return entity;
    }

    @Override
    public State save(final State bean)
    {
        getSession().save(bean);
        return bean;
    }

    @Override
    public State deleteById(final Integer id)
    {
//        throw new UnsupportedOperationException();
    	State entity = super.get(id);
        if (entity != null)
        {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    protected Class<State> getEntityClass()
    {
        return State.class;
    }
    
    @SuppressWarnings("unchecked")
	public List<State> findAll()
    {
    	Criteria cr = createCriteria();
    	cr.addOrder(Order.asc("shortName"));
    	List<State> list = cr.list();
    	return list;
    }

}
