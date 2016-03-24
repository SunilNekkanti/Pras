package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.PlanTypeDao;
import com.pfchoice.core.entity.PlanType;

/**
 *
 * @author Mohanasundharam
 */
@Repository
public class PlanTypeDaoImpl extends HibernateBaseDao<PlanType, Integer> implements PlanTypeDao
{

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(PlanTypeDaoImpl.class
        .getName());

    @Override
    public Pagination getPage(final int pageNo,final  int pageSize)
    {
        Criteria crit = createCriteria();
        crit.add(Restrictions.eq("activeInd", 'Y'));
        Pagination page = findByCriteria(crit, pageNo, pageSize);
        return page;
    }

    @Override
    public PlanType findById(final Integer id)
    {
    	PlanType entity = get(id);
        return entity;
    }

    @Override
    public PlanType save(final PlanType bean)
    {
        getSession().save(bean);
        return bean;
    }

    @Override
    public PlanType deleteById(final Integer id)
    {
//        throw new UnsupportedOperationException();
    	PlanType entity = super.get(id);
        if (entity != null)
        {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    protected Class<PlanType> getEntityClass()
    {
        return PlanType.class;
    }
    
    @SuppressWarnings("unchecked")
	public List<PlanType> findAll()
    {
    	Criteria cr = createCriteria();
    	cr.addOrder(Order.asc("code"))
    	.add(Restrictions.eq("activeInd", 'Y'));
    	List<PlanType> list = cr.list();
    	return list;
    }

}
