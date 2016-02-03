package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.HedisMeasureRuleDao;
import com.pfchoice.core.entity.HedisMeasureRule;

/**
 *
 * @author Sarath
 */
@Repository
public class HedisMeasureRuleDaoImpl extends HibernateBaseDao<HedisMeasureRule, Integer> implements HedisMeasureRuleDao
{

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(HedisMeasureRuleDaoImpl.class
        .getName());

    @Override
    public Pagination getPage(final int pageNo,final  int pageSize)
    {
        Criteria crit = createCriteria();
        Pagination page = findByCriteria(crit, pageNo, pageSize);
        return page;
    }

    @Override
    public HedisMeasureRule findById(final Integer id)
    {
    	HedisMeasureRule entity = get(id);
        return entity;
    }

    @Override
    public HedisMeasureRule save(final HedisMeasureRule bean)
    {
        getSession().save(bean);
        return bean;
    }

    @Override
    public HedisMeasureRule deleteById(final Integer id)
    {
//        throw new UnsupportedOperationException();
    	HedisMeasureRule entity = super.get(id);
        if (entity != null)
        {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    protected Class<HedisMeasureRule> getEntityClass()
    {
        return HedisMeasureRule.class;
    }
    
    @SuppressWarnings("unchecked")
	public List<HedisMeasureRule> findAll()
    {
    	Criteria cr = getSession().createCriteria(getEntityClass());
    	List<HedisMeasureRule> list = cr.list();
    	return list;
    }

}
