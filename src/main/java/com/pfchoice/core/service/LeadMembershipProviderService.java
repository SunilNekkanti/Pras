package com.pfchoice.core.service;

import java.util.List;

import com.pfchoice.core.entity.LeadMembershipProvider;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface LeadMembershipProviderService {

	/**
	 * @param id
	 * @return
	 */
	LeadMembershipProvider deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	LeadMembershipProvider findById(Integer id);

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
	LeadMembershipProvider save(LeadMembershipProvider bean);

	/**
	 * @param bean
	 * @return
	 */
	LeadMembershipProvider update(LeadMembershipProvider bean);

	/**
	 * @param id
	 * @return
	 */
	List<LeadMembershipProvider> findAllByMbrId(Integer id);

	/**
	 * @param id
	 * @return
	 */
	LeadMembershipProvider findByMbrId(final Integer id);

}
