package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;
import org.hibernate.Criteria;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.TrackModelDao;
import com.pfchoice.core.entity.TrackModel;

/**
 *
 * @author Sarath
 */
@Repository
public class TrackModelDaoImpl extends HibernateBaseDao<TrackModel, Integer> implements TrackModelDao
{

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(TrackModelDaoImpl.class
        .getName());

    @Override
    public Pagination getPage(int pageNo, int pageSize)
    {
        Criteria crit = createCriteria();
        Pagination page = findByCriteria(crit, pageNo, pageSize);
        return page;
    }

    @Override
    public TrackModel findById(Integer id)
    {
    	TrackModel entity = get(id);
        return entity;
    }

    @Override
    public TrackModel save(TrackModel bean)
    {
        getSession().save(bean);
        return bean;
    }

    @Override
    public TrackModel deleteById(Integer id)
    {
//        throw new UnsupportedOperationException();
    	TrackModel entity = super.get(id);
        if (entity != null)
        {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    protected Class<TrackModel> getEntityClass()
    {
        return TrackModel.class;
    }

}
