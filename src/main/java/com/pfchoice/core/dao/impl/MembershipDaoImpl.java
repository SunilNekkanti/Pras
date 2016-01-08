package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.MembershipDao;
import com.pfchoice.core.entity.Membership;

/**
 *
 * @author Sarath
 */
@Repository
public class MembershipDaoImpl extends HibernateBaseDao<Membership, Integer> implements MembershipDao
{

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(MembershipDaoImpl.class
        .getName());

    @Override
    public Pagination getPage(int pageNo, int pageSize)
    {
        Criteria crit = createCriteria();
        Pagination page = findByCriteria(crit, pageNo, pageSize);
        return page;
    }

    @Override
    public Membership findById(Integer id)
    {
        Membership entity = get(id);
        return entity;
    }

    @Override
    public Membership save(Membership bean)
    {
        getSession().save(bean);
        return bean;
    }

    @Override
    public Membership deleteById(Integer id)
    {
//        throw new UnsupportedOperationException();
        Membership entity = super.get(id);
        if (entity != null)
        {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    protected Class<Membership> getEntityClass()
    {
        return Membership.class;
    }

    @SuppressWarnings("unchecked")
	public List<Membership> findAll()
    {
    	List<Membership> list =	find("from Membership m",null);
    	return list;
    }
}
