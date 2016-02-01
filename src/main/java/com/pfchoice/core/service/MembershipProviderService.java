package com.pfchoice.core.service;

import java.util.List;

import com.pfchoice.core.entity.MembershipProvider;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface MembershipProviderService
{

	MembershipProvider deleteById(Integer id);

	MembershipProvider findById(Integer id);

    Pagination getPage(int pageNo, int pageSize);

    MembershipProvider save(MembershipProvider bean);

    MembershipProvider update(MembershipProvider bean);
    
    List<MembershipProvider> findAll();
    
    List<MembershipProvider> findAllByMbrId(Integer id);
    
    MembershipProvider findByMbrId(final Integer id);

}
