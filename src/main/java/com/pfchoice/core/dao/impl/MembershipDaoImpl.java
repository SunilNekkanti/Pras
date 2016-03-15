package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;


import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.type.StringType;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.MembershipDao;
import com.pfchoice.core.entity.Membership;

/**
 *
 * @author Sarath
 */
@Repository
public class MembershipDaoImpl extends HibernateBaseDao<Membership, Integer> implements MembershipDao
{

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(MembershipDaoImpl.class
        .getName());

    @Override
    public Pagination getPage(final int pageNo, final int pageSize, 
    		final String sSearch, final String sort, final String sortdir)
    {
    	Criteria crit = createCriteria()
         		.createAlias("genderId", "genderId")
         		.createAlias("countyCode", "countyCode", JoinType.LEFT_OUTER_JOIN)
         		.createAlias("status", "status");
       
    	Disjunction or = Restrictions.disjunction();
    
    	if( sSearch != null && !"".equals(sSearch))
    	{
    		or.add(Restrictions.ilike("firstName","%"+sSearch+"%"))
    		  .add(Restrictions.ilike("lastName","%"+sSearch+"%"))
    		  .add(Restrictions.ilike("genderId.description","%"+sSearch+"%"))
    		  .add(Restrictions.ilike("countyCode.description","%"+sSearch+"%"))
    		  .add(Restrictions.ilike("status.description","%"+sSearch+"%"))
    		  .add(Restrictions.sqlRestriction("CAST(mbr_dob AS CHAR) like ?", "%"+sSearch+"%", StringType.INSTANCE));
    	}
         crit.add(or);
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
        
        Pagination page = findByCriteria(crit, pageNo, pageSize);
        return page;
    }

    @Override
    public Pagination getPage(final int pageNo, final int pageSize, 
    		final String sSearch,final Integer sSearchIns,   final Integer sSearchPrvdr,
    		final Integer sSearchHedisCode,	 final String sort, final String sortdir)
    {
    	Criteria crit = createCriteria()
         		.createAlias("genderId", "genderId")
         		.createAlias("countyCode", "countyCode", JoinType.LEFT_OUTER_JOIN)
         		.createAlias("status", "status");
    	        
    	Disjunction or = Restrictions.disjunction();
    	Conjunction and = Restrictions.conjunction();

    	if( sSearch != null && !"".equals(sSearch))
    	{
    		or.add(Restrictions.ilike("firstName","%"+sSearch+"%"))
    		  .add(Restrictions.ilike("lastName","%"+sSearch+"%"))
    		  .add(Restrictions.ilike("genderId.description","%"+sSearch+"%"))
    		  .add(Restrictions.ilike("countyCode.description","%"+sSearch+"%"))
    		  .add(Restrictions.ilike("status.description","%"+sSearch+"%"))
    		  .add(Restrictions.sqlRestriction("CAST(mbr_dob AS CHAR) like ?", "%"+sSearch+"%", StringType.INSTANCE));
    	}
    	if( sSearchIns != null && !"".equals(sSearchIns))
    	{
    		crit.createAlias("mbrInsuranceList", "mbrInsurance", JoinType.INNER_JOIN);
    		and.add(Restrictions.eq("mbrInsurance.insId.id", sSearchIns));
    	}
    	
    	if( sSearchPrvdr != null && !"".equals(sSearchPrvdr) && !sSearchPrvdr.equals("All"))
    	{
    		crit.createAlias("mbrProviderList", "mbrProvider", JoinType.INNER_JOIN);
    		and.add(Restrictions.eq("mbrProvider.prvdr.id", sSearchPrvdr));
    	}
    	
    	if( sSearchHedisCode != null && !"".equals(sSearchHedisCode))
    	{
    	//	crit.createAlias("mbrHedisMeasureList", "mbrHedisMeasureRule", JoinType.INNER_JOIN);
		//	or.add(Restrictions.eq("mbrHedisMeasureRule.hedisMeasureRule.id", sSearchHedisCode));
    	}
    	
         crit.add(or);
         crit.add(and);
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
        
        Pagination page = findByCriteria(crit, pageNo, pageSize);
        return page;
    }

    
    @Override
    public Membership findById(final Integer id)
    {
        Membership entity = get(id);
        return entity;
    }

    @Override
    public Membership save(final Membership bean)
    {
        getSession().save(bean);
        return bean;
    }

    @Override
    public Membership deleteById(final Integer id)
    {
//        throw new UnsupportedOperationException();
        Membership entity = super.get(id);
        if (entity != null)
        {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    protected Class<Membership> getEntityClass()
    {
        return Membership.class;
    }

}
