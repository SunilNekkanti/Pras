package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.AttPhysicianDao;
import com.pfchoice.core.entity.AttPhysician;
import com.pfchoice.core.service.AttPhysicianService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class AttPhysicianServiceImpl implements AttPhysicianService {

	@Autowired
	private AttPhysicianDao attPhysicianDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.AttPhysicianService#deleteById(java.lang.
	 * Integer)
	 */
	@Override
	public AttPhysician deleteById(final Integer id) {
		return attPhysicianDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.AttPhysicianService#findById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public AttPhysician findById(final Integer id) {
		return attPhysicianDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.AttPhysicianService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return attPhysicianDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.AttPhysicianService#getPage(int, int,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		return attPhysicianDao.getPage(pageNo, pageSize, sSearch, sort, sortdir);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.AttPhysicianService#save(com.pfchoice.core.
	 * entity.AttPhysician)
	 */
	@Override
	public AttPhysician save(final AttPhysician bean) {
		return attPhysicianDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.AttPhysicianService#update(com.pfchoice.core.
	 * entity.AttPhysician)
	 */
	@Override
	public AttPhysician update(final AttPhysician bean) {
		Updater<AttPhysician> updater = new Updater<>(bean);
		return attPhysicianDao.updateByUpdater(updater);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.AttPhysicianService#loadData(java.lang.Integer)
	 */
	@Override
	public Integer loadData(final Integer fileId) {
		return attPhysicianDao.loadData(fileId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.AttPhysicianService#isNameUnique(java.lang.
	 * Integer, java.lang.String)
	 */
	@Override
	public boolean isCodeUnique(Integer id, String code) {
		AttPhysician attPhysician = findByCode(code);
		return (attPhysician == null || ((id != null) && (attPhysician.getId() == id)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.AttPhysicianService#findByName(java.lang.
	 * String)
	 */
	@Override
	public AttPhysician findByCode(String code) {
		AttPhysician attPhysician = attPhysicianDao.findByCode(code);
		return attPhysician;
	}
}
