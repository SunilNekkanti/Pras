package com.pfchoice.core.dao;

import java.util.List;

import com.pfchoice.core.entity.Contact;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface ContactDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	Contact deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	Contact findById(Integer id);

	/**
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize);

	/**
	 * 
	 * @param bean
	 * @return
	 */
	Contact save(Contact bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	Contact updateByUpdater(Updater<Contact> updater);

	/**
	 * 
	 * @param refString
	 * @param id
	 * @return
	 */
	List<Contact> findAllContactsByRefId(String refString, Integer id);

	/**
	 * 
	 * @param refString
	 * @param id
	 * @return
	 */
	Contact findActiveContactByRefId(String refString, Integer id);

}
