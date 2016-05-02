package com.pfchoice.core.dao;

import com.pfchoice.core.entity.AttPhysician;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface AttPhysicianDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	AttPhysician deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	AttPhysician findById(Integer id);

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
	AttPhysician save(AttPhysician bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	AttPhysician updateByUpdater(Updater<AttPhysician> updater);

}
