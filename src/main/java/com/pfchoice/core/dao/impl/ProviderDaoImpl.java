package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.ProviderDao;
import com.pfchoice.core.entity.Provider;

/**
 *
 * @author Mohanasundharam
 */
@Repository
public class ProviderDaoImpl extends HibernateBaseDao<Provider, Integer> implements ProviderDao
{

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ProviderDaoImpl.class
        .getName());

    @Override
    public Pagination getPage(int pageNo, int pageSize)
    {
        Criteria crit = createCriteria();
        Pagination page = findByCriteria(crit, pageNo, pageSize);
        return page;
    }

    @Override
    public Provider findById(Integer id)
    {
    	Provider entity = get(id);
        return entity;
    }

    @Override
    public Provider save(Provider bean)
    {
        getSession().save(bean);
        return bean;
    }

    @Override
    public Provider deleteById(Integer id)
    {
//        throw new UnsupportedOperationException();
    	Provider entity = super.get(id);
        if (entity != null)
        {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    protected Class<Provider> getEntityClass()
    {
        return Provider.class;
    }
    
    @SuppressWarnings("unchecked")
	public List<Provider> findAll()
    {
    	
    	Criteria cr = getSession().createCriteria(getEntityClass());
    	cr.setFetchMode("ProviderContract", FetchMode.JOIN);
    	List<Provider> list = cr.list();
    
    	return list;
    }

}
