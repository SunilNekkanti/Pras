package com.pfchoice.core.dao.impl;


import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.LeadMembershipDao;
import com.pfchoice.core.entity.LeadMembership;
import com.pfchoice.core.entity.PFCPagination;

/**
 *
 * @author Sarath
 */
@Repository
public class LeadMembershipDaoImpl extends HibernateBaseDao<LeadMembership, Integer> implements LeadMembershipDao {

	
	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.LeadMembershipDao#getPage(int, int)
	 */
	public Pagination getPage(int pageNo, int pageSize){
		final Criteria crit = createCriteria();
		return findByCriteria(crit, pageNo, pageSize);
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.LeadMembershipDao#getPage(int, int,
	 * java.lang.String, java.lang.Integer, java.lang.Integer,
	 * java.lang.Integer, java.util.List, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final Integer sSearchIns,
			final Integer sSearchPrvdr, final Integer sSearchHedisRule, final List<Integer> ruleIds, final String sort,
			final String sortdir) {
		//assert sSearchIns !=null : "sSearchIns cannot be null";
		//assert sSearchPrvdr !=null : "sSearchPrvdr cannot be null";
		//assert ruleIds !=null : "ruleIds  cannot be null";
		final Criteria crit = createCriteria().createAlias("genderId", "genderId")
				//.createAlias("mbrProviderList", "mbrProvider", JoinType.LEFT_OUTER_JOIN)
				.createAlias("status", "mbrStatus")
				.createAlias("countyCode", "countyCode", JoinType.LEFT_OUTER_JOIN);
			//	.createAlias("mbrProvider.prvdr", "prvdr", JoinType.LEFT_OUTER_JOIN)
		//.createAlias("mbrInsuranceList", "mbrInsurance", JoinType.LEFT_OUTER_JOIN);
		Disjunction or = Restrictions.disjunction();
		Conjunction and = Restrictions.conjunction();

		if (sSearch != null && !"".equals(sSearch)) {
			or.add(Restrictions.ilike("prvdr.name", "%" + sSearch + "%"))
					.add(Restrictions.ilike("firstName", "%" + sSearch + "%"))
					.add(Restrictions.ilike("lastName", "%" + sSearch + "%"))
					.add(Restrictions.like("genderId.code", new Character(sSearch.toCharArray()[0])))
					.add(Restrictions.ilike("mbrStatus.description", "%" + sSearch + "%"))
					.add(Restrictions.ilike("countyCode.description", "%" + sSearch + "%"))
					.add(Restrictions.sqlRestriction("CAST(DATE_FORMAT(mbr_dob, '%m/%d/%Y') AS CHAR) like ?",
							"%" + sSearch + "%", StringType.INSTANCE));
		}

		and.add(Restrictions.eq("activeInd", new Character('Y')));
	/*	if (sSearchPrvdr != 0) 
			and.add(Restrictions.eq("prvdr.activeInd", new Character('Y')));
		if (sSearchIns != 0) 
				and.add(Restrictions.eq("mbrInsurance.activeInd", new Character('Y')));
				*/
		and.add(Restrictions.ne("mbrStatus.description", "Terminated"));
		crit.add(or);
		crit.add(and);

		crit.setProjection(Projections.distinct(Projections.property("id")));
		List<Integer> mbrIds = (List<Integer>) crit.list();
		int totalCount = mbrIds.isEmpty() ? 0 : mbrIds.size();
		if (totalCount == 0) {
			return findByCriteria(crit, pageNo, pageSize);
		} else {
			Criteria criteria = createCriteria().add(Restrictions.in("id", mbrIds));
			criteria.createAlias("genderId", "genderId")
					//.createAlias("mbrProviderList", "mbrProvider", JoinType.LEFT_OUTER_JOIN)
					.createAlias("status", "status")
					.createAlias("countyCode", "countyCode", JoinType.LEFT_OUTER_JOIN);
					//.createAlias("mbrProvider.prvdr", "prvdr", JoinType.LEFT_OUTER_JOIN)
					//.createAlias("mbrInsuranceList", "mbrInsurance", JoinType.LEFT_OUTER_JOIN);

			criteria.add(Restrictions.eq("activeInd", new Character('Y')));
			/*
			if (sSearchIns != 0) 
				criteria.add(Restrictions.eq("mbrInsurance.activeInd", new Character('Y')));
			if (sSearchPrvdr != 0) 
				criteria.add(Restrictions.eq("mbrProvider.activeInd", new Character('Y')));
			criteria.add(Restrictions.eq("prvdr.activeInd", new Character('Y')));
			*/
			//criteria.add(Restrictions.eq("contact.activeInd", new Character('Y')));
			
			criteria.addOrder(Order.asc("lastName"));
			criteria.addOrder(Order.asc("firstName"));
			
			Pagination pagination = findByCriteria(criteria, pageNo, pageSize);
			pagination.setTotalCount(totalCount);
			PFCPagination  pfcPagination = new PFCPagination(pagination);
		/*	
			if (sSearchHedisRule != null && sSearchHedisRule != 0) {
				Criteria criteria1 = createCriteria().add(Restrictions.in("id", mbrIds));
				
				criteria1.createAlias("genderId", "genderId")
						.createAlias("mbrProviderList", "mbrProvider", JoinType.INNER_JOIN).createAlias("status", "status")
						.createAlias("countyCode", "countyCode", JoinType.LEFT_OUTER_JOIN)
						.createAlias("mbrProvider.prvdr", "prvdr")
						.createAlias("contactList", "contact")
						.createAlias("mbrInsuranceList", "mbrInsurance", JoinType.INNER_JOIN);
	
				criteria1.add(Restrictions.eq("activeInd", new Character('Y')));
				criteria1.add(Restrictions.eq("mbrInsurance.activeInd", new Character('Y')));
				criteria1.add(Restrictions.eq("mbrProvider.activeInd", new Character('Y')));
				criteria1.add(Restrictions.eq("prvdr.activeInd", new Character('Y')));
				criteria1.add(Restrictions.eq("contact.activeInd", new Character('Y')));
			
			
				criteria1.createAlias("mbrHedisMeasureList", "mbrHedisMeasureRule");
				criteria1.createAlias("mbrHedisMeasureRule.hedisMeasureRule", "hedisMeasureRule");
				criteria1.createAlias("mbrHedisMeasureRule.mbr", "mbr");
				
				criteria1.add(Restrictions.in("mbrHedisMeasureRule.hedisMeasureRule.id", ruleIds));
				
				ProjectionList projList = Projections.projectionList(); 
		        projList.add(Projections.rowCount(),"count");
		        projList.add(Projections.groupProperty("hedisMeasureRule.id").as("hedisRuleId"));
		        projList.add(Projections.groupProperty("hedisMeasureRule.shortDescription").as("shortDescription"));
		        projList.add(Projections.groupProperty("mbrHedisMeasureRule.activeInd").as("activeInd"));
		        criteria1.setProjection(projList);
		        criteria1.setResultTransformer(Transformers.aliasToBean(MembershipCountPerHedisRule.class));
		       
		        List<MembershipCountPerHedisRule> countList = (List<MembershipCountPerHedisRule>)criteria1.list();
				pfcPagination.setMbrCountPerHedisRuleList(countList);
			}
	*/		
			return pfcPagination;
		}

	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.LeadMembershipDao#findById(java.lang.Integer)
	 */
	@Override
	public LeadMembership findById(final Integer id) {
		return get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.LeadMembershipDao#save(com.pfchoice.core.entity.
	 * Membership)
	 */
	@Override
	public LeadMembership save(final LeadMembership bean) {
		getSession().save(bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.LeadMembershipDao#deleteById(java.lang.Integer)
	 */
	@Override
	public LeadMembership deleteById(final Integer id) {
		LeadMembership entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see ml.rugal.sshcommon.hibernate.HibernateBaseDao#getEntityClass()
	 */
	@Override
	protected Class<LeadMembership> getEntityClass() {
		return LeadMembership.class;
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.LeadMembershipDao#isLeadExist(com.pfchoice.core.entity.LeadMembership)
	 */
	@Override
	public boolean isLeadExist(LeadMembership lead) {
        return findByMedicareNo(lead.getMedicareNo())!=null;
    } 
	
	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.LeadMembershipDao#findByMedicareNo(java.lang.String)
	 */
	@Override
	public LeadMembership findByMedicareNo(String medicareNo){
		final Criteria crit = createCriteria();
		crit.add(Restrictions.eq("medicareNo", medicareNo));
		return (LeadMembership) crit.uniqueResult();
		
	}
}
