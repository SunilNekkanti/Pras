package com.pfchoice.core.service;

import java.util.List;

import com.pfchoice.core.entity.MembershipHedisFollowup;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Mohanasundharam
 */
public interface MembershipHedisFollowupService {

	MembershipHedisFollowup deleteById(Integer id);

	MembershipHedisFollowup findById(Integer id);

	Pagination getPage(int pageNo, int pageSize);

	MembershipHedisFollowup save(MembershipHedisFollowup bean);

	MembershipHedisFollowup update(MembershipHedisFollowup bean);

	List<MembershipHedisFollowup> findAll();

	List<MembershipHedisFollowup> findAllByMbrId(Integer id);

}
