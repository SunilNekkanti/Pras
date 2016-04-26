package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.FileDao;
import com.pfchoice.core.entity.File;

/**
 *
 * @author Sarath
 */
@Repository
public class FileDaoImpl extends HibernateBaseDao<File, Integer> implements FileDao {

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.FileDao#getPage(int, int)
	 */
	@Override
	public Pagination getPage(final int pageNo, final int pageSize) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("activeInd", 'Y'));

		return findByCriteria(crit, pageNo, pageSize);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.FileDao#findById(java.lang.Integer)
	 */
	@Override
	public File findById(final Integer id) {
		return get(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.FileDao#save(com.pfchoice.core.entity.File)
	 */
	@Override
	public File save(final File bean) {
		getSession().save(bean);
		return bean;
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.FileDao#deleteById(java.lang.Integer)
	 */
	@Override
	public File deleteById(final Integer id) {
		File entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	/* (non-Javadoc)
	 * @see ml.rugal.sshcommon.hibernate.HibernateBaseDao#getEntityClass()
	 */
	@Override
	protected Class<File> getEntityClass() {
		return File.class;
	}

}
