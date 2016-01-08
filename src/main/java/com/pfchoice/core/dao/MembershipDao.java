package com.pfchoice.core.dao;

import java.util.List;

import com.pfchoice.core.entity.Membership;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface MembershipDao
{

	Membership deleteById(Integer id);

	Membership findById(Integer id);

    Pagination getPage(int pageNo, int pageSize);

    Membership save(Membership bean);

    Membership updateByUpdater(Updater<Membership> updater);
    
    List<Membership> findAll();

}
