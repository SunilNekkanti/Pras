package com.pfchoice.core.dao.impl;

import static com.pfchoice.common.SystemDefaultProperties.QUERY_TYPE_INSERT;
import static com.pfchoice.common.SystemDefaultProperties.FILE_TYPE_AMG_MBR_CLAIM;
import static com.pfchoice.common.SystemDefaultProperties.FILE_TYPE_BH_MBR_CLAIM;
import static com.pfchoice.common.SystemDefaultProperties.QUERY_TYPE_BH_INSERT;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pfchoice.common.util.PrasUtil;
import com.pfchoice.core.dao.FacilityTypeDao;
import com.pfchoice.core.entity.FacilityType;

/**
 *
 * @author Sarath
 */
@Repository
public class FacilityTypeDaoImpl extends HibernateBaseDao<FacilityType, Integer> implements FacilityTypeDao {

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
	 * @see com.pfchoice.core.dao.facilityTypeDao#getPage(int, int,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		Disjunction or = Restrictions.disjunction();

		if (sSearch != null && !"".equals(sSearch)) {
			or.add(Restrictions.ilike("description", "%" + sSearch + "%"))
			.add(Restrictions.ilike("shortName", "%" + sSearch + "%"));
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
	 * @see com.pfchoice.core.dao.FacilityTypeDao#findById(java.lang.Integer)
	 */
	@Override
	public FacilityType findById(final Integer id) {
		return get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.FacilityTypeDao#save(com.pfchoice.core.entity.
	 * FacilityType)
	 */
	@Override
	public FacilityType save(final FacilityType bean) {
		getSession().save(bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.FacilityTypeDao#deleteById(java.lang.Integer)
	 */
	@Override
	public FacilityType deleteById(final Integer id) {
		FacilityType entity = super.get(id);
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
	protected Class<FacilityType> getEntityClass() {
		return FacilityType.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.FacilityTypeDao#loadData(java.lang.Integer)
	 */
	@Override
	public Integer loadData(final Integer fileId, String tableName) {
		
		String loadDataQuery = null;
		if(tableName == FILE_TYPE_BH_MBR_CLAIM)
			loadDataQuery = PrasUtil.getInsertQuery(getEntityClass(), QUERY_TYPE_BH_INSERT);
		else if(tableName == FILE_TYPE_AMG_MBR_CLAIM)
			loadDataQuery = PrasUtil.getInsertQuery(getEntityClass(), QUERY_TYPE_INSERT);

		return getSession().createSQLQuery(loadDataQuery)
				// .setInteger("fileId", fileId)
				.executeUpdate();
	}
	
	/**
	 * @param name
	 * @return
	 */
	@Override
	public FacilityType findByDescription(String facilityTypeDescription) {
		return findUniqueByProperty("description", facilityTypeDescription);
	}

}
