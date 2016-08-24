package com.pfchoice.core.dao.impl;

import static com.pfchoice.common.SystemDefaultProperties.ALL;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.ProblemDao;
import com.pfchoice.core.entity.Problem;

/**
 *
 * @author Mohanasundharam
 */
@Repository
public class ProblemDaoImpl extends HibernateBaseDao<Problem, Integer> implements ProblemDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.ProblemDao#getPage(int, int)
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
	 * @see com.pfchoice.core.dao.ProblemDao#getPage(int, int, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir, final Integer insId, final Integer effYear) {

		Criteria crit = createCriteria();
		crit.createAlias("insId", "insId");
		
		if (sSearch != null && !"".equals(sSearch)) {
			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.ilike("description", sSearch, MatchMode.ANYWHERE));
			crit.add(or);
		}

		if (insId != null && insId != ALL) {
			crit.add(Restrictions.eq("insId.id", insId));
		}

		if (effYear != null) {
			crit.add(Restrictions.eq("effectiveYear", effYear));
		}

		crit.add(Restrictions.eq("activeInd", 'Y'));

		if (sort != null && !"".equals(sort)) {
			if (sortdir != null && !"".equals(sortdir) && "desc".equals(sortdir)) {
				crit.addOrder(Order.desc(sort));
			} else {
				crit.addOrder(Order.asc(sort));
			}
		}
		else{
			crit.addOrder(Order.asc("description"));
		}
		
		 return findByCriteria(crit, pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.ProblemDao#findById(java.lang.Integer)
	 */
	@Override
	public Problem findById(final Integer id) {
		return get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.dao.ProblemDao#save(com.pfchoice.core.entity.Problem)
	 */
	@Override
	public Problem save(final Problem bean) {
		getSession().save(bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.ProblemDao#deleteById(java.lang.Integer)
	 */
	@Override
	public Problem deleteById(final Integer id) {
		Problem entity = super.get(id);
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
	protected Class<Problem> getEntityClass() {
		return Problem.class;
	}
}
