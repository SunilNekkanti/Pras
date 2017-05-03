package com.pfchoice.core.service;

import java.util.List;

import com.pfchoice.core.entity.LeadMembershipClaimDetails;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface LeadMembershipClaimDetailsService {

	/**
	 * @param id
	 * @return
	 */
	LeadMembershipClaimDetails deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	LeadMembershipClaimDetails findById(Integer id);

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
	LeadMembershipClaimDetails save(LeadMembershipClaimDetails bean);

	/**
	 * @param bean
	 * @return
	 */
	LeadMembershipClaimDetails update(LeadMembershipClaimDetails bean);

	/**
	 * @param fileId
	 * @param insId
	 * @param reportMonth
	 * @return
	 */
	Integer loadData(Integer fileId, Integer insId, Integer reportMonth);

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
