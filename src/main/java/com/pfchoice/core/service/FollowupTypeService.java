package com.pfchoice.core.service;

import com.pfchoice.core.entity.FollowupType;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface FollowupTypeService {

	/**
	 * @param id
	 * @return
	 */
	FollowupType deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	FollowupType findById(Integer id);

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
	FollowupType save(FollowupType bean);

	/**
	 * @param bean
	 * @return
	 */
	FollowupType update(FollowupType bean);

	
	/**
	 * @param code
	 * @return
	 */
	FollowupType findByCode(String code);

}
