package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
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
    public Pagination getPage(int pageNo, int pageSize)
    {
        Criteria crit = createCriteria();
        Pagination page = findByCriteria(crit, pageNo, pageSize);
        return page;
    }

    @Override
    public MembershipStatus findById(Byte id)
    {
        MembershipStatus entity = get(id);
        return entity;
    }

    @Override
    public MembershipStatus save(MembershipStatus bean)
    {
        getSession().save(bean);
        return bean;
    }

    @Override
    public MembershipStatus deleteById(Byte id)
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
    	Criteria cr = getSession().createCriteria(getEntityClass());
    	List<MembershipStatus>  list = cr.list();
    	return list;
    }
}
