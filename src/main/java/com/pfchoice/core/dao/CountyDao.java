package com.pfchoice.core.dao;

import com.pfchoice.core.entity.County;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface CountyDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	County deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	County findById(Integer id);

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
	County save(County bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	County updateByUpdater(Updater<County> updater);

}
