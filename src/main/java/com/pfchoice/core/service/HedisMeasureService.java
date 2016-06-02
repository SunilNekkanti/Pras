package com.pfchoice.core.service;

import com.pfchoice.core.entity.HedisMeasure;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface HedisMeasureService {

	/**
	 * @param id
	 * @return
	 */
	HedisMeasure deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	HedisMeasure findById(Integer id);

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
	HedisMeasure save(HedisMeasure bean);

	/**
	 * @param bean
	 * @return
	 */
	HedisMeasure update(HedisMeasure bean);

}
