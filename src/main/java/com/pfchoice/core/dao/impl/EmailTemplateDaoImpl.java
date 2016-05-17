package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.EmailTemplateDao;
import com.pfchoice.core.entity.EmailTemplate;

/**
 *
 * @author Mohanasundharam
 */
@Repository
public class EmailTemplateDaoImpl extends HibernateBaseDao<EmailTemplate, Integer> implements EmailTemplateDao {

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.EmailTemplatesDao#getPage(int, int)
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
	 * @see com.pfchoice.core.dao.EmailTemplatesDao#findById(java.lang.Integer)
	 */
	@Override
	public EmailTemplate findById(final Integer id) {
		return get(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.EmailTemplatesDao#save(com.pfchoice.core.entity.EmailTemplates)
	 */
	@Override
	public EmailTemplate save(final EmailTemplate bean) {
		getSession().save(bean);
		return bean;
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.EmailTemplatesDao#deleteById(java.lang.Integer)
	 */
	@Override
	public EmailTemplate deleteById(final Integer id) {
		EmailTemplate entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	/* (non-Javadoc)
	 * @see ml.rugal.sshcommon.hibernate.HibernateBaseDao#getEntityClass()
	 */
	@Override
	protected Class<EmailTemplate> getEntityClass() {
		return EmailTemplate.class;
	}
}
