package com.pfchoice.core.dao;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface UnprocessedClaimDao {

	/**
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize);

	/**
	 * @return
	 */
	Integer loadDataCSV2Table(Integer fileId, String insuranceCode, String tableNames, Integer insId);

}
