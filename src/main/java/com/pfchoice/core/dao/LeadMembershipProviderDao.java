package com.pfchoice.core.dao;

import java.util.List;

import com.pfchoice.core.entity.LeadMembershipProvider;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface LeadMembershipProviderDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	LeadMembershipProvider deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	LeadMembershipProvider findById(Integer id);

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
	LeadMembershipProvider save(LeadMembershipProvider bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	LeadMembershipProvider updateByUpdater(Updater<LeadMembershipProvider> updater);

	/**
	 * 
	 * @param id
	 * @return
	 */
	List<LeadMembershipProvider> findAllByMbrId(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	LeadMembershipProvider findByMbrId(Integer id);

}
