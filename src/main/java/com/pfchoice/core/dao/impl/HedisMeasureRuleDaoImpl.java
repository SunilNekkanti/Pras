package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.type.StringType;
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
    public Pagination getPage(final int pageNo,final  int pageSize, 
			final String sSearch, final String sort, final String sortdir)
    {
    	Criteria crit = createCriteria()
         		.createAlias("hedisMeasure", "hedisMeasure")
         		.createAlias("genderId", "genderId", JoinType.LEFT_OUTER_JOIN)
         		.createAlias("cptCodes", "cptMeasure")
         		.createAlias("icdCodes", "icdMeasure");
       
    	if( sSearch != null && !"".equals(sSearch))
    	{
    		Disjunction or = Restrictions.disjunction();
    		
    		or.add(Restrictions.ilike("hedisMeasure.code",sSearch, MatchMode.ANYWHERE))
    		  .add(Restrictions.ilike("cptMeasure.code",sSearch, MatchMode.ANYWHERE))
    		  .add(Restrictions.ilike("icdMeasure.code",sSearch, MatchMode.ANYWHERE))
    		  .add(Restrictions.sqlRestriction("CAST({alias}.effective_Year AS CHAR) like ?", "%"+sSearch+"%", StringType.INSTANCE))
    		  .add(Restrictions.ilike("genderId.description",sSearch, MatchMode.ANYWHERE))
    		  .add(Restrictions.sqlRestriction("CAST({alias}.lower_age_limit AS CHAR) like ?", "%"+sSearch+"%", StringType.INSTANCE))
    		  .add(Restrictions.sqlRestriction("CAST({alias}.upper_age_limit AS CHAR) like ?", "%"+sSearch+"%", StringType.INSTANCE))
    		  .add(Restrictions.sqlRestriction("CAST({alias}.age_effective_from AS CHAR) like ?", "%"+sSearch+"%", StringType.INSTANCE))
    		  .add(Restrictions.sqlRestriction("CAST({alias}.age_effective_to AS CHAR) like ?", "%"+sSearch+"%", StringType.INSTANCE));
    		
    		crit.add(or);
    	}
    	
    	crit.add(Restrictions.eq("activeInd", 'Y'));
    	
    	if(sort != null && !"".equals(sort)) 
		{
			if(sortdir != null && !"".equals(sortdir) && "desc".equals(sortdir))
			{
				crit.addOrder(Order.desc(sort));
			}
			else 
			{
				crit.addOrder(Order.asc(sort));
			}
		}
			crit.addOrder(Order.asc("hedisMeasure.code"));
			crit.addOrder(Order.asc("cptMeasure.code"));
			crit.addOrder(Order.asc("icdMeasure.code"));
    	    crit.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		//	crit.setResultTransformer(Criteria.PROJECTION);   
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
    
}
