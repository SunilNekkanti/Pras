package com.pfchoice.core.dao.impl;

import static com.pfchoice.common.SystemDefaultProperties.DEFAULT_PAGE_NO;
import static com.pfchoice.common.SystemDefaultProperties.MEDIUM_LIST_SIZE;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.EmailTemplatePlaceholderDao;
import com.pfchoice.core.entity.EmailTemplatePlaceholder;
import com.pfchoice.core.entity.Membership;

/**
 *
 * @author Mohanasundharam
 */
@Repository
public class EmailTemplatePlaceholderDaoImpl extends HibernateBaseDao<EmailTemplatePlaceholder, Integer> implements EmailTemplatePlaceholderDao {

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.EmailsDao#getPage(int, int)
	 */
	@Override
	public Pagination getPage(final int pageNo, final int pageSize) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("activeInd", 'Y'));
		return findByCriteria(crit, pageNo, pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.EmailsDao#getPage(int, int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		Criteria crit = createCriteria();
		return findByCriteria(crit, pageNo, pageSize);

	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.EmailsDao#findById(java.lang.Integer)
	 */
	@Override
	public EmailTemplatePlaceholder findById(final Integer id) {
		return get(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.EmailsDao#save(com.pfchoice.core.entity.Emails)
	 */
	@Override
	public EmailTemplatePlaceholder save(final EmailTemplatePlaceholder bean) {
		getSession().save(bean);
		return bean;
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.EmailsDao#deleteById(java.lang.Integer)
	 */
	@Override
	public EmailTemplatePlaceholder deleteById(final Integer id) {
		EmailTemplatePlaceholder entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	/* (non-Javadoc)
	 * @see ml.rugal.sshcommon.hibernate.HibernateBaseDao#getEntityClass()
	 */
	@Override
	protected Class<EmailTemplatePlaceholder> getEntityClass() {
		return EmailTemplatePlaceholder.class;
	}
	
	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.EmailTemplatePlaceholderDao#findByEmailTemplateId(java.lang.Integer)
	 */
	@Override
	public Pagination findByEmailTemplateId(Integer  emailTemplateId){
		Criteria crit = createCriteria();
		crit.createAlias("emailTemplate", "emailTemplate");
		crit.add(Restrictions.eq("emailTemplate.id", emailTemplateId));
		crit.add(Restrictions.eq("activeInd", 'Y'));
		crit.add(Restrictions.eq("attachmentFlag", 'N'));
		
		crit.addOrder(Order.asc("orderNo"));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		Pagination page = findByCriteria(crit, DEFAULT_PAGE_NO, MEDIUM_LIST_SIZE);
		return page;
	}
	
	
	@Override
	public List<Object> getSQLScriptResults(List<EmailTemplatePlaceholder> emailTemplatePlaceholders, Integer id) {
		List<Object> objects = new ArrayList<>();
		emailTemplatePlaceholders.forEach(emailTemplatePlaceholder -> {
			String sqlScript = emailTemplatePlaceholder.getSqlScript();
			Query query  = getSession().createSQLQuery(sqlScript) 
					.setInteger("prvdrId", id);
			objects.add(query.uniqueResult());
		});
		System.out.println("before returning non attachment queries");
		return objects;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> generateAttachmentFile(Integer  emailTemplateId, Integer id){
		System.out.println("before   attachment queries");
		Criteria crit = createCriteria();
		crit.createAlias("emailTemplate", "emailTemplate");
		
		crit.add(Restrictions.eq("emailTemplate.id", emailTemplateId));
		crit.add(Restrictions.eq("activeInd", 'Y'));
		crit.add(Restrictions.eq("attachmentFlag", 'Y'));
		
		crit.addOrder(Order.asc("orderNo"));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		Pagination page = findByCriteria(crit, DEFAULT_PAGE_NO, MEDIUM_LIST_SIZE);
		
		if(!page.getList().isEmpty()){
			EmailTemplatePlaceholder emailTemplatePlaceholder = (EmailTemplatePlaceholder) page.getList().get(0);
			String sqlScript = emailTemplatePlaceholder.getSqlScript();
			SQLQuery query  = getSession().createSQLQuery(sqlScript);
			System.out.println("before returning  attachment queries");
			return  query.list();
		}
		
		return null;
		
	}
}
