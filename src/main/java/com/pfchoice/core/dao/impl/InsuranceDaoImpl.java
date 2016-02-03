package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.InsuranceDao;
import com.pfchoice.core.entity.Insurance;

/**
 *
 * @author Mohanasundharam
 */
@Repository
public class InsuranceDaoImpl extends HibernateBaseDao<Insurance, Integer> implements InsuranceDao
{

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(InsuranceDaoImpl.class
        .getName());

    @Override
    public Pagination getPage(final int pageNo, final int pageSize)
    {
        Criteria crit = createCriteria();
        Pagination page = findByCriteria(crit, pageNo, pageSize);
        return page;
    }

    @Override
    public Insurance findById(final Integer id)
    {
    	Insurance entity = get(id);
        return entity;
    }

    @Override
    public Insurance save(final Insurance bean)
    {
        getSession().save(bean);
        return bean;
    }

    @Override
    public Insurance deleteById(final Integer id)
    {
//        throw new UnsupportedOperationException();
    	Insurance entity = super.get(id);
        if (entity != null)
        {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    protected Class<Insurance> getEntityClass()
    {
        return Insurance.class;
    }

    @SuppressWarnings("unchecked")
	public List<Insurance> findAll()
    {
    	Criteria cr = getSession().createCriteria(getEntityClass());
    	List<Insurance> list = cr.list();
    	return list;
    }
}
