package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.MembershipProviderDao;
import com.pfchoice.core.entity.MembershipProvider;
import com.pfchoice.core.service.MembershipProviderService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class MembershipProviderServiceImpl implements MembershipProviderService
{

    @Autowired
    private MembershipProviderDao membershipProviderDao;

    @Override
    public MembershipProvider deleteById(Integer id)
    {
        //Used for transaction test
        return membershipProviderDao.deleteById(id);
//        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional(readOnly = true)
    public MembershipProvider findById(Integer id)
    {
        return membershipProviderDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(int pageNo, int pageSize)
    {
        return membershipProviderDao.getPage(pageNo, pageSize);
    }

    @Override
    public MembershipProvider save(MembershipProvider bean)
    {
        //Used for transaction test
        return membershipProviderDao.save(bean);
//        this.deleteById(1);
//        return null;
    }

    @Override
    public MembershipProvider update(MembershipProvider bean)
    {
        Updater<MembershipProvider> updater = new Updater<>(bean);
        return membershipProviderDao.updateByUpdater(updater);
    }

    @Override
    public List<MembershipProvider> findAll()
    {
    	return membershipProviderDao.findAll();
    }
    
    @Override
    public List<MembershipProvider> findAllByMbrId(Integer id)
    {
    	return membershipProviderDao.findAllByMbrId(id);
    }
}
