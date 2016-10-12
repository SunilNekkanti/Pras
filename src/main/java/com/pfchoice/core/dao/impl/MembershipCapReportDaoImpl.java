package com.pfchoice.core.dao.impl;

import static com.pfchoice.common.SystemDefaultProperties.FILES_UPLOAD_DIRECTORY_PATH;
import static com.pfchoice.common.SystemDefaultProperties.FILE_TYPE_AMG_CAP_REPORT;
import static com.pfchoice.common.SystemDefaultProperties.QUERY_TYPE_INSERT;
import static com.pfchoice.common.SystemDefaultProperties.QUERY_TYPE_LOAD;

import java.math.BigInteger;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pfchoice.common.util.PrasUtil;
import com.pfchoice.core.dao.MembershipCapReportDao;
import com.pfchoice.core.entity.MembershipCapReport;

/**
 *
 * @author Sarath
 */
@Repository
public class MembershipCapReportDaoImpl extends HibernateBaseDao<MembershipCapReport, Integer> implements MembershipCapReportDao {

	private static final Logger LOG = LoggerFactory.getLogger(MembershipCapReportDaoImpl.class);
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
	 * @see com.pfchoice.core.dao.MembershipCapReportDao#findById(java.lang.Integer)
	 */
	@Override
	public MembershipCapReport findById(final Integer id) {
		assert id !=null;
		return get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.MembershipCapReportDao#save(com.pfchoice.core.entity.
	 * MembershipCapReport)
	 */
	@Override
	public MembershipCapReport save(final MembershipCapReport bean) {
		assert bean !=null;
		getSession().save(bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.MembershipCapReportDao#deleteById(java.lang.Integer)
	 */
	@Override
	public MembershipCapReport deleteById(final Integer id) {
		assert id !=null;
		MembershipCapReport entity = super.get(id);
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
	protected Class<MembershipCapReport> getEntityClass() {
		return MembershipCapReport.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.MembershipDao#loadData()
	 */
	@Override
	public Integer loadDataCSV2Table(String fileName, String tableName) {

		String loadDataQuery = null;
		 if (tableName.equals(FILE_TYPE_AMG_CAP_REPORT))
			loadDataQuery = PrasUtil.getInsertQuery(getEntityClass(), QUERY_TYPE_LOAD);
		
		return getSession().createSQLQuery(loadDataQuery).setString("file", FILES_UPLOAD_DIRECTORY_PATH + fileName)
				.executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.MembershipDao#loadData()
	 */
	@Override
	public Boolean isDataExists(String tableName) {
		boolean returnvalue = false;
		StringBuilder sql = new StringBuilder();
		 if (tableName.equals(FILE_TYPE_AMG_CAP_REPORT))
			sql.append("SELECT count(*) FROM csv2table_cap_report");

		int rowCount = ((BigInteger) getSession().createSQLQuery(sql.toString()).uniqueResult()).intValue();
		if (rowCount > 0) {
			returnvalue = true;
		} else {
			returnvalue = false;
		}

		return returnvalue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.MembershipDao#loadData()
	 */
	@Override
	public Integer loadData(final Integer insId, final Integer fileId, final Integer activityMonth,
			final String tableName) {
		StringBuilder loadDataQuery = new StringBuilder();
		if (tableName.equals(FILE_TYPE_AMG_CAP_REPORT)) {
			loadDataQuery.append(PrasUtil.getInsertQuery(getEntityClass(), QUERY_TYPE_INSERT));
		}

		return getSession().createSQLQuery(loadDataQuery.toString()).setInteger("insId", insId)
				.setInteger("activityMonth", activityMonth).setInteger("fileId", fileId).executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.MembershipDao#unloadCSV2Table()
	 */
	@Override
	public Integer unloadCSV2Table(String tableName) {
		Session session = getSession();
		int rowsAffected = 0;
		String table = null;

	  if (tableName.equals(FILE_TYPE_AMG_CAP_REPORT))
			table = "csv2Table_cap_Report";

		try {
			rowsAffected = session.createSQLQuery("TRUNCATE TABLE " + table).executeUpdate();
		} catch (Exception e) {
			LOG.warn("exception " + e.getCause());
		}
		return rowsAffected;
	}

}
