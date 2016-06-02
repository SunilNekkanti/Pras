package com.pfchoice.core.service;

import com.pfchoice.core.entity.ICDMeasure;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface ICDMeasureService {

	/**
	 * @param id
	 * @return
	 */
	ICDMeasure deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	ICDMeasure findById(Integer id);

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
	ICDMeasure save(ICDMeasure bean);

	/**
	 * @param bean
	 * @return
	 */
	ICDMeasure update(ICDMeasure bean);

}
