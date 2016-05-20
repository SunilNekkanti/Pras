package com.pfchoice.core.service;

import java.util.List;

import com.pfchoice.core.entity.Contact;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface ContactService {

	/**
	 * @param id
	 * @return
	 */
	Contact deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	Contact findById(Integer id);

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
	Contact save(Contact bean);

	/**
	 * @param bean
	 * @return
	 */
	Contact update(Contact bean);

	/**
	 * @param refString
	 * @param id
	 * @return
	 */
	List<Contact> findAllContactsByRefId(String refString, Integer id);

	/**
	 * @param refString
	 * @param id
	 * @return
	 */
	Contact findActiveContactByRefId(String refString, Integer id);

}
