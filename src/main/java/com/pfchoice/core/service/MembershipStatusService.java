package com.pfchoice.core.service;

import java.util.List;

import com.pfchoice.core.entity.MembershipStatus;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Mohanasundharam
 */
public interface MembershipStatusService
{

	MembershipStatus deleteById(Byte id);

	MembershipStatus findById(Byte id);

    Pagination getPage(int pageNo, int pageSize);

    MembershipStatus save(MembershipStatus bean);

    MembershipStatus update(MembershipStatus bean);

	List<MembershipStatus> findAll();
}
