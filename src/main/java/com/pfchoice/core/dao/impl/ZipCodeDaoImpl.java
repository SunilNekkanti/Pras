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

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.ZipCodeDao#getPage(int, int)
	 */
	@Override
	public Pagination getPage(final int pageNo, final int pageSize) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("activeInd", 'Y'));
		return findByCriteria(crit, pageNo, pageSize);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.ZipCodeDao#findById(java.lang.Integer)
	 */
	@Override
	public ZipCode findById(final Integer id) {
		return get(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.ZipCodeDao#save(com.pfchoice.core.entity.ZipCode)
	 */
	@Override
	public ZipCode save(final ZipCode bean) {
		getSession().save(bean);
		return bean;
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.ZipCodeDao#deleteById(java.lang.Integer)
	 */
	@Override
	public ZipCode deleteById(final Integer id) {
		ZipCode entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	/* (non-Javadoc)
	 * @see ml.rugal.sshcommon.hibernate.HibernateBaseDao#getEntityClass()
	 */
	@Override
	protected Class<ZipCode> getEntityClass() {
		return ZipCode.class;
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.ZipCodeDao#findByStateCode(java.lang.Integer)
	 */
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
