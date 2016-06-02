package com.pfchoice.core.service;

import com.pfchoice.core.entity.MembershipClaim;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface MembershipClaimService {

	/**
	 * @param id
	 * @return
	 */
	MembershipClaim deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	MembershipClaim findById(Integer id);

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
	MembershipClaim save(MembershipClaim bean);

	/**
	 * @param bean
	 * @return
	 */
	MembershipClaim update(MembershipClaim bean);

	/**
	 * @return
	 */
	Integer loadData(Integer fileId);

	/**
	 * @return
	 */
	Boolean isDataExists();

	/**
	 * @return
	 */
	Integer loadDataCSV2Table(String fileName);

	/**
	 * @return
	 */
	Integer unloadCSV2Table();

	/**
	 * @return
	 */
	Integer updateData(Integer fileId);

}
