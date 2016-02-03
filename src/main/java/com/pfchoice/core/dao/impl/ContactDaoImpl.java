package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.ContactDao;
import com.pfchoice.core.entity.Contact;

/**
 *
 * @author Sarath
 */
@Repository
public class ContactDaoImpl extends HibernateBaseDao<Contact, Integer> implements ContactDao
{

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ContactDaoImpl.class
        .getName());

    @Override
    public Pagination getPage(final int pageNo, final int pageSize)
    {
        Criteria crit = createCriteria();
        Pagination page = findByCriteria(crit, pageNo, pageSize);
        return page;
    }

    @Override
    public Contact findById(final Integer id)
    {
    	Contact entity = get(id);
        return entity;
    }

    @Override
    public Contact save(final Contact bean)
    {
        getSession().save(bean);
        return bean;
    }

    @Override
    public Contact deleteById(final Integer id)
    {
//        throw new UnsupportedOperationException();
    	Contact entity = super.get(id);
        if (entity != null)
        {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    protected Class<Contact> getEntityClass()
    {
        return Contact.class;
    }

    @SuppressWarnings("unchecked")
 	public List<Contact> findAllContactsByRefId(final String refString, final Integer id)
     {
    	String refRestrictionString = null;
    	if("membership".equals(refString)){
    		refRestrictionString = "refContact.mbr.id";
			}else if("provider".equals(refString)){
				refRestrictionString = "refContact.prvdr.id";
			}else {
				refRestrictionString = "refContact.ins.id";
			}
    	
     	Criteria cr = getSession().createCriteria(getEntityClass(), "contact")
     			.createAlias("contact.refContact","refContact")
     			.add(Restrictions.eq(refRestrictionString, id));
     	List<Contact> list = cr.list();
     	System.out.println("findAllByRefId list size is"+ list.size());
     	return list;
     }
    
    @SuppressWarnings("unchecked")
 	public Contact findActiveContactByRefId(final String refString, final Integer id)
     {
    	Contact contact  = null;
    	String refRestrictionString = null;
    	if("membership".equals(refString)){
    		refRestrictionString = "refContact.mbr.id";
		}else if("provider".equals(refString)){
				refRestrictionString = "refContact.prvdr.id";
		}else {
				refRestrictionString = "refContact.ins.id";
		}
				
     	Criteria cr = getSession().createCriteria(getEntityClass(), "contact")
     			.createAlias("contact.refContact","refContact")
     				.add(Restrictions.eq(refRestrictionString, id))
     			
     			 .add(Restrictions.eq("contact.activeInd", 'Y'));
     	List<Contact> list = cr.list();
     	System.out.println("findAllByRefId list size is"+ list.size());
     	if(list.size()>0)
     		contact =list.get(0);
     	return contact;
     }
}
