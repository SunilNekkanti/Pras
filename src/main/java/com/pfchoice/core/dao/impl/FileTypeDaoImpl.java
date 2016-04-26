package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.FileTypeDao;
import com.pfchoice.core.entity.FileType;

/**
 *
 * @author Sarath
 */
@Repository
public class FileTypeDaoImpl extends HibernateBaseDao<FileType, Integer> implements FileTypeDao {

	@Override
	public Pagination getPage(final int pageNo, final int pageSize) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("activeInd", 'Y'));
		return findByCriteria(crit, pageNo, pageSize);
	}

	@Override
	public FileType findById(final Integer id) {
		return get(id);
	}

	@Override
	public FileType save(final FileType bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public FileType deleteById(final Integer id) {
		FileType entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<FileType> getEntityClass() {
		return FileType.class;
	}

}
