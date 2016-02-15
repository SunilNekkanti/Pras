package com.pfchoice.core.service;


import com.pfchoice.core.entity.Role;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface RoleService
{

	Role deleteById(Integer id);

	Role findById(Integer id);

    Pagination getPage(int pageNo, int pageSize);

    Role save(Role bean);

    Role update(Role bean);

}
