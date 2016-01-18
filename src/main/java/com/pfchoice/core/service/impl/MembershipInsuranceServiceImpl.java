package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.MembershipInsuranceDao;
import com.pfchoice.core.entity.MembershipInsurance;
import com.pfchoice.core.service.MembershipInsuranceService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class MembershipInsuranceServiceImpl implements MembershipInsuranceService
{

    @Autowired
    private MembershipInsuranceDao membershipInsuranceDao;

    @Override
    public MembershipInsurance deleteById(Integer id)
    {
        //Used for transaction test
        return membershipInsuranceDao.deleteById(id);
//        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional(readOnly = true)
    public MembershipInsurance findById(Integer id)
    {
        return membershipInsuranceDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(int pageNo, int pageSize)
    {
        return membershipInsuranceDao.getPage(pageNo, pageSize);
    }

    @Override
    public MembershipInsurance save(MembershipInsurance bean)
    {
        //Used for transaction test
        return membershipInsuranceDao.save(bean);
//        this.deleteById(1);
//        return null;
    }

    @Override
    public MembershipInsurance update(MembershipInsurance bean)
    {
        Updater<MembershipInsurance> updater = new Updater<>(bean);
        return membershipInsuranceDao.updateByUpdater(updater);
    }

    @Override
    public List<MembershipInsurance> findAll()
    {
    	return membershipInsuranceDao.findAll();
    }
}
