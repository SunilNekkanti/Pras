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

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.MembershipStatusDao#getPage(int, int)
	 */
	@Override
	public Pagination getPage(final int pageNo, final int pageSize) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("activeInd", 'Y'));
		return findByCriteria(crit, pageNo, pageSize);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.MembershipStatusDao#findById(java.lang.Byte)
	 */
	@Override
	public MembershipStatus findById(final Byte id) {
		return get(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.MembershipStatusDao#save(com.pfchoice.core.entity.MembershipStatus)
	 */
	@Override
	public MembershipStatus save(final MembershipStatus bean) {
		getSession().save(bean);
		return bean;
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.MembershipStatusDao#deleteById(java.lang.Byte)
	 */
	@Override
	public MembershipStatus deleteById(final Byte id) {
		MembershipStatus entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	/* (non-Javadoc)
	 * @see ml.rugal.sshcommon.hibernate.HibernateBaseDao#getEntityClass()
	 */
	@Override
	protected Class<MembershipStatus> getEntityClass() {
		return MembershipStatus.class;
	}

}
