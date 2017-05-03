package com.pfchoice.core.dao;

import java.util.List;

import com.pfchoice.core.entity.LeadMembership;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface LeadMembershipDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	LeadMembership deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	LeadMembership findById(Integer id);


	/**
	 * 
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
	Pagination getPage(int pageNo, int pageSize, String sSearch, Integer sSearchIns, Integer sSearchPrvdr,
			Integer sSearchHedisCode, final List<Integer> ruleIds, String sort, String sortdir);

	/**
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
	LeadMembership save(LeadMembership bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	LeadMembership updateByUpdater(Updater<LeadMembership> updater);

	
	/**
	 * @param lead
	 * @return
	 */
	boolean isLeadExist(LeadMembership lead);
	
	/**
	 * @param medicareNo
	 * @return
	 */
	LeadMembership findByMedicareNo(String medicareNo);

}
