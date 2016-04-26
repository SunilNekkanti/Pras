package com.pfchoice.core.service;

import com.pfchoice.core.entity.County;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface CountyService {

	/**
	 * @param id
	 * @return
	 */
	County deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	County findById(Integer id);

	/**
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize);

	/**
	 * @param bean
	 * @return
	 */
	County save(County bean);

	/**
	 * @param bean
	 * @return
	 */
	County update(County bean);

}
