package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.FilesUploadDao;
import com.pfchoice.core.entity.FilesUpload;

/**
 *
 * @author Sarath
 */
@Repository
public class FilesUploadDaoImpl extends HibernateBaseDao<FilesUpload, Integer> implements FilesUploadDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.FilesUploadDao#getPage(int, int)
	 */
	@Override
	public Pagination getPage(final int pageNo, final int pageSize) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("activeInd", 'Y'));
		return findByCriteria(crit, pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.FilesUploadDao#findById(java.lang.Integer)
	 */
	@Override
	public FilesUpload findById(final Integer id) {
		return get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.FilesUploadDao#save(com.pfchoice.core.entity.
	 * FilesUpload)
	 */
	@Override
	public FilesUpload save(final FilesUpload bean) {
		getSession().save(bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.FilesUploadDao#deleteById(java.lang.Integer)
	 */
	@Override
	public FilesUpload deleteById(final Integer id) {
		FilesUpload entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ml.rugal.sshcommon.hibernate.HibernateBaseDao#getEntityClass()
	 */
	@Override
	protected Class<FilesUpload> getEntityClass() {
		return FilesUpload.class;
	}

}
