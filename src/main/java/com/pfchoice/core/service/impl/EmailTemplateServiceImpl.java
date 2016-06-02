package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.EmailTemplateDao;
import com.pfchoice.core.entity.EmailTemplate;
import com.pfchoice.core.service.EmailTemplateService;

/**
 *
 * @author Mohanasundharam
 */
@Service
@Transactional
public class EmailTemplateServiceImpl implements EmailTemplateService {

	@Autowired
	private EmailTemplateDao emailTemplatesDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.EmailTemplatesService#deleteById(java.lang.
	 * Integer)
	 */
	@Override
	public EmailTemplate deleteById(final Integer id) {
		return emailTemplatesDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.EmailTemplatesService#findById(java.lang.
	 * Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public EmailTemplate findById(final Integer id) {
		return emailTemplatesDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.EmailTemplatesService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return emailTemplatesDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.EmailTemplatesService#getPage(int, int,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		return emailTemplatesDao.getPage(pageNo, pageSize, sSearch, sort, sortdir);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.EmailTemplatesService#save(com.pfchoice.core.
	 * entity.Emails)
	 */
	@Override
	public EmailTemplate save(final EmailTemplate bean) {
		return emailTemplatesDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.EmailsService#update(com.pfchoice.core.entity.
	 * Emails)
	 */
	@Override
	public EmailTemplate update(final EmailTemplate bean) {
		Updater<EmailTemplate> updater = new Updater<>(bean);
		return emailTemplatesDao.updateByUpdater(updater);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.EmailTemplateService#findBySubject(java.lang.
	 * String)
	 */
	@Override
	@Transactional(readOnly = true)
	public EmailTemplate findBySubject(final String description) {
		return emailTemplatesDao.findByDescription(description);
	}
}
