package com.pfchoice.core.service;

import com.pfchoice.core.entity.User;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface UserService {

	/**
	 * @param id
	 * @return
	 */
	User deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	User findById(Integer id);

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
	User save(User bean);

	/**
	 * @param bean
	 * @return
	 */
	User update(User bean);

	/**
	 * @param login
	 * @return
	 */
	User findByLogin(String login);

	/**
	 * @param login
	 * @param password
	 * @return
	 */
	boolean isValidUser(String login, String password);
	
	/**
	 * @param id
	 * @param userName
	 * @return
	 */
	boolean isUserUnique(Integer id, String userName);
	
	/**
	 * @param userName
	 * @return
	 */
	User findByUserName(String userName);
}
