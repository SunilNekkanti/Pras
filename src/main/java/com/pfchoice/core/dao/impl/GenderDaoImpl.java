package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;


import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.GenderDao;
import com.pfchoice.core.entity.Gender;

/**
 *
 * @author Sarath
 */
@Repository
public class GenderDaoImpl extends HibernateBaseDao<Gender, Byte> implements GenderDao {

	@Override
	public Pagination getPage(final int pageNo, final int pageSize) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("activeInd", 'Y'));
		return findByCriteria(crit, pageNo, pageSize);
	}

	@Override
	public Gender findById(final Byte id) {
		return get(id);
	}

	@Override
	public Gender save(final Gender bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public Gender deleteById(final Byte id) {
		Gender entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<Gender> getEntityClass() {
		return Gender.class;
	}

}
