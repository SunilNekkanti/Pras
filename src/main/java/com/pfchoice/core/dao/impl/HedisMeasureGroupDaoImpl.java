package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.HedisMeasureGroupDao;
import com.pfchoice.core.entity.HedisMeasureGroup;

/**
 *
 * @author Sarath
 */
@Repository
public class HedisMeasureGroupDaoImpl extends HibernateBaseDao<HedisMeasureGroup, Integer>
		implements HedisMeasureGroupDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.HedisMeasureGroupDao#getPage(int, int)
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
	 * @see com.pfchoice.core.dao.HedisMeasureGroupDao#getPage(int, int,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		Criteria crit = createCriteria();
		if (sSearch != null && !"".equals(sSearch)) {
			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.ilike("code", "%" + sSearch + "%"));
			or.add(Restrictions.ilike("description", "%" + sSearch + "%"));
			crit.add(or);
		}
		crit.add(Restrictions.eq("activeInd", 'Y'));

		if (sSearch != null && !"".equals(sSearch)) {
			Disjunction or = Restrictions.disjunction();
			crit.add(or);
		}

		crit.add(Restrictions.eq("activeInd", new Character('Y')));
		if (sort != null && !"".equals(sort)) {
			if (sortdir != null && !"".equals(sortdir) && "desc".equals(sortdir)) {
				crit.addOrder(Order.desc(sort));
			} else {
				crit.addOrder(Order.asc(sort));
			}
		}

		return findByCriteria(crit, pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.dao.HedisMeasureGroupDao#findById(java.lang.Integer)
	 */
	@Override
	public HedisMeasureGroup findById(final Integer id) {
		return get(id);
	}

	@Override
	public HedisMeasureGroup save(final HedisMeasureGroup bean) {
		getSession().save(bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.dao.HedisMeasureGroupDao#deleteById(java.lang.Integer)
	 */
	@Override
	public HedisMeasureGroup deleteById(final Integer id) {
		HedisMeasureGroup entity = super.get(id);
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
	protected Class<HedisMeasureGroup> getEntityClass() {
		return HedisMeasureGroup.class;
	}

}
