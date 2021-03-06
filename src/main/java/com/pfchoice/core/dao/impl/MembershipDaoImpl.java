package com.pfchoice.core.dao.impl;

import static com.pfchoice.common.SystemDefaultProperties.FILTER_BY_PROCESSING_DATE;
import static com.pfchoice.common.SystemDefaultProperties.QUERY_TYPE_BH_INSERT;
import static com.pfchoice.common.SystemDefaultProperties.QUERY_TYPE_FETCH;
import static com.pfchoice.common.SystemDefaultProperties.QUERY_TYPE_BH_LOAD;
import static com.pfchoice.common.SystemDefaultProperties.QUERY_TYPE_INSERT;
import static com.pfchoice.common.SystemDefaultProperties.QUERY_TYPE_LOAD;
import static com.pfchoice.common.SystemDefaultProperties.FILTER_BY_HOSPOTALIZATION_DATE;
import static com.pfchoice.common.SystemDefaultProperties.ALL;
import static com.pfchoice.common.SystemDefaultProperties.FILES_UPLOAD_DIRECTORY_PATH;
import static com.pfchoice.common.SystemDefaultProperties.FILE_TYPE_AMG_MBR_ROSTER;
import static com.pfchoice.common.SystemDefaultProperties.FILE_TYPE_BH_MBR_ROSTER;
import static com.pfchoice.common.SystemDefaultProperties.FILE_TYPE_AMG_CAP_REPORT; 
import com.pfchoice.core.entity.HedisMeasure;
import com.pfchoice.core.entity.MembershipHedisMeasure;


import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.StringType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pfchoice.common.util.PrasUtil;
import com.pfchoice.core.dao.MembershipDao;
import com.pfchoice.core.entity.Membership;
import com.pfchoice.core.entity.MembershipCountPerHedisRule;
import com.pfchoice.core.entity.PFCPagination;

/**
 *
 * @author Sarath
 */
@Repository
public class MembershipDaoImpl extends HibernateBaseDao<Membership, Integer> implements MembershipDao {

