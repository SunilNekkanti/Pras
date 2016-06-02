package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.GenderDao;
import com.pfchoice.core.entity.Gender;
import com.pfchoice.core.service.GenderService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class GenderServiceImpl implements GenderService {

	@Autowired
	private GenderDao genderDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.GenderService#deleteById(java.lang.Byte)
	 */
	@Override
	public Gender deleteById(final Byte id) {
		return genderDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.GenderService#findById(java.lang.Byte)
	 */
	@Override
	@Transactional(readOnly = true)
	public Gender findById(final Byte id) {
		return genderDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.GenderService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return genderDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.GenderService#save(com.pfchoice.core.entity.
	 * Gender)
	 */
	@Override
	public Gender save(final Gender bean) {
		return genderDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.GenderService#update(com.pfchoice.core.entity.
	 * Gender)
	 */
	@Override
	public Gender update(final Gender bean) {
		Updater<Gender> updater = new Updater<>(bean);
		return genderDao.updateByUpdater(updater);
	}

}
