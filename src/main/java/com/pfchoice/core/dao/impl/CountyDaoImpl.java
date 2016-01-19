package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.CountyDao;
import com.pfchoice.core.entity.County;

/**
 *
 * @author Sarath
 */
@Repository
public class CountyDaoImpl extends HibernateBaseDao<County, Integer> implements CountyDao
{

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(CountyDaoImpl.class
        .getName());

    @Override
    public Pagination getPage(int pageNo, int pageSize)
    {
        Criteria crit = createCriteria();
        Pagination page = findByCriteria(crit, pageNo, pageSize);
        return page;
    }

    @Override
    public County findById(Integer id)
    {
    	County entity = get(id);
        return entity;
    }

    @Override
    public County save(County bean)
    {
        getSession().save(bean);
        return bean;
    }

    @Override
    public County deleteById(Integer id)
    {
//        throw new UnsupportedOperationException();
    	County entity = super.get(id);
        if (entity != null)
        {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    protected Class<County> getEntityClass()
    {
        return County.class;
    }
    
    @SuppressWarnings("unchecked")
	public List<County> findAll()
    {
    	Criteria cr = getSession().createCriteria(getEntityClass());
    	List<County> list = cr.list();
    	return list;
    }

}
