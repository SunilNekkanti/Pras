package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;


import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.CountyDao;
import com.pfchoice.core.entity.County;

/**
 *
 * @author Sarath
 */
@Repository
public class CountyDaoImpl extends HibernateBaseDao<County, Integer> implements CountyDao {

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.CountyDao#getPage(int, int)
	 */
	@Override
	public Pagination getPage(final int pageNo, final int pageSize) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("activeInd", 'Y'));

		return findByCriteria(crit, pageNo, pageSize);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.CountyDao#findById(java.lang.Integer)
	 */
	@Override
	public County findById(final Integer id) {
		return get(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.CountyDao#save(com.pfchoice.core.entity.County)
	 */
	@Override
	public County save(final County bean) {
		getSession().save(bean);
		return bean;
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.CountyDao#deleteById(java.lang.Integer)
	 */
	@Override
	public County deleteById(final Integer id) {
		County entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	/* (non-Javadoc)
	 * @see ml.rugal.sshcommon.hibernate.HibernateBaseDao#getEntityClass()
	 */
	@Override
	protected Class<County> getEntityClass() {
		return County.class;
	}

}
