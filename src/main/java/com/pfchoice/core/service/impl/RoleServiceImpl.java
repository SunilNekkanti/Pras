package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.RoleDao;
import com.pfchoice.core.entity.Role;
import com.pfchoice.core.service.RoleService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService
{

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role deleteById(final Integer id)
    {
        //Used for transaction test
        return roleDao.deleteById(id);
//        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional(readOnly = true)
    public Role findById(final Integer id)
    {
        return roleDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(final int pageNo, final int pageSize)
    {
        return roleDao.getPage(pageNo, pageSize);
    }

    @Override
    public Role save(final Role bean)
    {
        //Used for transaction test
        return roleDao.save(bean);
//        this.deleteById(1);
//        return null;
    }

    @Override
    public Role update(final Role bean)
    {
        Updater<Role> updater = new Updater<>(bean);
        return roleDao.updateByUpdater(updater);
    }

}
