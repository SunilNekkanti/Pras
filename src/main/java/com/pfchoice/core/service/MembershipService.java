package com.pfchoice.core.service;

import java.util.List;

import com.pfchoice.core.entity.Membership;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface MembershipService
{

	Membership deleteById(Integer id);

	Membership findById(Integer id);

    Pagination getPage(int pageNo, int pageSize);

    Membership save(Membership bean);

    Membership update(Membership bean);
    
    List<Membership> findAll();

}
