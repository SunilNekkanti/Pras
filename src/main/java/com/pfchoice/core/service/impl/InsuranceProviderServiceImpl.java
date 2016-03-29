package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.InsuranceProviderDao;
import com.pfchoice.core.entity.InsuranceProvider;
import com.pfchoice.core.service.InsuranceProviderService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class InsuranceProviderServiceImpl implements InsuranceProviderService
{

    @Autowired
    private InsuranceProviderDao InsuranceProviderDao;

    @Override
    public InsuranceProvider deleteById(final Integer id)
    {
        //Used for transaction test
        return InsuranceProviderDao.deleteById(id);
//        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional(readOnly = true)
    public InsuranceProvider findById(final Integer id)
    {
        return InsuranceProviderDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(final int pageNo,final  int pageSize)
    {
        return InsuranceProviderDao.getPage(pageNo, pageSize);
    }

    @Override
    public InsuranceProvider save(final InsuranceProvider bean)
    {
        //Used for transaction test
        return InsuranceProviderDao.save(bean);
//        this.deleteById(1);
//        return null;
    }

    @Override
    public InsuranceProvider update(final InsuranceProvider bean)
    {
        Updater<InsuranceProvider> updater = new Updater<>(bean);
        return InsuranceProviderDao.updateByUpdater(updater);
    }

    @Override
    public List<InsuranceProvider> findAll()
    {
    	return InsuranceProviderDao.findAll();
    }
    
    @Override
    public List<InsuranceProvider> findAllByPrvdrId(final Integer id)
    {
    	return InsuranceProviderDao.findAllByPrvdrId(id);
    }
    
}
