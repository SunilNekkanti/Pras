package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.RoleDao;
import com.pfchoice.core.entity.Role;
import com.pfchoice.core.service.RoleService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.RoleService#deleteById(java.lang.Integer)
	 */
	@Override
	public Role deleteById(final Integer id) {
		return roleDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.RoleService#findById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public Role findById(final Integer id) {
		return roleDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.RoleService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return roleDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.RoleService#save(com.pfchoice.core.entity.Role)
	 */
	@Override
	public Role save(final Role bean) {
		return roleDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.RoleService#update(com.pfchoice.core.entity.
	 * Role)
	 */
	@Override
	public Role update(final Role bean) {
		Updater<Role> updater = new Updater<>(bean, Updater.UpdateMode.MAX);
		updater.exclude("createdBy");
		updater.exclude("createdDate");
		return roleDao.updateByUpdater(updater);
	}

}
