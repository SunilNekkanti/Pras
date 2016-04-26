package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.CPTMeasureDao;
import com.pfchoice.core.entity.CPTMeasure;

/**
 *
 * @author Sarath
 */
@Repository
public class CPTMeasureDaoImpl extends HibernateBaseDao<CPTMeasure, Integer> implements CPTMeasureDao {

	
	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.CPTMeasureDao#getPage(int, int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {

		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("activeInd", 'Y'));
		Disjunction or = Restrictions.disjunction();

		if (sSearch != null && !"".equals(sSearch)) {
			or.add(Restrictions.ilike("code", "%" + sSearch + "%"))
					.add(Restrictions.ilike("shortDescription", "%" + sSearch + "%"))
					.add(Restrictions.ilike("description", "%" + sSearch + "%"));
		}

		if (sort != null && !"".equals(sort)) {
			if (sortdir != null && !"".equals(sortdir) && "desc".equals(sortdir)) {
				crit.addOrder(Order.desc(sort));
			} else {
				crit.addOrder(Order.asc(sort));
			}
		}

		return findByCriteria(crit, pageNo, pageSize);

	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.CPTMeasureDao#getPage(int, int)
	 */
	@Override
	public Pagination getPage(final int pageNo, final int pageSize) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("activeInd", 'Y'));
		return findByCriteria(crit, pageNo, pageSize);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.CPTMeasureDao#findById(java.lang.Integer)
	 */
	@Override
	public CPTMeasure findById(final Integer id) {
		return get(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.CPTMeasureDao#save(com.pfchoice.core.entity.CPTMeasure)
	 */
	@Override
	public CPTMeasure save(final CPTMeasure bean) {
		getSession().save(bean);
		return bean;
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.CPTMeasureDao#deleteById(java.lang.Integer)
	 */
	@Override
	public CPTMeasure deleteById(final Integer id) {
		CPTMeasure entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	/* (non-Javadoc)
	 * @see ml.rugal.sshcommon.hibernate.HibernateBaseDao#getEntityClass()
	 */
	@Override
	protected Class<CPTMeasure> getEntityClass() {
		return CPTMeasure.class;
	}

}
