package com.pfchoice.core.dao.impl;

import static com.pfchoice.common.SystemDefaultProperties.ALL;
import static com.pfchoice.common.SystemDefaultProperties.FILES_UPLOAD_DIRECTORY_PATH;
import static com.pfchoice.common.SystemDefaultProperties.QUERY_TYPE_INSERT;
import static com.pfchoice.common.SystemDefaultProperties.QUERY_TYPE_LOAD;
import static com.pfchoice.common.SystemDefaultProperties.QUERY_TYPE_UPDATE;
import static com.pfchoice.common.SystemDefaultProperties.FILTER_BY_HOSPOTALIZATION_DATE;
import static com.pfchoice.common.SystemDefaultProperties.FILTER_BY_PROCESSING_DATE;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.math.BigInteger;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.type.DateType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pfchoice.common.util.PrasUtil;
import com.pfchoice.core.dao.MembershipClaimDao;
import com.pfchoice.core.entity.MembershipClaim;

/**
 *
 * @author Sarath
 */
@Repository
public class MembershipClaimDaoImpl extends HibernateBaseDao<MembershipClaim, Integer> implements MembershipClaimDao {

	private static final Logger LOG = LoggerFactory.getLogger(MembershipClaimDaoImpl.class);

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
	public MembershipClaim findById(final Integer id) {
		return get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.HospitalDao#save(com.pfchoice.core.entity.
	 * MembershipClaim)
	 */
	@Override
	public MembershipClaim save(final MembershipClaim bean) {
		getSession().save(bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.HospitalDao#deleteById(java.lang.Integer)
	 */
	@Override
	public MembershipClaim deleteById(final Integer id) {
		MembershipClaim entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.MembershipDao#getPage(int, int,
	 * java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Pagination getClaimPage(final int pageNo, final int pageSize, final String sSearch, final Integer sSearchIns,
			final Integer sSearchPrvdr, final String sort, final String sortdir, final Date processingFrom,
			final Date processingTo, final Integer processClaim) {

		Criteria crit = createCriteria().createAlias("mbr", "mbr").createAlias("mbr.genderId", "genderId")
				.createAlias("prvdr", "prvdr").createAlias("ins", "ins");

		crit.createAlias("frequencyType", "frequency", JoinType.LEFT_OUTER_JOIN);
		crit.createAlias("facilityType", "facilityType", JoinType.LEFT_OUTER_JOIN);
		crit.createAlias("billType", "billType", JoinType.LEFT_OUTER_JOIN);

		Disjunction or = Restrictions.disjunction();
		Conjunction and = Restrictions.conjunction();

		if (sSearch != null && !"".equals(sSearch)) {
			or.add(Restrictions.ilike("prvdr.name", "%" + sSearch + "%"))
					.add(Restrictions.ilike("mbr.firstName", "%" + sSearch + "%"))
					.add(Restrictions.ilike("mbr.lastName", "%" + sSearch + "%"))
					.add(Restrictions.ilike("genderId.description", "%" + sSearch + "%"))
					.add(Restrictions.like("genderId.code", new Character(sSearch.toCharArray()[0])));
		}
		if (sSearchIns != null) {
			and.add(Restrictions.eq("ins.id", sSearchIns));
		}
		if (sSearchPrvdr == ALL) {

		}

		if (sSearchPrvdr != null && sSearchPrvdr != ALL) {

			crit.createAlias("prvdr.refContracts", "refContract");
			crit.createAlias("refContract.ins", "inss");
			and.add(Restrictions.eq("prvdr.id", sSearchPrvdr));
			and.add(Restrictions.eq("inss.id", sSearchIns));
		}

		and.add(Restrictions.eq("activeInd", new Character('Y')));

		if (processClaim == FILTER_BY_PROCESSING_DATE)
			and.add(Restrictions.between("updatedDate", new java.sql.Date(processingFrom.getTime()),
					new java.sql.Date(processingTo.getTime() + 86400000)));
		if (processClaim == FILTER_BY_HOSPOTALIZATION_DATE) {
			and.add(Restrictions.sqlRestriction(" ? between claim_start_date and claim_end_date",
					new java.sql.Date(processingFrom.getTime()), DateType.INSTANCE));
			and.add(Restrictions.sqlRestriction(" ? between claim_start_date and claim_end_date",
					new java.sql.Date(processingTo.getTime()), DateType.INSTANCE));
		}

		crit.add(or);
		crit.add(and);

		crit.setProjection(Projections.distinct(Projections.property("id")));
		List<Integer> MbrClaimIds = (List<Integer>) crit.list();
		int totalCount = (MbrClaimIds.isEmpty()) ? 0 : MbrClaimIds.size();

		if (totalCount == 0) {
			return findByCriteria(crit, pageNo, pageSize);
		} else {
			Criteria criteria = createCriteria().createAlias("mbr", "mbr").createAlias("mbr.genderId", "genderId")
					.createAlias("prvdr", "prvdr").createAlias("ins", "ins")
					.createAlias("frequencyType", "frequency", JoinType.LEFT_OUTER_JOIN)
					.createAlias("facilityType", "facilityType", JoinType.LEFT_OUTER_JOIN)
					.createAlias("billType", "billType", JoinType.LEFT_OUTER_JOIN)
					.add(Restrictions.in("id", MbrClaimIds));
					if (sort != null && !"".equals(sort)) {
						if (sortdir != null && !"".equals(sortdir) && "desc".equals(sortdir)) {
							if ("prvdr.name".equals(sort)) {
								criteria.addOrder(Order.desc("prvdr.name"));
							} else {
								criteria.addOrder(Order.desc(sort));
							}
						} else {
							if ("prvdr.name".equals(sort)) {
								criteria.addOrder(Order.asc("prvdr.name"));
							} else {
								criteria.addOrder(Order.asc(sort));
							}
						}
					}
			criteria.addOrder(Order.asc("mbr.lastName"));
			criteria.addOrder(Order.asc("mbr.firstName"));
			criteria.addOrder(Order.asc("claimNumber"));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			Pagination pagination = findByCriteria(criteria, pageNo, pageSize);
			pagination.setTotalCount(totalCount);

			return pagination;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ml.rugal.sshcommon.hibernate.HibernateBaseDao#getEntityClass()
	 */
	@Override
	protected Class<MembershipClaim> getEntityClass() {
		return MembershipClaim.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.MembershipClaimDao#loadData()
	 */
	@Override
	public Integer loadDataCSV2Table(String fileName, String insuranceCode, String tableNames) {
		int retValue =0;
		String[] tokens = tableNames.split(",", -1);
		for(String tableName :tokens){
			String	loadDataQuery = PrasUtil.getInsertQuery(getEntityClass(), insuranceCode + QUERY_TYPE_LOAD);
			Object[] objArray = {tableName, "','","'\"'","'\r\n'" };
			MessageFormat mf = new MessageFormat(loadDataQuery);
			String sqlQuery = mf.format(objArray);
			retValue =  getSession().createSQLQuery(sqlQuery).setString("file", FILES_UPLOAD_DIRECTORY_PATH + fileName)
					.executeUpdate();
		}
		return retValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.MembershipClaimDao#loadData()
	 */
	@Override
	public Boolean isDataExists(String tableNames) {
		boolean returnvalue = false;
		String[] tokens = tableNames.split(",", -1);
		for(String tableName :tokens){
			String sql = " SELECT count(*) FROM "+tableName;
		
			int rowCount = (int) ((BigInteger) getSession().createSQLQuery(sql).uniqueResult()).intValue();
			if (rowCount > 0) {
				returnvalue = returnvalue||true;
			} else {
				returnvalue = returnvalue||false;
			}
		}
			return returnvalue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.MembershipClaimDao#loadData()
	 */
	@Override
	public Integer loadData(final Integer fileId, final Integer insId, final String insuranceCode) {
		String loadDataQuery = PrasUtil.getInsertQuery(getEntityClass(), insuranceCode+QUERY_TYPE_INSERT);

		return getSession().createSQLQuery(loadDataQuery).setInteger("fileId", fileId).setInteger("insId", insId).executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.MembershipClaimDao#unloadCSV2Table()
	 */
	@Override
	public Integer unloadCSV2Table(final String tableNames) {
		
		String[] tokens = tableNames.split(",", -1);
		Session session = getSession();
		int rowsAffected = 0;
		for(String tableName :tokens){
			try {
				rowsAffected = session.createSQLQuery("TRUNCATE TABLE " + tableName).executeUpdate();
			} catch (Exception e) {
				LOG.warn("exception " + e.getCause());
			}
		}
		return rowsAffected;
	}

	@Override
	public Integer updateData(final Integer fileId, final String insuranceCode) {
		String loadDataQuery =  PrasUtil.getInsertQuery(getEntityClass(), insuranceCode+QUERY_TYPE_UPDATE);

		return getSession().createSQLQuery(loadDataQuery).setInteger("fileId", fileId).executeUpdate();
	}

}
