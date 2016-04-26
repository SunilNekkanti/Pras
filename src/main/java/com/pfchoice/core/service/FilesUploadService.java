package com.pfchoice.core.service;

import com.pfchoice.core.entity.FilesUpload;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface FilesUploadService {

	/**
	 * @param id
	 * @return
	 */
	FilesUpload deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	FilesUpload findById(Integer id);

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
	FilesUpload save(FilesUpload bean);

	/**
	 * @param bean
	 * @return
	 */
	FilesUpload update(FilesUpload bean);

}
