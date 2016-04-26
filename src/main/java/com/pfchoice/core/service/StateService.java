package com.pfchoice.core.service;

import com.pfchoice.core.entity.State;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface StateService {

	/**
	 * @param id
	 * @return
	 */
	State deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	State findById(Integer id);

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
	State save(State bean);

	/**
	 * @param bean
	 * @return
	 */
	State update(State bean);


}
