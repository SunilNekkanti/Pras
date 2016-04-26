package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.pfchoice.core.dao.MembershipStatusDao;
import com.pfchoice.core.entity.MembershipStatus;

/**
 *
 * @author Mohanasundharam
 */
@Repository
public class MembershipStatusDaoImpl extends HibernateBaseDao<MembershipStatus, Byte> implements MembershipStatusDao {

	@Override
	public Pagination getPage(final int pageNo, final int pageSize) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("activeInd", 'Y'));
		return findByCriteria(crit, pageNo, pageSize);
	}

	@Override
	public MembershipStatus findById(final Byte id) {
		return get(id);
	}

	@Override
	public MembershipStatus save(final MembershipStatus bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public MembershipStatus deleteById(final Byte id) {
		MembershipStatus entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<MembershipStatus> getEntityClass() {
		return MembershipStatus.class;
	}

}
