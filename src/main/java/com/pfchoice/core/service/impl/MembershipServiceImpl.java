package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.MembershipDao;
import com.pfchoice.core.entity.Membership;
import com.pfchoice.core.service.MembershipService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class MembershipServiceImpl implements MembershipService
{

    @Autowired
    private MembershipDao membershipDao;

    @Override
    public Membership deleteById(final Integer id)
    {
        //Used for transaction test
        return membershipDao.deleteById(id);
//        throw new UnsupportedOperationException();
    }

    @Override
  //  @Transactional(readOnly = true)
    public Membership findById(final Integer id)
    {
        return membershipDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(final int pageNo,final int pageSize, final String sSearch, 
    		final String sort, final String sortdir)
    {
        return membershipDao.getPage(pageNo, pageSize, sSearch, sort, sortdir);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(final int pageNo,final int pageSize, final String sSearch, 
    		final int sSearchIns,   final int sSearchPrvdr, final int sSearchHedisCode, 
    		final String sort, final String sortdir)
    {
        return membershipDao.getPage(pageNo, pageSize, sSearch, sSearchIns,
        		sSearchPrvdr, sSearchHedisCode,  sort, sortdir);
    }
    
    

    @Override
    public Membership save(final Membership bean)
    {
        //Used for transaction test
        return membershipDao.save(bean);
//        this.deleteById(1);
//        return null;
    }

    @Override
    public Membership update(final Membership bean)
    {
        Updater<Membership> updater = new Updater<>(bean);
        return membershipDao.updateByUpdater(updater);
    }

}
