package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.ZipCodeDao;
import com.pfchoice.core.entity.ZipCode;
import com.pfchoice.core.service.ZipCodeService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class ZipCodeServiceImpl implements ZipCodeService
{

    @Autowired
    private ZipCodeDao zipCodeDao;

    @Override
    public ZipCode deleteById(final Integer id)
    {
        //Used for transaction test
        return zipCodeDao.deleteById(id);
//        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional(readOnly = true)
    public ZipCode findById(final Integer id)
    {
        return zipCodeDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(final int pageNo,final  int pageSize)
    {
        return zipCodeDao.getPage(pageNo, pageSize);
    }

    @Override
    public ZipCode save(final ZipCode bean)
    {
        //Used for transaction test
        return zipCodeDao.save(bean);
//        this.deleteById(1);
//        return null;
    }

    @Override
    public ZipCode update(final ZipCode bean)
    {
        Updater<ZipCode> updater = new Updater<>(bean);
        return zipCodeDao.updateByUpdater(updater);
    }

    @Override
    public List<ZipCode> findAll()
    {
    	return zipCodeDao.findAll();
    }
    
    @Override
    public List<ZipCode> findByStateCode(Integer stateCode)
    {
     return zipCodeDao.findByStateCode(stateCode);
    }
}
