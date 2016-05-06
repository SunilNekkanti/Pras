package com.pfchoice.core.service;

import java.util.List;

import com.pfchoice.core.entity.MembershipFollowup;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Mohanasundharam
 */
public interface MembershipFollowupService {

	/**
	 * @param id
	 * @return
	 */
	MembershipFollowup deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	MembershipFollowup findById(Integer id);

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
	MembershipFollowup save(MembershipFollowup bean);

	/**
	 * @param bean
	 * @return
	 */
	MembershipFollowup update(MembershipFollowup bean);

	/**
	 * @param id
	 * @return
	 */
	List<MembershipFollowup> findAllByMbrId(Integer id, String followupTypeCode);

}
