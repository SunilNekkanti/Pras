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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.StateDao#getPage(int, int)
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
	 * @see com.pfchoice.core.dao.StateDao#findById(java.lang.Integer)
	 */
	@Override
	public State findById(final Integer id) {
		return get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.StateDao#save(com.pfchoice.core.entity.State)
	 */
	@Override
	public State save(final State bean) {
		getSession().save(bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.StateDao#deleteById(java.lang.Integer)
	 */
	@Override
	public State deleteById(final Integer id) {
		State entity = super.get(id);
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
	protected Class<State> getEntityClass() {
		return State.class;
	}

}
