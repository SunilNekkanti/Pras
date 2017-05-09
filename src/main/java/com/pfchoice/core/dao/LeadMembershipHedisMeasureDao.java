package com.pfchoice.core.dao;

import com.pfchoice.core.entity.LeadMembershipHedisMeasure;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface LeadMembershipHedisMeasureDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	LeadMembershipHedisMeasure deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	LeadMembershipHedisMeasure findById(Integer id);

	/**
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param sSearch
	 * @param sort
	 * @param sortdir
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize, String sSearch, String sort, String sortdir);

	/**
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param sSearch
	 * @param sSearchIns
	 * @param sSearchPrvdr
	 * @param sSearchHedisCode
	 * @param sort
	 * @param sortdir
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize, String sSearch, Integer sSearchIns, Integer sSearchPrvdr,
			Integer sSearchHedisCode, String sort, String sortdir);

	/**
	 * 
	 * @param bean
	 * @return
	 */
	LeadMembershipHedisMeasure save(LeadMembershipHedisMeasure bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	LeadMembershipHedisMeasure updateByUpdater(Updater<LeadMembershipHedisMeasure> updater);

	/**
	 * 
	 * @param mbrId
	 * @param ruleId
	 * @return
	 */
	Pagination findByMbrIdAndRuleId(Integer mbrId, Integer ruleId);

	/**
	 * @return
	 */
	Integer loadData(Integer fileId, Integer insId, Integer leadMbrId, String insuranceCode, Integer reportMonth);
	
	
	/**
	 * @return
	 */
	Integer unloadTable(Integer insId, String insuranceCode);
}
