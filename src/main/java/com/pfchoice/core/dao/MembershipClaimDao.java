package com.pfchoice.core.dao;

import java.util.Date;
import java.util.List;

import com.pfchoice.core.entity.MembershipClaim;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface MembershipClaimDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	MembershipClaim deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	MembershipClaim findById(Integer id);

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
	MembershipClaim save(MembershipClaim bean);

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
	Pagination getClaimPage(int pageNo, int pageSize, String sSearch, Integer sSearchIns, Integer sSearchPrvdr,
			String sort, String sortdir, Date processingFrom, Date processingTo, Integer processClaim);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	MembershipClaim updateByUpdater(Updater<MembershipClaim> updater);

	/**
	 * @return
	 */
	Integer loadDataCSV2Table(String fileName, String insuranceCode, String tableNames);

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
	Integer unloadCSV2Table(String tableName);

	/**
	 * @return
	 */
	Integer updateData(Integer fileId, String tableName);

	/**
	 * @return
	 */
	Integer unloadTable();
	
	/**
	 * @param insId
	 * @param prvdrId
	 * @param reportMonth
	 * @param activityMonth
	 * @return
	 */
	List<MembershipClaim> getUnwantedClaims(Integer insId, Integer prvdrId, Integer reportMonth, Integer activityMonth, Boolean isUnwanted);
}
