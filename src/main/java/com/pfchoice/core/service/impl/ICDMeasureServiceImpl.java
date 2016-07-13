package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.ICDMeasureDao;
import com.pfchoice.core.entity.ICDMeasure;
import com.pfchoice.core.service.ICDMeasureService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class ICDMeasureServiceImpl implements ICDMeasureService {

	@Autowired
	private ICDMeasureDao icdMeasureDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.ICDMeasureService#deleteById(java.lang.Integer)
	 */
	@Override
	public ICDMeasure deleteById(final Integer id) {
		return icdMeasureDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.ICDMeasureService#findById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public ICDMeasure findById(final Integer id) {
		return icdMeasureDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.ICDMeasureService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return icdMeasureDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.ICDMeasureService#getPage(int, int,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		return icdMeasureDao.getPage(pageNo, pageSize, sSearch, sort, sortdir);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.ICDMeasureService#save(com.pfchoice.core.entity
	 * .ICDMeasure)
	 */
	@Override
	public ICDMeasure save(final ICDMeasure bean) {
		return icdMeasureDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.ICDMeasureService#update(com.pfchoice.core.
	 * entity.ICDMeasure)
	 */
	@Override
	public ICDMeasure update(final ICDMeasure bean) {
		Updater<ICDMeasure> updater = new Updater<>(bean, Updater.UpdateMode.MAX);
		updater.exclude("createdBy");
		updater.exclude("createdDate");
		return icdMeasureDao.updateByUpdater(updater);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.ICDMeasureService#findByCodes(java.lang.String[
	 * ])
	 */
	public List<ICDMeasure> findByCodes(final String[] icdCodes) {
		return icdMeasureDao.findByCodes(icdCodes);
	}
}
