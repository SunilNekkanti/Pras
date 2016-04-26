package com.pfchoice.core.dao;

import com.pfchoice.core.entity.State;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface StateDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	State deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	State findById(Integer id);

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
	State save(State bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	State updateByUpdater(Updater<State> updater);

}
