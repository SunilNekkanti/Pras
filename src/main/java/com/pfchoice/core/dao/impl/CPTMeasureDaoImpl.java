package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.CPTMeasureDao;
import com.pfchoice.core.entity.CPTMeasure;

/**
 *
 * @author Sarath
 */
@Repository
public class CPTMeasureDaoImpl extends HibernateBaseDao<CPTMeasure, Integer> implements CPTMeasureDao
{

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(CPTMeasureDaoImpl.class
        .getName());

    @Override
    public Pagination getPage(final int pageNo, final int pageSize)
    {
        Criteria crit = createCriteria();
        Pagination page = findByCriteria(crit, pageNo, pageSize);
        return page;
    }

    @Override
    public CPTMeasure findById(final Integer id)
    {
    	CPTMeasure entity = get(id);
        return entity;
    }

    @Override
    public CPTMeasure save(final CPTMeasure bean)
    {
        getSession().save(bean);
        return bean;
    }

    @Override
    public CPTMeasure deleteById(final Integer id)
    {
//        throw new UnsupportedOperationException();
    	CPTMeasure entity = super.get(id);
        if (entity != null)
        {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    protected Class<CPTMeasure> getEntityClass()
    {
        return CPTMeasure.class;
    }
    
    @SuppressWarnings("unchecked")
	public List<CPTMeasure> findAll()
    {
    	Criteria cr = getSession().createCriteria(getEntityClass());
    	List<CPTMeasure> list = cr.list();
    	return list;
    }

}
