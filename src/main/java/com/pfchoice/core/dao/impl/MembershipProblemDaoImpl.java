package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import static com.pfchoice.common.SystemDefaultProperties.FILE_TYPE_AMG_MBR_CLAIM;
import static com.pfchoice.common.SystemDefaultProperties.FILE_TYPE_BH_MBR_CLAIM;
import static com.pfchoice.common.SystemDefaultProperties.QUERY_TYPE_BH_INSERT;
import static com.pfchoice.common.SystemDefaultProperties.QUERY_TYPE_INSERT;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pfchoice.common.util.PrasUtil;
import com.pfchoice.core.dao.MembershipProblemDao;
import com.pfchoice.core.entity.MembershipProblem;

/**
 *
 * @author Mohanasundharam
 */
@Repository
public class MembershipProblemDaoImpl extends HibernateBaseDao<MembershipProblem, Integer>
		implements MembershipProblemDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.MembershipProblemDao#getPage(int, int)
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
	 * @see com.pfchoice.core.dao.MembershipProblemDao#getPage(int, int,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		Criteria crit = createCriteria();

		return findByCriteria(crit, pageNo, pageSize);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.dao.MembershipProblemDao#findById(java.lang.Integer)
	 */
	@Override
	public MembershipProblem findById(final Integer id) {
		return get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.dao.MembershipProblemDao#save(com.pfchoice.core.entity.
	 * Problem)
	 */
	@Override
	public MembershipProblem save(final MembershipProblem bean) {
		getSession().save(bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.dao.MembershipProblemDao#deleteById(java.lang.Integer)
	 */
	@Override
	public MembershipProblem deleteById(final Integer id) {
		MembershipProblem entity = super.get(id);
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
	protected Class<MembershipProblem> getEntityClass() {
		return MembershipProblem.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.dao.MembershipProblemDao#loadData(java.lang.Integer)
	 */
	@Override
	public Integer loadData(final Integer fileId, final String tableName) {
		String loadDataQuery = null;
		if (tableName == FILE_TYPE_BH_MBR_CLAIM)
			loadDataQuery = PrasUtil.getInsertQuery(getEntityClass(), QUERY_TYPE_BH_INSERT);
		else if (tableName == FILE_TYPE_AMG_MBR_CLAIM)
			loadDataQuery = PrasUtil.getInsertQuery(getEntityClass(), QUERY_TYPE_INSERT);

		return getSession().createSQLQuery(loadDataQuery).setInteger("fileId", fileId).executeUpdate();
	}
}
