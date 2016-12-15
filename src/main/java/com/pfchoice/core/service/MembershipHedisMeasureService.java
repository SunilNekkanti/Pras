package com.pfchoice.core.service;

import com.pfchoice.core.entity.MembershipHedisMeasure;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface MembershipHedisMeasureService {

	/**
	 * @param id
	 * @return
	 */
	MembershipHedisMeasure deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	MembershipHedisMeasure findById(Integer id);

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
	MembershipHedisMeasure save(MembershipHedisMeasure bean);

	/**
	 * @param bean
	 * @return
	 */
	MembershipHedisMeasure update(MembershipHedisMeasure bean);

	/**
	 * @param mbrId
	 * @param ruleId
	 * @return
	 */
	Pagination findByMbrIdAndRuleId(Integer mbrId, Integer ruleId);

	/**
	 * @return
	 */
	Integer loadData(Integer fileId, Integer insId, String insuranceCode, Integer reportMonth);
	
	/**
	 * @return
	 */
	Integer unloadTable(Integer insId, String insuranceCode);

}
