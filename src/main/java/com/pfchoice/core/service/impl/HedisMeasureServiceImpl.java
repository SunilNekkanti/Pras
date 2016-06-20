package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.HedisMeasureDao;
import com.pfchoice.core.entity.HedisMeasure;
import com.pfchoice.core.service.HedisMeasureService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class HedisMeasureServiceImpl implements HedisMeasureService {

	@Autowired
	private HedisMeasureDao hedisMeasureDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.HedisMeasureService#deleteById(java.lang.
	 * Integer)
	 */
	@Override
	public HedisMeasure deleteById(final Integer id) {
		return hedisMeasureDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.HedisMeasureService#findById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public HedisMeasure findById(final Integer id) {
		return hedisMeasureDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.HedisMeasureService#getPage(int, int,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return hedisMeasureDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.HedisMeasureService#getPage(int, int,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		return hedisMeasureDao.getPage(pageNo, pageSize, sSearch, sort, sortdir);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.HedisMeasureService#save(com.pfchoice.core.
	 * entity.HedisMeasure)
	 */
	@Override
	public HedisMeasure save(final HedisMeasure bean) {
		return hedisMeasureDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.HedisMeasureService#update(com.pfchoice.core.
	 * entity.HedisMeasure)
	 */
	@Override
	public HedisMeasure update(final HedisMeasure bean) {
		Updater<HedisMeasure> updater = new Updater<>(bean, Updater.UpdateMode.MAX);
		updater.exclude("createdBy");
		updater.exclude("createdDate");
		return hedisMeasureDao.updateByUpdater(updater);
	}

}
