package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.EthinicityDao;
import com.pfchoice.core.entity.Ethinicity;
import com.pfchoice.core.service.EthinicityService;

/**
 *
 * @author Mohanasundharam
 */
@Service
@Transactional
public class EthinicityServiceImpl implements EthinicityService
{

    @Autowired
    private EthinicityDao ethinicityDao;

    @Override
    public Ethinicity deleteById(Byte id)
    {
        //Used for transaction test
        return ethinicityDao.deleteById(id);
//        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional(readOnly = true)
    public Ethinicity findById(final Byte id)
    {
        return ethinicityDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(final int pageNo, final int pageSize)
    {
        return ethinicityDao.getPage(pageNo, pageSize);
    }

    @Override
    public Ethinicity save(final Ethinicity bean)
    {
        //Used for transaction test
        return ethinicityDao.save(bean);
//        this.deleteById(1);
//        return null;
    }

    @Override
    public Ethinicity update(final Ethinicity bean)
    {
        Updater<Ethinicity> updater = new Updater<>(bean);
        return ethinicityDao.updateByUpdater(updater);
    }

    @Override
    public List<Ethinicity> findAll()
    {
    	return ethinicityDao.findAll();
    }
}
