package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pfchoice.core.dao.InsuranceContractDao;
import com.pfchoice.core.entity.InsuranceContract;
import com.pfchoice.core.service.InsuranceContractService;

/**
 *
 * @author Mohanasundharam
 */
@Service
@Transactional
public class InsuranceContractServiceImpl implements InsuranceContractService
{

    @Autowired
    private InsuranceContractDao insuranceContractDao;

    @Override
    public InsuranceContract deleteById(Integer id)
    {
        //Used for transaction test
        return insuranceContractDao.deleteById(id);
//        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional(readOnly = true)
    public InsuranceContract findById(Integer id)
    {
        return insuranceContractDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(int pageNo, int pageSize)
    {
        return insuranceContractDao.getPage(pageNo, pageSize);
    }

    @Override
    public InsuranceContract save(InsuranceContract bean)
    {
        //Used for transaction test
        return insuranceContractDao.save(bean);
//        this.deleteById(1);
//        return null;
    }

    @Override
    public InsuranceContract update(InsuranceContract bean)
    {
        Updater<InsuranceContract> updater = new Updater<>(bean);
        return insuranceContractDao.updateByUpdater(updater);
    }

    @Override
    public List<InsuranceContract> findAll()
    {
    	return insuranceContractDao.findAll();
    }
}
