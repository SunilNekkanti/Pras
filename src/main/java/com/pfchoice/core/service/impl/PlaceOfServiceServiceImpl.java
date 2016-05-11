package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.PlaceOfServiceDao;
import com.pfchoice.core.entity.PlaceOfService;
import com.pfchoice.core.service.PlaceOfServiceService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class PlaceOfServiceServiceImpl implements PlaceOfServiceService {

	@Autowired
	private PlaceOfServiceDao placeOfServiceDao;

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.PlaceOfServiceService#deleteById(java.lang.Integer)
	 */
	@Override
	public PlaceOfService deleteById(final Integer id) {
		return placeOfServiceDao.deleteById(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.PlaceOfServiceService#findById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public PlaceOfService findById(final Integer id) {
		return placeOfServiceDao.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.PlaceOfServiceService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return placeOfServiceDao.getPage(pageNo, pageSize);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.PlaceOfServiceService#save(com.pfchoice.core.entity.PlaceOfService)
	 */
	@Override
	public PlaceOfService save(final PlaceOfService bean) {
		return placeOfServiceDao.save(bean);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.PlaceOfServiceService#update(com.pfchoice.core.entity.PlaceOfService)
	 */
	@Override
	public PlaceOfService update(final PlaceOfService bean) {
		Updater<PlaceOfService> updater = new Updater<>(bean);
		return placeOfServiceDao.updateByUpdater(updater);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.PlaceOfServiceService#findByCode(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public PlaceOfService findByCode(final String code) {
		return placeOfServiceDao.findByCode(code);
	}
}
