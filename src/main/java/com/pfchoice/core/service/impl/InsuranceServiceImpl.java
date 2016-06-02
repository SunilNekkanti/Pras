package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.InsuranceDao;
import com.pfchoice.core.entity.Insurance;
import com.pfchoice.core.service.InsuranceService;

/**
 *
 * @author Mohanasundharam
 */
@Service
@Transactional
public class InsuranceServiceImpl implements InsuranceService {

	@Autowired
	private InsuranceDao insuranceDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.InsuranceService#deleteById(java.lang.Integer)
	 */
	@Override
	public Insurance deleteById(final Integer id) {
		return insuranceDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.InsuranceService#findById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public Insurance findById(final Integer id) {
		return insuranceDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.InsuranceService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return insuranceDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.InsuranceService#getPage(int, int,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		return insuranceDao.getPage(pageNo, pageSize, sSearch, sort, sortdir);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.InsuranceService#save(com.pfchoice.core.entity.
	 * Insurance)
	 */
	@Override
	public Insurance save(final Insurance bean) {
		return insuranceDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.InsuranceService#update(com.pfchoice.core.
	 * entity.Insurance)
	 */
	@Override
	public Insurance update(final Insurance bean) {
		Updater<Insurance> updater = new Updater<>(bean);
		return insuranceDao.updateByUpdater(updater);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.InsuranceService#isInsUnique(java.lang.Integer,
	 * java.lang.String)
	 */
	@Override
	public boolean isInsUnique(Integer id, String insName) {
		Insurance ins = findByInsName(insName);
		return (ins == null || ((id != null) && (ins.getId() == id)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.InsuranceService#findByInsName(java.lang.
	 * String)
	 */
	@Override
	public Insurance findByInsName(String insName) {
		Insurance ins = insuranceDao.findByInsName(insName);
		return ins;
	}

}
