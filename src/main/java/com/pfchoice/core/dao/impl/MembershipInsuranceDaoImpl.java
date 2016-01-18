package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
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
    public Pagination getPage(int pageNo, int pageSize)
    {
        Criteria crit = createCriteria();
        Pagination page = findByCriteria(crit, pageNo, pageSize);
        return page;
    }

    @Override
    public MembershipInsurance findById(Integer id)
    {
    	MembershipInsurance entity = get(id);
        return entity;
    }

    @Override
    public MembershipInsurance save(MembershipInsurance bean)
    {
        getSession().save(bean);
        return bean;
    }

    @Override
    public MembershipInsurance deleteById(Integer id)
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
    	cr.setFetchMode("Membership", FetchMode.JOIN);
    	cr.setFetchMode("Insurance", FetchMode.JOIN);
    	List list = cr.list();
    	//List<Membership> list =	find("from Membership m, Gender g, MembershipStatus ms where m.genderId = g.id and m.status =ms.id",null);
    	return list;
    }
}
