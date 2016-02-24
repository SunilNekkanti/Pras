package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.FileDao;
import com.pfchoice.core.entity.File;

/**
 *
 * @author Sarath
 */
@Repository
public class FileDaoImpl extends HibernateBaseDao<File, Integer> implements FileDao
{

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(FileDaoImpl.class
        .getName());

    @Override
    public Pagination getPage(final int pageNo,final  int pageSize)
    {
        Criteria crit = createCriteria();
        crit.add(Restrictions.eq("activeInd", 'Y'));
        Pagination page = findByCriteria(crit, pageNo, pageSize);
        return page;
    }

    @Override
    public File findById(final Integer id)
    {
    	File entity = get(id);
        return entity;
    }

    @Override
    public File save(final File bean)
    {
        getSession().save(bean);
        return bean;
    }

    @Override
    public File deleteById(final Integer id)
    {
//        throw new UnsupportedOperationException();
    	File entity = super.get(id);
        if (entity != null)
        {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    protected Class<File> getEntityClass()
    {
        return File.class;
    }

}
