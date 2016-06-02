package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.HedisMeasureRuleDao;
import com.pfchoice.core.entity.HedisMeasureRule;

/**
 *
 * @author Sarath
 */
@Repository
public class HedisMeasureRuleDaoImpl extends HibernateBaseDao<HedisMeasureRule, Integer>
		implements HedisMeasureRuleDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.HedisMeasureRuleDao#getPage(int, int,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		Criteria crit = createCriteria().createAlias("hedisMeasure", "hedisMeasure")
				.createAlias("genderId", "genderId", JoinType.LEFT_OUTER_JOIN)
				.createAlias("cptCodes", "cptMeasure", JoinType.INNER_JOIN)
				.createAlias("icdCodes", "icdMeasure", JoinType.INNER_JOIN);

		if (sSearch != null && !"".equals(sSearch)) {
			Disjunction or = Restrictions.disjunction();

			or.add(Restrictions.ilike("hedisMeasure.code", sSearch, MatchMode.ANYWHERE))
					.add(Restrictions.ilike("cptMeasure.code", sSearch, MatchMode.ANYWHERE))
					.add(Restrictions.ilike("icdMeasure.code", sSearch, MatchMode.ANYWHERE))
					.add(Restrictions.sqlRestriction("CAST({alias}.effective_Year AS CHAR) like ?", "%" + sSearch + "%",
							StringType.INSTANCE))
					.add(Restrictions.ilike("description", sSearch, MatchMode.ANYWHERE))
					.add(Restrictions.ilike("genderId.description", sSearch, MatchMode.ANYWHERE))
					.add(Restrictions.sqlRestriction("CAST({alias}.lower_age_limit AS CHAR) like ?",
							"%" + sSearch + "%", StringType.INSTANCE))
					.add(Restrictions.sqlRestriction("CAST({alias}.upper_age_limit AS CHAR) like ?",
							"%" + sSearch + "%", StringType.INSTANCE))
					.add(Restrictions.sqlRestriction("CAST({alias}.age_effective_from AS CHAR) like ?",
							"%" + sSearch + "%", StringType.INSTANCE))
					.add(Restrictions.sqlRestriction("CAST({alias}.age_effective_to AS CHAR) like ?",
							"%" + sSearch + "%", StringType.INSTANCE));

			crit.add(or);
		}
		crit.add(Restrictions.eq("activeInd", 'Y'));
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.property("id"), "id");
		projList.add(Projections.property("description"), "description");
		projList.add(Projections.property("doseCount"), "doseCount");
		projList.add(Projections.property("lowerAgeLimit"), "lowerAgeLimit");
		projList.add(Projections.property("upperAgeLimit"), "upperAgeLimit");
		projList.add(Projections.property("ageEffectiveFrom"), "ageEffectiveFrom");
		projList.add(Projections.property("ageEffectiveTo"), "ageEffectiveTo");
		projList.add(Projections.property("effectiveYear"), "effectiveYear");
		projList.add(Projections.property("hedisMeasure.code"), "hedisMeasureCode");
		projList.add(Projections.property("cptMeasure.code"), "cptMeasureCode");
		projList.add(Projections.property("icdMeasure.code"), "icdMeasureCode");
		projList.add(Projections.property("genderId.description"), "genderDescription");

		crit.setProjection(Projections.distinct(projList));

		if (sort != null && !"".equals(sort)) {
			if (sortdir != null && !"".equals(sortdir) && "desc".equals(sortdir)) {
				crit.addOrder(Order.desc(sort));
			} else {
				crit.addOrder(Order.asc(sort));
			}
		}
		crit.addOrder(Order.asc("hedisMeasure.code"));
		crit.addOrder(Order.asc("cptMeasure.code"));
		crit.addOrder(Order.asc("icdMeasure.code"));
		crit.setResultTransformer(Transformers.aliasToBean(getEntityClass()));
		return findByCriteria(crit, pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.HedisMeasureRuleDao#getPage(int, int,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.Integer,
	 * java.lang.Integer)
	 */
	@Override
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir, final Integer insId, final Integer effYear) {
		Criteria crit = createCriteria().createAlias("hedisMeasure", "hedisMeasure")
				.createAlias("pbm", "pbm", JoinType.LEFT_OUTER_JOIN)
				.createAlias("frequencyType", "frequencyType", JoinType.LEFT_OUTER_JOIN)
				.createAlias("genderId", "genderId", JoinType.LEFT_OUTER_JOIN);

		Conjunction and = Restrictions.conjunction();
		if (sSearch != null && !"".equals(sSearch)) {
			Disjunction or = Restrictions.disjunction();

			or.add(Restrictions.ilike("hedisMeasure.code", sSearch, MatchMode.ANYWHERE))
					.add(Restrictions.sqlRestriction("CAST({alias}.effective_Year AS CHAR) like ?", "%" + sSearch + "%",
							StringType.INSTANCE))
					.add(Restrictions.ilike("description", sSearch, MatchMode.ANYWHERE))
					.add(Restrictions.ilike("genderId.description", sSearch, MatchMode.ANYWHERE))
					.add(Restrictions.sqlRestriction("CAST({alias}.lower_age_limit AS CHAR) like ?",
							"%" + sSearch + "%", StringType.INSTANCE))
					.add(Restrictions.sqlRestriction("CAST({alias}.upper_age_limit AS CHAR) like ?",
							"%" + sSearch + "%", StringType.INSTANCE))
					.add(Restrictions.sqlRestriction("CAST({alias}.age_effective_from AS CHAR) like ?",
							"%" + sSearch + "%", StringType.INSTANCE))
					.add(Restrictions.sqlRestriction("CAST({alias}.age_effective_to AS CHAR) like ?",
							"%" + sSearch + "%", StringType.INSTANCE));

			crit.add(or);
		}

		if (insId != null) {
			crit.createAlias("insId", "insId");
			and.add(Restrictions.eq("insId.id", insId));
		}

		if (effYear != null) {
			and.add(Restrictions.eq("effectiveYear", effYear));
		}
		crit.add(and);

		crit.add(Restrictions.eq("activeInd", 'Y'));

		if (sort != null && !"".equals(sort)) {
			if (sortdir != null && !"".equals(sortdir) && "desc".equals(sortdir)) {
				crit.addOrder(Order.desc(sort));
			} else {
				crit.addOrder(Order.asc(sort));
			}
		}
		return findByCriteria(crit, pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.dao.HedisMeasureRuleDao#findById(java.lang.Integer)
	 */
	@Override
	public HedisMeasureRule findById(final Integer id) {
		return get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.dao.HedisMeasureRuleDao#save(com.pfchoice.core.entity.
	 * HedisMeasureRule)
	 */
	@Override
	public HedisMeasureRule save(final HedisMeasureRule bean) {
		getSession().save(bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.dao.HedisMeasureRuleDao#deleteById(java.lang.Integer)
	 */
	@Override
	public HedisMeasureRule deleteById(final Integer id) {
		HedisMeasureRule entity = super.get(id);
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
	protected Class<HedisMeasureRule> getEntityClass() {
		return HedisMeasureRule.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.HedisMeasureRuleDao#findAllByInsId(java.lang.
	 * Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<HedisMeasureRule> findAllByInsId(final Integer insId) {

		Criteria cr = createCriteria();
		cr.createAlias("insId", "ins");
		cr.add(Restrictions.eq("activeInd", 'Y'));
		cr.add(Restrictions.eq("ins.activeInd", 'Y'));
		cr.add(Restrictions.eq("ins.id", insId));

		cr.setProjection(Projections.distinct(Projections.projectionList()
				.add(Projections.property("description"), "description").add(Projections.property("id"), "id")))
				.setResultTransformer(Transformers.aliasToBean(getEntityClass()));

		return cr.list();
	}
}
