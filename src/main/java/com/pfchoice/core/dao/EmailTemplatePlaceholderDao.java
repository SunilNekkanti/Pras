package com.pfchoice.core.dao;

import java.util.List;

import com.pfchoice.core.entity.EmailTemplatePlaceholder;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Mohanasundharam
 */
public interface EmailTemplatePlaceholderDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	EmailTemplatePlaceholder deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	EmailTemplatePlaceholder findById(Integer id);

	/**
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize);

	/**
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param sSearch
	 * @param sort
	 * @param sortdir
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize, String sSearch, String sort, String sortdir);

	/**
	 * 
	 * @param bean
	 * @return
	 */
	EmailTemplatePlaceholder save(EmailTemplatePlaceholder bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	EmailTemplatePlaceholder updateByUpdater(Updater<EmailTemplatePlaceholder> updater);

	/**
	 * @param emailTemplateId
	 * @return
	 */
	Pagination findByEmailTemplateId(Integer emailTemplateId);

	/**
	 * @param emailTemplatePlaceholders
	 * @return
	 */
	List<Object> getSQLScriptResults(List<EmailTemplatePlaceholder> emailTemplateId, Integer id);

	/**
	 * @param emailTemplateId
	 * @param id
	 * @return
	 */
	List<Object[]> generateAttachmentFile(Integer emailTemplateId, Integer id);
}
