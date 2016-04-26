package com.pfchoice.core.dao;

import java.util.List;

import com.pfchoice.core.entity.MembershipHedisFollowup;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Mohanasundharam
 */
public interface MembershipHedisFollowupDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	MembershipHedisFollowup deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	MembershipHedisFollowup findById(Integer id);

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
	MembershipHedisFollowup save(MembershipHedisFollowup bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	MembershipHedisFollowup updateByUpdater(Updater<MembershipHedisFollowup> updater);

	/**
	 * 
	 * @param mbrId
	 * @return
	 */
	List<MembershipHedisFollowup> findAllByMbrId(Integer mbrId);

}
