package com.pfchoice.core.dao.impl;

import static com.pfchoice.common.SystemDefaultProperties.QUERY_TYPE_INSERT;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import com.pfchoice.common.util.PrasUtil;
import com.pfchoice.core.dao.LeadMembershipHospitalizationDetailsDao;
import com.pfchoice.core.entity.LeadMembershipHospitalizationDetails;

/**
 *
 * @author Sarath
 */
@Repository
public class LeadMembershipHospitalizationDetailsDaoImpl extends HibernateBaseDao<LeadMembershipHospitalizationDetails, Integer>
		implements LeadMembershipHospitalizationDetailsDao {

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
	public LeadMembershipHospitalizationDetails findById(final Integer id) {
		return get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.HospitalDao#save(com.pfchoice.core.entity.
	 * LeadMembershipHospitalizationDetails)
	 */
	@Override
	public LeadMembershipHospitalizationDetails save(final LeadMembershipHospitalizationDetails bean) {
		getSession().save(bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.HospitalDao#deleteById(java.lang.Integer)
	 */
	@Override
	public LeadMembershipHospitalizationDetails deleteById(final Integer id) {
		LeadMembershipHospitalizationDetails entity = super.get(id);
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
	protected Class<LeadMembershipHospitalizationDetails> getEntityClass() {
		return LeadMembershipHospitalizationDetails.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.MembershipHospitalizationDao#loadData()
	 */
	@Override
	public Integer loadData(final Integer fileId) {
		String loadDataQuery = PrasUtil.getInsertQuery(getEntityClass(), QUERY_TYPE_INSERT);

		return getSession().createSQLQuery(loadDataQuery).setInteger("fileId", fileId).executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.MembershipHospitalizationDetailsDao#
	 * getMbrHospitalizationDetailsPage(java.lang.Integer)
	 */
	public Pagination getMbrHospitalizationDetailsPage(final Integer mbrHosId) {

		Criteria crit = createCriteria().createAlias("mbrHospitalization", "mbrHospitalization");
		crit.createAlias("attPhysician", "attPhysician");
		crit.createAlias("roomType", "roomType", JoinType.LEFT_OUTER_JOIN);

		crit.add(Restrictions.eq("mbrHospitalization.id", mbrHosId));
		crit.add(Restrictions.eq("activeInd", new Character('Y')));

		crit.addOrder(Order.asc("id"));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return findByCriteria(crit, 0, 12);
	}
}
