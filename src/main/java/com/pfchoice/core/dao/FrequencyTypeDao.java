package com.pfchoice.core.dao;

import com.pfchoice.core.entity.FrequencyType;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface FrequencyTypeDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	FrequencyType deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	FrequencyType findById(Integer id);

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
	FrequencyType save(FrequencyType bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	FrequencyType updateByUpdater(Updater<FrequencyType> updater);

	/**
	 * 
	 * @param code
	 * @return
	 */
	FrequencyType findByDescription(String description);
}
