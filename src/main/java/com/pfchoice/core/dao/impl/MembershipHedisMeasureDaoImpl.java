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

import com.pfchoice.core.dao.MembershipHedisMeasureDao;
import com.pfchoice.core.entity.MembershipHedisMeasure;

/**
 *
 * @author Sarath
 */
@Repository
public class MembershipHedisMeasureDaoImpl extends HibernateBaseDao<MembershipHedisMeasure, Integer>
		implements MembershipHedisMeasureDao {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(MembershipHedisMeasureDaoImpl.class.getName());

	@Override
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		Criteria crit = createCriteria().createAlias("mbr", "mbr").createAlias("mbr.genderId", "genderId");

		Disjunction or = Restrictions.disjunction();

		if (sSearch != null && !"".equals(sSearch)) {
			or.add(Restrictions.ilike("firstName", "%" + sSearch + "%"))
					.add(Restrictions.ilike("lastName", "%" + sSearch + "%"))
					.add(Restrictions.ilike("genderId.description", "%" + sSearch + "%")).add(Restrictions
							.sqlRestriction("CAST(mbr_dob AS CHAR) like ?", "%" + sSearch + "%", StringType.INSTANCE));
		}
		crit.add(or);
		crit.add(Restrictions.eq("activeInd", 'Y'));
		if (sort != null && !"".equals(sort)) {
			if (sortdir != null && !"".equals(sortdir) && "desc".equals(sortdir)) {
				crit.addOrder(Order.desc(sort));
			} else {
				crit.addOrder(Order.asc(sort));
			}
		}

		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}

	@Override
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final Integer sSearchIns,
			final Integer sSearchPrvdr, final Integer sSearchHedisCode, final String sort, final String sortdir) {
		Criteria crit = createCriteria().createAlias("mbr", "mbr").createAlias("mbr.genderId", "genderId");

		Disjunction or = Restrictions.disjunction();
		Conjunction and = Restrictions.conjunction();

		if (sSearch != null && !"".equals(sSearch)) {
			or.add(Restrictions.ilike("mbr.firstName", "%" + sSearch + "%"))
					.add(Restrictions.ilike("mbr.lastName", "%" + sSearch + "%"))
					.add(Restrictions.ilike("genderId.description", "%" + sSearch + "%")).add(Restrictions
							.sqlRestriction("CAST(mbr_dob AS CHAR) like ?", "%" + sSearch + "%", StringType.INSTANCE));
		}
		if (sSearchIns != null && !"".equals(sSearchIns)) {
			crit.createAlias("mbr.mbrInsuranceList", "mbrInsurance", JoinType.INNER_JOIN);
			and.add(Restrictions.eq("mbrInsurance.insId.id", sSearchIns));
		}

		if (sSearchPrvdr != null && !"".equals(sSearchPrvdr) && sSearchPrvdr != 9999) {
			crit.createAlias("mbr.mbrProviderList", "mbrProvider", JoinType.INNER_JOIN);
			and.add(Restrictions.eq("mbrProvider.prvdr.id", sSearchPrvdr));
		}

		if (sSearchHedisCode != null && !"".equals(sSearchHedisCode) && sSearchHedisCode != 9999) {
			crit.createAlias("hedisMeasureRule", "mbrHedisMeasureRule", JoinType.INNER_JOIN);
			or.add(Restrictions.eq("mbrHedisMeasureRule.id", sSearchHedisCode));
		}

		crit.add(or);
		crit.add(and);
		crit.add(Restrictions.eq("activeInd", 'Y'));
		if (sort != null && !"".equals(sort)) {
			if (sortdir != null && !"".equals(sortdir) && "desc".equals(sortdir)) {
				crit.addOrder(Order.desc(sort));
			} else {
				crit.addOrder(Order.asc(sort));
			}
		}

		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}

	@Override
	public MembershipHedisMeasure findById(final Integer id) {
		MembershipHedisMeasure entity = get(id);
		return entity;
	}

	@Override
	public MembershipHedisMeasure save(final MembershipHedisMeasure bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public MembershipHedisMeasure deleteById(final Integer id) {
		// throw new UnsupportedOperationException();
		MembershipHedisMeasure entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<MembershipHedisMeasure> getEntityClass() {
		return MembershipHedisMeasure.class;
	}

	@Override
	public Pagination findByMbrIdAndRuleId(final Integer mbrId, final Integer ruleId) {
		Criteria cr = getSession().createCriteria(getEntityClass(), "mbrHedisMeasure");
		cr.createAlias("mbrHedisMeasure.mbr", "mbr");
		cr.createAlias("mbrHedisMeasure.hedisMeasureRule", "hedisMeasureRule");
		cr.add(Restrictions.eq("mbr.id", mbrId));
		cr.add(Restrictions.eq("hedisMeasureRule.id", ruleId));
		cr.add(Restrictions.eq("activeInd", new Character('Y')));

		Pagination page = findByCriteria(cr, 0, 1);
		return page;
	}
}
