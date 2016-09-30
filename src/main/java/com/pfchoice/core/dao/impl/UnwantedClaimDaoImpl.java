package com.pfchoice.core.dao.impl;

import static com.pfchoice.common.SystemDefaultProperties.QUERY_TYPE_FETCH;
import static com.pfchoice.common.SystemDefaultProperties.QUERY_TYPE_STOPLOSS;

import java.util.List;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.pfchoice.common.util.PrasUtil;
import com.pfchoice.core.dao.UnwantedClaimDao;
import com.pfchoice.core.entity.UnwantedClaim;

/**
 *
 * @author Sarath
 */
@Repository
public class UnwantedClaimDaoImpl extends HibernateBaseDao<UnwantedClaim, Integer> implements UnwantedClaimDao {

	/* (non-Javadoc)
	 * @see ml.rugal.sshcommon.hibernate.HibernateBaseDao#getEntityClass()
	 */
	@Override
	protected Class<UnwantedClaim> getEntityClass() {
		return UnwantedClaim.class;
	}

	
	/**
	 * @param insId
	 * @param prvdrId
	 * @param reportMonth
	 * @param activityMonth
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UnwantedClaim> getUnwantedClaims(final Integer insId, final Integer prvdrId, final Integer reportMonth, final Integer activityMonth,final Boolean isUnwanted){
		String fetchDataQuery;
		if(isUnwanted) {
			fetchDataQuery = PrasUtil.getInsertQuery(getEntityClass(), QUERY_TYPE_FETCH);
		} else {
			fetchDataQuery = PrasUtil.getInsertQuery(getEntityClass(), QUERY_TYPE_STOPLOSS);
		}
		
		SQLQuery  query =   (SQLQuery) getSession().createSQLQuery(fetchDataQuery)
				.setInteger("insId", insId)
				.setInteger("prvdrId", prvdrId) 
				.setInteger("reportMonth", reportMonth)
				.setInteger("activityMonth", activityMonth);
		return query.setResultTransformer(Transformers.aliasToBean(getEntityClass())).list();
	}
}
