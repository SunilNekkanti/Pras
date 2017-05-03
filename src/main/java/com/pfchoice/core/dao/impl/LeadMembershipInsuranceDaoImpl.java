package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.LeadMembershipInsuranceDao;
import com.pfchoice.core.entity.LeadMembershipInsurance;

/**
 *
 * @author Sarath
 */
@Repository
public class LeadMembershipInsuranceDaoImpl extends HibernateBaseDao<LeadMembershipInsurance, Integer>
		implements LeadMembershipInsuranceDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.MembershipInsuranceDao#getPage(int, int)
	 */
	@Override
	public Pagination getPage(final int pageNo, final int pageSize) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("activeInd", 'Y'));
		return findByCriteria(crit, pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.dao.MembershipInsuranceDao#findById(java.lang.Integer)
	 */
	@Override
	public LeadMembershipInsurance findById(final Integer id) {
		return get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.MembershipInsuranceDao#save(com.pfchoice.core.
	 * entity.LeadMembershipInsurance)
	 */
	@Override
	public LeadMembershipInsurance save(final LeadMembershipInsurance bean) {
		getSession().save(bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.MembershipInsuranceDao#deleteById(java.lang.
	 * Integer)
	 */
	@Override
	public LeadMembershipInsurance deleteById(final Integer id) {
		LeadMembershipInsurance entity = super.get(id);
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
	protected Class<LeadMembershipInsurance> getEntityClass() {
		return LeadMembershipInsurance.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.dao.MembershipInsuranceDao#findAllByMbrId(java.lang.
	 * Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LeadMembershipInsurance> findAllByMbrId(final Integer id) {
		Criteria cr = getSession().createCriteria(getEntityClass(), "mbrIns")
				.createAlias("mbrIns.leadMbr", "leadMbr", JoinType.INNER_JOIN)
				.add(Restrictions.eq("leadMbr.id", id)).add(Restrictions.eq("mbrIns.activeInd", 'Y'))
				.add(Restrictions.eq("leadMbr.activeInd", 'Y'));
		return cr.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.MembershipInsuranceDao#findByMbrId(java.lang.
	 * Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public LeadMembershipInsurance findByMbrId(final Integer id) {
		LeadMembershipInsurance dbMembershipInsurance = null;
		Criteria cr = getSession().createCriteria(getEntityClass(), "mbrIns")
				.createAlias("mbrIns.leadMbr", "leadMbr", JoinType.INNER_JOIN)
				.add(Restrictions.eq("leadMbr.id", id)).add(Restrictions.eq("mbrIns.activeInd", 'Y'))
				.add(Restrictions.eq("leadMbr.activeInd", 'Y'));
		List<LeadMembershipInsurance> list = cr.list();
		if (!list.isEmpty()) {
			dbMembershipInsurance = list.get(0);
		}

		return dbMembershipInsurance;
	}
}
