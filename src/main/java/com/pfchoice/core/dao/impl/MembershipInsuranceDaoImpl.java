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

	@Override
	public Pagination getPage(final int pageNo, final int pageSize) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("activeInd", 'Y'));
		return findByCriteria(crit, pageNo, pageSize);
	}

	@Override
	public MembershipInsurance findById(final Integer id) {
		return get(id);
	}

	@Override
	public MembershipInsurance save(final MembershipInsurance bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public MembershipInsurance deleteById(final Integer id) {
		MembershipInsurance entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<MembershipInsurance> getEntityClass() {
		return MembershipInsurance.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MembershipInsurance> findAllByMbrId(final Integer id) {
		Criteria cr = getSession().createCriteria(getEntityClass(), "mbrIns").createAlias("mbrIns.mbr", "mbr")
				.add(Restrictions.eq("mbr.id", id)).add(Restrictions.eq("mbrIns.activeInd", 'Y'))
				.add(Restrictions.eq("mbr.activeInd", 'Y'));
		return cr.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public MembershipInsurance findByMbrId(final Integer id) {
		MembershipInsurance dbMembershipInsurance = null;
		Criteria cr = getSession().createCriteria(getEntityClass(), "mbrIns").createAlias("mbrIns.mbr", "mbr")
				.add(Restrictions.eq("mbr.id", id)).add(Restrictions.eq("mbrIns.activeInd", 'Y'))
				.add(Restrictions.eq("mbr.activeInd", 'Y'));
		List<MembershipInsurance> list = cr.list();
		if(!list.isEmpty()) {
			dbMembershipInsurance =  list.get(0);
		}
		 
		return dbMembershipInsurance;
	}
}
