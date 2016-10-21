package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;


import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.ClaimTypeDao;
import com.pfchoice.core.entity.ClaimType;

/**
 *
 * @author Sarath
 */
@Repository
public class ClaimTypeDaoImpl extends HibernateBaseDao<ClaimType, Integer> implements ClaimTypeDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.PlaceOfServiceDao#getPage(int, int)
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
	 * @see com.pfchoice.core.dao.ClaimTypeDao#findById(java.lang.Integer)
	 */
	@Override
	public ClaimType findById(final Integer id) {
		assert id !=null;
		return get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.ClaimTypeDao#save(com.pfchoice.core.entity.
	 * ClaimType)
	 */
	@Override
	public ClaimType save(final ClaimType bean) {
		assert bean !=null;
		getSession().save(bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.ClaimTypeDao#deleteById(java.lang.Integer)
	 */
	@Override
	public ClaimType deleteById(final Integer id) {
		assert id !=null;
		ClaimType entity = super.get(id);
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
	protected Class<ClaimType> getEntityClass() {
		return ClaimType.class;
	}

}
