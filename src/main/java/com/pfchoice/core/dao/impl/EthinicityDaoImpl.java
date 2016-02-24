package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.EthinicityDao;
import com.pfchoice.core.entity.Ethinicity;

/**
 *
 * @author Mohanasundharam
 */
@Repository
public class EthinicityDaoImpl extends HibernateBaseDao<Ethinicity, Byte> implements EthinicityDao
{

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(EthinicityDaoImpl.class
        .getName());

    @Override
    public Pagination getPage(final int pageNo, final int pageSize)
    {
        Criteria crit = createCriteria();
        crit.add(Restrictions.eq("activeInd", 'Y'));
        Pagination page = findByCriteria(crit, pageNo, pageSize);
        return page;
    }

    @Override
    public Ethinicity findById(final Byte id)
    {
    	Ethinicity entity = get(id);
        return entity;
    }

    @Override
    public Ethinicity save(final Ethinicity bean)
    {
        getSession().save(bean);
        return bean;
    }

    @Override
    public Ethinicity deleteById(final Byte id)
    {
//        throw new UnsupportedOperationException();
    	Ethinicity entity = super.get(id);
        if (entity != null)
        {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    protected Class<Ethinicity> getEntityClass()
    {
        return Ethinicity.class;
    }

    @SuppressWarnings("unchecked")
	public List<Ethinicity> findAll()
    {
    	Criteria cr = createCriteria();
    	cr.add(Restrictions.eq("activeInd", 'Y'));
    	List<Ethinicity> list = cr.list();
    	return list;
    }
}
