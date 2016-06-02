package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.EmailDao;
import com.pfchoice.core.entity.Email;
import com.pfchoice.core.service.EmailService;

/**
 *
 * @author Mohanasundharam
 */
@Service
@Transactional
public class EmailServiceImpl implements EmailService {

	@Autowired
	private EmailDao emailsDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.EmailsService#deleteById(java.lang.Integer)
	 */
	@Override
	public Email deleteById(final Integer id) {
		return emailsDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.EmailsService#findById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public Email findById(final Integer id) {
		return emailsDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.EmailsService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return emailsDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.EmailsService#getPage(int, int,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		return emailsDao.getPage(pageNo, pageSize, sSearch, sort, sortdir);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.EmailsService#save(com.pfchoice.core.entity.
	 * Emails)
	 */
	@Override
	public Email save(final Email bean) {
		return emailsDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.EmailsService#update(com.pfchoice.core.entity.
	 * Emails)
	 */
	@Override
	public Email update(final Email bean) {
		Updater<Email> updater = new Updater<>(bean);
		return emailsDao.updateByUpdater(updater);
	}
}
