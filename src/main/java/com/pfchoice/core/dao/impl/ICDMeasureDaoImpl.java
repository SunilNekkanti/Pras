package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.ICDMeasureDao;
import com.pfchoice.core.entity.ICDMeasure;

/**
 *
 * @author Sarath
 */
@Repository
public class ICDMeasureDaoImpl extends HibernateBaseDao<ICDMeasure, Integer> implements ICDMeasureDao
{

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ICDMeasureDaoImpl.class
        .getName());

    @Override
    public Pagination getPage(final int pageNo,final  int pageSize, 
    					final String sSearch, final String sort, final String sortdir)
    {
    	Criteria crit = createCriteria();
    	
    	if(sSearch != null && !"".equals(sSearch))
    	{
    		Disjunction or = Restrictions.disjunction();
        	
    		or.add(Restrictions.ilike("code","%"+sSearch+"%"))
    		  .add(Restrictions.ilike("description","%"+sSearch+"%"))
    		  .add(Restrictions.ilike("hcc","%"+sSearch+"%"))
    		  .add(Restrictions.ilike("rxhcc","%"+sSearch+"%"));
    		crit.add(or);
    	}

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

		crit.add(Restrictions.eq("activeInd", 'Y'));
		Pagination page = findByCriteria(crit, pageNo, pageSize);
        return page;
    }

    @Override
    public ICDMeasure findById(final Integer id)
    {
    	ICDMeasure entity = get(id);
        return entity;
    }

    @Override
    public ICDMeasure save(final ICDMeasure bean)
    {
        getSession().save(bean);
        return bean;
    }

    @Override
    public ICDMeasure deleteById(final Integer id)
    {
//        throw new UnsupportedOperationException();
    	ICDMeasure entity = super.get(id);
        if (entity != null)
        {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    protected Class<ICDMeasure> getEntityClass()
    {
        return ICDMeasure.class;
    }
    
    @SuppressWarnings("unchecked")
	public List<ICDMeasure> findAll()
    {
    	Criteria cr = createCriteria();
    	cr.add(Restrictions.eq("activeInd", 'Y'))
    	.addOrder(Order.asc("code"));
    	List<ICDMeasure> list = cr.list();
    	return list;
    }

}
