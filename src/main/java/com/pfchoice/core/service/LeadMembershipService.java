package com.pfchoice.core.service;

import java.util.List;

import com.pfchoice.core.entity.LeadMembership;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface LeadMembershipService {

	/**
	 * @param id
	 * @return
	 */
	LeadMembership deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	LeadMembership findById(Integer id);


	/**
	 * @param pageNo
	 * @param pageSize
	 * @param sSearch
	 * @param sSearchIns
	 * @param sSearchPrvdr
	 * @param sSearchHedisCode
	 * @param ruleIds
	 * @param sort
	 * @param sortdir
	 * @return
	 */
	Pagination getPage(Integer pageNo, Integer pageSize, String sSearch, Integer sSearchIns, Integer sSearchPrvdr, Integer sSearchHedisCode,
			final List<Integer> ruleIds, String sort, String sortdir);

	/**
	 * @param bean
	 * @return
	 */
	LeadMembership save(LeadMembership bean);

	/**
	 * @param bean
	 * @return
	 */
	LeadMembership update(LeadMembership bean);

	/**
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination getPage(Integer pageNo, Integer pageSize);

	/**
	 * @param lead
	 * @return
	 */
	boolean isLeadExist(LeadMembership lead);
}
