package com.pfchoice.core.dao;

import com.pfchoice.core.entity.FilesUpload;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface FilesUploadDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	FilesUpload deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	FilesUpload findById(Integer id);

	/**
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize);

	/**
	 * 
	 * @param bean
	 * @return
	 */
	FilesUpload save(FilesUpload bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	FilesUpload updateByUpdater(Updater<FilesUpload> updater);

}
