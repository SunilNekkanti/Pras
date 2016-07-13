package com.pfchoice.core.service;

import com.pfchoice.core.entity.FileType;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface FileTypeService {

	/**
	 * @param id
	 * @return
	 */
	FileType deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	FileType findById(Integer id);

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
	FileType save(FileType bean);

	/**
	 * @param bean
	 * @return
	 */
	FileType update(FileType bean);

	/**
	 * @param code
	 * @return
	 */
	FileType findByCode(String code);

}
