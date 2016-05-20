package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.EmailTemplatePlaceholderDao;
import com.pfchoice.core.entity.EmailTemplatePlaceholder;
import com.pfchoice.core.service.EmailTemplatePlaceholderService;

/**
 *
 * @author Mohanasundharam
 */
@Service
@Transactional
public class EmailTemplatePlaceholderServiceImpl implements EmailTemplatePlaceholderService {

	@Autowired
	private EmailTemplatePlaceholderDao emailTemplatePlaceholderDao;

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.EmailsService#deleteById(java.lang.Integer)
	 */
	@Override
	public EmailTemplatePlaceholder deleteById(final Integer id) {
		return emailTemplatePlaceholderDao.deleteById(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.EmailsService#findById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public EmailTemplatePlaceholder findById(final Integer id) {
		return emailTemplatePlaceholderDao.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.EmailsService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return emailTemplatePlaceholderDao.getPage(pageNo, pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.EmailsService#getPage(int, int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		return emailTemplatePlaceholderDao.getPage(pageNo, pageSize, sSearch, sort, sortdir);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.EmailsService#save(com.pfchoice.core.entity.Emails)
	 */
	@Override
	public EmailTemplatePlaceholder save(final EmailTemplatePlaceholder bean) {
		return emailTemplatePlaceholderDao.save(bean);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.EmailsService#update(com.pfchoice.core.entity.Emails)
	 */
	@Override
	public EmailTemplatePlaceholder update(final EmailTemplatePlaceholder bean) {
		Updater<EmailTemplatePlaceholder> updater = new Updater<>(bean);
		return emailTemplatePlaceholderDao.updateByUpdater(updater);
	}
	
	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.EmailTemplatePlaceholderService#findByEmailTemplateId(java.lang.Integer)
	 */
	@Override
	public Pagination findByEmailTemplateId(Integer  emailTemplateId){
		return emailTemplatePlaceholderDao.findByEmailTemplateId(emailTemplateId);
		
	}
	
	@Override
	public List<Object> getSQLScriptResults(List<EmailTemplatePlaceholder> emailTemplatePlaceholders, Integer id){
		return emailTemplatePlaceholderDao.getSQLScriptResults(emailTemplatePlaceholders, id);
	}
	
	
	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.EmailTemplatePlaceholderService#generateAttachmentFile(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<Object[]> generateAttachmentFile(Integer emailTemplateId, Integer id){
		
		return emailTemplatePlaceholderDao.generateAttachmentFile(emailTemplateId, id);
	}
}
