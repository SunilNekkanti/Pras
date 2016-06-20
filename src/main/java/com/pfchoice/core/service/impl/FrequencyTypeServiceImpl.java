package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.FrequencyTypeDao;
import com.pfchoice.core.entity.FrequencyType;
import com.pfchoice.core.service.FrequencyTypeService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class FrequencyTypeServiceImpl implements FrequencyTypeService {

	@Autowired
	private FrequencyTypeDao frequencyTypeDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.FrequencyTypeService#deleteById(java.lang.
	 * Integer)
	 */
	@Override
	public FrequencyType deleteById(final Integer id) {
		return frequencyTypeDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.FrequencyTypeService#findById(java.lang.
	 * Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public FrequencyType findById(final Integer id) {
		return frequencyTypeDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.FrequencyTypeService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return frequencyTypeDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.FrequencyTypeService#getPage(int, int,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		return frequencyTypeDao.getPage(pageNo, pageSize, sSearch, sort, sortdir);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.FrequencyTypeService#save(com.pfchoice.core.
	 * entity.FrequencyType)
	 */
	@Override
	public FrequencyType save(final FrequencyType bean) {
		return frequencyTypeDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.FrequencyTypeService#update(com.pfchoice.core.
	 * entity.FrequencyType)
	 */
	@Override
	public FrequencyType update(final FrequencyType bean) {
		Updater<FrequencyType> updater = new Updater<>(bean, Updater.UpdateMode.MAX);
		updater.exclude("createdBy");
		updater.exclude("createdDate");
		return frequencyTypeDao.updateByUpdater(updater);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.FrequencyTypeService#findByCode(java.lang.
	 * String)
	 */
	@Override
	@Transactional(readOnly = true)
	public FrequencyType findByDescription(final String description) {
		return frequencyTypeDao.findByDescription(description);
	}
}
