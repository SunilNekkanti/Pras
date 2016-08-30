package com.pfchoice.core.dao.impl;

import static com.pfchoice.common.SystemDefaultProperties.QUERY_TYPE_INSERT;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pfchoice.common.util.PrasUtil;
import com.pfchoice.core.dao.AttPhysicianDao;
import com.pfchoice.core.entity.AttPhysician;

/**
 *
 * @author Sarath
 */
@Repository
public class AttPhysicianDaoImpl extends HibernateBaseDao<AttPhysician, Integer> implements AttPhysicianDao {

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
		Disjunction or = Restrictions.disjunction();

		if (sSearch != null && !"".equals(sSearch)) {
			or.add(Restrictions.ilike("name", "%" + sSearch + "%"))
					.add(Restrictions.ilike("code", "%" + sSearch + "%"));
		}
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("activeInd", 'Y'));
		crit.add(or);

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
	 * @see com.pfchoice.core.dao.AttPhysicianDao#findById(java.lang.Integer)
	 */
	@Override
	public AttPhysician findById(final Integer id) {
		assert id !=null;
		return get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.AttPhysicianDao#save(com.pfchoice.core.entity.
	 * AttPhysician)
	 */
	@Override
	public AttPhysician save(final AttPhysician bean) {
		assert bean !=null;
		getSession().save(bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.AttPhysicianDao#deleteById(java.lang.Integer)
	 */
	@Override
	public AttPhysician deleteById(final Integer id) {
		assert id !=null;
		AttPhysician entity = super.get(id);
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
	protected Class<AttPhysician> getEntityClass() {
		return AttPhysician.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.AttPhysicianDao#loadData(java.lang.Integer)
	 */
	@Override
	public Integer loadData(final Integer fileId) {
		assert fileId !=null;
		String loadDataQuery = PrasUtil.getInsertQuery(getEntityClass(), QUERY_TYPE_INSERT);

		return getSession().createSQLQuery(loadDataQuery).setInteger("fileId", fileId).executeUpdate();
	}

	/**
	 * @param code
	 * @return
	 */
	@Override
	public AttPhysician findByCode(String code) {
		assert code !=null || !"".equals(code) : "The physician code is empty";; 
		return findUniqueByProperty("code", code);
	}

}
