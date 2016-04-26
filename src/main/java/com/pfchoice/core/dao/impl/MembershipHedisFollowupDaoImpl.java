package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.MembershipHedisFollowupDao;
import com.pfchoice.core.entity.MembershipHedisFollowup;

/**
 *
 * @author Mohanasundharam
 */
@Repository
public class MembershipHedisFollowupDaoImpl extends HibernateBaseDao<MembershipHedisFollowup, Integer>
		implements MembershipHedisFollowupDao {

	@Override
	public Pagination getPage(final int pageNo, final int pageSize) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("activeInd", 'Y'));
		return findByCriteria(crit, pageNo, pageSize);
	}

	@Override
	public MembershipHedisFollowup findById(final Integer id) {
		return get(id);
	}

	@Override
	public MembershipHedisFollowup save(final MembershipHedisFollowup bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public MembershipHedisFollowup deleteById(final Integer id) {
		MembershipHedisFollowup entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<MembershipHedisFollowup> getEntityClass() {
		return MembershipHedisFollowup.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MembershipHedisFollowup> findAllByMbrId(final Integer id) {
		Criteria cr = createCriteria();
		cr.add(Restrictions.eq("mbr.id", id));
		cr.add(Restrictions.eq("activeInd", 'Y'));
		cr.addOrder(Order.desc("id"));

		return cr.list();
	}

}
