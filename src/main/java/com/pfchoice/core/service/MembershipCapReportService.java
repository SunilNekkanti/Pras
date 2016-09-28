package com.pfchoice.core.service;

import com.pfchoice.core.entity.MembershipCapReport;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface MembershipCapReportService {

	/**
	 * @param id
	 * @return
	 */
	MembershipCapReport deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	MembershipCapReport findById(Integer id);

	/**
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize);

	/**
	 * @param pageNo
	 * @param pageSize
	 * @param sSearch
	 * @param sort
	 * @param sortdir
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize, String sSearch, String sort, String sortdir);

	/**
	 * @param bean
	 * @return
	 */
	MembershipCapReport save(MembershipCapReport bean);

	/**
	 * @param bean
	 * @return
	 */
	MembershipCapReport update(MembershipCapReport bean);

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
	Integer unloadCSV2Table(String tableName);
}
