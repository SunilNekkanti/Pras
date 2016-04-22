package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

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

	@Override
	public HedisMeasure deleteById(final Integer id) {
		// Used for transaction test
		return hedisMeasureDao.deleteById(id);
		// throw new UnsupportedOperationException();
	}

	@Override
	@Transactional(readOnly = true)
	public HedisMeasure findById(final Integer id) {
		return hedisMeasureDao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		return hedisMeasureDao.getPage(pageNo, pageSize, sSearch, sort, sortdir);
	}

	@Override
	public HedisMeasure save(final HedisMeasure bean) {
		// Used for transaction test
		return hedisMeasureDao.save(bean);
		// this.deleteById(1);
		// return null;
	}

	@Override
	public HedisMeasure update(final HedisMeasure bean) {
		Updater<HedisMeasure> updater = new Updater<>(bean);
		return hedisMeasureDao.updateByUpdater(updater);
	}

	@Override
	public List<HedisMeasure> findAll() {
		return hedisMeasureDao.findAll();
	}
}
