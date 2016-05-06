package com.pfchoice.core.dao;

import java.util.List;

import com.pfchoice.core.entity.MembershipFollowup;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Mohanasundharam
 */
public interface MembershipFollowupDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	MembershipFollowup deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	MembershipFollowup findById(Integer id);

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
	MembershipFollowup save(MembershipFollowup bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	MembershipFollowup updateByUpdater(Updater<MembershipFollowup> updater);

	/**
	 * 
	 * @param mbrId
	 * @return
	 */
	List<MembershipFollowup> findAllByMbrId(Integer mbrId, String followupTypeCode);

}
