package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.MembershipFollowupDao;
import com.pfchoice.core.entity.MembershipFollowup;

/**
 *
 * @author Mohanasundharam
 */
@Repository
public class MembershipFollowupDaoImpl extends HibernateBaseDao<MembershipFollowup, Integer>
		implements MembershipFollowupDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.MembershipFollowupDao#getPage(int, int)
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
	 * com.pfchoice.core.dao.MembershipFollowupDao#findById(java.lang.Integer)
	 */
	@Override
	public MembershipFollowup findById(final Integer id) {
		return get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.dao.MembershipFollowupDao#save(com.pfchoice.core.entity
	 * .MembershipFollowup)
	 */
	@Override
	public MembershipFollowup save(final MembershipFollowup bean) {
		getSession().save(bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.dao.MembershipFollowupDao#deleteById(java.lang.Integer)
	 */
	@Override
	public MembershipFollowup deleteById(final Integer id) {
		MembershipFollowup entity = super.get(id);
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
	protected Class<MembershipFollowup> getEntityClass() {
		return MembershipFollowup.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.dao.MembershipFollowupDao#findAllByMbrId(java.lang.
	 * Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MembershipFollowup> findAllByMbrId(final Integer id, final String followupTypeCode) {
		Criteria cr = createCriteria();
		cr.createAlias("followupType", "followupType");
		cr.add(Restrictions.eq("mbr.id", id));
		cr.add(Restrictions.eq("activeInd", 'Y'));
		cr.add(Restrictions.eq("followupType.code", followupTypeCode));
		cr.addOrder(Order.desc("id"));

		return cr.list();
	}

}
