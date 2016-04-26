package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.ZipCodeDao;
import com.pfchoice.core.entity.ZipCode;

/**
 *
 * @author Sarath
 */
@Repository
public class ZipCodeDaoImpl extends HibernateBaseDao<ZipCode, Integer> implements ZipCodeDao {

	@Override
	public Pagination getPage(final int pageNo, final int pageSize) {
		Criteria crit = createCriteria();

		return findByCriteria(crit, pageNo, pageSize);
	}

	@Override
	public ZipCode findById(final Integer id) {
		return get(id);
	}

	@Override
	public ZipCode save(final ZipCode bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public ZipCode deleteById(final Integer id) {
		ZipCode entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<ZipCode> getEntityClass() {
		return ZipCode.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ZipCode> findByStateCode(Integer stateCode) {
		Criteria cr = createCriteria();
		cr.createAlias("stateCode", "stateCode");
		cr.add(Restrictions.eq("activeInd", 'Y'));
		cr.add(Restrictions.eq("stateCode.code", stateCode));

		return cr.list();
	}

}
