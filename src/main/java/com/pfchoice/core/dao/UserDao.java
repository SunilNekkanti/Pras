package com.pfchoice.core.dao;


import com.pfchoice.core.entity.User;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface UserDao
{

	User deleteById(Integer id);

	User findById(Integer id);

    Pagination getPage(int pageNo, int pageSize);

    User save(User bean);

    User updateByUpdater(Updater<User> updater);
    
}
