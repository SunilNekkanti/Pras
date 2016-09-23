package com.pfchoice.core.dao.impl;

import static com.pfchoice.common.SystemDefaultProperties.ALL;
import static com.pfchoice.common.SystemDefaultProperties.QUERY_TYPE_INSERT;

import java.util.List;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.pfchoice.common.util.PrasUtil;
import com.pfchoice.core.dao.MedicalLossRatioDao;
import com.pfchoice.core.entity.MedicalLossRatio;
import com.pfchoice.core.entity.MedicalLossRatioGenerateDate;

/**
 *
 * @author Sarath
 */
@Repository
public class MedicalLossRatioDaoImpl extends HibernateBaseDao<MedicalLossRatio, Integer> implements MedicalLossRatioDao {


	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.MedicalLossRatioDao#getPage(int, int)
	 */
	@Override
	public Pagination getPage(final int pageNo, final int pageSize) {
		
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("activeInd", 'Y'));
		return findByCriteria(crit, pageNo, pageSize);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.MedicalLossRatioDao#getPage(int, int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Pagination getPage(final Integer pageNo, final Integer pageSize, final Integer insId, final Integer prvdrId, final String sSearch, final String sort,
			final String sortdir) {
		
		Disjunction or = Restrictions.disjunction();
		
		Criteria crit = createCriteria().createAlias("ins", "ins")
				.createAlias("prvdr", "prvdr");
		crit.add(Restrictions.eq("activeInd", 'Y'));
		crit.add(Restrictions.eq("ins.id", insId));
		crit.add(Restrictions.eq("prvdr.id", prvdrId));
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
	
	
	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.MedicalLossRatioDao#getMlrReportDate(int, int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Pagination getMlrReportDate(final Integer pageNo, final Integer pageSize, final Integer insId, final Integer prvdrId, final String sort,
			final String sortdir) {
		
		Criteria crit = createCriteria().createAlias("ins", "ins")
				.createAlias("prvdr", "prvdr");
		crit.add(Restrictions.eq("activeInd", 'Y'));
		crit.add(Restrictions.eq("ins.id", insId));
		
		ProjectionList projList = Projections.projectionList(); 
		projList.add(Projections.property("ins.id").as("insId"));
		projList.add(Projections.groupProperty("ins.id").as("insId"));
        projList.add(Projections.groupProperty("reportGenDate").as("reportDate"));
         
		if(prvdrId != ALL) {
			projList.add(Projections.property("prvdr.id").as("prvdrId"));
			crit.add(Restrictions.eq("prvdr.id", prvdrId));
		}
        crit.setProjection(projList);
        crit.setResultTransformer(Transformers.aliasToBean(MedicalLossRatioGenerateDate.class));

		crit.addOrder(Order.desc("reportGenDate"));

		System.out.println( "query is "+PrasUtil.printCriteriaQuery(crit));
		return findByCriteria(crit, pageNo, pageSize);

	}

	
	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.MedicalLossRatioDao#findById(java.lang.Integer)
	 */
	@Override
	public MedicalLossRatio findById(final Integer id) {
		assert id !=null;
		return get(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.MedicalLossRatioDao#save(com.pfchoice.core.entity.MedicalLossRatio)
	 */
	@Override
	public MedicalLossRatio save(final MedicalLossRatio bean) {
		assert bean !=null;
		getSession().save(bean);
		return bean;
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.MedicalLossRatioDao#deleteById(java.lang.Integer)
	 */
	@Override
	public MedicalLossRatio deleteById(final Integer id) {
		assert id !=null;
		MedicalLossRatio entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	
	/* (non-Javadoc)
	 * @see ml.rugal.sshcommon.hibernate.HibernateBaseDao#getEntityClass()
	 */
	@Override
	protected Class<MedicalLossRatio> getEntityClass() {
		return MedicalLossRatio.class;
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.MedicalLossRatioDao#loadData(java.lang.Integer)
	 */
	@Override
	public Integer loadData(final Integer fileId) {
		assert fileId !=null;
		String loadDataQuery = PrasUtil.getInsertQuery(getEntityClass(), QUERY_TYPE_INSERT);

		return getSession().createSQLQuery(loadDataQuery).setInteger("fileId", fileId).executeUpdate();
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.MedicalLossRatioDao#findByCode(java.lang.String)
	 */
	@Override
	public MedicalLossRatio findByCode(String code) {
		assert code !=null || !"".equals(code) : "The physician code is empty";; 
		return findUniqueByProperty("code", code);
	}

	
	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.MedicalLossRatioDao#reportQuery(java.lang.String)
	 */
	@Override
	public List<Object[]> reportQuery( final String tableName, final Integer insId, final Integer prvdrId, final String repGenDate, final String category){
		assert tableName !=null && !"".equals(tableName);
		String loadDataQuery = PrasUtil.getInsertQuery(getEntityClass(), QUERY_TYPE_INSERT);

		SQLQuery  query =   (SQLQuery) getSession().createSQLQuery(loadDataQuery).setString("tableName",tableName)
				.setInteger("insId", insId) .setInteger("prvdrId", prvdrId) .setString("repGenDate", repGenDate)
				.setString("category", category);
		@SuppressWarnings("unchecked")
		List<Object[]> entities =  query.list();
		return entities;
	}
}
