package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.FacilityTypeDao;
import com.pfchoice.core.entity.FacilityType;
import com.pfchoice.core.service.FacilityTypeService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class FacilityTypeServiceImpl implements FacilityTypeService {

	@Autowired
	private FacilityTypeDao facilityTypeDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.FacilityTypeService#deleteById(java.lang.
	 * Integer)
	 */
	@Override
	public FacilityType deleteById(final Integer id) {
		return facilityTypeDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.FacilityTypeService#findById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public FacilityType findById(final Integer id) {
		return facilityTypeDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.FacilityTypeService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return facilityTypeDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.FacilityTypeService#save(com.pfchoice.core.
	 * entity.FacilityType)
	 */
	@Override
	public FacilityType save(final FacilityType bean) {
		return facilityTypeDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.FacilityTypeService#update(com.pfchoice.core.
	 * entity.FacilityType)
	 */
	@Override
	public FacilityType update(final FacilityType bean) {
		Updater<FacilityType> updater = new Updater<>(bean);
		return facilityTypeDao.updateByUpdater(updater);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.FacilityTypeService#loadData(java.lang.Integer)
	 */
	@Override
	public Integer loadData(final Integer fileId) {
		return facilityTypeDao.loadData(fileId);
	}
}
