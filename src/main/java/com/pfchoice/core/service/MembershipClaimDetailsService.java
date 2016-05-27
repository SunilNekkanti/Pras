package com.pfchoice.core.service;

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
	Integer loadData(Integer fileId);
	
	/**
	 * @param mbrHosId
	 * @return
	 */
	Pagination getMbrClaimDetailsPage(int mbrHosId);
}
