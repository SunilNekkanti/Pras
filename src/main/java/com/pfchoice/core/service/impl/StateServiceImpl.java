package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.StateDao;
import com.pfchoice.core.entity.State;
import com.pfchoice.core.service.StateService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class StateServiceImpl implements StateService
{

    @Autowired
    private StateDao stateDao;

    @Override
    public State deleteById(Integer id)
    {
        //Used for transaction test
        return stateDao.deleteById(id);
//        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional(readOnly = true)
    public State findById(Integer id)
    {
        return stateDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(int pageNo, int pageSize)
    {
        return stateDao.getPage(pageNo, pageSize);
    }

    @Override
    public State save(State bean)
    {
        //Used for transaction test
        return stateDao.save(bean);
//        this.deleteById(1);
//        return null;
    }

    @Override
    public State update(State bean)
    {
        Updater<State> updater = new Updater<>(bean);
        return stateDao.updateByUpdater(updater);
    }

    @Override
    public List<State> findAll()
    {
    	return stateDao.findAll();
    }
}
