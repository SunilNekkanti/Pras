package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.ContactDao;
import com.pfchoice.core.entity.Contact;
import com.pfchoice.core.entity.MembershipInsurance;
import com.pfchoice.core.service.ContactService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class ContactServiceImpl implements ContactService
{

    @Autowired
    private ContactDao contactDao;

    @Override
    public Contact deleteById(final Integer id)
    {
        //Used for transaction test
        return contactDao.deleteById(id);
//        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional(readOnly = true)
    public Contact findById(final Integer id)
    {
        return contactDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(final int pageNo, final int pageSize)
    {
        return contactDao.getPage(pageNo, pageSize);
    }

    @Override
    public Contact save(final Contact bean)
    {
        //Used for transaction test
        return contactDao.save(bean);
//        this.deleteById(1);
//        return null;
    }

    @Override
    public Contact update(final Contact bean)
    {
        Updater<Contact> updater = new Updater<>(bean);
        return contactDao.updateByUpdater(updater);
    }

    @Override
    public List<Contact> findAllContactsByRefId(final String refString, final Integer id)
    {
    	return contactDao.findAllContactsByRefId(refString, id);
    }

    @Override
    public Contact findActiveContactByRefId(final String refString, final Integer id)
    {
    	return contactDao.findActiveContactByRefId(refString, id);
    }
}
