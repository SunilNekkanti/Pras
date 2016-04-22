package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.CPTMeasureDao;
import com.pfchoice.core.entity.CPTMeasure;
import com.pfchoice.core.service.CPTMeasureService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class CPTMeasureServiceImpl implements CPTMeasureService {

	@Autowired
	private CPTMeasureDao cptMeasureDao;

	@Override
	public CPTMeasure deleteById(final Integer id) {
		// Used for transaction test
		return cptMeasureDao.deleteById(id);
		// throw new UnsupportedOperationException();
	}

	@Override
	@Transactional(readOnly = true)
	public CPTMeasure findById(final Integer id) {
		return cptMeasureDao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		return cptMeasureDao.getPage(pageNo, pageSize, sSearch, sort, sortdir);
	}

	@Override
	public CPTMeasure save(final CPTMeasure bean) {
		// Used for transaction test
		return cptMeasureDao.save(bean);
		// this.deleteById(1);
		// return null;
	}

	@Override
	public CPTMeasure update(final CPTMeasure bean) {
		Updater<CPTMeasure> updater = new Updater<>(bean);
		return cptMeasureDao.updateByUpdater(updater);
	}

	@Override
	public List<CPTMeasure> findAll() {
		return cptMeasureDao.findAll();
	}
}
