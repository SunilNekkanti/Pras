package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.HedisMeasureDao;
import com.pfchoice.core.entity.HedisMeasure;

/**
 *
 * @author Sarath
 */
@Repository
public class HedisMeasureDaoImpl extends HibernateBaseDao<HedisMeasure, Integer> implements HedisMeasureDao
{

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(HedisMeasureDaoImpl.class
        .getName());

    @Override
    public Pagination getPage(final int pageNo, final int pageSize)
    {
        Criteria crit = createCriteria();
        Pagination page = findByCriteria(crit, pageNo, pageSize);
        return page;
    }

    @Override
    public HedisMeasure findById(final Integer id)
    {
    	HedisMeasure entity = get(id);
        return entity;
    }

    @Override
    public HedisMeasure save(final HedisMeasure bean)
    {
        getSession().save(bean);
        return bean;
    }

    @Override
    public HedisMeasure deleteById(final Integer id)
    {
//        throw new UnsupportedOperationException();
    	HedisMeasure entity = super.get(id);
        if (entity != null)
        {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    protected Class<HedisMeasure> getEntityClass()
    {
        return HedisMeasure.class;
    }
    
    @SuppressWarnings("unchecked")
	public List<HedisMeasure> findAll()
    {
    	Criteria cr = getSession().createCriteria(getEntityClass());
    	List<HedisMeasure> list = cr.list();
    	return list;
    }

}
