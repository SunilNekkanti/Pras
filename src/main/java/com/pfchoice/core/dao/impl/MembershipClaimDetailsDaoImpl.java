package com.pfchoice.core.dao.impl;

import static com.pfchoice.common.SystemDefaultProperties.ALL;
import static com.pfchoice.common.SystemDefaultProperties.FILE_TYPE_AMG_MBR_CLAIM;
import static com.pfchoice.common.SystemDefaultProperties.FILE_TYPE_BH_MBR_CLAIM;
import static com.pfchoice.common.SystemDefaultProperties.ACCEPTABLE_CLAIM;
import static com.pfchoice.common.SystemDefaultProperties.UNACCEPTABLE_CLAIM;
import static com.pfchoice.common.SystemDefaultProperties.QUERY_TYPE_BH_INSERT;
import static com.pfchoice.common.SystemDefaultProperties.QUERY_TYPE_INSERT;

import java.util.List;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
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
		if (tableName == FILE_TYPE_BH_MBR_CLAIM)
			loadDataQuery = PrasUtil.getInsertQuery(getEntityClass(), QUERY_TYPE_BH_INSERT);
		else if (tableName == FILE_TYPE_AMG_MBR_CLAIM)
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

	@SuppressWarnings("unchecked")
	@Override
	public Pagination getMbrClaimDetailsByActivityMonth(final int pageNo, final int pageSize, final String sSearch,
			final Integer sSearchIns, final Integer sSearchPrvdr, final String sort, final String sortdir,
			final List<Integer> monthPicker, final Integer processedClaim) {

		Criteria crit = createCriteria().createAlias("mbrClaim", "mbrClaim");
		crit.createAlias("mbrClaim.mbr", "mbr");
		crit.createAlias("mbrClaim.prvdr", "mbrClaimprvdr");
		crit.createAlias("mbrClaim.ins", "mbrClaimins");
		crit.createAlias("cpt", "cpt");
		crit.createAlias("mbrClaim.frequencyType", "frequency", JoinType.LEFT_OUTER_JOIN);
		crit.createAlias("mbrClaim.facilityType", "facilityType", JoinType.LEFT_OUTER_JOIN);
		crit.createAlias("mbrClaim.billType", "billType", JoinType.LEFT_OUTER_JOIN);
		crit.createAlias("mbr.mbrActivityMonthList", "mbrActivityMonth", JoinType.LEFT_OUTER_JOIN,
				Restrictions.eqProperty(crit.getAlias() + ".activityMonth", "mbrActivityMonth.activityMonth"));

		Disjunction or = Restrictions.disjunction();

		if (sSearch != null && !"".equals(sSearch)) {
			or.add(Restrictions.ilike("mbrClaimprvdr.name", "%" + sSearch + "%"))
					.add(Restrictions.ilike("mbr.firstName", "%" + sSearch + "%"))
					.add(Restrictions.ilike("mbr.lastName", "%" + sSearch + "%"));
		}
		if (sSearchIns != null) {
			crit.add(Restrictions.eq("mbrClaimins.id", sSearchIns));
		}
		if (sSearchPrvdr != null && sSearchPrvdr != ALL) {
			crit.add(Restrictions.eq("mbrClaimprvdr.id", sSearchPrvdr));
		}

		crit.add(Restrictions.eq("activeInd", new Character('Y')));
		crit.add(Restrictions.in("activityMonth", monthPicker));

		if (processedClaim == ACCEPTABLE_CLAIM) {
			crit.add(Restrictions.isNotNull("mbrActivityMonth.activityMonth"));
		} else if (processedClaim == UNACCEPTABLE_CLAIM) {
			crit.add(Restrictions.isNull("mbrActivityMonth.activityMonth"));
		}

		crit.add(or);

		if (sort != null && !"".equals(sort)) {
			if (sortdir != null && !"".equals(sortdir) && "desc".equals(sortdir)) {
				if ("mbrClaimprvdr.name".equals(sort)) {
					crit.addOrder(Order.desc("mbrClaimprvdr.name"));
				} else {
					crit.addOrder(Order.desc(sort));
				}
			} else {
				if ("mbrClaimprvdr.name".equals(sort)) {
					crit.addOrder(Order.asc("mbrClaimprvdr.name"));
				} else {
					crit.addOrder(Order.asc(sort));
				}
			}
		}
		crit.setProjection(Projections.distinct(Projections.property("id")));
		List<Integer> MbrClaimDetailIds = (List<Integer>) crit.list();
		int totalCount = (MbrClaimDetailIds.isEmpty()) ? 0 : MbrClaimDetailIds.size();

		if (totalCount == 0) {
			return findByCriteria(crit, pageNo, pageSize);
		} else {
			Criteria criteria = createCriteria()
					.createAlias("mbrClaim", "mbrClaim")
					.createAlias("mbrClaim.mbr", "mbr")
					.createAlias("cpt", "cpt")
					.createAlias("mbrClaim.prvdr", "prvdr")
					.createAlias("mbrClaim.ins", "ins")
					.createAlias("mbrClaim.frequencyType", "frequency", JoinType.LEFT_OUTER_JOIN)
					.createAlias("mbrClaim.facilityType", "facilityType", JoinType.LEFT_OUTER_JOIN)
					.createAlias("mbrClaim.billType", "billType", JoinType.LEFT_OUTER_JOIN);
			
			criteria.add(Restrictions.in("id", MbrClaimDetailIds));
			
			criteria.addOrder(Order.asc("mbr.lastName"))
					.addOrder(Order.asc("mbr.firstName"))
					.addOrder(Order.asc("mbrClaim.claimNumber"))
					.addOrder(Order.asc("claimLineseqNbr"));
			
			Pagination pagination = findByCriteria(criteria, pageNo, pageSize);
			pagination.setTotalCount(totalCount);

			return pagination;
		}
	}

}
