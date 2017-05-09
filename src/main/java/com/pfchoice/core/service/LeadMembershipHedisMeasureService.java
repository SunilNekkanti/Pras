package com.pfchoice.core.service;

import com.pfchoice.core.entity.LeadMembershipHedisMeasure;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface LeadMembershipHedisMeasureService {

	/**
	 * @param id
	 * @return
	 */
	LeadMembershipHedisMeasure deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	LeadMembershipHedisMeasure findById(Integer id);

	/**
	 * @param pageNo
	 * @param pageSize
	 * @param sSearch
	 * @param sort
	 * @param sortdir
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize, String sSearch, String sort, String sortdir);

	/**
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
	Pagination getPage(int pageNo, int pageSize, String sSearch, int sSearchIns, int sSearchPrvdr, int sSearchHedisCode,
			String sort, String sortdir);

	/**
	 * @param bean
	 * @return
	 */
	LeadMembershipHedisMeasure save(LeadMembershipHedisMeasure bean);

	/**
	 * @param bean
	 * @return
	 */
	LeadMembershipHedisMeasure update(LeadMembershipHedisMeasure bean);

	/**
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
