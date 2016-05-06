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
import com.pfchoice.core.dao.MembershipHospitalizationDetailsDao;
import com.pfchoice.core.entity.MembershipHospitalizationDetails;

/**
 *
 * @author Sarath
 */
@Repository
public class MembershipHospitalizationDetailsDaoImpl extends HibernateBaseDao<MembershipHospitalizationDetails, Integer> implements MembershipHospitalizationDetailsDao {

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
	public MembershipHospitalizationDetails findById(final Integer id) {
		return get(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.HospitalDao#save(com.pfchoice.core.entity.MembershipHospitalizationDetails)
	 */
	@Override
	public MembershipHospitalizationDetails save(final MembershipHospitalizationDetails bean) {
		getSession().save(bean);
		return bean;
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.HospitalDao#deleteById(java.lang.Integer)
	 */
	@Override
	public MembershipHospitalizationDetails deleteById(final Integer id) {
		MembershipHospitalizationDetails entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	/* (non-Javadoc)
	 * @see ml.rugal.sshcommon.hibernate.HibernateBaseDao#getEntityClass()
	 */
	@Override
	protected Class<MembershipHospitalizationDetails> getEntityClass() {
		return MembershipHospitalizationDetails.class;
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
}
