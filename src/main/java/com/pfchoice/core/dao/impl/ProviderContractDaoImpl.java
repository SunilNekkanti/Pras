package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.pfchoice.core.dao.ProviderContractDao;
import com.pfchoice.core.entity.ProviderContract;

/**
 *
 * @author Mohanasundharam
 */
@Repository
public class ProviderContractDaoImpl extends HibernateBaseDao<ProviderContract, Integer> implements ProviderContractDao
{

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ProviderContractDaoImpl.class
        .getName());

    @Override
    public Pagination getPage(final int pageNo,final int pageSize)
    {
        Criteria crit = createCriteria();
        Pagination page = findByCriteria(crit, pageNo, pageSize);
        return page;
    }

    @Override
    public ProviderContract findById(final Integer id)
    {
    	ProviderContract entity = get(id);
        return entity;
    }

    @Override
    public ProviderContract save(final ProviderContract bean)
    {
        getSession().save(bean);
        return bean;
    }

    @Override
    public ProviderContract deleteById(final Integer id)
    {
//        throw new UnsupportedOperationException();
    	ProviderContract entity = super.get(id);
        if (entity != null)
        {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    protected Class<ProviderContract> getEntityClass()
    {
        return ProviderContract.class;
    }

    @SuppressWarnings("unchecked")
	public List<ProviderContract> findAll()
    {
    	
    	Criteria cr = getSession().createCriteria(getEntityClass());
    	cr.setFetchMode("Insurance", FetchMode.JOIN);
    	List<ProviderContract> list = cr.list();
    
    	return list;
    }
}
