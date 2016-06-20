package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.FollowupTypeDao;
import com.pfchoice.core.entity.FollowupType;
import com.pfchoice.core.service.FollowupTypeService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class FollowupTypeServiceImpl implements FollowupTypeService {

	@Autowired
	private FollowupTypeDao followupTypeDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.HedisMeasureGroupService#deleteById(java.lang.
	 * Integer)
	 */
	@Override
	public FollowupType deleteById(final Integer id) {
		return followupTypeDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.HedisMeasureGroupService#findById(java.lang.
	 * Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public FollowupType findById(final Integer id) {
		return followupTypeDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.HedisMeasureGroupService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return followupTypeDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.HedisMeasureGroupService#save(com.pfchoice.core
	 * .entity.HedisMeasureGroup)
	 */
	@Override
	public FollowupType save(final FollowupType bean) {
		return followupTypeDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.HedisMeasureGroupService#update(com.pfchoice.
	 * core.entity.HedisMeasureGroup)
	 */
	@Override
	public FollowupType update(final FollowupType bean) {
		Updater<FollowupType> updater = new Updater<>(bean, Updater.UpdateMode.MAX);
		updater.exclude("createdBy");
		updater.exclude("createdDate");
		return followupTypeDao.updateByUpdater(updater);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.HedisMeasureGroupService#findById(java.lang.
	 * Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public FollowupType findByCode(final String code) {
		return followupTypeDao.findByCode(code);
	}

}
