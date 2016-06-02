package com.pfchoice.core.dao;

import com.pfchoice.core.entity.FollowupType;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface FollowupTypeDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	FollowupType deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	FollowupType findById(Integer id);

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
	FollowupType save(FollowupType bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	FollowupType updateByUpdater(Updater<FollowupType> updater);

	/**
	 * 
	 * @param code
	 * @return
	 */
	FollowupType findByCode(String code);

}
