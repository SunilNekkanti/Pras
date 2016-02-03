package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.CountyDao;
import com.pfchoice.core.entity.County;
import com.pfchoice.core.service.CountyService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class CountyServiceImpl implements CountyService
{

    @Autowired
    private CountyDao countyDao;

    @Override
    public County deleteById(final Integer id)
    {
        //Used for transaction test
        return countyDao.deleteById(id);
//        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional(readOnly = true)
    public County findById(final Integer id)
    {
        return countyDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(final int pageNo,final  int pageSize)
    {
        return countyDao.getPage(pageNo, pageSize);
    }

    @Override
    public County save(final County bean)
    {
        //Used for transaction test
        return countyDao.save(bean);
//        this.deleteById(1);
//        return null;
    }

    @Override
    public County update(final County bean)
    {
        Updater<County> updater = new Updater<>(bean);
        return countyDao.updateByUpdater(updater);
    }

    @Override
    public List<County> findAll()
    {
    	return countyDao.findAll();
    }
}
