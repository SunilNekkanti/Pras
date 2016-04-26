package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.TrackModelDao;
import com.pfchoice.core.entity.TrackModel;

/**
 *
 * @author Sarath
 */
@Repository
public class TrackModelDaoImpl extends HibernateBaseDao<TrackModel, Integer> implements TrackModelDao {

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.TrackModelDao#getPage(int, int)
	 */
	@Override
	public Pagination getPage(final int pageNo, final int pageSize) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("activeInd", 'Y'));
		return findByCriteria(crit, pageNo, pageSize);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.TrackModelDao#findById(java.lang.Integer)
	 */
	@Override
	public TrackModel findById(final Integer id) {
		return get(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.TrackModelDao#save(com.pfchoice.core.entity.TrackModel)
	 */
	@Override
	public TrackModel save(final TrackModel bean) {
		getSession().save(bean);
		return bean;
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.TrackModelDao#deleteById(java.lang.Integer)
	 */
	@Override
	public TrackModel deleteById(final Integer id) {
		TrackModel entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	/* (non-Javadoc)
	 * @see ml.rugal.sshcommon.hibernate.HibernateBaseDao#getEntityClass()
	 */
	@Override
	protected Class<TrackModel> getEntityClass() {
		return TrackModel.class;
	}

}
