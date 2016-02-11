package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.pfchoice.core.dao.InsuranceContractDao;
import com.pfchoice.core.entity.InsuranceContract;

/**
 *
 * @author Mohanasundharam
 */
@Repository
public class InsuranceContractDaoImpl extends HibernateBaseDao<InsuranceContract, Integer> implements InsuranceContractDao
{

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(InsuranceContractDaoImpl.class
        .getName());

    @Override
    public Pagination getPage(final int pageNo, final int pageSize)
    {
        Criteria crit = createCriteria();
        Pagination page = findByCriteria(crit, pageNo, pageSize);
        return page;
    }

    @Override
    public InsuranceContract findById(final Integer id)
    {
    	InsuranceContract entity = get(id);
        return entity;
    }

    @Override
    public InsuranceContract save(final InsuranceContract bean)
    {
        getSession().save(bean);
        return bean;
    }

    @Override
    public InsuranceContract deleteById(final Integer id)
    {
//        throw new UnsupportedOperationException();
    	InsuranceContract entity = super.get(id);
        if (entity != null)
        {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    protected Class<InsuranceContract> getEntityClass()
    {
        return InsuranceContract.class;
    }

    @SuppressWarnings("unchecked")
	public List<InsuranceContract> findAll()
    {
    	
    	Criteria cr = createCriteria();
    	cr.setFetchMode("Insurance", FetchMode.JOIN);
    	List<InsuranceContract> list = cr.list();
    
    	return list;
    }
}
