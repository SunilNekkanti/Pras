package com.pfchoice.core.service;

import java.util.Date;
import java.util.List;

import com.pfchoice.core.entity.LeadMembershipClaim;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface LeadMembershipClaimService {

	/**
	 * @param id
	 * @return
	 */
	LeadMembershipClaim deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	LeadMembershipClaim findById(Integer id);

	/**
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize);

	/**
	 * @param pageNo
	 * @param pageSize
	 * @param leadMbrId
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize, int leadMbrId);

	
	/**
	 * @param bean
	 * @return
	 */
	LeadMembershipClaim save(LeadMembershipClaim bean);

	/**
	 * @param bean
	 * @return
	 */
	LeadMembershipClaim update(LeadMembershipClaim bean);

	/**
	 * @return
	 */
	Integer loadData(Integer fileId, Integer insId, String insuranceCode, Integer reportMonth);

	/**
	 * @return
	 */
	Boolean isDataExists(String tableName);

	/**
	 * @return
	 */
	Integer loadDataCSV2Table(String fileName, String insuranceCode, String tableNames);

	/**
	 * @return
	 */
	Integer unloadCSV2Table(String tableName);

	/**
	 * @return
	 */
	Integer updateData(Integer fileId, String tableName);

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
	Pagination getClaimPage(int pageNo, int pageSize, String sSearch, int sSearchIns, int sSearchPrvdr, String sort,
			String sortdir, Date processingFrom, Date processingTo, int processClaim);
	
	/**
	 * @return
	 */
	Integer unloadTable();
	
	/**
	 * @param insId
	 * @param prvdrId
	 * @param reportMonth
	 * @param activityMonth
	 * @param isUnwanted
	 * @return
	 */
	List<LeadMembershipClaim> getUnwantedClaims(Integer insId, Integer prvdrId, Integer reportMonth, Integer activityMonth, Boolean isUnwanted);

	/**
	 * @param leadMembershipClaim
	 * @return
	 */
	LeadMembershipClaim merge(LeadMembershipClaim leadMembershipClaim) ;
}
