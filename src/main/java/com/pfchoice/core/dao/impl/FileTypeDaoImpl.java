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

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.FileTypeDao#getPage(int, int)
	 */
	@Override
	public Pagination getPage(final int pageNo, final int pageSize) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("activeInd", 'Y'));
		return findByCriteria(crit, pageNo, pageSize);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.FileTypeDao#findById(java.lang.Integer)
	 */
	@Override
	public FileType findById(final Integer id) {
		return get(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.FileTypeDao#save(com.pfchoice.core.entity.FileType)
	 */
	@Override
	public FileType save(final FileType bean) {
		getSession().save(bean);
		return bean;
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.FileTypeDao#deleteById(java.lang.Integer)
	 */
	@Override
	public FileType deleteById(final Integer id) {
		FileType entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	/* (non-Javadoc)
	 * @see ml.rugal.sshcommon.hibernate.HibernateBaseDao#getEntityClass()
	 */
	@Override
	protected Class<FileType> getEntityClass() {
		return FileType.class;
	}

}
