package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.ZipCodeDao;
import com.pfchoice.core.entity.ZipCode;
import com.pfchoice.core.service.ZipCodeService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class ZipCodeServiceImpl implements ZipCodeService {

	@Autowired
	private ZipCodeDao zipCodeDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.ZipCodeService#deleteById(java.lang.Integer)
	 */
	@Override
	public ZipCode deleteById(final Integer id) {
		return zipCodeDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.ZipCodeService#findById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public ZipCode findById(final Integer id) {
		return zipCodeDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.ZipCodeService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return zipCodeDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.ZipCodeService#save(com.pfchoice.core.entity.
	 * ZipCode)
	 */
	@Override
	public ZipCode save(final ZipCode bean) {
		return zipCodeDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.ZipCodeService#update(com.pfchoice.core.entity.
	 * ZipCode)
	 */
	@Override
	public ZipCode update(final ZipCode bean) {
		Updater<ZipCode> updater = new Updater<>(bean);
		return zipCodeDao.updateByUpdater(updater);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.ZipCodeService#findByStateCode(java.lang.
	 * Integer)
	 */
	@Override
	public List<ZipCode> findByStateCode(Integer stateCode) {
		return zipCodeDao.findByStateCode(stateCode);
	}
}
