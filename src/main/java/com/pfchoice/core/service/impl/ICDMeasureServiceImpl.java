package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.ICDMeasureDao;
import com.pfchoice.core.entity.ICDMeasure;
import com.pfchoice.core.service.ICDMeasureService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class ICDMeasureServiceImpl implements ICDMeasureService
{

    @Autowired
    private ICDMeasureDao icdMeasureDao;

    @Override
    public ICDMeasure deleteById(final Integer id)
    {
        //Used for transaction test
        return icdMeasureDao.deleteById(id);
//        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional(readOnly = true)
    public ICDMeasure findById(final Integer id)
    {
        return icdMeasureDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(final int pageNo,final  int pageSize, final String sSearch)
    {
        return icdMeasureDao.getPage(pageNo, pageSize, sSearch);
    }

    @Override
    public ICDMeasure save(final ICDMeasure bean)
    {
        //Used for transaction test
        return icdMeasureDao.save(bean);
//        this.deleteById(1);
//        return null;
    }

    @Override
    public ICDMeasure update(final ICDMeasure bean)
    {
        Updater<ICDMeasure> updater = new Updater<>(bean);
        return icdMeasureDao.updateByUpdater(updater);
    }

    @Override
    public List<ICDMeasure> findAll()
    {
    	return icdMeasureDao.findAll();
    }
}
