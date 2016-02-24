package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.FileTypeDao;
import com.pfchoice.core.entity.FileType;

/**
 *
 * @author Sarath
 */
@Repository
public class FileTypeDaoImpl extends HibernateBaseDao<FileType, Integer> implements FileTypeDao
{

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(FileTypeDaoImpl.class
        .getName());

    @Override
    public Pagination getPage(final int pageNo, final int pageSize)
    {
        Criteria crit = createCriteria();
        crit.add(Restrictions.eq("activeInd", 'Y'));
        Pagination page = findByCriteria(crit, pageNo, pageSize);
        return page;
    }

    @Override
    public FileType findById(final Integer id)
    {
    	FileType entity = get(id);
        return entity;
    }

    @Override
    public FileType save(final FileType bean)
    {
        getSession().save(bean);
        return bean;
    }

    @Override
    public FileType deleteById(final Integer id)
    {
//        throw new UnsupportedOperationException();
    	FileType entity = super.get(id);
        if (entity != null)
        {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    protected Class<FileType> getEntityClass()
    {
        return FileType.class;
    }

}
