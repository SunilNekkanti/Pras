package com.pfchoice.core.service;

import com.pfchoice.core.entity.File;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface FileService {

	/**
	 * @param id
	 * @return
	 */
	File deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	File findById(Integer id);

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
	File save(File bean);

	/**
	 * @param bean
	 * @return
	 */
	File update(File bean);

}
