package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

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

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.CPTMeasureService#deleteById(java.lang.Integer)
	 */
	@Override
	public CPTMeasure deleteById(final Integer id) {
		return cptMeasureDao.deleteById(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.CPTMeasureService#findById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public CPTMeasure findById(final Integer id) {
		return cptMeasureDao.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.CPTMeasureService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return cptMeasureDao.getPage(pageNo, pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.CPTMeasureService#getPage(int, int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		return cptMeasureDao.getPage(pageNo, pageSize, sSearch, sort, sortdir);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.CPTMeasureService#save(com.pfchoice.core.entity.CPTMeasure)
	 */
	@Override
	public CPTMeasure save(final CPTMeasure bean) {
		return cptMeasureDao.save(bean);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.CPTMeasureService#update(com.pfchoice.core.entity.CPTMeasure)
	 */
	@Override
	public CPTMeasure update(final CPTMeasure bean) {
		Updater<CPTMeasure> updater = new Updater<>(bean);
		return cptMeasureDao.updateByUpdater(updater);
	}

}
