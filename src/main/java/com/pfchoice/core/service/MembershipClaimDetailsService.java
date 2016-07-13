package com.pfchoice.core.service;

import java.util.List;

import com.pfchoice.core.entity.MembershipClaimDetails;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface MembershipClaimDetailsService {

	/**
	 * @param id
	 * @return
	 */
	MembershipClaimDetails deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	MembershipClaimDetails findById(Integer id);

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
	MembershipClaimDetails save(MembershipClaimDetails bean);

	/**
	 * @param bean
	 * @return
	 */
	MembershipClaimDetails update(MembershipClaimDetails bean);

	/**
	 * @param fileId
	 * @return
	 */
	Integer loadData(Integer fileId, String tableName);

	/**
	 * @param mbrHosId
	 * @return
	 */
	Pagination getMbrClaimDetailsPage(int mbrHosId);

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
	 *            * @param processClaim
	 * @return
	 */
	Pagination getMbrClaimDetailsByActivityMonth(int pageNo, int pageSize, String sSearch, int sSearchIns,
			int sSearchPrvdr, String sort, String sortdir, final List<Integer> monthPicker, int processClaim);
}
