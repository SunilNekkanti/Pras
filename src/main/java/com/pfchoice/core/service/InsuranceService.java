package com.pfchoice.core.service;

import com.pfchoice.core.entity.Insurance;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Mohanasundharam
 */
public interface InsuranceService {

	/**
	 * @param id
	 * @return
	 */
	Insurance deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	Insurance findById(Integer id);

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
	Insurance save(Insurance bean);

	/**
	 * @param bean
	 * @return
	 */
	Insurance update(Insurance bean);

	/**
	 * @param insName
	 * @return
	 */
	Insurance findByInsName(String insName);

	/**
	 * @param id
	 * @param name
	 * @return
	 */
	boolean isInsUnique(Integer id, String name);

}
