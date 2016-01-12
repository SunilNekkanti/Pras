package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.ProviderDao;
import com.pfchoice.core.entity.Provider;
import com.pfchoice.core.service.ProviderService;

/**
 *
 * @author Mohanasundharam
 */
@Service
@Transactional
public class ProviderServiceImpl implements ProviderService
{

    @Autowired
    private ProviderDao providerDao;

    @Override
    public Provider deleteById(Integer id)
    {
        //Used for transaction test
        return providerDao.deleteById(id);
//        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional(readOnly = true)
    public Provider findById(Integer id)
    {
        return providerDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(int pageNo, int pageSize)
    {
        return providerDao.getPage(pageNo, pageSize);
    }

    @Override
    public Provider save(Provider bean)
    {
        //Used for transaction test
        return providerDao.save(bean);
//        this.deleteById(1);
//        return null;
    }

    @Override
    public Provider update(Provider bean)
    {
        Updater<Provider> updater = new Updater<>(bean);
        return providerDao.updateByUpdater(updater);
    }
    
    @Override
    public List<Provider> findAll()
    {
    	return providerDao.findAll();
    }

}
