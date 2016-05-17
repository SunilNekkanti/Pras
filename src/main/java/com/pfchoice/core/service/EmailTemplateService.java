package com.pfchoice.core.service;

import com.pfchoice.core.entity.EmailTemplate;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Mohanasundharam
 */
public interface EmailTemplateService {

	/**
	 * @param id
	 * @return
	 */
	EmailTemplate deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	EmailTemplate findById(Integer id);

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
	EmailTemplate save(EmailTemplate bean);

	/**
	 * @param bean
	 * @return
	 */
	EmailTemplate update(EmailTemplate bean);
}
