package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;
import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.FileDao#getPage(int, int)
	 */
	@Override
	public Pagination getPage(final int pageNo, final int pageSize) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("activeInd", 'Y'));

		return findByCriteria(crit, pageNo, pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.FileDao#getPage(int, int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		Disjunction or = Restrictions.disjunction();

		if (sSearch != null && !"".equals(sSearch)) {
			
					or.add(Restrictions.ilike("fileName", "%" + sSearch + "%"))
					.add(Restrictions.ilike("fileType.description", "%" + sSearch + "%"))
					.add(Restrictions.ilike("ins.name", "%" + sSearch + "%"));
		}
		Criteria crit = createCriteria();
		crit.createAlias("fileType", "fileType");
		crit.createAlias("fileType.Ins", "ins");
		crit.add(or);
		crit.add(Restrictions.eq("activeInd", 'Y'));
		

		if (sort != null && !"".equals(sort)) {
			if (sortdir != null && !"".equals(sortdir) && "desc".equals(sortdir)) {
				crit.addOrder(Order.desc(sort));
			} else {
				crit.addOrder(Order.asc(sort));
			}
		}

		return findByCriteria(crit, pageNo, pageSize);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.FileDao#findById(java.lang.Integer)
	 */
	@Override
	public File findById(final Integer id) {
		return get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.FileDao#save(com.pfchoice.core.entity.File)
	 */
	@Override
	public File save(final File bean) {
		getSession().save(bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see ml.rugal.sshcommon.hibernate.HibernateBaseDao#getEntityClass()
	 */
	@Override
	protected Class<File> getEntityClass() {
		return File.class;
	}

}
