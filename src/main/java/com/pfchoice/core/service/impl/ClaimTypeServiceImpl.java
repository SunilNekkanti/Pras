package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.ClaimTypeDao;
import com.pfchoice.core.entity.ClaimType;
import com.pfchoice.core.service.ClaimTypeService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class ClaimTypeServiceImpl implements ClaimTypeService {

	@Autowired
	private ClaimTypeDao claimTypeDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.ClaimTypeService#deleteById(java.lang.
	 * Integer)
	 */
	@Override
	public ClaimType deleteById(final Integer id) {
		return claimTypeDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.ClaimTypeService#findById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public ClaimType findById(final Integer id) {
		return claimTypeDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.ClaimTypeService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return claimTypeDao.getPage(pageNo, pageSize);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.ClaimTypeService#save(com.pfchoice.core.
	 * entity.ClaimType)
	 */
	@Override
	public ClaimType save(final ClaimType bean) {
		return claimTypeDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.ClaimTypeService#update(com.pfchoice.core.
	 * entity.ClaimType)
	 */
	@Override
	public ClaimType update(final ClaimType bean) {
		Updater<ClaimType> updater = new Updater<>(bean, Updater.UpdateMode.MAX);
		updater.exclude("createdBy");
		updater.exclude("createdDate");
		return claimTypeDao.updateByUpdater(updater);
	}

}
