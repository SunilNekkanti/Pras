package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.CountyDao;
import com.pfchoice.core.entity.County;
import com.pfchoice.core.service.CountyService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class CountyServiceImpl implements CountyService {

	@Autowired
	private CountyDao countyDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.CountyService#deleteById(java.lang.Integer)
	 */
	@Override
	public County deleteById(final Integer id) {
		return countyDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.CountyService#findById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public County findById(final Integer id) {
		return countyDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.CountyService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return countyDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.CountyService#save(com.pfchoice.core.entity.
	 * County)
	 */
	@Override
	public County save(final County bean) {
		return countyDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.CountyService#update(com.pfchoice.core.entity.
	 * County)
	 */
	@Override
	public County update(final County bean) {
		Updater<County> updater = new Updater<>(bean, Updater.UpdateMode.MAX);
		updater.exclude("createdBy");
		updater.exclude("createdDate");
		return countyDao.updateByUpdater(updater);
	}

}
