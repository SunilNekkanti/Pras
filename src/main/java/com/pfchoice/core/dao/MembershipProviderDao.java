package com.pfchoice.core.dao;

import java.util.List;

import com.pfchoice.core.entity.MembershipProvider;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface MembershipProviderDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	MembershipProvider deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	MembershipProvider findById(Integer id);

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
	MembershipProvider save(MembershipProvider bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	MembershipProvider updateByUpdater(Updater<MembershipProvider> updater);

	/**
	 * 
	 * @param id
	 * @return
	 */
	List<MembershipProvider> findAllByMbrId(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	MembershipProvider findByMbrId(Integer id);

}
