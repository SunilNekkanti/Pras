package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.GenderDao;
import com.pfchoice.core.entity.Gender;
import com.pfchoice.core.service.GenderService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class GenderServiceImpl implements GenderService
{

    @Autowired
    private GenderDao genderDao;

    @Override
    public Gender deleteById(Byte id)
    {
        //Used for transaction test
        return genderDao.deleteById(id);
//        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional(readOnly = true)
    public Gender findById(Byte id)
    {
        return genderDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(int pageNo, int pageSize)
    {
        return genderDao.getPage(pageNo, pageSize);
    }

    @Override
    public Gender save(Gender bean)
    {
        //Used for transaction test
        return genderDao.save(bean);
//        this.deleteById(1);
//        return null;
    }

    @Override
    public Gender update(Gender bean)
    {
        Updater<Gender> updater = new Updater<>(bean);
        return genderDao.updateByUpdater(updater);
    }
    
    @Override
    public List<Gender> findAll()
    {
    	return genderDao.findAll();
    }

}
