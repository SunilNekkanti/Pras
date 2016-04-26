package com.pfchoice.core.service;

import com.pfchoice.core.entity.Role;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface RoleService {

	/**
	 * @param id
	 * @return
	 */
	Role deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	Role findById(Integer id);

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
	Role save(Role bean);

	/**
	 * @param bean
	 * @return
	 */
	Role update(Role bean);

}
