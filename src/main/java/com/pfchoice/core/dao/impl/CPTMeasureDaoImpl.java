package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.CPTMeasureDao;
import com.pfchoice.core.entity.CPTMeasure;

/**
 *
 * @author Sarath
 */
@Repository
public class CPTMeasureDaoImpl extends HibernateBaseDao<CPTMeasure, Integer> implements CPTMeasureDao {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(CPTMeasureDaoImpl.class.getName());

	@Override
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {

		Criteria crit = createCriteria();

		Disjunction or = Restrictions.disjunction();

		if (sSearch != null && !"".equals(sSearch)) {
			or.add(Restrictions.ilike("code", "%" + sSearch + "%"))
					.add(Restrictions.ilike("shortDescription", "%" + sSearch + "%"))
					.add(Restrictions.ilike("description", "%" + sSearch + "%"));
		}
		crit.add(or).add(Restrictions.eq("activeInd", 'Y'));

		if (sort != null && !"".equals(sort)) {
			if (sortdir != null && !"".equals(sortdir) && "desc".equals(sortdir)) {
				crit.addOrder(Order.desc(sort));
			} else {
				crit.addOrder(Order.asc(sort));
			}
		}

		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;

	}

	public Pagination getPage(final int pageNo, final int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}

	@Override
	public CPTMeasure findById(final Integer id) {
		CPTMeasure entity = get(id);
		return entity;
	}

	@Override
	public CPTMeasure save(final CPTMeasure bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public CPTMeasure deleteById(final Integer id) {
		// throw new UnsupportedOperationException();
		CPTMeasure entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<CPTMeasure> getEntityClass() {
		return CPTMeasure.class;
	}

	@SuppressWarnings("unchecked")
	public List<CPTMeasure> findAll() {
		Criteria cr = createCriteria();
		cr.addOrder(Order.asc("code")).add(Restrictions.eq("activeInd", 'Y'));
		cr.setProjection(Projections.distinct(Projections.projectionList().add(Projections.property("code"), "code")
				.add(Projections.property("id"), "id")))
				.setResultTransformer(Transformers.aliasToBean(getEntityClass()));
		List<CPTMeasure> list = cr.list();
		return list;
	}

}
