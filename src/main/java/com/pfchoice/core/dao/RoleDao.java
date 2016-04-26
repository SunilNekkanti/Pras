package com.pfchoice.core.dao;

import com.pfchoice.core.entity.Role;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface RoleDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	Role deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	Role findById(Integer id);

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
	Role save(Role bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	Role updateByUpdater(Updater<Role> updater);

}
