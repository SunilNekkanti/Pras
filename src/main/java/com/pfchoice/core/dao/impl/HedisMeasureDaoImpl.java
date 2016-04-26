package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.HedisMeasureDao;
import com.pfchoice.core.entity.HedisMeasure;

/**
 *
 * @author Sarath
 */
@Repository
public class HedisMeasureDaoImpl extends HibernateBaseDao<HedisMeasure, Integer> implements HedisMeasureDao {

	@Override
	public Pagination getPage(final int pageNo, final int pageSize) {
		Criteria crit = createCriteria();
		return findByCriteria(crit, pageNo, pageSize);
	}
	
	@Override
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {

		Criteria crit = createCriteria().createAlias("hedisMsrGrp", "hedisMsrGrp");

		if (sSearch != null && !"".equals(sSearch)) {
			Disjunction or = Restrictions.disjunction();

			or.add(Restrictions.ilike("code", "%" + sSearch + "%"))
					.add(Restrictions.ilike("description", "%" + sSearch + "%"))
					.add(Restrictions.ilike("hedisMsrGrp.code", "%" + sSearch + "%"));
			crit.add(or);
		}

		crit.add(Restrictions.eq("activeInd", 'Y'));
		crit.add(Restrictions.eq("hedisMsrGrp.activeInd", 'Y'));

		if (sort != null && !"".equals(sort)) {
			if (sortdir != null && !"".equals(sortdir) && "desc".equals(sortdir)) {
				crit.addOrder(Order.desc(sort));
			} else {
				crit.addOrder(Order.asc(sort));
			}
		}

		return findByCriteria(crit, pageNo, pageSize);

	}

	@Override
	public HedisMeasure findById(final Integer id) {
		return get(id);
	}

	@Override
	public HedisMeasure save(final HedisMeasure bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public HedisMeasure deleteById(final Integer id) {
		HedisMeasure entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<HedisMeasure> getEntityClass() {
		return HedisMeasure.class;
	}

}
