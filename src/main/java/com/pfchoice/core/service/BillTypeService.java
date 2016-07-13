package com.pfchoice.core.service;

import com.pfchoice.core.entity.BillType;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface BillTypeService {

	/**
	 * @param id
	 * @return
	 */
	BillType deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	BillType findById(Integer id);

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
	BillType save(BillType bean);

	/**
	 * @param bean
	 * @return
	 */
	BillType update(BillType bean);

	/**
	 * @param Name
	 * @return
	 */
	BillType findByDescription(String billTypeDescription);

	/**
	 * @param id
	 * @param name
	 * @return
	 */
	boolean isDescriptionUnique(Integer id, String billTypeDescription);

	/**
	 * @param fileId
	 * @return
	 */
	Integer loadData(Integer fileId, String tableName);

}
