package com.pfchoice.core.service;

import com.pfchoice.core.entity.Hospital;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface HospitalService {

	/**
	 * @param id
	 * @return
	 */
	Hospital deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	Hospital findById(Integer id);

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
	Hospital save(Hospital bean);

	/**
	 * @param bean
	 * @return
	 */
	Hospital update(Hospital bean);

	/**
	 * @param fileId
	 * @return
	 */
	Integer loadData(Integer fileId);

	/**
	 * @param Name
	 * @return
	 */
	Hospital findByName(String hospitalName);

	/**
	 * @param id
	 * @param name
	 * @return
	 */
	boolean isNameUnique(Integer id, String name);

}
