package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.MembershipHospitalizationDao;
import com.pfchoice.core.entity.MembershipHospitalization;

import static com.pfchoice.common.SystemDefaultProperties.SQL_DIRECTORY_PATH;
import static com.pfchoice.common.SystemDefaultProperties.FILES_UPLOAD_DIRECTORY_PATH;
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
	public Integer loadDataCSV2Table() {
		Session session = getSession();
		int rowsAffected = 0;
		try{
			Path path = FileSystems.getDefault().getPath(SQL_DIRECTORY_PATH+ getEntityClass().getSimpleName()+".sql");
			String loadDataQuery = new String(Files.readAllBytes(path.toAbsolutePath()), StandardCharsets.UTF_8);
			LOG.info("contents is"+loadDataQuery);
			
			//session.createSQLQuery("TRUNCATE TABLE csv2AmgHospitalization")
			//			.executeUpdate();
			
			rowsAffected = session.createSQLQuery(loadDataQuery)
				    .setString("file", FILES_UPLOAD_DIRECTORY_PATH+"Physicians First Choice Census 04-28-2016.csv")
				    .executeUpdate();
			
		}catch(IOException e){
			LOG.warn("exception "+e.getCause());
		}
		return rowsAffected;
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
		System.out.println("rowCount"+rowCount);
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
		Session session = getSession();
		int rowsAffected = 0;
		try{
			Path path = FileSystems.getDefault().getPath(SQL_DIRECTORY_PATH+ getEntityClass().getSimpleName()+"_insert.sql");
			String loadDataQuery = new String(Files.readAllBytes(path.toAbsolutePath()), StandardCharsets.UTF_8);
			LOG.info("contents is"+loadDataQuery);
			
			rowsAffected = session.createSQLQuery(loadDataQuery)
				    .setInteger("fileId", fileId)
				    .executeUpdate();
		}catch(IOException e){
			LOG.warn("exception "+e.getCause());
		}
		return rowsAffected;
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

}
