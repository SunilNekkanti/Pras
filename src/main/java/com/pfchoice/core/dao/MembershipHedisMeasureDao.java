package com.pfchoice.core.dao;

import com.pfchoice.core.entity.MembershipHedisMeasure;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface MembershipHedisMeasureDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	MembershipHedisMeasure deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	MembershipHedisMeasure findById(Integer id);

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
	MembershipHedisMeasure save(MembershipHedisMeasure bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	MembershipHedisMeasure updateByUpdater(Updater<MembershipHedisMeasure> updater);

	/**
	 * 
	 * @param mbrId
	 * @param ruleId
	 * @return
	 */
	Pagination findByMbrIdAndRuleId(Integer mbrId, Integer ruleId);

}
