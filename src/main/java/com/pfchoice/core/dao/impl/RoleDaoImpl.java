package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;


import org.hibernate.Criteria;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.RoleDao;
import com.pfchoice.core.entity.Role;

/**
 *
 * @author Sarath
 */
@Repository
public class RoleDaoImpl extends HibernateBaseDao<Role, Integer> implements RoleDao
{

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(RoleDaoImpl.class
        .getName());

    @Override
    public Pagination getPage(final int pageNo, final int pageSize)
    {
        Criteria crit = createCriteria();
        Pagination page = findByCriteria(crit, pageNo, pageSize);
        return page;
    }

    @Override
    public Role findById(final Integer id)
    {
    	Role entity = get(id);
        return entity;
    }

    @Override
    public Role save(final Role bean)
    {
        getSession().save(bean);
        return bean;
    }

    @Override
    public Role deleteById(final Integer id)
    {
//        throw new UnsupportedOperationException();
    	Role entity = super.get(id);
        if (entity != null)
        {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    protected Class<Role> getEntityClass()
    {
        return Role.class;
    }
}
