package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.InsuranceDao;
import com.pfchoice.core.entity.Insurance;

/**
 *
 * @author Mohanasundharam
 */
@Repository
public class InsuranceDaoImpl extends HibernateBaseDao<Insurance, Integer> implements InsuranceDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.InsuranceDao#getPage(int, int)
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
	 * @see com.pfchoice.core.dao.InsuranceDao#getPage(int, int,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		Disjunction or = Restrictions.disjunction();

		if (sSearch != null && !"".equals(sSearch)) {
			Criterion name = Restrictions.ilike("name", "%" + sSearch + "%");
			or.add(name);
		}
		Criteria crit = createCriteria();
		crit.createAlias("planTypeId", "planTypeId");
		crit.add(or);
		crit.add(Restrictions.eq("activeInd", 'Y'));

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
	 * @see com.pfchoice.core.dao.InsuranceDao#findById(java.lang.Integer)
	 */
	@Override
	public Insurance findById(final Integer id) {
		return get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.InsuranceDao#save(com.pfchoice.core.entity.
	 * Insurance)
	 */
	@Override
	public Insurance save(final Insurance bean) {
		getSession().save(bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.InsuranceDao#deleteById(java.lang.Integer)
	 */
	@Override
	public Insurance deleteById(final Integer id) {
		Insurance entity = super.get(id);
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
	protected Class<Insurance> getEntityClass() {
		return Insurance.class;
	}

	/**
	 * @param insName
	 * @return
	 */
	@Override
	public Insurance findByInsName(String insName) {
		return findUniqueByProperty("name", insName);
	}

}
