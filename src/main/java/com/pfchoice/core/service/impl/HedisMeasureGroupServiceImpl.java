package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.HedisMeasureGroupDao;
import com.pfchoice.core.entity.HedisMeasureGroup;
import com.pfchoice.core.service.HedisMeasureGroupService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class HedisMeasureGroupServiceImpl implements HedisMeasureGroupService
{

    @Autowired
    private HedisMeasureGroupDao hedisMeasureGroupDao;

    @Override
    public HedisMeasureGroup deleteById(Integer id)
    {
        //Used for transaction test
        return hedisMeasureGroupDao.deleteById(id);
//        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional(readOnly = true)
    public HedisMeasureGroup findById(Integer id)
    {
        return hedisMeasureGroupDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(int pageNo, int pageSize)
    {
        return hedisMeasureGroupDao.getPage(pageNo, pageSize);
    }

    @Override
    public HedisMeasureGroup save(HedisMeasureGroup bean)
    {
        //Used for transaction test
        return hedisMeasureGroupDao.save(bean);
//        this.deleteById(1);
//        return null;
    }

    @Override
    public HedisMeasureGroup update(HedisMeasureGroup bean)
    {
        Updater<HedisMeasureGroup> updater = new Updater<>(bean);
        return hedisMeasureGroupDao.updateByUpdater(updater);
    }

    @Override
    public List<HedisMeasureGroup> findAll()
    {
    	return hedisMeasureGroupDao.findAll();
    }
}
