package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.FrequencyTypeDao;
import com.pfchoice.core.entity.FrequencyType;

/**
 *
 * @author Sarath
 */
@Repository
public class FrequencyTypeDaoImpl extends HibernateBaseDao<FrequencyType, Integer> implements FrequencyTypeDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.FrequencyTypeDao#getPage(int, int)
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
	 * @see com.pfchoice.core.dao.FrequencyTypeDao#getPage(int, int,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		Criteria crit = createCriteria();
		if (sSearch != null && !"".equals(sSearch)) {
			Disjunction or = Restrictions.disjunction();

			or.add(Restrictions.ilike("shortName", "%" + sSearch + "%"))
					.add(Restrictions.ilike("description", "%" + sSearch + "%"))
					.add(Restrictions
							.sqlRestriction("CAST(noOfDays AS CHAR) like ?", "%" + sSearch + "%", StringType.INSTANCE));
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
	 * @see com.pfchoice.core.dao.FrequencyTypeDao#findById(java.lang.Integer)
	 */
	@Override
	public FrequencyType findById(final Integer id) {
		return get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.dao.FrequencyTypeDao#save(com.pfchoice.core.entity.
	 * FrequencyType)
	 */
	@Override
	public FrequencyType save(final FrequencyType bean) {
		getSession().save(bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.FrequencyTypeDao#deleteById(java.lang.Integer)
	 */
	@Override
	public FrequencyType deleteById(final Integer id) {
		FrequencyType entity = super.get(id);
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
	protected Class<FrequencyType> getEntityClass() {
		return FrequencyType.class;
	}

	@Override
	public FrequencyType findByDescription(final String description) {
		return findUniqueByProperty("description", description);
	}
}
