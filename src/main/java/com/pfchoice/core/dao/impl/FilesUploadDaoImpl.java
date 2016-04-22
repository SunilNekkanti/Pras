package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import org.hibernate.Criteria;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.FilesUploadDao;
import com.pfchoice.core.entity.FilesUpload;

/**
 *
 * @author Sarath
 */
@Repository
public class FilesUploadDaoImpl extends HibernateBaseDao<FilesUpload, Integer> implements FilesUploadDao {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(FilesUploadDaoImpl.class.getName());

	@Override
	public Pagination getPage(final int pageNo, final int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}

	@Override
	public FilesUpload findById(final Integer id) {
		FilesUpload entity = get(id);
		return entity;
	}

	@Override
	public FilesUpload save(final FilesUpload bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public FilesUpload deleteById(final Integer id) {
		// throw new UnsupportedOperationException();
		FilesUpload entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<FilesUpload> getEntityClass() {
		return FilesUpload.class;
	}

}
