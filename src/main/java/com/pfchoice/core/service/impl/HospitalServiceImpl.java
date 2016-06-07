package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.HospitalDao;
import com.pfchoice.core.entity.Hospital;
import com.pfchoice.core.service.HospitalService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class HospitalServiceImpl implements HospitalService {

	@Autowired
	private HospitalDao hospitalDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.HospitalService#deleteById(java.lang.Integer)
	 */
	@Override
	public Hospital deleteById(final Integer id) {
		return hospitalDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.HospitalService#findById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public Hospital findById(final Integer id) {
		return hospitalDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.HospitalService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return hospitalDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.HospitalService#getPage(int, int,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		return hospitalDao.getPage(pageNo, pageSize, sSearch, sort, sortdir);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.HospitalService#save(com.pfchoice.core.entity.
	 * Hospital)
	 */
	@Override
	public Hospital save(final Hospital bean) {
		return hospitalDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.HospitalService#update(com.pfchoice.core.entity
	 * .Hospital)
	 */
	@Override
	public Hospital update(final Hospital bean) {
		Updater<Hospital> updater = new Updater<>(bean);
		return hospitalDao.updateByUpdater(updater);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.HospitalService#loadData(java.lang.Integer)
	 */
	@Override
	public Integer loadData(final Integer fileId) {
		return hospitalDao.loadData(fileId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.HospitalService#isNameUnique(java.lang.Integer,
	 * java.lang.String)
	 */
	@Override
	public boolean isNameUnique(Integer id, String insName) {
		Hospital hospital = findByName(insName);
		return (hospital == null || ((id != null) && (hospital.getId() == id)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.HospitalService#findByName(java.lang.
	 * String)
	 */
	@Override
	public Hospital findByName(String hospitalName) {
		Hospital hospital = hospitalDao.findByName(hospitalName);
		return hospital;
	}
}
