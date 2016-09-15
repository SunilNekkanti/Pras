package com.pfchoice.core.service;

import com.pfchoice.core.entity.Pharmacy;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface PharmacyService {

	/**
	 * @param id
	 * @return
	 */
	Pharmacy deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	Pharmacy findById(Integer id);

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
	Pharmacy save(Pharmacy bean);

	/**
	 * @param bean
	 * @return
	 */
	Pharmacy update(Pharmacy bean);

	/**
	 * @param fileId
	 * @return
	 */
	Integer loadData(Integer fileId);

	/**
	 * @param code
	 * @return
	 */
	Pharmacy findByCode(String code);

	/**
	 * @param id
	 * @param code
	 * @return
	 */
	boolean isCodeUnique(Integer id, String code);
	
	/**
	 * @return
	 */
	Integer loadData(Integer fileId, Integer insId, String insuranceCode);

	/**
	 * @return
	 */
	Boolean isDataExists(String tableName);

	/**
	 * @return
	 */
	Integer loadDataCSV2Table(String fileName, String insuranceCode, String tableNames);

	/**
	 * @return
	 */
	Integer unloadCSV2Table(String tableName);

}
