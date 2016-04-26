package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.HedisMeasureGroupDao;
import com.pfchoice.core.entity.HedisMeasureGroup;
import com.pfchoice.core.service.HedisMeasureGroupService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class HedisMeasureGroupServiceImpl implements HedisMeasureGroupService {

	@Autowired
	private HedisMeasureGroupDao hedisMeasureGroupDao;

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.HedisMeasureGroupService#deleteById(java.lang.Integer)
	 */
	@Override
	public HedisMeasureGroup deleteById(final Integer id) {
		return hedisMeasureGroupDao.deleteById(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.HedisMeasureGroupService#findById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public HedisMeasureGroup findById(final Integer id) {
		return hedisMeasureGroupDao.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.HedisMeasureGroupService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return hedisMeasureGroupDao.getPage(pageNo, pageSize);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.HedisMeasureGroupService#save(com.pfchoice.core.entity.HedisMeasureGroup)
	 */
	@Override
	public HedisMeasureGroup save(final HedisMeasureGroup bean) {
		return hedisMeasureGroupDao.save(bean);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.HedisMeasureGroupService#update(com.pfchoice.core.entity.HedisMeasureGroup)
	 */
	@Override
	public HedisMeasureGroup update(final HedisMeasureGroup bean) {
		Updater<HedisMeasureGroup> updater = new Updater<>(bean);
		return hedisMeasureGroupDao.updateByUpdater(updater);
	}

}
