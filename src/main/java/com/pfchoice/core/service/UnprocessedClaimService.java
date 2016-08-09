package com.pfchoice.core.service;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface UnprocessedClaimService {

	/**
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize);

	/**
	 * @return
	 */
	Integer loadDataCSV2Table(Integer fileId, String insuranceCode, String tableNames);

}
