package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.ZipCodeDao;
import com.pfchoice.core.entity.ZipCode;

/**
 *
 * @author Sarath
 */
@Repository
public class ZipCodeDaoImpl extends HibernateBaseDao<ZipCode, Integer> implements ZipCodeDao
{

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ZipCodeDaoImpl.class
        .getName());

    @Override
    public Pagination getPage(int pageNo, int pageSize)
    {
        Criteria crit = createCriteria();
        Pagination page = findByCriteria(crit, pageNo, pageSize);
        return page;
    }

    @Override
    public ZipCode findById(Integer id)
    {
    	ZipCode entity = get(id);
        return entity;
    }

    @Override
    public ZipCode save(ZipCode bean)
    {
        getSession().save(bean);
        return bean;
    }

    @Override
    public ZipCode deleteById(Integer id)
    {
//        throw new UnsupportedOperationException();
    	ZipCode entity = super.get(id);
        if (entity != null)
        {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    protected Class<ZipCode> getEntityClass()
    {
        return ZipCode.class;
    }
    
    @SuppressWarnings("unchecked")
	public List<ZipCode> findAll()
    {
    	Criteria cr = getSession().createCriteria(getEntityClass());
    	List<ZipCode> list = cr.list();
    	return list;
    }

}