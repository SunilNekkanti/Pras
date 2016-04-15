package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.MembershipHedisMeasureDao;
import com.pfchoice.core.entity.MembershipHedisMeasure;
import com.pfchoice.core.service.MembershipHedisMeasureService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class MembershipHedisMeasureServiceImpl implements MembershipHedisMeasureService
{

    @Autowired
    private MembershipHedisMeasureDao membershipHedisMeasureDao;

    @Override
    public MembershipHedisMeasure deleteById(final Integer id)
    {
        //Used for transaction test
        return membershipHedisMeasureDao.deleteById(id);
//        throw new UnsupportedOperationException();
    }

    @Override
  //  @Transactional(readOnly = true)
    public MembershipHedisMeasure findById(final Integer id)
    {
        return membershipHedisMeasureDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(final int pageNo,final int pageSize, final String sSearch, 
    		final String sort, final String sortdir)
    {
        return membershipHedisMeasureDao.getPage(pageNo, pageSize, sSearch, sort, sortdir);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(final int pageNo,final int pageSize, final String sSearch, 
    		final int sSearchIns,   final int sSearchPrvdr, final int sSearchHedisCode, 
    		final String sort, final String sortdir)
    {
        return membershipHedisMeasureDao.getPage(pageNo, pageSize, sSearch, sSearchIns,
        		sSearchPrvdr, sSearchHedisCode,  sort, sortdir);
    }
    
    

    @Override
    public MembershipHedisMeasure save(final MembershipHedisMeasure bean)
    {
        //Used for transaction test
        return membershipHedisMeasureDao.save(bean);
//        this.deleteById(1);
//        return null;
    }

    @Override
    public MembershipHedisMeasure update(final MembershipHedisMeasure bean)
    {
        Updater<MembershipHedisMeasure> updater = new Updater<>(bean);
        return membershipHedisMeasureDao.updateByUpdater(updater);
    }

    @Override
    public  Pagination findByMbrIdAndRuleId(final Integer mbrId,final Integer ruleId)
    {
    	 return membershipHedisMeasureDao.findByMbrIdAndRuleId(mbrId, ruleId);
    }
}
