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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.UserDao#getPage(int, int)
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
	 * @see com.pfchoice.core.dao.UserDao#findById(java.lang.Integer)
	 */
	@Override
	public User findById(final Integer id) {
		return get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.UserDao#save(com.pfchoice.core.entity.User)
	 */
	@Override
	public User save(final User bean) {
		getSession().save(bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.UserDao#deleteById(java.lang.Integer)
	 */
	@Override
	public User deleteById(final Integer id) {
		User entity = super.get(id);
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
	protected Class<User> getEntityClass() {
		return User.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.UserDao#findByLogin(java.lang.String)
	 */
	@Override
	public User findByLogin(final String login) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("username", login));
		crit.add(Restrictions.eq("activeInd", 'Y'));

		return (User) crit.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.UserDao#isValidUser(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean isValidUser(final String login, final String password) {

		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("username", login));
		crit.add(Restrictions.eq("password", password));
		crit.add(Restrictions.eq("activeInd", 'Y'));

		User entity = (User) crit.uniqueResult();

		return (entity != null) ? true : false;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.UserDao#findByUserName(java.lang.String)
	 */
	@Override
	public User findByUserName(String userName) {
		return findUniqueByProperty("name", userName);
	}
}
