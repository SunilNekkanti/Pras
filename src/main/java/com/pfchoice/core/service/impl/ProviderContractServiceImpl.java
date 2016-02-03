package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pfchoice.core.dao.ProviderContractDao;
import com.pfchoice.core.entity.ProviderContract;
import com.pfchoice.core.service.ProviderContractService;

/**
 *
 * @author Mohanasundharam
 */
@Service
@Transactional
public class ProviderContractServiceImpl implements ProviderContractService
{

    @Autowired
    private ProviderContractDao providerContractDao;

    @Override
    public ProviderContract deleteById(final Integer id)
    {
        //Used for transaction test
        return providerContractDao.deleteById(id);
//        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional(readOnly = true)
    public ProviderContract findById(final Integer id)
    {
        return providerContractDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(final int pageNo,final  int pageSize)
    {
        return providerContractDao.getPage(pageNo, pageSize);
    }

    @Override
    public ProviderContract save(final ProviderContract bean)
    {
        //Used for transaction test
        return providerContractDao.save(bean);
//        this.deleteById(1);
//        return null;
    }

    @Override
    public ProviderContract update(final ProviderContract bean)
    {
        Updater<ProviderContract> updater = new Updater<>(bean);
        return providerContractDao.updateByUpdater(updater);
    }

    @Override
    public List<ProviderContract> findAll()
    {
    	return providerContractDao.findAll();
    }
}
