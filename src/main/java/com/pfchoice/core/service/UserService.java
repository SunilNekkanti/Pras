package com.pfchoice.core.service;

import com.pfchoice.core.entity.User;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface UserService {

	User deleteById(Integer id);

	User findById(Integer id);

	Pagination getPage(int pageNo, int pageSize);

	User save(User bean);

	User update(User bean);

	User findByLogin(String login);

	boolean isValidUser(String login, String password);
}
