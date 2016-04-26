package com.pfchoice.core.service;

import com.pfchoice.core.entity.MembershipStatus;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Mohanasundharam
 */
public interface MembershipStatusService {

	/**
	 * @param id
	 * @return
	 */
	MembershipStatus deleteById(Byte id);

	/**
	 * @param id
	 * @return
	 */
	MembershipStatus findById(Byte id);

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
	MembershipStatus save(MembershipStatus bean);

	/**
	 * @param bean
	 * @return
	 */
	MembershipStatus update(MembershipStatus bean);

}
