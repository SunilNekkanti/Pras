package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.CPTMeasureDao;
import com.pfchoice.core.entity.CPTMeasure;
import com.pfchoice.core.service.CPTMeasureService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class CPTMeasureServiceImpl implements CPTMeasureService
{

    @Autowired
    private CPTMeasureDao cptMeasureDao;

    @Override
    public CPTMeasure deleteById(Integer id)
    {
        //Used for transaction test
        return cptMeasureDao.deleteById(id);
//        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional(readOnly = true)
    public CPTMeasure findById(Integer id)
    {
        return cptMeasureDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(int pageNo, int pageSize)
    {
        return cptMeasureDao.getPage(pageNo, pageSize);
    }

    @Override
    public CPTMeasure save(CPTMeasure bean)
    {
        //Used for transaction test
        return cptMeasureDao.save(bean);
//        this.deleteById(1);
//        return null;
    }

    @Override
    public CPTMeasure update(CPTMeasure bean)
    {
        Updater<CPTMeasure> updater = new Updater<>(bean);
        return cptMeasureDao.updateByUpdater(updater);
    }

    @Override
    public List<CPTMeasure> findAll()
    {
    	return cptMeasureDao.findAll();
    }
}
