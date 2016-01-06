package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.MembershipStatusDao;
import com.pfchoice.core.entity.MembershipStatus;
import com.pfchoice.core.service.MembershipStatusService;

/**
 *
 * @author Mohanasundharam
 */
@Service
@Transactional
public class MembershipStatusServiceImpl implements MembershipStatusService
{

    @Autowired
    private MembershipStatusDao membershipStatusDao;

    @Override
    public MembershipStatus deleteById(Byte id)
    {
        //Used for transaction test
        return membershipStatusDao.deleteById(id);
//        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional(readOnly = true)
    public MembershipStatus findById(Byte id)
    {
        return membershipStatusDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(int pageNo, int pageSize)
    {
        return membershipStatusDao.getPage(pageNo, pageSize);
    }

    @Override
    public MembershipStatus save(MembershipStatus bean)
    {
        //Used for transaction test
        return membershipStatusDao.save(bean);
//        this.deleteById(1);
//        return null;
    }

    @Override
    public MembershipStatus update(MembershipStatus bean)
    {
        Updater<MembershipStatus> updater = new Updater<>(bean);
        return membershipStatusDao.updateByUpdater(updater);
    }

}
