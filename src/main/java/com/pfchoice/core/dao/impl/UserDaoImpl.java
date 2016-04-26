package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.UserDao;
import com.pfchoice.core.entity.User;

/**
 *
 * @author Sarath
 */
@Repository
public class UserDaoImpl extends HibernateBaseDao<User, Integer> implements UserDao {

	@Override
	public Pagination getPage(final int pageNo, final int pageSize) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("activeInd", 'Y'));
		return findByCriteria(crit, pageNo, pageSize);
	}

	@Override
	public User findById(final Integer id) {
		return get(id);
	}

	@Override
	public User save(final User bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public User deleteById(final Integer id) {
		User entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}

	@Override
	public User findByLogin(final String login) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("username", login));
		crit.add(Restrictions.eq("activeInd", 'Y'));

		return (User) crit.uniqueResult();
	}

	@Override
	public boolean isValidUser(final String login, final String password) {

		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("username", login));
		crit.add(Restrictions.eq("password", password));
		crit.add(Restrictions.eq("activeInd", 'Y'));

		User entity = (User) crit.uniqueResult();

		return (entity != null) ? true : false;

	}
}
