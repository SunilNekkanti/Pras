package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.MembershipHedisFollowupDao;
import com.pfchoice.core.entity.MembershipHedisFollowup;

/**
 *
 * @author Mohanasundharam
 */
@Repository
public class MembershipHedisFollowupDaoImpl extends HibernateBaseDao<MembershipHedisFollowup, Integer> implements MembershipHedisFollowupDao
{

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(MembershipHedisFollowupDaoImpl.class
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
    public MembershipHedisFollowup findById(final Integer id)
    {
    	MembershipHedisFollowup entity = get(id);
        return entity;
    }

    @Override
    public MembershipHedisFollowup save(final MembershipHedisFollowup bean)
    {
        getSession().save(bean);
        return bean;
    }

    @Override
    public MembershipHedisFollowup deleteById(final Integer id)
    {
//        throw new UnsupportedOperationException();
    	MembershipHedisFollowup entity = super.get(id);
        if (entity != null)
        {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    protected Class<MembershipHedisFollowup> getEntityClass()
    {
        return MembershipHedisFollowup.class;
    }
    
    @SuppressWarnings("unchecked")
	public List<MembershipHedisFollowup> findAll()
    {
    	Criteria cr = createCriteria();
    	cr.addOrder(Order.desc("dateOfContact"))
    	.add(Restrictions.eq("activeInd", 'Y'));
    	List<MembershipHedisFollowup> list = cr.list();
    	return list;
    }

}
