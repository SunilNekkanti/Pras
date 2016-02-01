package com.pfchoice.core.dao;

import java.util.List;

import com.pfchoice.core.entity.MembershipProvider;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface MembershipProviderDao
{

	MembershipProvider deleteById(Integer id);

	MembershipProvider findById(Integer id);

    Pagination getPage(int pageNo, int pageSize);

    MembershipProvider save(MembershipProvider bean);

    MembershipProvider updateByUpdater(Updater<MembershipProvider> updater);
    
    List<MembershipProvider> findAll();
    
    List<MembershipProvider> findAllByMbrId(Integer id);
    
    MembershipProvider findByMbrId(Integer id);

}
