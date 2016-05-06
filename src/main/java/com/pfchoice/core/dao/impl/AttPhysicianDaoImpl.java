package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import static com.pfchoice.common.SystemDefaultProperties.SQL_DIRECTORY_PATH;

import java.io.IOException;
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

import com.pfchoice.core.dao.AttPhysicianDao;
import com.pfchoice.core.entity.AttPhysician;

/**
 *
 * @author Sarath
 */
@Repository
public class AttPhysicianDaoImpl extends HibernateBaseDao<AttPhysician, Integer> implements AttPhysicianDao {

	private static final Logger LOG = LoggerFactory.getLogger(AttPhysicianDaoImpl.class);
	
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
	 * @see com.pfchoice.core.dao.AttPhysicianDao#findById(java.lang.Integer)
	 */
	@Override
	public AttPhysician findById(final Integer id) {
		return get(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.AttPhysicianDao#save(com.pfchoice.core.entity.AttPhysician)
	 */
	@Override
	public AttPhysician save(final AttPhysician bean) {
		getSession().save(bean);
		return bean;
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.AttPhysicianDao#deleteById(java.lang.Integer)
	 */
	@Override
	public AttPhysician deleteById(final Integer id) {
		AttPhysician entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	/* (non-Javadoc)
	 * @see ml.rugal.sshcommon.hibernate.HibernateBaseDao#getEntityClass()
	 */
	@Override
	protected Class<AttPhysician> getEntityClass() {
		return AttPhysician.class;
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.AttPhysicianDao#loadData(java.lang.Integer)
	 */
	@Override
	public Integer loadData(final Integer fileId) {
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

}
