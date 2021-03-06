package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.ContactDao;
import com.pfchoice.core.entity.Contact;

/**
 *
 * @author Sarath
 */
@Repository
public class ContactDaoImpl extends HibernateBaseDao<Contact, Integer> implements ContactDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.ContactDao#getPage(int, int)
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
	 * @see com.pfchoice.core.dao.ContactDao#getPage(int, int, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {

		Criteria crit = getSession().createCriteria(getEntityClass(), "contact");
		crit.createAlias("contact.refContact", "refContact");
		crit.createAlias("refContact.prvdr", "prvdr");
		crit.add(Restrictions.isNotNull("refContact.prvdr.id"));

		if (sSearch != null && !"".equals(sSearch)) {
			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.ilike("email", "%" + sSearch + "%"))
					.add(Restrictions.ilike("prvdr.name", "%" + sSearch + "%"))
					.add(Restrictions.ilike("contactPerson", "%" + sSearch + "%"));
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.ContactDao#findById(java.lang.Integer)
	 */
	@Override
	public Contact findById(final Integer id) {
		return get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.dao.ContactDao#save(com.pfchoice.core.entity.Contact)
	 */
	@Override
	public Contact save(final Contact bean) {
		getSession().save(bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.ContactDao#deleteById(java.lang.Integer)
	 */
	@Override
	public Contact deleteById(final Integer id) {
		Contact entity = super.get(id);
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
	protected Class<Contact> getEntityClass() {
		return Contact.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> findAllContactsByRefId(final String refString, final Integer id) {
		String refRestrictionString;
		if ("membership".equals(refString)) {
			refRestrictionString = "refContact.mbr.id";
		}else if ("leadMembership".equals(refString)) {
			refRestrictionString = "refContact.leadMbr.id";
		} 		
		else if ("provider".equals(refString)) {
			refRestrictionString = "refContact.prvdr.id";
		} else {
			refRestrictionString = "refContact.ins.id";
		}

		Criteria cr = getSession().createCriteria(getEntityClass(), "contact")
				.createAlias("contact.refContact", "refContact").add(Restrictions.eq(refRestrictionString, id));
		return cr.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.ContactDao#findActiveContactByRefId(java.lang.
	 * String, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Contact findActiveContactByRefId(final String refString, final Integer id) {
		Contact contact = null;
		String refRestrictionString;
		if ("membership".equals(refString)) {
			refRestrictionString = "refContact.mbr.id";
		} else if ("provider".equals(refString)) {
			refRestrictionString = "refContact.prvdr.id";
		} else {
			refRestrictionString = "refContact.ins.id";
		}

		Criteria cr = getSession().createCriteria(getEntityClass(), "contact")
				.createAlias("contact.refContact", "refContact").add(Restrictions.eq(refRestrictionString, id))

				.add(Restrictions.eq("contact.activeInd", 'Y'));
		List<Contact> list = cr.list();
		if (!list.isEmpty())
			contact = list.get(0);
		return contact;
	}
}
