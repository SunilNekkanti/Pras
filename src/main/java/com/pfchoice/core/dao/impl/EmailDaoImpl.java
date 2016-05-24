package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.EmailDao;
import com.pfchoice.core.entity.Email;

/**
 *
 * @author Mohanasundharam
 */
@Repository
public class EmailDaoImpl extends HibernateBaseDao<Email, Integer> implements EmailDao {

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
		Criteria crit = createCriteria().createAlias("emailTemplate", "emailTemplate");
		if (sSearch != null && !"".equals(sSearch)) {
			Disjunction or = Restrictions.disjunction();

			or.add(Restrictions.ilike("emailTemplate.description", "%" + sSearch + "%"))
					.add(Restrictions.ilike("body", "%" + sSearch + "%"))
					.add(Restrictions.ilike("emailTo", "%" + sSearch + "%"))
					.add(Restrictions.ilike("emailFrom", "%" + sSearch + "%"));
			crit.add(or);
		}
		crit.add(Restrictions.eq("activeInd", 'Y'));
		
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
	 * @see com.pfchoice.core.dao.EmailsDao#findById(java.lang.Integer)
	 */
	@Override
	public Email findById(final Integer id) {
		return get(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.EmailsDao#save(com.pfchoice.core.entity.Emails)
	 */
	@Override
	public Email save(final Email bean) {
		getSession().save(bean);
		return bean;
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.EmailsDao#deleteById(java.lang.Integer)
	 */
	@Override
	public Email deleteById(final Integer id) {
		Email entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	/* (non-Javadoc)
	 * @see ml.rugal.sshcommon.hibernate.HibernateBaseDao#getEntityClass()
	 */
	@Override
	protected Class<Email> getEntityClass() {
		return Email.class;
	}
}
