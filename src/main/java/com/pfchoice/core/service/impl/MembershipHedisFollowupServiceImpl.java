package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.MembershipHedisFollowupDao;
import com.pfchoice.core.entity.MembershipHedisFollowup;
import com.pfchoice.core.service.MembershipHedisFollowupService;

/**
 *
 * @author Mohanasundharam
 */
@Service
@Transactional
public class MembershipHedisFollowupServiceImpl implements MembershipHedisFollowupService
{

    @Autowired
    private MembershipHedisFollowupDao mbrHedisFollowupDao;

    @Override
    public MembershipHedisFollowup deleteById(final Integer id)
    {
        //Used for transaction test
        return mbrHedisFollowupDao.deleteById(id);
//        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional(readOnly = true)
    public MembershipHedisFollowup findById(final Integer id)
    {
        return mbrHedisFollowupDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(final int pageNo, final int pageSize)
    {
        return mbrHedisFollowupDao.getPage(pageNo, pageSize);
    }

    @Override
    public MembershipHedisFollowup save(final MembershipHedisFollowup bean)
    {
        //Used for transaction test
        return mbrHedisFollowupDao.save(bean);
//        this.deleteById(1);
//        return null;
    }

    @Override
    public MembershipHedisFollowup update(final MembershipHedisFollowup bean)
    {
        Updater<MembershipHedisFollowup> updater = new Updater<>(bean);
        return mbrHedisFollowupDao.updateByUpdater(updater);
    }

    @Override
    public List<MembershipHedisFollowup> findAll()
    {
    	return mbrHedisFollowupDao.findAll();
    }
}
