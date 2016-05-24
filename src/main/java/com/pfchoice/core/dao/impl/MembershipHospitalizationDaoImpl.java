package com.pfchoice.core.dao.impl;

import static com.pfchoice.common.SystemDefaultProperties.FILES_UPLOAD_DIRECTORY_PATH;
import static com.pfchoice.common.SystemDefaultProperties.QUERY_TYPE_INSERT;
import static com.pfchoice.common.SystemDefaultProperties.QUERY_TYPE_LOAD;
import static com.pfchoice.common.SystemDefaultProperties.QUERY_TYPE_UPDATE;


import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.math.BigInteger;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pfchoice.common.util.PrasUtil;
import com.pfchoice.core.dao.MembershipHospitalizationDao;
import com.pfchoice.core.entity.MembershipHospitalization;

/**
 *
 * @author Sarath
 */
@Repository
public class MembershipHospitalizationDaoImpl extends HibernateBaseDao<MembershipHospitalization, Integer> implements MembershipHospitalizationDao {

	private static final Logger LOG = LoggerFactory.getLogger(MembershipHospitalizationDaoImpl.class);
	
	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.PlaceOfServiceDao#getPage(int, int)
	 */
	@Override
	public Pagination getPage(final int pageNo, final int pageSize) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("activeInd", 'Y'));
		return findByCriteria(crit, pageNo, pageSize);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.HospitalDao#findById(java.lang.Integer)
	 */
	@Override
	public MembershipHospitalization findById(final Integer id) {
		return get(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.HospitalDao#save(com.pfchoice.core.entity.MembershipHospitalization)
	 */
	@Override
	public MembershipHospitalization save(final MembershipHospitalization bean) {
		getSession().save(bean);
		return bean;
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.HospitalDao#deleteById(java.lang.Integer)
	 */
	@Override
	public MembershipHospitalization deleteById(final Integer id) {
		MembershipHospitalization entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	/* (non-Javadoc)
	 * @see ml.rugal.sshcommon.hibernate.HibernateBaseDao#getEntityClass()
	 */
	@Override
	protected Class<MembershipHospitalization> getEntityClass() {
		return MembershipHospitalization.class;
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.MembershipHospitalizationDao#loadData()
	 */
	@Override
	public Integer loadDataCSV2Table(String fileName) {
		
		String loadDataQuery = PrasUtil.getInsertQuery(getEntityClass(), QUERY_TYPE_LOAD);
		return getSession().createSQLQuery(loadDataQuery)
							.setString("file", FILES_UPLOAD_DIRECTORY_PATH+fileName)
				    		.executeUpdate();
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.MembershipHospitalizationDao#loadData()
	 */
	@Override
	public Boolean isDataExists() {
		boolean returnvalue =false;
		String sql = "SELECT count(*) FROM csv2AmgHospitalization";
		int rowCount = (int) ((BigInteger) getSession().createSQLQuery(sql)
				                   .uniqueResult()).intValue() ;
		if(rowCount > 0){
			returnvalue = true;
		}else{
			returnvalue = false;
		}
		
		return returnvalue;
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.MembershipHospitalizationDao#loadData()
	 */
	@Override 
	public Integer loadData(final Integer fileId){
		String loadDataQuery = PrasUtil.getInsertQuery(getEntityClass(), QUERY_TYPE_INSERT);
		
		return getSession().createSQLQuery(loadDataQuery)
				    					.setInteger("fileId", fileId)
				    					.executeUpdate();
	}
	
	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.MembershipHospitalizationDao#unloadCSV2Table()
	 */
	@Override
	public Integer unloadCSV2Table() {
		Session session = getSession();
		int rowsAffected = 0;

		try{
			rowsAffected =  session.createSQLQuery("TRUNCATE TABLE csv2AmgHospitalization")
						.executeUpdate();
		}catch(Exception e){
			LOG.warn("exception "+e.getCause());
		}
		return rowsAffected;
	}
	
	@Override 
	public Integer updateData(final Integer fileId){
		String loadDataQuery = PrasUtil.getInsertQuery(getEntityClass(), QUERY_TYPE_UPDATE);
		
		return getSession().createSQLQuery(loadDataQuery)
				    					.setInteger("fileId", fileId)
				    					.executeUpdate();
	}

}
