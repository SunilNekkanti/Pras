package com.pfchoice.core.dao;


import com.pfchoice.core.entity.Role;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface RoleDao
{

	Role deleteById(Integer id);

	Role findById(Integer id);

    Pagination getPage(int pageNo, int pageSize);

    Role save(Role bean);

    Role updateByUpdater(Updater<Role> updater);
    
}
