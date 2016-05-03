package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.RoleDao;
import com.pfchoice.core.entity.Role;

/**
 *
 * @author Sarath
 */
@Repository
public class RoleDaoImpl extends HibernateBaseDao<Role, Integer> implements RoleDao {

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.RoleDao#getPage(int, int)
	 */
	@Override
	public Pagination getPage(final int pageNo, final int pageSize) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("activeInd", 'Y'));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return findByCriteria(crit, pageNo, pageSize);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.RoleDao#findById(java.lang.Integer)
	 */
	@Override
	public Role findById(final Integer id) {
		return get(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.RoleDao#save(com.pfchoice.core.entity.Role)
	 */
	@Override
	public Role save(final Role bean) {
		getSession().save(bean);
		return bean;
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.RoleDao#deleteById(java.lang.Integer)
	 */
	@Override
	public Role deleteById(final Integer id) {
		Role entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	/* (non-Javadoc)
	 * @see ml.rugal.sshcommon.hibernate.HibernateBaseDao#getEntityClass()
	 */
	@Override
	protected Class<Role> getEntityClass() {
		return Role.class;
	}

}
