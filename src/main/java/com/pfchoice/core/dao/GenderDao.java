package com.pfchoice.core.dao;

import com.pfchoice.core.entity.Gender;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface GenderDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	Gender deleteById(Byte id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	Gender findById(Byte id);

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
	Gender save(Gender bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	Gender updateByUpdater(Updater<Gender> updater);

}
