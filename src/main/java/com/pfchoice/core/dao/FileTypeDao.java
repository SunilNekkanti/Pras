package com.pfchoice.core.dao;

import com.pfchoice.core.entity.FileType;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface FileTypeDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	FileType deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	FileType findById(Integer id);

	/**
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize);

	/**
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param sSearch
	 * @param sort
	 * @param sortdir
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize, String sSearch, String sort, String sortdir);

	/**
	 * 
	 * @param bean
	 * @return
	 */
	FileType save(FileType bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	FileType updateByUpdater(Updater<FileType> updater);

	/**
	 * 
	 * @param code
	 * @return
	 */
	FileType findByCode(String code);
}
