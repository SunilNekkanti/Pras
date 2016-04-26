package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.RoleDao;
import com.pfchoice.core.entity.Role;

/**
 *
 * @author Sarath
 */
@Repository
public class RoleDaoImpl extends HibernateBaseDao<Role, Integer> implements RoleDao {

	@Override
	public Pagination getPage(final int pageNo, final int pageSize) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("activeInd", 'Y'));
		return findByCriteria(crit, pageNo, pageSize);
	}

	@Override
	public Role findById(final Integer id) {
		return get(id);
	}

	@Override
	public Role save(final Role bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public Role deleteById(final Integer id) {
		Role entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<Role> getEntityClass() {
		return Role.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findAll() {

		Criteria cr = createCriteria();
		cr.add(Restrictions.eq("activeInd", 'Y'));
		cr.setProjection(Projections.distinct(Projections.projectionList().add(Projections.property("role"), "role")
				.add(Projections.property("id"), "id")))
				.setResultTransformer(Transformers.aliasToBean(getEntityClass()));

		return cr.list();
	}

}
