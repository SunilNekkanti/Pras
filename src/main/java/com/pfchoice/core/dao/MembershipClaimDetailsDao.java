package com.pfchoice.core.dao;

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
	Integer loadData(Integer fileId);

	/**
	 * @param mbrHosId
	 * @return
	 */
	Pagination getMbrClaimDetailsPage(Integer mbrHosId);
}
