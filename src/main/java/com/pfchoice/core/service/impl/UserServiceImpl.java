package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.UserDao;
import com.pfchoice.core.entity.User;
import com.pfchoice.core.service.UserService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.UserService#deleteById(java.lang.Integer)
	 */
	@Override
	public User deleteById(final Integer id) {
		return userDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.UserService#findById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public User findById(final Integer id) {
		return userDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.UserService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return userDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.UserService#save(com.pfchoice.core.entity.User)
	 */
	@Override
	public User save(final User bean) {
		return userDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.UserService#update(com.pfchoice.core.entity.
	 * User)
	 */
	@Override
	public User update(final User bean) {
		Updater<User> updater = new Updater<>(bean, Updater.UpdateMode.MAX);
		updater.exclude("createdBy");
		updater.exclude("createdDate");
		return userDao.updateByUpdater(updater);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.UserService#findByLogin(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public User findByLogin(final String login) {
		return userDao.findByLogin(login);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.UserService#isValidUser(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public boolean isValidUser(final String login, final String password) {
		return userDao.isValidUser(login, password);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.UserService#isUserUnique(java.lang.Integer,
	 * java.lang.String)
	 */
	@Override
	public boolean isUserUnique(Integer id, String userName) {
		User user = findByUserName(userName);
		return (user == null || ((id != null) && (user.getId() == id)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.UserService#findByUserName(java.lang.String)
	 */
	@Override
	public User findByUserName(String userName) {
		User user = userDao.findByUserName(userName);
		return user;
	}

}
