package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.MembershipInsuranceDao;
import com.pfchoice.core.entity.MembershipInsurance;

/**
 *
 * @author Sarath
 */
@Repository
public class MembershipInsuranceDaoImpl extends HibernateBaseDao<MembershipInsurance, Integer> implements MembershipInsuranceDao
{

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(MembershipInsuranceDaoImpl.class
        .getName());

    @Override
    public Pagination getPage(final int pageNo, final int pageSize)
    {
        Criteria crit = createCriteria();
        Pagination page = findByCriteria(crit, pageNo, pageSize);
        return page;
    }

    @Override
    public MembershipInsurance findById(final Integer id)
    {
    	MembershipInsurance entity = get(id);
        return entity;
    }

    @Override
    public MembershipInsurance save(final MembershipInsurance bean)
    {
        getSession().save(bean);
        return bean;
    }

    @Override
    public MembershipInsurance deleteById(final Integer id)
    {
//        throw new UnsupportedOperationException();
    	MembershipInsurance entity = super.get(id);
        if (entity != null)
        {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    protected Class<MembershipInsurance> getEntityClass()
    {
        return MembershipInsurance.class;
    }

    @SuppressWarnings("unchecked")
	public List<MembershipInsurance> findAll()
    {
    	Criteria cr = getSession().createCriteria(getEntityClass());
    	List<MembershipInsurance> list = cr.list();
    	return list;
    }
    
    @SuppressWarnings("unchecked")
	public List<MembershipInsurance> findAllByMbrId(final Integer id)
    {
    	Criteria cr = getSession().createCriteria(getEntityClass(), "mbrIns")
    			.createAlias("mbrIns.mbr","mbr")
    			.add(Restrictions.eq("mbr.id", id));
    	List<MembershipInsurance> list = cr.list();
    	return list;
    }
}
