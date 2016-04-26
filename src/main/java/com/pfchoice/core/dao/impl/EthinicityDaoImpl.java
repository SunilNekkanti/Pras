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

	@Override
	public Pagination getPage(final int pageNo, final int pageSize) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("activeInd", 'Y'));
		return findByCriteria(crit, pageNo, pageSize);
	}

	@Override
	public Ethinicity findById(final Byte id) {
		return get(id);
	}

	@Override
	public Ethinicity save(final Ethinicity bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public Ethinicity deleteById(final Byte id) {
		Ethinicity entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<Ethinicity> getEntityClass() {
		return Ethinicity.class;
	}

}
