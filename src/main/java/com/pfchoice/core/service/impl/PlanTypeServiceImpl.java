package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.PlanTypeDao;
import com.pfchoice.core.entity.PlanType;
import com.pfchoice.core.service.PlanTypeService;

/**
 *
 * @author Mohanasundharam
 */
@Service
@Transactional
public class PlanTypeServiceImpl implements PlanTypeService
{

    @Autowired
    private PlanTypeDao planTypeDao;

    @Override
    public PlanType deleteById(final Integer id)
    {
        //Used for transaction test
        return planTypeDao.deleteById(id);
//        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional(readOnly = true)
    public PlanType findById(final Integer id)
    {
        return planTypeDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(final int pageNo, final int pageSize)
    {
        return planTypeDao.getPage(pageNo, pageSize);
    }

    @Override
    public PlanType save(final PlanType bean)
    {
        //Used for transaction test
        return planTypeDao.save(bean);
//        this.deleteById(1);
//        return null;
    }

    @Override
    public PlanType update(final PlanType bean)
    {
        Updater<PlanType> updater = new Updater<>(bean);
        return planTypeDao.updateByUpdater(updater);
    }

    @Override
    public List<PlanType> findAll()
    {
    	return planTypeDao.findAll();
    }
}
