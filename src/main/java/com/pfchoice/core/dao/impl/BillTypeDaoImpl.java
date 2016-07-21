package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pfchoice.common.util.PrasUtil;
import com.pfchoice.core.dao.BillTypeDao;
import com.pfchoice.core.entity.BillType;

/**
 *
 * @author Sarath
 */
@Repository
public class BillTypeDaoImpl extends HibernateBaseDao<BillType, Integer> implements BillTypeDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.BillTypeDao#getPage(int, int)
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
	 * @see com.pfchoice.core.dao.BillTypeDao#getPage(int, int,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		Disjunction or = Restrictions.disjunction();

		if (sSearch != null && !"".equals(sSearch)) {
			or.add(Restrictions.ilike("description", "%" + sSearch + "%"))
					.add(Restrictions.ilike("shortName", "%" + sSearch + "%"));
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
	 * @see com.pfchoice.core.dao.BillTypeDao#findById(java.lang.Integer)
	 */
	@Override
	public BillType findById(final Integer id) {
		return get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.dao.BillTypeDao#save(com.pfchoice.core.entity.Hospital)
	 */
	@Override
	public BillType save(final BillType bean) {
		getSession().save(bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.BillTypeDao#deleteById(java.lang.Integer)
	 */
	@Override
	public BillType deleteById(final Integer id) {
		BillType entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ml.rugal.sshcommon.hibernate.BillTypeDao#getEntityClass()
	 */
	@Override
	protected Class<BillType> getEntityClass() {
		return BillType.class;
	}

	/**
	 * @param name
	 * @return
	 */
	@Override
	public BillType findByDescription(String billTypeDescription) {
		return findUniqueByProperty("description", billTypeDescription);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.BillTypeDao#loadData(java.lang.Integer)
	 */
	@Override
	public Integer loadData(final Integer fileId, String insuranceCode) {

		String loadDataQuery =  PrasUtil.getInsertQuery(getEntityClass(),insuranceCode+ QUERY_TYPE_INSERT);

		return getSession().createSQLQuery(loadDataQuery)
				// .setInteger("fileId", fileId)
				.executeUpdate();
	}

}
