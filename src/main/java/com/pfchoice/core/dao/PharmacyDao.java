package com.pfchoice.core.dao;

import com.pfchoice.core.entity.Pharmacy;
import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface PharmacyDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	Pharmacy deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	Pharmacy findById(Integer id);

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
	Pharmacy save(Pharmacy bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	Pharmacy updateByUpdater(Updater<Pharmacy> updater);

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
	 * @return
	 */
	Integer loadDataCSV2Table(String fileName, String insuranceCode, String tableNames);

	/**
	 * @return
	 */
	Integer loadData(Integer fileId, Integer insId, String insuranceCode, Integer reportMonth);

	/**
	 * @return
	 */
	Boolean isDataExists(String tableName);

	/**
	 * @return
	 */
	Integer unloadCSV2Table(String tableName);

}
