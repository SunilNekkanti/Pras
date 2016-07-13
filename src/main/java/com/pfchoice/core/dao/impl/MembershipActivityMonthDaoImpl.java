package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.MembershipActivityMonthDao;
import com.pfchoice.core.entity.MembershipActivityMonth;

/**
 *
 * @author Sarath
 */
@Repository
public class MembershipActivityMonthDaoImpl extends HibernateBaseDao<MembershipActivityMonth, Integer>
		implements MembershipActivityMonthDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.MembershipActivityMonthDao#getPage(int, int)
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
	 * @see com.pfchoice.core.dao.MembershipActivityMonthDao#findById(java.lang.
	 * Integer)
	 */
	@Override
	public MembershipActivityMonth findById(final Integer id) {
		return get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.dao.MembershipActivityMonthDao#save(com.pfchoice.core.
	 * entity.MembershipActivityMonth)
	 */
	@Override
	public MembershipActivityMonth save(final MembershipActivityMonth bean) {
		getSession().save(bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.dao.MembershipActivityMonthDao#deleteById(java.lang.
	 * Integer)
	 */
	@Override
	public MembershipActivityMonth deleteById(final Integer id) {
		MembershipActivityMonth entity = super.get(id);
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
	protected Class<MembershipActivityMonth> getEntityClass() {
		return MembershipActivityMonth.class;
	}

}
