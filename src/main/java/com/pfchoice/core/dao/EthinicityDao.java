package com.pfchoice.core.dao;

import com.pfchoice.core.entity.Ethinicity;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Mohanasundharam
 */
public interface EthinicityDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	Ethinicity deleteById(Byte id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	Ethinicity findById(Byte id);

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
	Ethinicity save(Ethinicity bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	Ethinicity updateByUpdater(Updater<Ethinicity> updater);

}
