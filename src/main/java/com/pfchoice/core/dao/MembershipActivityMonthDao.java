package com.pfchoice.core.dao;

import com.pfchoice.core.entity.MembershipActivityMonth;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface MembershipActivityMonthDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	MembershipActivityMonth deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	MembershipActivityMonth findById(Integer id);

	/**
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize);

	/**
	 * 
	 * @param bean
	 * @return
	 */
	MembershipActivityMonth save(MembershipActivityMonth bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	MembershipActivityMonth updateByUpdater(Updater<MembershipActivityMonth> updater);

}
