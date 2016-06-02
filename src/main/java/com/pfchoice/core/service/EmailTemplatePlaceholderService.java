package com.pfchoice.core.service;

import java.util.List;

import com.pfchoice.core.entity.EmailTemplatePlaceholder;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Mohanasundharam
 */
public interface EmailTemplatePlaceholderService {

	/**
	 * @param id
	 * @return
	 */
	EmailTemplatePlaceholder deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	EmailTemplatePlaceholder findById(Integer id);

	/**
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize);

	/**
	 * @param pageNo
	 * @param pageSize
	 * @param sSearch
	 * @param sort
	 * @param sortdir
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize, String sSearch, String sort, String sortdir);

	/**
	 * @param bean
	 * @return
	 */
	EmailTemplatePlaceholder save(EmailTemplatePlaceholder bean);

	/**
	 * @param bean
	 * @return
	 */
	EmailTemplatePlaceholder update(EmailTemplatePlaceholder bean);

	/**
	 * @param emailTemplateId
	 * @return
	 */
	Pagination findByEmailTemplateId(Integer emailTemplateId);

	/**
	 * @param emailTemplatePlaceholders
	 * @return
	 */
	List<Object> getSQLScriptResults(List<EmailTemplatePlaceholder> emailTemplatePlaceholders, Integer id);

	/**
	 * @param emailTemplateId
	 * @param id
	 * @return
	 */
	List<Object[]> generateAttachmentFile(Integer emailTemplateId, Integer id);
}
