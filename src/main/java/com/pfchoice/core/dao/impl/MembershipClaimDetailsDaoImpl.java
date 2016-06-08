package com.pfchoice.core.dao.impl;

import static com.pfchoice.common.SystemDefaultProperties.FILE_TYPE_AMG_MBR_CLAIM;
import static com.pfchoice.common.SystemDefaultProperties.FILE_TYPE_BH_MBR_CLIAM;
import static com.pfchoice.common.SystemDefaultProperties.QUERY_TYPE_BH_INSERT;
import static com.pfchoice.common.SystemDefaultProperties.QUERY_TYPE_INSERT;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import com.pfchoice.common.util.PrasUtil;
import com.pfchoice.core.dao.MembershipClaimDetailsDao;
import com.pfchoice.core.entity.MembershipClaimDetails;

/**
 *
 * @author Sarath
 */
@Repository
public class MembershipClaimDetailsDaoImpl extends HibernateBaseDao<MembershipClaimDetails, Integer>
		implements MembershipClaimDetailsDao {

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
	 * @see com.pfchoice.core.dao.HospitalDao#findById(java.lang.Integer)
	 */
	@Override
	public MembershipClaimDetails findById(final Integer id) {
		return get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.HospitalDao#save(com.pfchoice.core.entity.
	 * MembershipClaimDetails)
	 */
	@Override
	public MembershipClaimDetails save(final MembershipClaimDetails bean) {
		getSession().save(bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.HospitalDao#deleteById(java.lang.Integer)
	 */
	@Override
	public MembershipClaimDetails deleteById(final Integer id) {
		MembershipClaimDetails entity = super.get(id);
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
	protected Class<MembershipClaimDetails> getEntityClass() {
		return MembershipClaimDetails.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.MembershipClaimDao#loadData()
	 */
	@Override
	public Integer loadData(final Integer fileId, final String tableName) {
		String loadDataQuery = null;
		if(tableName == FILE_TYPE_BH_MBR_CLIAM)
			loadDataQuery = PrasUtil.getInsertQuery(getEntityClass(), QUERY_TYPE_BH_INSERT);
		else if(tableName == FILE_TYPE_AMG_MBR_CLAIM)
			loadDataQuery = PrasUtil.getInsertQuery(getEntityClass(), QUERY_TYPE_INSERT);

		return getSession().createSQLQuery(loadDataQuery).setInteger("fileId", fileId).executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.dao.MembershipClaimDetailsDao#getMbrClaimDetailsPage(
	 * java.lang.Integer)
	 */
	public Pagination getMbrClaimDetailsPage(final Integer mbrClaimId) {

		Criteria crit = createCriteria().createAlias("mbrClaim", "mbrClaim");
		crit.createAlias("cpt", "cpt", JoinType.LEFT_OUTER_JOIN);
		crit.createAlias("roomType", "roomType", JoinType.LEFT_OUTER_JOIN);

		crit.add(Restrictions.eq("mbrClaim.id", mbrClaimId));
		crit.add(Restrictions.eq("activeInd", new Character('Y')));

		crit.addOrder(Order.asc("id"));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return findByCriteria(crit, 0, 12);
	}
}
