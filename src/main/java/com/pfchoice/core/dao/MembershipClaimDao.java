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
	Integer loadDataCSV2Table(String fileName, String tableName);

	/**
	 * @return
	 */
	Integer loadData(Integer fileId, String tablename);

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

}
