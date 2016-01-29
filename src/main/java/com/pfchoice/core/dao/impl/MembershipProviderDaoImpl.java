package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.MembershipProviderDao;
import com.pfchoice.core.entity.MembershipProvider;

/**
 *
 * @author Sarath
 */
@Repository
public class MembershipProviderDaoImpl extends HibernateBaseDao<MembershipProvider, Integer> implements MembershipProviderDao
{

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(MembershipProviderDaoImpl.class
        .getName());

    @Override
    public Pagination getPage(int pageNo, int pageSize)
    {
        Criteria crit = createCriteria();
        Pagination page = findByCriteria(crit, pageNo, pageSize);
        return page;
    }

    @Override
    public MembershipProvider findById(Integer id)
    {
    	MembershipProvider entity = get(id);
        return entity;
    }

    @Override
    public MembershipProvider save(MembershipProvider bean)
    {
        getSession().save(bean);
        return bean;
    }

    @Override
    public MembershipProvider deleteById(Integer id)
    {
//        throw new UnsupportedOperationException();
    	MembershipProvider entity = super.get(id);
        if (entity != null)
        {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    protected Class<MembershipProvider> getEntityClass()
    {
        return MembershipProvider.class;
    }

    @SuppressWarnings("unchecked")
	public List<MembershipProvider> findAll()
    {
    	Criteria cr = getSession().createCriteria(getEntityClass());
    	List<MembershipProvider> list = cr.list();
    	return list;
    }
    
    @SuppressWarnings("unchecked")
	public List<MembershipProvider> findAllByMbrId(Integer id)
    {
    	Criteria cr = getSession().createCriteria(getEntityClass(), "mbrPrvdr")
    			.createAlias("mbrPrvdr.mbr","mbr")
    			.add(Restrictions.eq("mbr.id", id));
    	List<MembershipProvider> list = cr.list();
    	System.out.println("findAllByMbrId list size is"+ list.size());
    	return list;
    }
}
