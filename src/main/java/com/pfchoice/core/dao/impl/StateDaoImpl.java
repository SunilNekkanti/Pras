package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.StateDao;
import com.pfchoice.core.entity.State;

/**
 *
 * @author Sarath
 */
@Repository
public class StateDaoImpl extends HibernateBaseDao<State, Integer> implements StateDao {

	@Override
	public Pagination getPage(final int pageNo, final int pageSize) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("activeInd", 'Y'));
		return findByCriteria(crit, pageNo, pageSize);
	}

	@Override
	public State findById(final Integer id) {
		return get(id);
	}

	@Override
	public State save(final State bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public State deleteById(final Integer id) {
		State entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<State> getEntityClass() {
		return State.class;
	}

}
