package com.pfchoice.core.service;

import java.util.Date;
import java.util.List;

import com.pfchoice.core.entity.Membership;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface MembershipService {

	/**
	 * @param id
	 * @return
	 */
	Membership deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	Membership findById(Integer id);

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
	 * @param ruleIds
	 * @param sort
	 * @param sortdir
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize, String sSearch, int sSearchIns, int sSearchPrvdr, int sSearchHedisCode,
			final List<Integer> ruleIds, String sort, String sortdir);

	/**
	 * @param pageNo
	 * @param pageSize
	 * @param sSearch
	 * @param sSearchIns
	 * @param sSearchPrvdr
	 * @param sort
	 * @param sortdir
	 * @param processingFrom
	 * @param processingTo
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize, String sSearch, int sSearchIns, int sSearchPrvdr,
			String sort, String sortdir, Date processingFrom, Date processingTo);

	/**
	 * @param bean
	 * @return
	 */
	Membership save(Membership bean);

	/**
	 * @param bean
	 * @return
	 */
	Membership update(Membership bean);

}
