package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.FollowupTypeDao;
import com.pfchoice.core.entity.FollowupType;

/**
 *
 * @author Sarath
 */
@Repository
public class FollowupTypeDaoImpl extends HibernateBaseDao<FollowupType, Integer>
		implements FollowupTypeDao {

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.HedisMeasureGroupDao#getPage(int, int)
	 */
	@Override
	public Pagination getPage(final int pageNo, final int pageSize) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("activeInd", 'Y'));
		return findByCriteria(crit, pageNo, pageSize);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.HedisMeasureGroupDao#findById(java.lang.Integer)
	 */
	@Override
	public FollowupType findById(final Integer id) {
		return get(id);
	}

	@Override
	public FollowupType save(final FollowupType bean) {
		getSession().save(bean);
		return bean;
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.HedisMeasureGroupDao#deleteById(java.lang.Integer)
	 */
	@Override
	public FollowupType deleteById(final Integer id) {
		FollowupType entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	/* (non-Javadoc)
	 * @see ml.rugal.sshcommon.hibernate.HibernateBaseDao#getEntityClass()
	 */
	@Override
	protected Class<FollowupType> getEntityClass() {
		return FollowupType.class;
	}

	@Override
	public FollowupType findByCode(final String code) {
		return  findUniqueByProperty("code", code);
	}
}
