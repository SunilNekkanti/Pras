package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.HedisMeasureGroupDao;
import com.pfchoice.core.entity.HedisMeasureGroup;

/**
 *
 * @author Sarath
 */
@Repository
public class HedisMeasureGroupDaoImpl extends HibernateBaseDao<HedisMeasureGroup, Integer> implements HedisMeasureGroupDao
{

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(HedisMeasureGroupDaoImpl.class
        .getName());

    @Override
    public Pagination getPage(int pageNo, int pageSize)
    {
        Criteria crit = createCriteria();
        Pagination page = findByCriteria(crit, pageNo, pageSize);
        return page;
    }

    @Override
    public HedisMeasureGroup findById(Integer id)
    {
    	HedisMeasureGroup entity = get(id);
        return entity;
    }

    @Override
    public HedisMeasureGroup save(HedisMeasureGroup bean)
    {
        getSession().save(bean);
        return bean;
    }

    @Override
    public HedisMeasureGroup deleteById(Integer id)
    {
//        throw new UnsupportedOperationException();
    	HedisMeasureGroup entity = super.get(id);
        if (entity != null)
        {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    protected Class<HedisMeasureGroup> getEntityClass()
    {
        return HedisMeasureGroup.class;
    }
    
    @SuppressWarnings("unchecked")
	public List<HedisMeasureGroup> findAll()
    {
    	Criteria cr = getSession().createCriteria(getEntityClass());
    	List<HedisMeasureGroup> list = cr.list();
    	return list;
    }

}
