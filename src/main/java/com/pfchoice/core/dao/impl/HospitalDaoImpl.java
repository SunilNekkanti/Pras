package com.pfchoice.core.dao.impl;

import static com.pfchoice.common.SystemDefaultProperties.QUERY_TYPE_INSERT;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pfchoice.common.util.PrasUtil;
import com.pfchoice.core.dao.HospitalDao;
import com.pfchoice.core.entity.Hospital;

/**
 *
 * @author Sarath
 */
@Repository
public class HospitalDaoImpl extends HibernateBaseDao<Hospital, Integer> implements HospitalDao {

	private static final Logger LOG = LoggerFactory.getLogger(HospitalDaoImpl.class);
	
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
	public Hospital findById(final Integer id) {
		return get(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.HospitalDao#save(com.pfchoice.core.entity.Hospital)
	 */
	@Override
	public Hospital save(final Hospital bean) {
		getSession().save(bean);
		return bean;
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.HospitalDao#deleteById(java.lang.Integer)
	 */
	@Override
	public Hospital deleteById(final Integer id) {
		Hospital entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	/* (non-Javadoc)
	 * @see ml.rugal.sshcommon.hibernate.HibernateBaseDao#getEntityClass()
	 */
	@Override
	protected Class<Hospital> getEntityClass() {
		return Hospital.class;
	}
	
	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.HospitalDao#loadData(java.lang.Integer)
	 */
	@Override
	public Integer loadData(final Integer fileId) {
		String loadDataQuery = PrasUtil.getInsertQuery(getEntityClass(), QUERY_TYPE_INSERT);
		
		return getSession().createSQLQuery(loadDataQuery)
				    		.setInteger("fileId", fileId)
				    		.executeUpdate();
	}


}
