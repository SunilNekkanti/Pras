package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.MembershipDao;
import com.pfchoice.core.entity.Membership;

/**
 *
 * @author Sarath
 */
@Repository
public class MembershipDaoImpl extends HibernateBaseDao<Membership, Integer> implements MembershipDao {

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.MembershipDao#getPage(int, int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		Criteria crit = createCriteria().createAlias("genderId", "genderId")
				.createAlias("countyCode", "countyCode", JoinType.LEFT_OUTER_JOIN).createAlias("status", "status");

		Disjunction or = Restrictions.disjunction();

		if (sSearch != null && !"".equals(sSearch)) {
			or.add(Restrictions.ilike("firstName", "%" + sSearch + "%"))
					.add(Restrictions.ilike("lastName", "%" + sSearch + "%"))
					.add(Restrictions.ilike("genderId.description", "%" + sSearch + "%"))
					.add(Restrictions.ilike("countyCode.description", "%" + sSearch + "%"))
					.add(Restrictions.ilike("status.description", "%" + sSearch + "%")).add(Restrictions
							.sqlRestriction("CAST(mbr_dob AS CHAR) like ?", "%" + sSearch + "%", StringType.INSTANCE));
		}
		crit.add(or);
		crit.add(Restrictions.eq("activeInd", new Character('Y')));
		if (sort != null && !"".equals(sort)) {
			if (sortdir != null && !"".equals(sortdir) && "desc".equals(sortdir)) {
				crit.addOrder(Order.desc(sort));
			} else {
				crit.addOrder(Order.asc(sort));
			}
		}
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return findByCriteria(crit, pageNo, pageSize);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.MembershipDao#getPage(int, int, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.util.List, java.lang.String, java.lang.String)
	 */
	@Override
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final Integer sSearchIns,
			final Integer sSearchPrvdr, final Integer sSearchHedisRule, final List<Integer> ruleIds, final String sort,
			final String sortdir) {

		Criteria crit = createCriteria().createAlias("genderId", "genderId")
				.createAlias("mbrProviderList", "mbrProvider", JoinType.INNER_JOIN)
				.createAlias("mbrProvider.prvdr", "prvdr");
		crit.createAlias("mbrInsuranceList", "mbrInsurance", JoinType.INNER_JOIN);
		Disjunction or = Restrictions.disjunction();
		Conjunction and = Restrictions.conjunction();

		if (sSearch != null && !"".equals(sSearch)) {
			or.add(Restrictions.ilike("prvdr.name", "%" + sSearch + "%"))
					.add(Restrictions.ilike("firstName", "%" + sSearch + "%"))
					.add(Restrictions.ilike("lastName", "%" + sSearch + "%"))
					.add(Restrictions.ilike("genderId.description", "%" + sSearch + "%"))
					.add(Restrictions.like("genderId.code", new Character(sSearch.toCharArray()[0]))).add(Restrictions
							.sqlRestriction("CAST(mbr_dob AS CHAR) like ?", "%" + sSearch + "%", StringType.INSTANCE));
		}
		if (sSearchIns != null) {

			and.add(Restrictions.eq("mbrInsurance.insId.id", sSearchIns));
		}

		if (sSearchPrvdr != null && sSearchPrvdr != 9999) {
			crit.createAlias("prvdr.refContracts", "refContract");
			crit.createAlias("refContract.ins", "ins");
			and.add(Restrictions.eq("prvdr.id", sSearchPrvdr));
			and.add(Restrictions.eq("ins.id", sSearchIns));
		}

		if (sSearchHedisRule != null && sSearchHedisRule != 9999
				&& sSearchHedisRule != 0) {
			crit.createAlias("mbrHedisMeasureList", "mbrHedisMeasureRule");
			and.add(Restrictions.eq("mbrHedisMeasureRule.hedisMeasureRule.id", sSearchHedisRule));
			and.add(Restrictions.eq("mbrHedisMeasureRule.activeInd", new Character('Y')));
			if (sSearch != null && !"".equals(sSearch)) {
				or.add(Restrictions.sqlRestriction("CAST(due_date AS CHAR) like ?", "%" + sSearch + "%",
						StringType.INSTANCE));
			}
		} else if (sSearchHedisRule == 9999) {
			crit.createAlias("mbrHedisMeasureList", "mbrHedisMeasureRule");
			and.add(Restrictions.in("mbrHedisMeasureRule.hedisMeasureRule.id", ruleIds));
			and.add(Restrictions.eq("mbrHedisMeasureRule.activeInd", new Character('Y')));
			if (sSearch != null && !"".equals(sSearch)) {
				or.add(Restrictions.sqlRestriction("CAST(due_date AS CHAR) like ?", "%" + sSearch + "%",
						StringType.INSTANCE));
			}
		}

		and.add(Restrictions.eq("activeInd", new Character('Y')));
		and.add(Restrictions.eq("prvdr.activeInd", new Character('Y')));
		and.add(Restrictions.eq("mbrInsurance.activeInd", new Character('Y')));

		crit.add(or);
		crit.add(and);

		if (sort != null && !"".equals(sort)) {
			if (sortdir != null && !"".equals(sortdir) && "desc".equals(sortdir)) {
				if ("mbrProviderList.0.prvdr.name".equals(sort)) {
					crit.addOrder(Order.desc("prvdr.name"));
				} else {
					crit.addOrder(Order.desc(sort));
				}
			} else {
				if ("mbrProviderList.0.prvdr.name".equals(sort)) {
					crit.addOrder(Order.asc("prvdr.name"));
				} else {
					crit.addOrder(Order.asc(sort));
				}
			}
		}
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		return findByCriteria(crit, pageNo, pageSize);
	}
	
	
	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.MembershipDao#getMbrHospitalizationPage(int, int, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public Pagination getMbrHospitalizationPage(final int pageNo, final int pageSize, final String sSearch, final Integer sSearchIns,
			final Integer sSearchPrvdr, final String sort,
			final String sortdir) {

		Criteria crit = createCriteria().createAlias("genderId", "genderId")
				.createAlias("mbrProviderList", "mbrProvider", JoinType.INNER_JOIN)
				.createAlias("mbrProvider.prvdr", "prvdr");
		crit.createAlias("mbrInsuranceList", "mbrInsurance", JoinType.INNER_JOIN);
		
		crit.createAlias("mbrHospitalizationList", "mbrHospitalizationList", JoinType.INNER_JOIN);
		crit.createAlias("mbrHospitalizationList.hospital", "hospital", JoinType.INNER_JOIN);
		
		Disjunction or = Restrictions.disjunction();
		Conjunction and = Restrictions.conjunction();

		if (sSearch != null && !"".equals(sSearch)) {
			or.add(Restrictions.ilike("prvdr.name", "%" + sSearch + "%"))
					.add(Restrictions.ilike("firstName", "%" + sSearch + "%"))
					.add(Restrictions.ilike("lastName", "%" + sSearch + "%"))
					.add(Restrictions.ilike("genderId.description", "%" + sSearch + "%"))
					.add(Restrictions.like("genderId.code", new Character(sSearch.toCharArray()[0])))
					.add(Restrictions.ilike("hospital.name", "%" + sSearch + "%"))
					.add(Restrictions.ilike("mbrHospitalizationList.planDesc", "%" + sSearch + "%"))
					.add(Restrictions.ilike("mbrHospitalizationList.authNum", "%" + sSearch + "%"))
					.add(Restrictions.ilike("mbrHospitalizationList.priorAdmits", "%" + sSearch + "%"))
					.add(Restrictions
							.sqlRestriction("CAST(mbr_dob AS CHAR) like ?", "%" + sSearch + "%", StringType.INSTANCE));
		}
		if (sSearchIns != null) {

			and.add(Restrictions.eq("mbrInsurance.insId.id", sSearchIns));
		}

		if (sSearchPrvdr != null && sSearchPrvdr != 9999) {
			crit.createAlias("prvdr.refContracts", "refContract");
			crit.createAlias("refContract.ins", "ins");
			and.add(Restrictions.eq("prvdr.id", sSearchPrvdr));
			and.add(Restrictions.eq("ins.id", sSearchIns));
		}

		and.add(Restrictions.eq("activeInd", new Character('Y')));
		and.add(Restrictions.eq("prvdr.activeInd", new Character('Y')));
		and.add(Restrictions.eq("mbrInsurance.activeInd", new Character('Y')));

		crit.add(or);
		crit.add(and);

		if (sort != null && !"".equals(sort)) {
			if (sortdir != null && !"".equals(sortdir) && "desc".equals(sortdir)) {
				if ("mbrProviderList.0.prvdr.name".equals(sort)) {
					crit.addOrder(Order.desc("prvdr.name"));
				} else {
					crit.addOrder(Order.desc(sort));
				}
			} else {
				if ("mbrProviderList.0.prvdr.name".equals(sort)) {
					crit.addOrder(Order.asc("prvdr.name"));
				} else {
					crit.addOrder(Order.asc(sort));
				}
			}
		}
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		return findByCriteria(crit, pageNo, pageSize);
	}
	

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.MembershipDao#findById(java.lang.Integer)
	 */
	@Override
	public Membership findById(final Integer id) {
		return get(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.MembershipDao#save(com.pfchoice.core.entity.Membership)
	 */
	@Override
	public Membership save(final Membership bean) {
		getSession().save(bean);
		return bean;
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.MembershipDao#deleteById(java.lang.Integer)
	 */
	@Override
	public Membership deleteById(final Integer id) {
		Membership entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	/* (non-Javadoc)
	 * @see ml.rugal.sshcommon.hibernate.HibernateBaseDao#getEntityClass()
	 */
	@Override
	protected Class<Membership> getEntityClass() {
		return Membership.class;
	}

}
