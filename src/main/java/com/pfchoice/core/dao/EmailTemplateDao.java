package com.pfchoice.core.dao;

import com.pfchoice.core.entity.EmailTemplate;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Mohanasundharam
 */
public interface EmailTemplateDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	EmailTemplate deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	EmailTemplate findById(Integer id);

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
	EmailTemplate save(EmailTemplate bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	EmailTemplate updateByUpdater(Updater<EmailTemplate> updater);
	
	/**
	 * 
	 * @param description
	 * @return
	 */
	EmailTemplate findByDescription(String description);
}
