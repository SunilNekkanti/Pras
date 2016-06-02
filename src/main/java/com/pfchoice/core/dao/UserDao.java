package com.pfchoice.core.dao;

import com.pfchoice.core.entity.User;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface UserDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	User deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	User findById(Integer id);

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
	User save(User bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	User updateByUpdater(Updater<User> updater);

	/**
	 * 
	 * @param login
	 * @return
	 */
	User findByLogin(String login);

	/**
	 * 
	 * @param login
	 * @param password
	 * @return
	 */
	boolean isValidUser(String login, String password);

	/**
	 * @param userName
	 * @return
	 */
	User findByUserName(String userName);
}
