package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.PlanTypeDao;
import com.pfchoice.core.entity.PlanType;

/**
 *
 * @author Mohanasundharam
 */
@Repository
public class PlanTypeDaoImpl extends HibernateBaseDao<PlanType, Integer> implements PlanTypeDao {

	@Override
	public Pagination getPage(final int pageNo, final int pageSize) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("activeInd", 'Y'));
		return findByCriteria(crit, pageNo, pageSize);
	}

	@Override
	public PlanType findById(final Integer id) {
		return get(id);
	}

	@Override
	public PlanType save(final PlanType bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public PlanType deleteById(final Integer id) {
		PlanType entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<PlanType> getEntityClass() {
		return PlanType.class;
	}

}
