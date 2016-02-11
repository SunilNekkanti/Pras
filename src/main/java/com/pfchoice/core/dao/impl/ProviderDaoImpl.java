package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
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
    public Pagination getPage(final int pageNo, final int pageSize, 
    		final String sSearch, final String sort, final String sortdir)
    {
    	Disjunction or = Restrictions.disjunction();

    	if( sSearch != null && !"".equals(sSearch))
    	{
    		Criterion name   = Restrictions.ilike("name","%"+sSearch+"%");
    		Criterion code   = Restrictions.ilike("code","%"+sSearch+"%");
    		
    		or.add(name);
    		or.add(code);
    	}
        Criteria crit = createCriteria();
        crit.add(or);
        
        if(sort != null && !"".equals(sort)) 
		{
			if(sortdir != null && !"".equals(sortdir) && "desc".equals(sortdir))
			{
				crit.addOrder(Order.desc(sort));
			}
			else 
			{
				crit.addOrder(Order.asc(sort));
			}
		}
        Pagination page = findByCriteria(crit, pageNo, pageSize);
        return page;
        
    }

    @Override
    public Provider findById(final Integer id)
    {
    	Provider entity = get(id);
        return entity;
    }

    @Override
    public Provider save(final Provider bean)
    {
        getSession().save(bean);
        return bean;
    }

    @Override
    public Provider deleteById(final Integer id)
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
    	
    	Criteria cr = createCriteria();
    	cr.setFetchMode("ProviderContract", FetchMode.JOIN);
    	List<Provider> list = cr.list();
    
    	return list;
    }

}
