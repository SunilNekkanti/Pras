package com.pfchoice.core.dao;

import java.util.List;

import com.pfchoice.core.entity.LeadMembershipClaimDetails;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface LeadMembershipClaimDetailsDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	LeadMembershipClaimDetails deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	LeadMembershipClaimDetails findById(Integer id);

	/**
	 * 
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
	LeadMembershipClaimDetails save(LeadMembershipClaimDetails bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	LeadMembershipClaimDetails updateByUpdater(Updater<LeadMembershipClaimDetails> updater);


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
	Pagination getMbrClaimDetailsPage(Integer mbrHosId);

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
	 * @param processClaim
	 * @return
	 */
	Pagination getMbrClaimDetailsByActivityMonth(int pageNo, int pageSize, String sSearch, Integer sSearchIns,
			Integer sSearchPrvdr, String sort, String sortdir, List<Integer> monthPicker, Integer processClaim);
}
