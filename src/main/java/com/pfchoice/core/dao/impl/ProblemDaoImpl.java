package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import org.hibernate.Criteria;
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

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.ProblemDao#getPage(int, int)
	 */
	@Override
	public Pagination getPage(final int pageNo, final int pageSize) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("activeInd", 'Y'));
		return findByCriteria(crit, pageNo, pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.ProblemDao#getPage(int, int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		Criteria crit = createCriteria();
		
		return findByCriteria(crit, pageNo, pageSize);

	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.ProblemDao#findById(java.lang.Integer)
	 */
	@Override
	public Problem findById(final Integer id) {
		return get(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.ProblemDao#save(com.pfchoice.core.entity.Problem)
	 */
	@Override
	public Problem save(final Problem bean) {
		getSession().save(bean);
		return bean;
	}

	/* (non-Javadoc)
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

	/* (non-Javadoc)
	 * @see ml.rugal.sshcommon.hibernate.HibernateBaseDao#getEntityClass()
	 */
	@Override
	protected Class<Problem> getEntityClass() {
		return Problem.class;
	}
}
