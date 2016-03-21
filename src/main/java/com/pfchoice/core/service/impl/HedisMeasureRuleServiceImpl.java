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
    private HedisMeasureRuleDao hedisMeasureRuleDao;

    @Override
    public HedisMeasureRule deleteById(final Integer id)
    {
        //Used for transaction test
        return hedisMeasureRuleDao.deleteById(id);
//        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional(readOnly = true)
    public HedisMeasureRule findById(final Integer id)
    {
        return hedisMeasureRuleDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(final int pageNo,final  int pageSize,
    		final String sSearch, final String sort, final String sortdir)
    {
        return hedisMeasureRuleDao.getPage(pageNo, pageSize, sSearch, sort, sortdir);
    }

    @Override
    public HedisMeasureRule save(final HedisMeasureRule bean)
    {
        //Used for transaction test
        return hedisMeasureRuleDao.save(bean);
//        this.deleteById(1);
//        return null;
    }

    @Override
    public HedisMeasureRule update(final HedisMeasureRule bean)
    {
        Updater<HedisMeasureRule> updater = new Updater<>(bean);
        return hedisMeasureRuleDao.updateByUpdater(updater);
    }
    
    @Override
    public List<HedisMeasureRule> findAll()
    {
    	return hedisMeasureRuleDao.findAll();
    }

    @Override
    public List<HedisMeasureRule> findAllByInsId(final Integer insId)
    {
    	return hedisMeasureRuleDao.findAllByInsId(insId);
    }

}
