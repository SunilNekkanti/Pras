package com.pfchoice.core.dao;

import com.pfchoice.core.entity.File;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface FileDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	File deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	File findById(Integer id);

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
	File save(File bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	File updateByUpdater(Updater<File> updater);

}
