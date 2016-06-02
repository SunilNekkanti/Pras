package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.MembershipInsuranceDao;
import com.pfchoice.core.entity.MembershipInsurance;

/**
 *
 * @author Sarath
 */
@Repository
public class MembershipInsuranceDaoImpl extends HibernateBaseDao<MembershipInsurance, Integer>
		implements MembershipInsuranceDao {

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
	public MembershipInsurance findById(final Integer id) {
		return get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.MembershipInsuranceDao#save(com.pfchoice.core.
	 * entity.MembershipInsurance)
	 */
	@Override
	public MembershipInsurance save(final MembershipInsurance bean) {
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
	public MembershipInsurance deleteById(final Integer id) {
		MembershipInsurance entity = super.get(id);
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
	protected Class<MembershipInsurance> getEntityClass() {
		return MembershipInsurance.class;
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
	public List<MembershipInsurance> findAllByMbrId(final Integer id) {
		Criteria cr = getSession().createCriteria(getEntityClass(), "mbrIns").createAlias("mbrIns.mbr", "mbr")
				.add(Restrictions.eq("mbr.id", id)).add(Restrictions.eq("mbrIns.activeInd", 'Y'))
				.add(Restrictions.eq("mbr.activeInd", 'Y'));
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
	public MembershipInsurance findByMbrId(final Integer id) {
		MembershipInsurance dbMembershipInsurance = null;
		Criteria cr = getSession().createCriteria(getEntityClass(), "mbrIns").createAlias("mbrIns.mbr", "mbr")
				.add(Restrictions.eq("mbr.id", id)).add(Restrictions.eq("mbrIns.activeInd", 'Y'))
				.add(Restrictions.eq("mbr.activeInd", 'Y'));
		List<MembershipInsurance> list = cr.list();
		if (!list.isEmpty()) {
			dbMembershipInsurance = list.get(0);
		}

		return dbMembershipInsurance;
	}
}
