package com.pfchoice.core.dao;

import com.pfchoice.core.entity.BillType;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface BillTypeDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	BillType deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	BillType findById(Integer id);

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
	BillType save(BillType bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	BillType updateByUpdater(Updater<BillType> updater);
	
	/**
	 * @param billtypeName
	 * @return
	 */
	BillType findByDescription(String billTypeDescription);
	
	/**
	 * @param fileId
	 * @return
	 */
	Integer loadData(Integer fileId , String tableName);

}
