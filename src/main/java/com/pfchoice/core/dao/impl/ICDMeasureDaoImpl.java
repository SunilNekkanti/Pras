package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.Finder;
import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.ICDMeasureDao;
import com.pfchoice.core.entity.ICDMeasure;

/**
 *
 * @author Sarath
 */
@Repository
public class ICDMeasureDaoImpl extends HibernateBaseDao<ICDMeasure, Integer> implements ICDMeasureDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.ICDMeasureDao#getPage(int, int)
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
	 * @see com.pfchoice.core.dao.ICDMeasureDao#getPage(int, int,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		Criteria crit = createCriteria();

		if (sSearch != null && !"".equals(sSearch)) {
			Disjunction or = Restrictions.disjunction();

			or.add(Restrictions.ilike("code", "%" + sSearch + "%"))
					.add(Restrictions.ilike("description", "%" + sSearch + "%"))
					.add(Restrictions.ilike("hcc", "%" + sSearch + "%"))
					.add(Restrictions.ilike("rxhcc", "%" + sSearch + "%"));
			crit.add(or);
		}

		if (sort != null && !"".equals(sort)) {
			if (sortdir != null && !"".equals(sortdir) && "desc".equals(sortdir)) {
				crit.addOrder(Order.desc(sort));
			} else {
				crit.addOrder(Order.asc(sort));
			}
		}

		crit.add(Restrictions.eq("activeInd", 'Y'));
		return findByCriteria(crit, pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.ICDMeasureDao#findById(java.lang.Integer)
	 */
	@Override
	public ICDMeasure findById(final Integer id) {
		return get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.ICDMeasureDao#save(com.pfchoice.core.entity.
	 * ICDMeasure)
	 */
	@Override
	public ICDMeasure save(final ICDMeasure bean) {
		getSession().save(bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.ICDMeasureDao#deleteById(java.lang.Integer)
	 */
	@Override
	public ICDMeasure deleteById(final Integer id) {
		ICDMeasure entity = super.get(id);
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
	protected Class<ICDMeasure> getEntityClass() {
		return ICDMeasure.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.ICDMeasureDao#findByCode(java.lang.String)
	 */
	@Override
	public ICDMeasure findByCode(final String code) {
		return findUniqueByProperty("code", code);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.ICDMeasureDao#findByCodes(java.lang.String[])
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ICDMeasure> findByCodes(final String[] icdCodes) {
		Finder finder = Finder.create("from ICDMeasure icd where  REPLACE(icd.code, '.','')  in (:code)");
		finder.setParamList("code", icdCodes);

		return find(finder);
	}
}
