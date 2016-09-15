package com.pfchoice.core.dao.impl;

import static com.pfchoice.common.SystemDefaultProperties.FILES_UPLOAD_DIRECTORY_PATH;
import static com.pfchoice.common.SystemDefaultProperties.QUERY_TYPE_INSERT;
import static com.pfchoice.common.SystemDefaultProperties.QUERY_TYPE_LOAD;

import java.math.BigInteger;
import java.text.MessageFormat;

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
import com.pfchoice.core.dao.PharmacyDao;
import com.pfchoice.core.entity.Pharmacy;

/**
 *
 * @author Sarath
 */
@Repository
public class PharmacyDaoImpl extends HibernateBaseDao<Pharmacy, Integer> implements PharmacyDao {

	private static final Logger LOG = LoggerFactory.getLogger(PharmacyDaoImpl.class);
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
	 * @see com.pfchoice.core.dao.AttPhysicianDao#findById(java.lang.Integer)
	 */
	@Override
	public Pharmacy findById(final Integer id) {
		assert id !=null;
		return get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.AttPhysicianDao#save(com.pfchoice.core.entity.
	 * AttPhysician)
	 */
	@Override
	public Pharmacy save(final Pharmacy bean) {
		assert bean !=null;
		getSession().save(bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.AttPhysicianDao#deleteById(java.lang.Integer)
	 */
	@Override
	public Pharmacy deleteById(final Integer id) {
		assert id !=null;
		Pharmacy entity = super.get(id);
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
	protected Class<Pharmacy> getEntityClass() {
		return Pharmacy.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.AttPhysicianDao#loadData(java.lang.Integer)
	 */
	@Override
	public Integer loadData(final Integer fileId) {
		assert fileId !=null;
		String loadDataQuery = PrasUtil.getInsertQuery(getEntityClass(), QUERY_TYPE_INSERT);

		return getSession().createSQLQuery(loadDataQuery).setInteger("fileId", fileId).executeUpdate();
	}

	/**
	 * @param code
	 * @return
	 */
	@Override
	public Pharmacy findByCode(String code) {
		assert code !=null || !"".equals(code) : "The pharmacy name is empty";; 
		return findUniqueByProperty("code", code);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.PharmacyDao#loadData()
	 */
	@Override
	public Integer loadDataCSV2Table(String fileName, String insuranceCode, String tableNames) {
		
		int retValue =0;
		String[] tokens = tableNames.split(",", -1);
		for(String tableName :tokens){
			String	loadDataQuery = PrasUtil.getInsertQuery(getEntityClass(), insuranceCode + QUERY_TYPE_LOAD);
			Object[] objArray = {tableName, "','","'\"'","'\r\n'"};
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
	 * @see com.pfchoice.core.dao.PharmacyDao#loadData()
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
	 * @see com.pfchoice.core.dao.PharmacyDao#loadData()
	 */
	@Override
	public Integer loadData(final Integer fileId, final Integer insId, final String insuranceCode) {
		String loadDataQuery = PrasUtil.getInsertQuery(getEntityClass(), insuranceCode+QUERY_TYPE_INSERT);

		return getSession().createSQLQuery(loadDataQuery).setInteger("fileId", fileId).setInteger("insId", insId).executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.PharmacyDao#unloadCSV2Table()
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


}
