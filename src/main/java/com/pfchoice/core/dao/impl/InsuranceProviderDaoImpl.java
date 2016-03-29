package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.InsuranceProviderDao;
import com.pfchoice.core.entity.InsuranceProvider;

/**
 *
 * @author Sarath
 */
@Repository
public class InsuranceProviderDaoImpl extends HibernateBaseDao<InsuranceProvider, Integer> implements InsuranceProviderDao
{

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(InsuranceProviderDaoImpl.class
        .getName());

    @Override
    public Pagination getPage(final int pageNo,final int pageSize)
    {
        Criteria crit = createCriteria();
        crit.add(Restrictions.eq("activeInd", 'Y'));
        Pagination page = findByCriteria(crit, pageNo, pageSize);
        return page;
    }

    @Override
    public InsuranceProvider findById(final Integer id)
    {
    	InsuranceProvider entity = get(id);
        return entity;
    }

    @Override
    public InsuranceProvider save(final InsuranceProvider bean)
    {
        getSession().save(bean);
        return bean;
    }

    @Override
    public InsuranceProvider deleteById(final Integer id)
    {
//        throw new UnsupportedOperationException();
    	InsuranceProvider entity = super.get(id);
        if (entity != null)
        {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    protected Class<InsuranceProvider> getEntityClass()
    {
        return InsuranceProvider.class;
    }

    @SuppressWarnings("unchecked")
	public List<InsuranceProvider> findAll()
    {
    	Criteria cr = createCriteria();
    	cr.add(Restrictions.eq("activeInd", 'Y'));
    	List<InsuranceProvider> list = cr.list();
    	return list;
    }
    
    @SuppressWarnings("unchecked")
	public List<InsuranceProvider> findAllByPrvdrId(final Integer id)
    {
    	Criteria cr = getSession().createCriteria(getEntityClass())
    			.createAlias("prvdr","prvdr")
    			.add(Restrictions.eq("prvdr.id", id))
    			.add(Restrictions.eq("activeInd", 'Y'))
    			.add(Restrictions.eq("prvdr.activeInd", 'Y'));
    	
    	List<InsuranceProvider> list = cr.list();
    	final String msg = "findAllByPrvdrId list size is %d";
    	final String fmt = String.format(msg, list.size());
    	LOG.info(fmt);
    	
    	return list;
    }
        
}
