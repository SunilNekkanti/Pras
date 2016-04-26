package com.pfchoice.core.service;

import java.util.List;

import com.pfchoice.core.entity.MembershipHedisFollowup;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Mohanasundharam
 */
public interface MembershipHedisFollowupService {

	/**
	 * @param id
	 * @return
	 */
	MembershipHedisFollowup deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	MembershipHedisFollowup findById(Integer id);

	/**
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize);

	/**
	 * @param bean
	 * @return
	 */
	MembershipHedisFollowup save(MembershipHedisFollowup bean);

	/**
	 * @param bean
	 * @return
	 */
	MembershipHedisFollowup update(MembershipHedisFollowup bean);

	/**
	 * @param id
	 * @return
	 */
	List<MembershipHedisFollowup> findAllByMbrId(Integer id);

}
