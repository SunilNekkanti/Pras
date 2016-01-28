package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.HedisMeasureRuleDao;
import com.pfchoice.core.entity.HedisMeasureRule;
import com.pfchoice.core.service.HedisMeasureRuleService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class HedisMeasureRuleServiceImpl implements HedisMeasureRuleService
{

    @Autowired
    private HedisMeasureRuleDao hedisMeasureMeasureDao;

    @Override
    public HedisMeasureRule deleteById(Integer id)
    {
        //Used for transaction test
        return hedisMeasureMeasureDao.deleteById(id);
//        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional(readOnly = true)
    public HedisMeasureRule findById(Integer id)
    {
        return hedisMeasureMeasureDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(int pageNo, int pageSize)
    {
        return hedisMeasureMeasureDao.getPage(pageNo, pageSize);
    }

    @Override
    public HedisMeasureRule save(HedisMeasureRule bean)
    {
        //Used for transaction test
        return hedisMeasureMeasureDao.save(bean);
//        this.deleteById(1);
//        return null;
    }

    @Override
    public HedisMeasureRule update(HedisMeasureRule bean)
    {
        Updater<HedisMeasureRule> updater = new Updater<>(bean);
        return hedisMeasureMeasureDao.updateByUpdater(updater);
    }

    @Override
    public List<HedisMeasureRule> findAll()
    {
    	return hedisMeasureMeasureDao.findAll();
    }
}
