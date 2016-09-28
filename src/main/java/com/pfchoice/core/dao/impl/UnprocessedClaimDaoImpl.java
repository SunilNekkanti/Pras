package com.pfchoice.core.dao.impl;

import static com.pfchoice.common.SystemDefaultProperties.QUERY_TYPE_INSERT;
import static com.pfchoice.common.SystemDefaultProperties.QUERY_TYPE_COUNT;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.math.BigInteger;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pfchoice.common.util.PrasUtil;
import com.pfchoice.core.dao.UnprocessedClaimDao;
import com.pfchoice.core.entity.UnprocessedClaim;

/**
 *
 * @author Sarath
 */
@Repository
public class UnprocessedClaimDaoImpl extends HibernateBaseDao<UnprocessedClaim, Integer> implements UnprocessedClaimDao {

	@Override
	public Pagination getPage(final int pageNo, final int pageSize) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("activeInd", 'Y'));
		return findByCriteria(crit, pageNo, pageSize);
	}

	@Override
	protected Class<UnprocessedClaim> getEntityClass() {
		return UnprocessedClaim.class;
	}
	@Override
	public Integer loadDataCSV2Table(final Integer fileId, final String insuranceCode,final String tableNames, final Integer insId) {
		int retValue =0;
		System.out.println(" insId "+insId);
		
		String[] tokens = tableNames.split(",", -1);
		String	loadDataQuery = "";
		for(int i=0;i<tokens.length;i++){
			int count = 0;
				loadDataQuery = PrasUtil.getInsertQuery(getEntityClass(), i+insuranceCode + QUERY_TYPE_COUNT);
				count =  (int) ((BigInteger) getSession().createSQLQuery(loadDataQuery).uniqueResult()).intValue();
				if(count > 0){
					loadDataQuery = PrasUtil.getInsertQuery(getEntityClass(), i+insuranceCode + QUERY_TYPE_INSERT);
					retValue = getSession().createSQLQuery(loadDataQuery).setInteger("fileId", fileId).setInteger("insId", insId).executeUpdate();
				}
		}
		
		return retValue;
	}

}
