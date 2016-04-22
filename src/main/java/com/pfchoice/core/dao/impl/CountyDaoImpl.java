package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.CountyDao;
import com.pfchoice.core.entity.County;

/**
 *
 * @author Sarath
 */
@Repository
public class CountyDaoImpl extends HibernateBaseDao<County, Integer> implements CountyDao {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(CountyDaoImpl.class.getName());

	@Override
	public Pagination getPage(final int pageNo, final int pageSize) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("activeInd", 'Y'));
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}

	@Override
	public County findById(final Integer id) {
		County entity = get(id);
		return entity;
	}

	@Override
	public County save(final County bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public County deleteById(final Integer id) {
		// throw new UnsupportedOperationException();
		County entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<County> getEntityClass() {
		return County.class;
	}

	@SuppressWarnings("unchecked")
	public List<County> findAll() {
		Criteria cr = createCriteria();
		cr.add(Restrictions.eq("activeInd", 'Y'));
		cr.addOrder(Order.asc("description"));
		List<County> list = cr.list();
		return list;
	}

}
