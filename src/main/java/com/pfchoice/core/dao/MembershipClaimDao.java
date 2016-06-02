package com.pfchoice.core.dao;

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
	 * 
	 * @param updater
	 * @return
	 */
	MembershipClaim updateByUpdater(Updater<MembershipClaim> updater);

	/**
	 * @return
	 */
	Integer loadDataCSV2Table(String fileName);

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
	Integer unloadCSV2Table();

	/**
	 * @return
	 */
	Integer updateData(Integer fileId);

}
