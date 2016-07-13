package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.PlanTypeDao;
import com.pfchoice.core.entity.PlanType;
import com.pfchoice.core.service.PlanTypeService;

/**
 *
 * @author Mohanasundharam
 */
@Service
@Transactional
public class PlanTypeServiceImpl implements PlanTypeService {

	@Autowired
	private PlanTypeDao planTypeDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.PlanTypeService#deleteById(java.lang.Integer)
	 */
	@Override
	public PlanType deleteById(final Integer id) {
		return planTypeDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.PlanTypeService#findById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public PlanType findById(final Integer id) {
		return planTypeDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.PlanTypeService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return planTypeDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.PlanTypeService#getPage(int, int
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		return planTypeDao.getPage(pageNo, pageSize, sSearch, sort, sortdir);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.PlanTypeService#save(com.pfchoice.core.entity.
	 * PlanType)
	 */
	@Override
	public PlanType save(final PlanType bean) {
		return planTypeDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.PlanTypeService#update(com.pfchoice.core.entity
	 * .PlanType)
	 */
	@Override
	public PlanType update(final PlanType bean) {
		Updater<PlanType> updater = new Updater<>(bean, Updater.UpdateMode.MAX);
		updater.exclude("createdBy");
		updater.exclude("createdDate");
		return planTypeDao.updateByUpdater(updater);
	}

}
