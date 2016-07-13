package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.PlanTypeDao;
import com.pfchoice.core.entity.PlanType;

/**
 *
 * @author Mohanasundharam
 */
@Repository
public class PlanTypeDaoImpl extends HibernateBaseDao<PlanType, Integer> implements PlanTypeDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.PlanTypeDao#getPage(int, int)
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
	 * @see com.pfchoice.core.dao.PlanTypeDao#getPage(int, int,
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
	 * @see com.pfchoice.core.dao.PlanTypeDao#findById(java.lang.Integer)
	 */
	@Override
	public PlanType findById(final Integer id) {
		return get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.dao.PlanTypeDao#save(com.pfchoice.core.entity.PlanType)
	 */
	@Override
	public PlanType save(final PlanType bean) {
		getSession().save(bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.PlanTypeDao#deleteById(java.lang.Integer)
	 */
	@Override
	public PlanType deleteById(final Integer id) {
		PlanType entity = super.get(id);
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
	protected Class<PlanType> getEntityClass() {
		return PlanType.class;
	}

}
