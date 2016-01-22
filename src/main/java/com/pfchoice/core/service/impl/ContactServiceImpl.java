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
    public Contact deleteById(Integer id)
    {
        //Used for transaction test
        return contactDao.deleteById(id);
//        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional(readOnly = true)
    public Contact findById(Integer id)
    {
        return contactDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(int pageNo, int pageSize)
    {
        return contactDao.getPage(pageNo, pageSize);
    }

    @Override
    public Contact save(Contact bean)
    {
        //Used for transaction test
        return contactDao.save(bean);
//        this.deleteById(1);
//        return null;
    }

    @Override
    public Contact update(Contact bean)
    {
        Updater<Contact> updater = new Updater<>(bean);
        return contactDao.updateByUpdater(updater);
    }

    @Override
    public List<Contact> findAllContactsByRefId(String refString, Integer id)
    {
    	return contactDao.findAllContactsByRefId(refString, id);
    }

    @Override
    public Contact findActiveContactByRefId(String refString, Integer id)
    {
    	return contactDao.findActiveContactByRefId(refString, id);
    }
}
