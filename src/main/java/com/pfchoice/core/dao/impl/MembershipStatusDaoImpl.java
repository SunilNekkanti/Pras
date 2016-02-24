package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.pfchoice.core.dao.MembershipStatusDao;
import com.pfchoice.core.entity.MembershipStatus;

/**
 *
 * @author Mohanasundharam
 */
@Repository
public class MembershipStatusDaoImpl extends HibernateBaseDao<MembershipStatus, Byte> implements MembershipStatusDao
{

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(MembershipStatusDaoImpl.class
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
    public MembershipStatus findById(final Byte id)
    {
        MembershipStatus entity = get(id);
        return entity;
    }

    @Override
    public MembershipStatus save(final MembershipStatus bean)
    {
        getSession().save(bean);
        return bean;
    }

    @Override
    public MembershipStatus deleteById(final Byte id)
    {
//        throw new UnsupportedOperationException();
        MembershipStatus entity = super.get(id);
        if (entity != null)
        {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    protected Class<MembershipStatus> getEntityClass()
    {
        return MembershipStatus.class;
    }

    @SuppressWarnings("unchecked")
	public List<MembershipStatus> findAll()
    {
    	Criteria cr = createCriteria();
    	cr.add(Restrictions.eq("activeInd", 'Y'));
    	List<MembershipStatus>  list = cr.list();
    	return list;
    }
}
