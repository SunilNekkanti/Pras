package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.EthinicityDao;
import com.pfchoice.core.entity.Ethinicity;

/**
 *
 * @author Mohanasundharam
 */
@Repository
public class EthinicityDaoImpl extends HibernateBaseDao<Ethinicity, Byte> implements EthinicityDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.EthinicityDao#getPage(int, int)
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
	 * @see com.pfchoice.core.dao.EthinicityDao#findById(java.lang.Byte)
	 */
	@Override
	public Ethinicity findById(final Byte id) {
		return get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.EthinicityDao#save(com.pfchoice.core.entity.
	 * Ethinicity)
	 */
	@Override
	public Ethinicity save(final Ethinicity bean) {
		getSession().save(bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.EthinicityDao#deleteById(java.lang.Byte)
	 */
	@Override
	public Ethinicity deleteById(final Byte id) {
		Ethinicity entity = super.get(id);
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
	protected Class<Ethinicity> getEntityClass() {
		return Ethinicity.class;
	}

}
