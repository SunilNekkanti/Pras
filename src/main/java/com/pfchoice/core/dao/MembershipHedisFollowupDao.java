package com.pfchoice.core.dao;

import java.util.List;

import com.pfchoice.core.entity.MembershipHedisFollowup;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Mohanasundharam
 */
public interface MembershipHedisFollowupDao
{

	MembershipHedisFollowup deleteById(Integer id);

	MembershipHedisFollowup findById(Integer id);

    Pagination getPage(int pageNo, int pageSize);

    MembershipHedisFollowup save(MembershipHedisFollowup bean);

    MembershipHedisFollowup updateByUpdater(Updater<MembershipHedisFollowup> updater);
    
    List<MembershipHedisFollowup> findAll();

}
