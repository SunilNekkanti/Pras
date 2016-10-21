package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import static com.pfchoice.common.SystemDefaultProperties.QUERY_TYPE_INSERT;
import static com.pfchoice.common.SystemDefaultProperties.QUERY_TYPE_INSERT_LEVEL2;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pfchoice.common.util.PrasUtil;
import com.pfchoice.core.dao.RiskReconDao;
import com.pfchoice.core.entity.RiskRecon;

/**
 *
 * @author Sarath
 */
@Repository
public class RiskReconDaoImpl extends HibernateBaseDao<RiskRecon, Integer> implements RiskReconDao {

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
	public Pagination getPage(final int pageNo, final int pageSize, final List<Integer> claimType, final String sSearch, final String sort,
			final String sortdir) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.in("id", claimType));
		crit.add(Restrictions.eq("activeInd", 'Y'));
		return findByCriteria(crit, pageNo, pageSize);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.RiskReconDao#findById(java.lang.Integer)
	 */
	@Override
	public RiskRecon findById(final Integer id) {
		assert id !=null;
		return get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.RiskReconDao#save(com.pfchoice.core.entity.
	 * RiskRecon)
	 */
	@Override
	public RiskRecon save(final RiskRecon bean) {
		assert bean !=null;
		getSession().save(bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.RiskReconDao#deleteById(java.lang.Integer)
	 */
	@Override
	public RiskRecon deleteById(final Integer id) {
		assert id !=null;
		RiskRecon entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	
	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.MedicalLossRatioDao#reportQuery(java.lang.String)
	 */
	@Override
	public List<Object[]> claimReportQuery( final String tableName, final Integer insId, final Integer prvdrId, final Integer mbrId,
			final String repGenDate, final Integer activityMonth, final String claimType, 
			final String category, final String roster, final String cap, final Integer levelNo){
		assert tableName !=null && !"".equals(tableName);
		
		String loadDataQuery = PrasUtil.getInsertQuery(getEntityClass(), QUERY_TYPE_INSERT);

		SQLQuery  query =   (SQLQuery) getSession().createSQLQuery(loadDataQuery)
				.setInteger("insId", insId)			.setInteger("prvdrId", prvdrId)			
				.setInteger("mbrId", mbrId)			.setString("tableName", tableName)
				.setString("repMonth", repGenDate)	.setInteger("activityMonth", activityMonth)
				.setString("category", category)	.setString("claimType", claimType)
				.setString("roster", roster)		.setString("cap", cap)				
				.setInteger("levelNo", levelNo);
		
		@SuppressWarnings("unchecked")
		
		List<Object[]> entities =  query.list();
		return entities;
	}
	
	
	@Override
	public List<Object[]> claimReportQueryLevel2(final Integer insId,  final String repGenDate, final String activityMonth,
			final String category, final String roster, final String cap){
		
		String loadDataQuery = PrasUtil.getInsertQuery(getEntityClass(), QUERY_TYPE_INSERT_LEVEL2);

		SQLQuery  query =   (SQLQuery) getSession().createSQLQuery(loadDataQuery)
				.setInteger("insId", insId)			.setString("repMonth", repGenDate)
				.setString("category", category)	.setString("activityMonth", activityMonth);
		@SuppressWarnings("unchecked")
		
		List<Object[]> entities =  query.list();
		return entities;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ml.rugal.sshcommon.hibernate.HibernateBaseDao#getEntityClass()
	 */
	@Override
	protected Class<RiskRecon> getEntityClass() {
		return RiskRecon.class;
	}

}
