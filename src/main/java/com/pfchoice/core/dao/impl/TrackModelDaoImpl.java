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

	@Override
	public Pagination getPage(final int pageNo, final int pageSize) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("activeInd", 'Y'));
		return findByCriteria(crit, pageNo, pageSize);
	}

	@Override
	public TrackModel findById(final Integer id) {
		return get(id);
	}

	@Override
	public TrackModel save(final TrackModel bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public TrackModel deleteById(final Integer id) {
		TrackModel entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<TrackModel> getEntityClass() {
		return TrackModel.class;
	}

}
