package com.pfchoice.core.dao;

import java.util.List;

import com.pfchoice.core.entity.MembershipClaimDetails;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface MembershipClaimDetailsDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	MembershipClaimDetails deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	MembershipClaimDetails findById(Integer id);

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
	MembershipClaimDetails save(MembershipClaimDetails bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	MembershipClaimDetails updateByUpdater(Updater<MembershipClaimDetails> updater);

	/**
	 * @param fileId
	 * @return
	 */
	Integer loadData(Integer fileId, String tableName);

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
	Pagination getMbrClaimDetailsByActivityMonth(int pageNo, int pageSize, String sSearch, Integer sSearchIns, Integer sSearchPrvdr,
			String sort, String sortdir, List<Integer> monthPicker,  Integer processClaim);
}
