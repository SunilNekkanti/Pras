package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.PlaceOfServiceDao;
import com.pfchoice.core.entity.PlaceOfService;

/**
 *
 * @author Sarath
 */
@Repository
public class PlaceOfServiceDaoImpl extends HibernateBaseDao<PlaceOfService, Integer> implements PlaceOfServiceDao {

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.PlaceOfServiceDao#getPage(int, int)
	 */
	@Override
	public Pagination getPage(final int pageNo, final int pageSize) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("activeInd", 'Y'));
		return findByCriteria(crit, pageNo, pageSize);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.PlaceOfServiceDao#findById(java.lang.Integer)
	 */
	@Override
	public PlaceOfService findById(final Integer id) {
		return get(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.PlaceOfServiceDao#save(com.pfchoice.core.entity.PlaceOfService)
	 */
	@Override
	public PlaceOfService save(final PlaceOfService bean) {
		getSession().save(bean);
		return bean;
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.PlaceOfServiceDao#deleteById(java.lang.Integer)
	 */
	@Override
	public PlaceOfService deleteById(final Integer id) {
		PlaceOfService entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	/* (non-Javadoc)
	 * @see ml.rugal.sshcommon.hibernate.HibernateBaseDao#getEntityClass()
	 */
	@Override
	protected Class<PlaceOfService> getEntityClass() {
		return PlaceOfService.class;
	}

}
