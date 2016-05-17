package com.pfchoice.core.service;

import com.pfchoice.core.entity.Email;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Mohanasundharam
 */
public interface EmailService {

	/**
	 * @param id
	 * @return
	 */
	Email deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	Email findById(Integer id);

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
	Email save(Email bean);

	/**
	 * @param bean
	 * @return
	 */
	Email update(Email bean);
}
