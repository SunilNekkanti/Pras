package com.pfchoice.core.service;

import com.pfchoice.core.entity.MembershipActivityMonth;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface MembershipActivityMonthService {

	/**
	 * @param id
	 * @return
	 */
	MembershipActivityMonth deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	MembershipActivityMonth findById(Integer id);

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
	MembershipActivityMonth save(MembershipActivityMonth bean);

	/**
	 * @param bean
	 * @return
	 */
	MembershipActivityMonth update(MembershipActivityMonth bean);

}
