package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.PlaceOfServiceDao;
import com.pfchoice.core.entity.PlaceOfService;

/**
 *
 * @author Sarath
 */
@Repository
public class PlaceOfServiceDaoImpl extends HibernateBaseDao<PlaceOfService, Integer> implements PlaceOfServiceDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.PlaceOfServiceDao#getPage(int, int)
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
	 * @see com.pfchoice.core.dao.PlaceOfServiceDao#getPage(int, int,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		Criteria crit = createCriteria();
		if (sSearch != null && !"".equals(sSearch)) {
			Disjunction or = Restrictions.disjunction();

			or.add(Restrictions.ilike("code", "%" + sSearch + "%")).add(Restrictions.ilike("name", "%" + sSearch + "%"))
					.add(Restrictions.ilike("description", "%" + sSearch + "%"));
			crit.add(or);
		}
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
	 * @see com.pfchoice.core.dao.PlaceOfServiceDao#findById(java.lang.Integer)
	 */
	@Override
	public PlaceOfService findById(final Integer id) {
		return get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.dao.PlaceOfServiceDao#save(com.pfchoice.core.entity.
	 * PlaceOfService)
	 */
	@Override
	public PlaceOfService save(final PlaceOfService bean) {
		getSession().save(bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.dao.PlaceOfServiceDao#deleteById(java.lang.Integer)
	 */
	@Override
	public PlaceOfService deleteById(final Integer id) {
		PlaceOfService entity = super.get(id);
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
	protected Class<PlaceOfService> getEntityClass() {
		return PlaceOfService.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.PlaceOfServiceDao#findByCode(java.lang.String)
	 */
	@Override
	public PlaceOfService findByCode(final String code) {
		return findUniqueByProperty("code", code);
	}
}