	private static final Logger LOG = LoggerFactory.getLogger(MembershipDaoImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.MembershipDao#getPage(int, int,
	 * java.lang.String, java.lang.String, java.lang.String)
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
		crit.addOrder(Order.asc("lastName"));
		crit.addOrder(Order.asc("firstName"));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return findByCriteria(crit, pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.MembershipDao#getPage(int, int,
	 * java.lang.String, java.lang.Integer, java.lang.Integer,
	 * java.lang.Integer, java.util.List, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final Integer sSearchIns,
			final Integer sSearchPrvdr, final Integer sSearchHedisRule, final List<Integer> ruleIds, final String sort,
			final String sortdir) {
		assert sSearchIns !=null : "sSearchIns cannot be null";
		assert sSearchPrvdr !=null : "sSearchPrvdr cannot be null";
		assert ruleIds !=null : "ruleIds  cannot be null";
		
		final Criteria crit = createCriteria().createAlias("genderId", "genderId")
				.createAlias("mbrProviderList", "mbrProvider", JoinType.INNER_JOIN).createAlias("status", "mbrStatus")
				.createAlias("countyCode", "countyCode", JoinType.LEFT_OUTER_JOIN)
				.createAlias("mbrProvider.prvdr", "prvdr");
		crit.createAlias("mbrInsuranceList", "mbrInsurance", JoinType.INNER_JOIN);
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
		if (sSearchIns != null) {

			and.add(Restrictions.eq("mbrInsurance.insId.id", sSearchIns));
		}

		if (sSearchPrvdr != null && sSearchPrvdr != ALL) {
			crit.createAlias("prvdr.refContracts", "refContract");
			crit.createAlias("refContract.ins", "ins");
			and.add(Restrictions.eq("prvdr.id", sSearchPrvdr));
			and.add(Restrictions.eq("ins.id", sSearchIns));
		}

		if (sSearchHedisRule != null && sSearchHedisRule != 0) {
			crit.createAlias("mbrHedisMeasureList", "mbrHedisMeasureRule");
			and.add(Restrictions.in("mbrHedisMeasureRule.hedisMeasureRule.id", ruleIds));
		//	and.add(Restrictions.eq("mbrHedisMeasureRule.activeInd", new Character('Y')));
			if (sSearch != null && !"".equals(sSearch)) {
				or.add(Restrictions.sqlRestriction("CAST(due_date AS CHAR) like ?", "%" + sSearch + "%",
						StringType.INSTANCE));
			}
		}

		and.add(Restrictions.eq("activeInd", new Character('Y')));
		and.add(Restrictions.eq("prvdr.activeInd", new Character('Y')));
		and.add(Restrictions.eq("mbrInsurance.activeInd", new Character('Y')));
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
					.createAlias("mbrProviderList", "mbrProvider", JoinType.INNER_JOIN).createAlias("status", "status")
					.createAlias("countyCode", "countyCode", JoinType.LEFT_OUTER_JOIN)
					.createAlias("mbrProvider.prvdr", "prvdr")
					.createAlias("refContactList", "refContact")
					.createAlias("refContact.cnt", "contact")
					.createAlias("mbrInsuranceList", "mbrInsurance", JoinType.INNER_JOIN);

			criteria.add(Restrictions.eq("activeInd", new Character('Y')));
			criteria.add(Restrictions.eq("mbrInsurance.activeInd", new Character('Y')));
			criteria.add(Restrictions.eq("mbrProvider.activeInd", new Character('Y')));
			criteria.add(Restrictions.eq("prvdr.activeInd", new Character('Y')));
			criteria.add(Restrictions.eq("contact.activeInd", new Character('Y')));
			
			
			if (sort != null && !"".equals(sort) && sortdir != null &&  "desc".equals(sortdir)) {
				switch (sort) {
				case "id":
					criteria.addOrder(Order.asc("dob"));
					break;
				case "mbrProviderList.0.prvdr.name":
					criteria.addOrder(Order.desc("prvdr.name"));
					break;
				default:
					criteria.addOrder(Order.desc(sort));
					break;
				}
			} else if (sort != null) {
				switch (sort) {
				case "id":
					criteria.addOrder(Order.desc("dob"));
					break;
				case "mbrProviderList.0.prvdr.name":
					criteria.addOrder(Order.asc("prvdr.name"));
					break;
				default:
					criteria.addOrder(Order.asc(sort));
					break;
				}
			}
			criteria.addOrder(Order.asc("lastName"));
			criteria.addOrder(Order.asc("firstName"));
			
			Pagination pagination = findByCriteria(criteria, pageNo, pageSize);
			pagination.setTotalCount(totalCount);
			PFCPagination  pfcPagination = new PFCPagination(pagination);
			if (sSearchHedisRule != null && sSearchHedisRule != 0) {
				Criteria criteria1 = createCriteria().add(Restrictions.in("id", mbrIds));
				
				criteria1.createAlias("genderId", "genderId")
						.createAlias("mbrProviderList", "mbrProvider", JoinType.INNER_JOIN).createAlias("status", "status")
						.createAlias("countyCode", "countyCode", JoinType.LEFT_OUTER_JOIN)
						.createAlias("mbrProvider.prvdr", "prvdr")
						.createAlias("refContactList", "refContact")
						.createAlias("refContact.cnt", "contact")
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
			
			return pfcPagination;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.MembershipDao#getPage(int, int,
	 * java.lang.String, java.lang.Integer, java.lang.Integer,
	 * java.lang.Integer, java.util.List, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Pagination getMembershipActivityMonthPage(final int pageNo, final int pageSize, final String sSearch,
			final Integer sSearchIns, final Integer sSearchPrvdr, final Integer sSearchYear,
			final List<Integer> ruleIds, final String sort, final String sortdir) {

		Criteria crit = createCriteria().createAlias("mbrActivityMonthList", "mbrActivityMonth")
				.createAlias("mbrActivityMonth.prvdr", "prvdr").createAlias("mbrActivityMonth.ins", "insId");
		Disjunction or = Restrictions.disjunction();
		Conjunction and = Restrictions.conjunction();

		if (sSearch != null && !"".equals(sSearch)) {
			or.add(Restrictions.ilike("prvdr.name", "%" + sSearch + "%"))
					.add(Restrictions.ilike("firstName", "%" + sSearch + "%"))
					.add(Restrictions.ilike("lastName", "%" + sSearch + "%")).add(Restrictions
							.sqlRestriction("CAST(mbr_dob AS CHAR) like ?", "%" + sSearch + "%", StringType.INSTANCE));
		}
		if (sSearchIns != null) {

			and.add(Restrictions.eq("insId.id", sSearchIns));
		}

		if (sSearchPrvdr != null && sSearchPrvdr != ALL) {
			and.add(Restrictions.eq("prvdr.id", sSearchPrvdr));
			and.add(Restrictions.eq("insId.id", sSearchIns));
		}

		if (sSearchYear != null && sSearchYear != ALL && sSearchYear != 0) {
			and.add(Restrictions.sqlRestriction("CAST(activity_month AS CHAR) like ?", "%" + sSearchYear + "%",
					StringType.INSTANCE));

		}

		and.add(Restrictions.eq("activeInd", new Character('Y')));
		and.add(Restrictions.eq("prvdr.activeInd", new Character('Y')));
		and.add(Restrictions.eq("insId.activeInd", new Character('Y')));
		crit.add(or);
		crit.add(and);

		crit.setProjection(Projections.distinct(Projections.property("id")));
		List<Integer> mbrIds = (List<Integer>) crit.list();
		int totalCount = (mbrIds.isEmpty()) ? 0 : mbrIds.size();
		if (totalCount == 0) {
			return findByCriteria(crit, pageNo, pageSize);
		} else {
			Criteria criteria = createCriteria().createAlias("mbrProviderList", "mbrProvider", JoinType.INNER_JOIN)
					.createAlias("mbrProvider.prvdr", "prvdr");
			criteria.add(Restrictions.in("id", mbrIds));

			if (sort != null && !"".equals(sort) && sortdir != null && "desc".equals(sortdir)) {
				if("mbrProviderList.0.prvdr.name".equals(sort)) {
					criteria.addOrder(Order.desc("prvdr.name"));
				}else{
					criteria.addOrder(Order.desc(sort));
				}
			} else if (sort != null){
				if("mbrProviderList.0.prvdr.name".equals(sort)) {
					criteria.addOrder(Order.asc("prvdr.name"));
				}else{
					criteria.addOrder(Order.asc(sort));
				}
			}
			criteria.addOrder(Order.asc("lastName"));
			criteria.addOrder(Order.asc("firstName"));

			Pagination pagination = findByCriteria(criteria, pageNo, pageSize);
			pagination.setTotalCount(totalCount);
			return pagination;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.MembershipDao#getPage(int, int,
	 * java.lang.String, java.lang.Integer, java.lang.Integer,
	 * java.lang.Integer, java.util.List, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Pagination getMembershipProblemPage(final int pageNo, final int pageSize, final String sSearch,
			final Integer sSearchIns, final Integer sSearchPrvdr, final Integer sSearchPbmRule,
			final List<Integer> ruleIds, final String sort, final String sortdir) {

		Criteria crit = createCriteria().createAlias("genderId", "genderId")
				.createAlias("mbrProviderList", "mbrProvider", JoinType.INNER_JOIN).createAlias("status", "mbrStatus")
				.createAlias("mbrProvider.prvdr", "prvdr");
		crit.createAlias("mbrInsuranceList", "mbrInsurance", JoinType.INNER_JOIN);
		crit.createAlias("mbrProblemList", "mbrProblemList");
		crit.createAlias("mbrProblemList.pbm", "pbm");
		Disjunction or = Restrictions.disjunction();
		Conjunction and = Restrictions.conjunction();

		if (sSearch != null && !"".equals(sSearch)) {
			or.add(Restrictions.ilike("prvdr.name", "%" + sSearch + "%"))
					.add(Restrictions.ilike("firstName", "%" + sSearch + "%"))
					.add(Restrictions.ilike("lastName", "%" + sSearch + "%"))
					.add(Restrictions.sqlRestriction("CAST(DATE_FORMAT(mbr_dob, '%m/%d/%Y') AS CHAR) like ?",
							"%" + sSearch + "%", StringType.INSTANCE))
					.add(Restrictions.sqlRestriction("CAST(DATE_FORMAT(start_date, '%m/%d/%Y') AS CHAR) like ?",
							"%" + sSearch + "%", StringType.INSTANCE))
					.add(Restrictions.sqlRestriction("CAST(DATE_FORMAT(resolved_date, '%m/%d/%Y') AS CHAR) like ?",
							"%" + sSearch + "%", StringType.INSTANCE));
		}
		if (sSearchIns != null) {

			and.add(Restrictions.eq("mbrInsurance.insId.id", sSearchIns));
		}

		if (sSearchPrvdr != null && sSearchPrvdr != ALL) {
			crit.createAlias("prvdr.refContracts", "refContract");
			crit.createAlias("refContract.ins", "ins");
			and.add(Restrictions.eq("prvdr.id", sSearchPrvdr));
			and.add(Restrictions.eq("ins.id", sSearchIns));
		}

		if (sSearchPbmRule != null && sSearchPbmRule != ALL && sSearchPbmRule != 0) {

			and.add(Restrictions.eq("mbrProblemList.pbm.id", sSearchPbmRule));
			and.add(Restrictions.eq("mbrProblemList.activeInd", new Character('Y')));

		} else if (sSearchPbmRule == ALL) {

			and.add(Restrictions.in("mbrProblemList.pbm.id", ruleIds));
			and.add(Restrictions.eq("mbrProblemList.activeInd", new Character('Y')));

		}

		and.add(Restrictions.eq("activeInd", new Character('Y')));
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
			criteria.createAlias("mbrProviderList", "mbrProvider", JoinType.INNER_JOIN)
					.createAlias("mbrProvider.prvdr", "prvdr");
	
			if (sort != null && !"".equals(sort) && sortdir != null &&  "desc".equals(sortdir)) {
					if ("mbrProviderList.0.prvdr.name".equals(sort)) {
						criteria.addOrder(Order.desc("prvdr.name"));
					} else {
						criteria.addOrder(Order.desc(sort));
					}
			} else {
					if ("mbrProviderList.0.prvdr.name".equals(sort)) {
						criteria.addOrder(Order.asc("prvdr.name"));
					} else {
						criteria.addOrder(Order.asc(sort));
					}
			}
			criteria.addOrder(Order.asc("lastName"));
			criteria.addOrder(Order.asc("firstName"));

			Pagination pagination = findByCriteria(criteria, pageNo, pageSize);
			pagination.setTotalCount(totalCount);
			return pagination;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.MembershipDao#getPage(int, int,
	 * java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final Integer sSearchIns,
			final Integer sSearchPrvdr, final String sort, final String sortdir, final Date processingFrom,
			final Date processingTo, final Integer processHospitalization) {

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
					.add(Restrictions.sqlRestriction("CAST(prior_admits AS CHAR) like ?", "%" + sSearch + "%",
							StringType.INSTANCE))
					.add(Restrictions.sqlRestriction("CAST(exp_dc_date AS CHAR) like ?", "%" + sSearch + "%",
							StringType.INSTANCE))
					.add(Restrictions.sqlRestriction("CAST(admit_date AS CHAR) like ?", "%" + sSearch + "%",
							StringType.INSTANCE));
		}
		if (sSearchIns != null) {

			and.add(Restrictions.eq("mbrInsurance.insId.id", sSearchIns));
		}

		if (sSearchPrvdr != null && sSearchPrvdr != ALL) {
			crit.createAlias("prvdr.refContracts", "refContract");
			crit.createAlias("refContract.ins", "ins");
			and.add(Restrictions.eq("prvdr.id", sSearchPrvdr));
			and.add(Restrictions.eq("ins.id", sSearchIns));
		}

		and.add(Restrictions.eq("activeInd", new Character('Y')));
		and.add(Restrictions.eq("prvdr.activeInd", new Character('Y')));
		and.add(Restrictions.eq("mbrInsurance.activeInd", new Character('Y')));

		if (processHospitalization == FILTER_BY_PROCESSING_DATE)
			and.add(Restrictions.between("mbrHospitalizationList.updatedDate",
					new java.sql.Date(processingFrom.getTime()), new java.sql.Date(processingTo.getTime() + 86400000)));
		if (processHospitalization == FILTER_BY_HOSPOTALIZATION_DATE) {
			and.add(Restrictions.sqlRestriction(" ? between admit_date and exp_dc_date",
					new java.sql.Date(processingFrom.getTime()), DateType.INSTANCE));
			and.add(Restrictions.sqlRestriction(" ? between admit_date and exp_dc_date",
					new java.sql.Date(processingTo.getTime()), DateType.INSTANCE));
		}

		crit.add(or);
		crit.add(and);

		if (sort != null && !"".equals(sort) && sortdir != null &&  "desc".equals(sortdir)) {
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
		crit.addOrder(Order.asc("lastName"));
		crit.addOrder(Order.asc("firstName"));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		return findByCriteria(crit, pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.MembershipDao#getPage(int, int,
	 * java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Pagination getClaimPage(final int pageNo, final int pageSize, final String sSearch, final Integer sSearchIns,
			final Integer sSearchPrvdr, final String sort, final String sortdir, final Date processingFrom,
			final Date processingTo, final Integer processClaim) {

		Criteria crit = createCriteria().createAlias("genderId", "genderId")
				.createAlias("mbrProviderList", "mbrProvider", JoinType.INNER_JOIN)
				.createAlias("mbrProvider.prvdr", "prvdr");
		crit.createAlias("mbrInsuranceList", "mbrInsurance", JoinType.INNER_JOIN);

		crit.createAlias("mbrClaimList", "mbrClaim", JoinType.INNER_JOIN);
		crit.createAlias("mbrClaim.frequencyType", "frequency", JoinType.LEFT_OUTER_JOIN);
		crit.createAlias("mbrClaim.facilityType", "facilityType", JoinType.LEFT_OUTER_JOIN);
		crit.createAlias("mbrClaim.billType", "billType", JoinType.LEFT_OUTER_JOIN);

		Disjunction or = Restrictions.disjunction();
		Conjunction and = Restrictions.conjunction();

		if (sSearch != null && !"".equals(sSearch)) {
			or.add(Restrictions.ilike("prvdr.name", "%" + sSearch + "%"))
					.add(Restrictions.ilike("firstName", "%" + sSearch + "%"))
					.add(Restrictions.ilike("lastName", "%" + sSearch + "%"))
					.add(Restrictions.ilike("genderId.description", "%" + sSearch + "%"))
					.add(Restrictions.like("genderId.code", new Character(sSearch.toCharArray()[0])));
		}
		if (sSearchIns != null) {

			and.add(Restrictions.eq("mbrInsurance.insId.id", sSearchIns));
		}

		if (sSearchPrvdr != null && sSearchPrvdr != ALL) {
			crit.createAlias("prvdr.refContracts", "refContract");
			crit.createAlias("refContract.ins", "ins");
			and.add(Restrictions.eq("prvdr.id", sSearchPrvdr));
			and.add(Restrictions.eq("ins.id", sSearchIns));
		}

		and.add(Restrictions.eq("activeInd", new Character('Y')));

		if (processClaim == FILTER_BY_PROCESSING_DATE)
			and.add(Restrictions.between("mbrClaim.updatedDate", new java.sql.Date(processingFrom.getTime()),
					new java.sql.Date(processingTo.getTime() + 86400000)));
		if (processClaim == FILTER_BY_HOSPOTALIZATION_DATE) {
			and.add(Restrictions.sqlRestriction(" ? between claim_start_date and claim_end_date",
					new java.sql.Date(processingFrom.getTime()), DateType.INSTANCE));
			and.add(Restrictions.sqlRestriction(" ? between claim_start_date and claim_end_date",
					new java.sql.Date(processingTo.getTime()), DateType.INSTANCE));
		}

		crit.add(or);
		crit.add(and);

		crit.setProjection(Projections.distinct(Projections.property("mbrClaim.id")));
		List<Integer> mbrClaimIds = (List<Integer>) crit.list();
		int totalCount = mbrClaimIds.isEmpty() ? 0 : mbrClaimIds.size();

		if (totalCount == 0) {
			return findByCriteria(crit, pageNo, pageSize);
		} else {
			Criteria criteria = createCriteria().createAlias("mbrClaimList", "mbrClaim", JoinType.INNER_JOIN)
					.add(Restrictions.in("mbrClaim.id", mbrClaimIds));
			
			if (sort != null && sortdir != null && "desc".equals(sortdir)) {
					if ("mbrProviderList.0.prvdr.name".equals(sort)) {
						criteria.addOrder(Order.desc("prvdr.name"));
					} else {
						criteria.addOrder(Order.desc(sort));
					}
			} else {
					if ("mbrProviderList.0.prvdr.name".equals(sort)) {
						criteria.addOrder(Order.asc("prvdr.name"));
					} else {
						criteria.addOrder(Order.asc(sort));
					}
			}
			criteria.addOrder(Order.asc("lastName"));
			criteria.addOrder(Order.asc("firstName"));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			Pagination pagination = findByCriteria(criteria, pageNo, pageSize);
			pagination.setTotalCount(totalCount);
			return pagination;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.MembershipDao#findById(java.lang.Integer)
	 */
	@Override
	public Membership findById(final Integer id) {
		return get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.MembershipDao#save(com.pfchoice.core.entity.
	 * Membership)
	 */
	@Override
	public Membership save(final Membership bean) {
		getSession().save(bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.MembershipDao#loadData()
	 */
	@Override
	public Integer loadDataCSV2Table(String fileName, String tableName) {

		String loadDataQuery = null;
		if (tableName.equals(FILE_TYPE_BH_MBR_ROSTER))
			loadDataQuery = PrasUtil.getInsertQuery(getEntityClass(), QUERY_TYPE_BH_LOAD);
		else if (tableName.equals(FILE_TYPE_AMG_MBR_ROSTER))
			loadDataQuery = PrasUtil.getInsertQuery(getEntityClass(), QUERY_TYPE_LOAD);
		
		return getSession().createSQLQuery(loadDataQuery).setString("file", FILES_UPLOAD_DIRECTORY_PATH + fileName)
				.executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.MembershipDao#loadData()
	 */
	@Override
	public Boolean isDataExists(String tableName) {
		boolean returnvalue = false;
		StringBuilder sql = new StringBuilder();
		if (tableName.equals(FILE_TYPE_BH_MBR_ROSTER))
			sql.append("SELECT count(*) FROM csv2Table_BH_Roster");
		else if (tableName.equals(FILE_TYPE_AMG_MBR_ROSTER))
			sql.append("SELECT count(*) FROM csv2Table_AMG_Roster");
		else if (tableName.equals(FILE_TYPE_AMG_CAP_REPORT))
			sql.append("SELECT count(*) FROM csv2table_cap_report");

		int rowCount = ((BigInteger) getSession().createSQLQuery(sql.toString()).uniqueResult()).intValue();
		if (rowCount > 0) {
			returnvalue = true;
		} else {
			returnvalue = false;
		}

		return returnvalue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.MembershipDao#loadData()
	 */
	@Override
	public Integer loadData(final Integer insId, final Integer fileId, final Integer activityMonth,
			final String tableName) {
		StringBuilder loadDataQuery = new StringBuilder();
		if (tableName.equals(FILE_TYPE_BH_MBR_ROSTER)) {
			loadDataQuery.append(PrasUtil.getInsertQuery(getEntityClass(), QUERY_TYPE_BH_INSERT));
		} else if (tableName.equals(FILE_TYPE_AMG_MBR_ROSTER)) {
			loadDataQuery.append(PrasUtil.getInsertQuery(getEntityClass(), QUERY_TYPE_INSERT));
		}
		return getSession().createSQLQuery(loadDataQuery.toString()).setInteger("insId", insId)
				.setInteger("activityMonth", activityMonth).setInteger("fileId", fileId).executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.MembershipDao#unloadCSV2Table()
	 */
	@Override
	public Integer unloadCSV2Table(String tableName) {
		Session session = getSession();
		int rowsAffected = 0;
		String table = null;

		if (tableName.equals(FILE_TYPE_BH_MBR_ROSTER))
			table = "csv2Table_BH_Roster";
		else if (tableName.equals(FILE_TYPE_AMG_MBR_ROSTER))
			table = "csv2Table_AMG_Roster";

		try {
			rowsAffected = session.createSQLQuery("TRUNCATE TABLE " + table).executeUpdate();
		} catch (Exception e) {
			LOG.warn("exception " + e.getCause());
		}
		return rowsAffected;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ml.rugal.sshcommon.hibernate.HibernateBaseDao#getEntityClass()
	 */
	@Override
	protected Class<Membership> getEntityClass() {
		return Membership.class;
	}
	
	public Pagination getNewHedisMeasure(final int pageNo, final int pageSize, final String sSearch, final int insId,
			final int prvdrId, final String hedisRuleList, final String reportMonth,
			final  Date startDate, final Date endDate, final String roster,
			final String cap, final String userName, final String sort, final String sortdir)
	{
		String loadDataQuery = PrasUtil.getInsertQuery(HedisMeasure.class, QUERY_TYPE_FETCH);
		SQLQuery  queryCount =   (SQLQuery) getSession().createSQLQuery(loadDataQuery).setInteger("insId", insId)
				.setInteger("prvdrId", prvdrId).setString("hedisRuleList", hedisRuleList)
				  .setDate("startDate", startDate).setDate("endDate", endDate) 
				.setString("reportMonth", reportMonth) .setString("roster", roster)
				.setString("cap", cap).setString("userName", userName)
				.setInteger("pageSize", pageSize).setInteger("pageNo", pageNo).setInteger("cnt", 0);
		Pagination pagination = new Pagination(pageNo, pageSize, queryCount.list().size());
	
	
		SQLQuery  query =   (SQLQuery) getSession().createSQLQuery(loadDataQuery).setInteger("insId", insId)
				.setInteger("prvdrId", prvdrId).setString("hedisRuleList", hedisRuleList)
				  .setDate("startDate", startDate).setDate("endDate", endDate) 
				.setString("reportMonth", reportMonth) .setString("roster", roster)
				.setString("cap", cap).setString("userName", userName)
				.setInteger("pageSize", pageSize).setInteger("pageNo", pageNo).setInteger("cnt", 1);
		pagination.setList(query.list());
		return pagination;
	}

	
	public Pagination getMbrNewHedisMeasure(final int pageNo, final int pageSize,  final int mbrId,
			final int ruleId, final String userName, final String sort, final String sortdir)
	{
		String loadDataQuery = PrasUtil.getInsertQuery(MembershipHedisMeasure.class, QUERY_TYPE_FETCH);
		
		SQLQuery  query =   (SQLQuery) getSession().createSQLQuery(loadDataQuery)
				.setInteger("mbrId", mbrId).setInteger("ruleId", ruleId)
				 .setString("userName", userName)
				.setInteger("pageSize", pageSize).setInteger("pageNo", pageNo).setInteger("count", 1);
		
		SQLQuery  queryCount =   (SQLQuery) getSession().createSQLQuery(loadDataQuery)
				.setInteger("mbrId", mbrId).setInteger("ruleId", ruleId)
				 .setString("userName", userName)
				.setInteger("pageSize", pageSize).setInteger("pageNo", pageNo).setInteger("count", 0);
		
		
		Pagination pagination = new Pagination(pageNo, pageSize, queryCount.list().size());
		pagination.setList(query.list());
		return pagination;
	}
}
