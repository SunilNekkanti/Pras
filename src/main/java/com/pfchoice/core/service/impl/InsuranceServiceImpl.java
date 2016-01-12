package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.InsuranceDao;
import com.pfchoice.core.entity.Insurance;
import com.pfchoice.core.entity.Membership;
import com.pfchoice.core.service.InsuranceService;

/**
 *
 * @author Mohanasundharam
 */
@Service
@Transactional
public class InsuranceServiceImpl implements InsuranceService
{

    @Autowired
    private InsuranceDao insuranceDao;

    @Override
    public Insurance deleteById(Integer id)
    {
        //Used for transaction test
        return insuranceDao.deleteById(id);
//        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional(readOnly = true)
    public Insurance findById(Integer id)
    {
        return insuranceDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(int pageNo, int pageSize)
    {
        return insuranceDao.getPage(pageNo, pageSize);
    }

    @Override
    public Insurance save(Insurance bean)
    {
        //Used for transaction test
        return insuranceDao.save(bean);
//        this.deleteById(1);
//        return null;
    }

    @Override
    public Insurance update(Insurance bean)
    {
        Updater<Insurance> updater = new Updater<>(bean);
        return insuranceDao.updateByUpdater(updater);
    }

    @Override
    public List<Insurance> findAll()
    {
    	return insuranceDao.findAll();
    }
}
