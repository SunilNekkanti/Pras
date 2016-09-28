package com.pfchoice.core.dao;

import com.pfchoice.core.entity.MembershipCapReport;
import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface MembershipCapReportDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	MembershipCapReport deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	MembershipCapReport findById(Integer id);

	/**
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize);

	/**
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param sSearch
	 * @param sort
	 * @param sortdir
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize, String sSearch, String sort, String sortdir);

	/**
	 * 
	 * @param bean
	 * @return
	 */
	MembershipCapReport save(MembershipCapReport bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	MembershipCapReport updateByUpdater(Updater<MembershipCapReport> updater);

	/**
	 * @param fileId
	 * @return
	 */
	Integer loadData(Integer insId, Integer fileId, Integer activityMonth, String tableName);

	/**
	 * @return
	 */
	Integer loadDataCSV2Table(String fileName, String tableName);

	/**
	 * @return
	 */
	Boolean isDataExists(String tableName);

	/**
	 * @return
	 */
	Integer unloadCSV2Table(String tableName);
}
